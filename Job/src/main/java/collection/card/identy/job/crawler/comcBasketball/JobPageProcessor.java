package collection.card.identy.job.crawler.comcBasketball;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Component
public class JobPageProcessor implements PageProcessor {
    private final String domain = "https://www.comc.com/";
    private final String pageUrl = domain + "Cards/Basketball";

    @Autowired
    private JobPipeline jobPipeline;

    @Scheduled(initialDelay = 0, fixedDelay = 10000)
    public void run() {
        Spider.create(this)
                .addPipeline(jobPipeline)
                .addUrl(pageUrl + ",p2")
                //.addUrl(pageUrl)
                .thread(10)
                .run();
    }

    @Override
    public void process(Page page) {
        //详情页抓字段
        if (isDetailUrl(page.getUrl().toString())) {
            if (!isEBayUrl(page.getUrl().toString())) {
                page.putField("title", page.getHtml().$("#img1", "alt"));
                page.putField("frontImgUrl", page.getHtml().$("#img1", "src"));
                page.putField("backImgUrl", page.getHtml().$("#img1", "data-otherside"));
                page.putField("price", page.getHtml().xpath("[@class='listprice']/a/text()"));
            } else {
                page.putField("title", page.getHtml().$("meta[name='keywords']", "content"));
                page.putField("frontImgUrl", page.getHtml().$("img[alt='Front']", "src"));
                page.putField("backImgUrl", page.getHtml().$("img[alt='Back']", "src"));
                page.putField("price", page.getHtml().xpath("[@class='listprice']/text()"));
            }
        }
        //列表页抓详情页链接和下一页的链接
        else {
            //详情页链接
            List<String> links = page.getHtml().$(".imgPanel").links().all();
            //下一页链接
            String nplinks = page.getHtml().$("#ctl00_ContentPlaceHolder1_cmdNext_Bottom", "href").toString();
            links.add(nplinks);
            page.addTargetRequests(links);
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setCycleRetryTimes(3).setDomain(domain);
    }

    //判断当前页面是否为详情页
    public boolean isDetailUrl(String url) {
        return urlToUniqueId(url).contains("/");
    }

    public boolean isEBayUrl(String url) {
        return url.contains("eBay_Auction");
    }

    public String urlToUniqueId(String url) {
        return url.replace(pageUrl, "");
    }
}
