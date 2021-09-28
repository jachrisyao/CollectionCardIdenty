package collection.card.identy.job.crawler.comcBasketball;

import collection.card.identy.job.es.ComcBasketballEsBiz;
import collection.card.identy.job.model.UrlResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobPageProcessor implements PageProcessor {
    protected static Logger logger = LoggerFactory.getLogger(JobPageProcessor.class);
    @Autowired
    private JobPipeline jobPipeline;
    @Autowired
    private ComcBasketballEsBiz comcBasketballEsBiz;

    //initialDelay=延时启动时间
    //fixedDelay=跑完后过多久开始跑下一次
    @Scheduled(initialDelay = 0, fixedDelay = 10000)
    public void run() {
        logger.warn("=============== spider start ===============");
        for (String pageUrl : UrlHandler.getAllUrls()) {
            logger.warn("【Page Start】" + pageUrl);
            Spider.create(this)
                    .addPipeline(jobPipeline)
                    .addUrl(pageUrl)
                    .thread(10)
                    .run();
            logger.warn("------" + pageUrl + "------");
        }
        logger.warn("=============== spider end ===============");
    }

    @Override
    public void process(Page page) {
        //详情页抓字段
        if (UrlHandler.isDetailUrl(page.getUrl().toString())) {
            if (!UrlHandler.isEBayUrl(page.getUrl().toString())) {
                page.putField("title", page.getHtml().$("#img1", "alt"));
                page.putField("frontImgUrl", page.getHtml().$("#img1", "src"));
                page.putField("backImgUrl", page.getHtml().$("#img1", "data-otherside"));
                page.putField("price", page.getHtml().xpath("[@class='listprice']/a/text()"));
            } else {
                page.putField("title", page.getHtml().xpath("[@id='ctl00_ContentPlaceHolder1_rptrPreviewItems_ctl00_CardDetails_SetNameHyperlink']/text()") + " - " + page.getHtml().xpath("[@class='description']/text()").toString().trim());
                page.putField("frontImgUrl", page.getHtml().$("img[alt='Front']", "src"));
                page.putField("backImgUrl", page.getHtml().$("img[alt='Back']", "src"));
                page.putField("price", page.getHtml().xpath("[@class='listprice']/text()"));
            }
        }
        //列表页抓详情页链接和下一页的链接
        else {
            //详情页链接
            List<String> links = page.getHtml().$(".imgPanel").links().all();

            //已下载的跳过
            UrlResponseWrapper urlResponseWrapper = comcBasketballEsBiz.queryUrls(links);
            if (urlResponseWrapper != null && urlResponseWrapper.getUrlList() != null)
                links = links.stream().filter(x -> !urlResponseWrapper.getUrlList().contains(x)).collect(Collectors.toList());

            //下一页链接
            String nplinks = page.getHtml().$("#ctl00_ContentPlaceHolder1_cmdNext_Bottom", "href").toString();
            links.add(nplinks);
            page.addTargetRequests(links);
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setCycleRetryTimes(3).setDomain(UrlHandler.getDomain());
    }
}
