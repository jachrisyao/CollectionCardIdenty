package collection.card.identy.job.crawler.comcBasketball;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static collection.card.identy.job.crawler.comcBasketball.readUrl.txt2String;

public class UrlHandler {
    private final static String domain = "https://www.comc.com/";
    private final static String basketballUrl = domain + "Cards/Basketball";

    public static String getDomain() {
        return domain;
    }

    public static String getBasketballUrl() {
        return basketballUrl;
    }

    public static boolean isDetailUrl(String url) {
        String shortUrl = url.replace(basketballUrl, "");
        return shortUrl.contains("/") && !shortUrl.contains(",");
    }

    public static boolean isEBayUrl(String url) {
        return url.contains("eBay_Auction");
    }

    public static List<String> getAllUrls() {
        File file = new File("./url/comc_basketball.txt");
        ArrayList list = new ArrayList(Arrays.asList(txt2String(file).split(";")));
        return list;
//        return Arrays.asList("https://www.comc.com/Cards/Basketball/1980s,sb,vDetails,i100");
    }
}
