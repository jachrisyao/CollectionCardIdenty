package collection.card.identy.crawler.comcBasketball;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobPageProcessor implements PageProcessor {
    private final String domain = "https://www.comc.com/";

    @Autowired
    private JobPipeline jobPipeline;

    //调试用
    public static void main(String[] args) {
        Spider.create(new JobPageProcessor())
                .addPipeline(new JobPipeline())
                .addUrl("https://www.comc.com/Cards/Basketball,p1")
                .thread(1)
                .run();
    }

//    //Spring Task
//    @Scheduled(cron = "* 0/1 0/1 * * ? *")
//    public void work() {
//        run();
//    }

    @Scheduled(initialDelay = 0, fixedDelay = 10000)
    public void run() {
        Spider.create(this)
                .addPipeline(jobPipeline)
                .addUrl(domain + "Cards/Basketball,p1")
                .thread(1)
                .run();
    }

    @Override
    public void process(Page page) {
        //获取当前页
        List<String> links = page.getHtml().links().regex("/Cards/Basketball/.*").all();
        //删除links中重复元素，去除带逗号的URL
        links = links.stream().filter(x -> !x.contains(",")).distinct().collect(Collectors.toList());

        page.addTargetRequests(links);
        page.putField("title", page.getHtml().$("#img1", "alt"));
        page.putField("frontImgUrl", page.getHtml().$("#img1", "src"));
        page.putField("backImgUrl", page.getHtml().$("#img1", "data-otherside"));
        page.putField("price", page.getHtml().xpath("[@class='listprice']/a/text()"));
        //$(".listprice")[0].outerText

        //获取下一页
        String nplinks = page.getHtml().$("#ctl00_ContentPlaceHolder1_cmdNext_Bottom", "href").toString();
        page.addTargetRequests(Collections.singletonList(nplinks));
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(5000).setDomain(domain);
    }
}
