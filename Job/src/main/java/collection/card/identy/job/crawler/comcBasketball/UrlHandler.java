package collection.card.identy.job.crawler.comcBasketball;

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
}
