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

    @Scheduled(initialDelay = 0, fixedDelay = 23 * 60 * 60 * 1000)
    public void run() {
        logger.warn("=============== spider start ===============");
        Spider.create(this)
                .addPipeline(jobPipeline)
                .addUrl("https://www.comc.com/Cards/Basketball/1980s,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/1970s,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/1960s,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/1950s,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/1940s,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2021,sb,vDetails,i100")
                .thread(10)
                .run();
        Spider.create(this)
                .addPipeline(jobPipeline)
                .addUrl("https://www.comc.com/Cards/Basketball/2020-21_Donruss_Elite,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020_Leaf_Best_of_Basketball,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Absolute_Memorabilia,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Certified,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Clearly_Donruss,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Contenders,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Contenders_Draft_Picks,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Court_Kings,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Crown_Royale,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Donruss,sb,vDetails,i100")
                .thread(10)
                .run();

        Spider.create(this)
                .addPipeline(jobPipeline)
                .addUrl("https://www.comc.com/Cards/Basketball/2020-21_Panini_Flawless_Collegiate,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Immaculate_Collection_Collegiate,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Impeccable,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Instant,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020_Panini_Instant_Draft_Night,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_National_Treasures,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_National_Treasures_Collegiate,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_NBA_Hoops,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Noir,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Origins,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Player_of_the_Day,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Prizm,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Prizm_Draft_Picks,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020_Panini_Prizm_WNBA,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Revolution,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Select,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Spectra,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020-21_Panini_Sticker__Card_Collection,sb,vDetails,i100",
                        "https://www.comc.com/Cards/Basketball/2020_Upper_Deck_Holiday_Card,sb,vDetails,i100")
                .thread(10)
                .run();
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
