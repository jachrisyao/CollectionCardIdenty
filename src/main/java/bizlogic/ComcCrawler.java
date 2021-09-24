package bizlogic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.*;
import java.util.stream.Collectors;

public class ComcCrawler implements PageProcessor {
    @Override
    public void process(Page page) {
        //获取当前页
        List<String> links = page.getHtml().links().regex("/Cards/Basketball/.*").all();
        //删除links中重复元素，去除带逗号的URL
        links = links.stream().filter(x -> !x.contains(",")).distinct().collect(Collectors.toList());

        page.addTargetRequests(links);
        page.putField("title", page.getHtml().$("#img1", "alt"));
        page.putField("img", page.getHtml().$("#img1", "src"));
        page.putField("img-bak", page.getHtml().$("#img1", "data-otherside"));
        page.putField("price", page.getHtml().xpath("[@class='listprice']/a/text()"));
        //$(".listprice")[0].outerText

        //获取下一页
        String nplinks = page.getHtml().$("#ctl00_ContentPlaceHolder1_cmdNext_Bottom", "href").toString();
        page.addTargetRequests(Collections.singletonList(nplinks));
    }

    @Override
    public Site getSite() {
        String url = "https://www.comc.com/";
        return Site.me().setRetryTimes(3).setSleepTime(1000).setDomain(url);
    }
}
