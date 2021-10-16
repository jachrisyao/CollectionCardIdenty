package collection.card.identy.api.service;

import collection.card.identy.api.es.EsClient;
import collection.card.identy.api.handler.JacksonUtil;
import collection.card.identy.api.model.OcrSearchRequestType;
import collection.card.identy.api.model.OcrSearchResponseType;
import collection.card.identy.api.proxy.OcrService;
import com.tencentcloudapi.ocr.v20181119.models.TextDetectionEn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class OcrSearchHandler {
    private final String seriesListJSON = "[\"23KT\",\"5\",\"7-ELEVEN\",\"7-UP\",\"ACC\",\"ACMI\",\"AMA\",\"APBA\",\"AT&T\",\"ABSOLUTE\",\"ACTION\",\"ADOLESCENT\",\"ADRENALYN\",\"ALL\",\"ANONYMOUS\",\"ARIZONA\",\"AUBURN\",\"AVIA\",\"BASF\",\"BBM\",\"BC\",\"BAIO\",\"BALLPARK\",\"BANK\",\"BASKETBALL\",\"BAY\",\"BAZOOKA\",\"BEST\",\"BIJAN\",\"BIMBO\",\"BLEACHERS\",\"BOB\",\"BOLLYCAO\",\"BOSTON\",\"BOWMAN\",\"BOWMAN'S\",\"BRAVO\",\"BRIGHAM\",\"BUGLES\",\"CAO\",\"CBA\",\"CACHE\",\"CALGARY\",\"CARL'S\",\"CARNATION\",\"CAROLINA\",\"CARVEL\",\"CERTIFIED\",\"CHINA\",\"CHINESE\",\"CINCINNATI\",\"CIRCLE\",\"CLASSIC\",\"CLEO\",\"COCA-COLA\",\"COCA-COLA/WPLO\",\"COLLECT-A-CARD\",\"COLLECTOR'S\",\"COLLEGIATE\",\"COMIC\",\"COMMONWEALTH\",\"CONVERSE\",\"COURT\",\"COURTSIDE\",\"CROWN\",\"DEPAUL\",\"DENNY'S\",\"DENVER\",\"DETROIT\",\"DIAMOND\",\"DONRUSS\",\"DRUG\",\"E-X\",\"E-XL\",\"EX\",\"EX2000\",\"EX2001\",\"EDGE\",\"EL\",\"ELITE\",\"ENTENMANN'S\",\"EQUAL\",\"EXPRESS\",\"EXQUISITE\",\"FARMER\",\"FATHEAD\",\"FELLOWSHIP\",\"FLAIR\",\"FLEER\",\"FLEER/SKYBOX\",\"FOODLAND\",\"FOODTOWN\",\"FOURNIER\",\"FRANZ\",\"FRITO\",\"FRONT\",\"FUTERA\",\"GE\",\"GENUINE\",\"GEORGETOWN\",\"GEORGIA\",\"GIANT\",\"GIGANTES\",\"GO\",\"GONZAGA\",\"GOODWILL\",\"HALL\",\"HANDYMAN\",\"HANES\",\"HASBRO\",\"HERO\",\"HIGH\",\"HIGHLAND\",\"HOOPS\",\"IBM\",\"ICEE\",\"ILLINOIS\",\"INDIANA\",\"INKREDIBLE\",\"IOWA\",\"ITAL\",\"JMS\",\"JACK\",\"KOS/JEZ\",\"KANSAS\",\"KAYO\",\"KELLOGG'S\",\"KENNER\",\"KENTUCKY\",\"KING'S\",\"KIWANIS\",\"KODAK\",\"KRAFT\",\"LDDS\",\"LSU\",\"LEAF\",\"LEGENDARY\",\"LIBERTY\",\"LIME\",\"LIMITED\",\"LITTLE\",\"LUXURY\",\"MSA\",\"MARYLAND\",\"MCDONALD'S\",\"MEMPHIS\",\"MERCHANTE\",\"METAL\",\"MIAMI\",\"MICHAEL\",\"MICHIGAN\",\"MILLENNIUM\",\"MINNESOTA\",\"MONEY\",\"MOTION\",\"MULTIAD\",\"MURRAY\",\"NABC\",\"NBA\",\"NATIONAL\",\"NEBRASKA\",\"NEW\",\"NIKE\",\"NORTHWESTERN\",\"NORWEST\",\"NOTRE\",\"O-PEE-CHEE\",\"OHIO\",\"OKLAHOMA\",\"OMNI\",\"OREGON\",\"ORLANDO\",\"PACIFIC\",\"PANCHERO'S\",\"PANINI\",\"PAPER\",\"PARKSIDE\",\"PAST\",\"PEPSI\",\"PEPSI-COLA\",\"PEPSI/7-ELEVEN\",\"PHIPPS\",\"PHOENIX\",\"PICTURE\",\"PINNACLE\",\"PIZZA\",\"PLAYOFF\",\"PORTLAND\",\"PRESS\",\"PRESTIGE\",\"PRIME\",\"PRISM/JEWEL\",\"PRO\",\"PROCARDS\",\"PURDUE\",\"QDOBA/CHILDREN'S\",\"REEBOK\",\"REFLECTIONS\",\"RITTENHOUSE\",\"ROOKIES\",\"ROOX\",\"SAGE\",\"SP\",\"SPX\",\"SAFEWAY\",\"SAGE\",\"SAINT\",\"SAN\",\"SCHOLASTIC\",\"SCHOOL\",\"SCORE\",\"SEATTLE\",\"SERIOUS\",\"SERVICE\",\"SHAQUILLE\",\"SHELBY\",\"SIGNATURE\",\"SKYBOX\",\"SKYBOX\",\"SMOKEY\",\"SMOKEY'S\",\"SOUTHERN\",\"SPORTS\",\"SPREE\",\"SPRITE\",\"STANFORD\",\"STAR\",\"STARTING\",\"STICKITO\",\"SUPER\",\"SYRACUSE\",\"TCMA\",\"TACO\",\"TARGET\",\"TED\",\"TENNESSEE\",\"THE\",\"TIMELESS\",\"TOP\",\"TOPPS\",\"TOPPS/UPPER\",\"TOTALSPORT\",\"TOTALLY\",\"TYSON\",\"UD\",\"UD3\",\"UNLV\",\"USC\",\"ULTIMATE\",\"UNIVERSITY\",\"UNO\",\"UNOCAL\",\"UPPER\",\"VANCOUVER\",\"VICTORY\",\"VIRGINIA\",\"VISIONS\",\"WNBA\",\"WAKE\",\"WEBER\",\"WENDY'S\",\"WHEELS\",\"WICHITA\",\"WILD\",\"WILDCAT\",\"WONDER\",\"WORTH\",\"WR\",\"WRIGHT\",\"Z-FORCE\",\"ETOPPS\"]";
    @Autowired
    private OcrService ocrService;
    @Autowired
    private EsClient esClient;

    public OcrSearchResponseType handler(OcrSearchRequestType request) throws Exception {
        if (request == null)
            return null;
        OcrSearchResponseType response = new OcrSearchResponseType();
        //searchAll();
        TextDetectionEn[] frontKeyWords = ocrService.getKeyWords(request.getFrontUrl());
        TextDetectionEn[] backKeyWords = ocrService.getKeyWords(request.getBackUrl());
        List<String> keywords = filterUsefulKeyWords(frontKeyWords, backKeyWords);
        response.setKeywords(JacksonUtil.toJSONString(keywords));
        return response;
    }

    private List<String> filterUsefulKeyWords(TextDetectionEn[] frontKeyWords, TextDetectionEn[] backKeyWords) {
        List<String> keywords = new ArrayList<>();
        List<Long> xy = rangeXY(backKeyWords);
        for (TextDetectionEn keyword : backKeyWords) {
            if (isYearSeries(keyword.getDetectedText())) {
                keywords.add(keyword.getDetectedText());
            }
            if (isNumber(keyword, xy)) {

            }
        }
        return keywords;
        //return Arrays.asList("#83", "LUIS SCOLA", "2016-17 PANINI");
    }

    private List<Long> rangeXY(TextDetectionEn[] array) {
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = 0;
        long maxY = 0;
        for (TextDetectionEn keyword : array) {
            for (com.tencentcloudapi.ocr.v20181119.models.Coord coord : keyword.getPolygon()) {
                if (coord.getX() < minX)
                    minX = coord.getX();
                if (coord.getX() > maxX)
                    maxX = coord.getX();
                if (coord.getY() < minY)
                    minY = coord.getY();
                if (coord.getY() > maxY)
                    maxY = coord.getY();
            }
        }
        BigDecimal rate = new BigDecimal("0.1");
        BigDecimal maxXD = new BigDecimal(String.valueOf(maxX));
        BigDecimal minXD = new BigDecimal(String.valueOf(minX));
        BigDecimal maxYD = new BigDecimal(String.valueOf(maxY));
        BigDecimal minYD = new BigDecimal(String.valueOf(minY));
        return Arrays.asList(
                //minX
                maxXD.subtract(minXD).multiply(rate).add(minXD).setScale(0, BigDecimal.ROUND_HALF_UP).longValue(),
                //minY
                maxYD.subtract(minYD).multiply(rate).add(minYD).setScale(0, BigDecimal.ROUND_HALF_UP).longValue(),
                //maxX
                maxXD.subtract(maxXD.subtract(minXD).multiply(rate)).setScale(0, BigDecimal.ROUND_HALF_UP).longValue(),
                //maxY
                maxYD.subtract(maxYD.subtract(minYD).multiply(rate)).setScale(0, BigDecimal.ROUND_HALF_UP).longValue()
        );
    }

    private boolean isYearSeries(String text) {
        //长度校验
        if (text.length() < 5)
            return false;
        //前4位是数字
        String yearPattern1 = "[1,2]{1}[0-9]{3}([^\"]+)";
        if (!Pattern.matches(yearPattern1, text))
            return false;
        int year = Integer.parseInt(text.substring(0, 4));
        //数字范围在1940-2030
        if (year < 1940 || year > 2030)
            return false;
        //第5位必须是空格或-
        if (text.toCharArray()[4] != '-' && text.toCharArray()[4] != ' ')
            return false;
        if (text.toCharArray()[4] == '-') {
            //校验2015-16格式
            String yearPattern2 = "[1,2]{1}[0-9]{3}-[0-9]{2}([^\"]+)";
            if (!Pattern.matches(yearPattern2, text))
                return false;
            //-后的年份为前面的+1
            if (!String.valueOf(year + 1).substring(2).equals(text.toCharArray()[5] + Character.toString(text.toCharArray()[6])))
                return false;
            //第8位必须为空格
            if (text.toCharArray()[7] != ' ')
                return false;
        }
        List<String> seriesList = JacksonUtil.parseListFromJson(seriesListJSON, String.class);
        return seriesList.contains(text.split(" ")[1].toUpperCase());
    }

    private boolean isNumber(TextDetectionEn text, List<Long> xys) {
        boolean isLeft = Arrays.stream(text.getPolygon()).allMatch(x -> x.getX() < xys.get(0));
        boolean isBottom = Arrays.stream(text.getPolygon()).allMatch(x -> x.getY() > xys.get(3));
        boolean isRight = Arrays.stream(text.getPolygon()).allMatch(x -> x.getX() > xys.get(2));
        boolean isTop = Arrays.stream(text.getPolygon()).allMatch(x -> x.getY() < xys.get(1));
        //编号在边框上
        if (!isLeft && !isRight && !isTop && !isBottom)
            return false;
        String pattern = "^[a-zA-Z0-9]{1,4}-?[a-zA-Z0-9]{0,4}$";
        return false;
    }

//    private void searchAll() throws IOException {
//        ComcBasketballResponseWrapper comcBasketballResponseWrapper = queryWithScroll();
//        List<String> seriesList = new ArrayList<>();
//        int i = 1;
//        System.out.println(i);
//        fillList(comcBasketballResponseWrapper.getList(), seriesList);
//        while (comcBasketballResponseWrapper.getScrollId() != null && !comcBasketballResponseWrapper.getList().isEmpty()) {
//            comcBasketballResponseWrapper = continueQueryWithScroll(comcBasketballResponseWrapper.getScrollId());
//            System.out.println(++i);
//            fillList(comcBasketballResponseWrapper.getList(), seriesList);
//        }
//        clearScroll(comcBasketballResponseWrapper.getScrollId());
//        seriesList = seriesList.stream().sorted(Comparator.comparing(x -> x)).collect(Collectors.toList());
//        System.out.println("==============================");
//        System.out.println(JacksonUtil.toJSONString(seriesList));
//    }
//
//    private void fillList(List<ComcBasketballDTO> list, List<String> seriesList) {
//        for (ComcBasketballDTO item : list) {
//            String series = item.getTitle().split(" - ")[0].trim();
//            //String pattern = "^[a-zA-Z0-9]{1,4}-?[a-zA-Z0-9]{0,4}$";
//            if (!seriesList.contains(series.split(" ")[1]))
//                seriesList.add(series.split(" ")[1]);
//        }
//        System.out.println(JacksonUtil.toJSONString(seriesList));
//    }
//
//    private ComcBasketballResponseWrapper queryWithScroll() throws IOException {
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("comc_basketball");
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
////        BoolQueryBuilder builder = QueryBuilders.boolQuery();
////        sourceBuilder.query(builder);
//        sourceBuilder.size(2000);
//        searchRequest.source(sourceBuilder);
//        searchRequest.scroll(TimeValue.timeValueMinutes(5L));
//        SearchResponse searchResponse = esClient.search(searchRequest);
//        ComcBasketballResponseWrapper comcBasketballResponseWrapper = new ComcBasketballResponseWrapper(searchResponse.getHits());
//        comcBasketballResponseWrapper.setScrollId(searchResponse.getScrollId());
//        return comcBasketballResponseWrapper;
//    }
//
//    private ComcBasketballResponseWrapper continueQueryWithScroll(String scrollId) throws IOException {
//        SearchScrollRequest searchRequest = new SearchScrollRequest();
//        searchRequest.scroll(TimeValue.timeValueMinutes(5L));
//        searchRequest.scrollId(scrollId);
//        SearchResponse searchResponse = esClient.scroll(searchRequest);
//        ComcBasketballResponseWrapper comcBasketballResponseWrapper = new ComcBasketballResponseWrapper(searchResponse.getHits());
//        comcBasketballResponseWrapper.setScrollId(searchResponse.getScrollId());
//        return comcBasketballResponseWrapper;
//    }
//
//    private void clearScroll(String scrollId) throws IOException {
//        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
//        clearScrollRequest.addScrollId(scrollId);
//        esClient.clearScroll(clearScrollRequest);
//    }
}
