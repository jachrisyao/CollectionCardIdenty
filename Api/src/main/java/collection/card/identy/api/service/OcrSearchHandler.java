package collection.card.identy.api.service;

import collection.card.identy.api.es.EsClient;
import collection.card.identy.api.handler.JacksonUtil;
import collection.card.identy.api.model.ComcBasketballDTO;
import collection.card.identy.api.model.ComcBasketballResponseWrapper;
import collection.card.identy.api.model.OcrSearchRequestType;
import collection.card.identy.api.model.OcrSearchResponseType;
import collection.card.identy.api.proxy.OcrService;
import com.tencentcloudapi.ocr.v20181119.models.TextDetectionEn;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.util.ArrayUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        response.setCardList(getList(keywords));
        return response;
    }

    private List<String> filterUsefulKeyWords(TextDetectionEn[] frontKeyWords, TextDetectionEn[] backKeyWords) {
        List<String> keywords = new ArrayList<>();
        List<String> yearKeyWords = getYearSeries(backKeyWords);
        if (yearKeyWords != null)
            keywords.addAll(yearKeyWords);
        List<String> numberKeyWords = getNumber(backKeyWords);
        if (numberKeyWords != null)
            keywords.addAll(numberKeyWords);
        List<String> nameKeywords = getName(ArrayUtils.concat(frontKeyWords, backKeyWords, TextDetectionEn.class), keywords);
        if (nameKeywords != null)
            keywords.addAll(nameKeywords);
        return keywords;
        //return Arrays.asList("#83", "LUIS SCOLA", "2016-17 PANINI");
    }

    private List<String> getYearSeries(TextDetectionEn[] array) {
        //必须满足的条件
        String yearPattern1 = "[1,2]{1}[0-9]{3}([^\"]+)";
        String yearPattern2 = "[1,2]{1}[0-9]{3}-[0-9]{2}([^\"]+)";
        List<TextDetectionEn> tempList = Arrays.stream(array).filter(x -> {
            //长度大于4
            if (x.getDetectedText().length() < 5)
                return false;
            //前4位是数字
            if (!Pattern.matches(yearPattern1, x.getDetectedText()))
                return false;
            //数字范围在1940-2030
            int year = Integer.parseInt(x.getDetectedText().substring(0, 4));
            if (year < 1940 || year > 2030)
                return false;
            //校验赛季格式
            if (x.getDetectedText().toCharArray()[4] == '-') {
                //校验格式，例如2015-16
                if (!Pattern.matches(yearPattern2, x.getDetectedText()))
                    return false;
                //-后的年份为前面的+1
                if (!String.valueOf(year + 1).substring(2).equals(x.getDetectedText().toCharArray()[5] + Character.toString(x.getDetectedText().toCharArray()[6])))
                    return false;
            }
            return true;
        }).collect(Collectors.toList());

        if (tempList.size() == 1)
            return Collections.singletonList(tempList.get(0).getDetectedText());
        if (tempList.size() == 0)
            return null;

        List<String> seriesList = JacksonUtil.parseListFromJson(seriesListJSON, String.class);
        //补充条件
        List<TextDetectionEn> tempList2 = tempList.stream().filter(x -> {
            //年份后的关键字满足条件
            if (x.getDetectedText().contains(" ") && seriesList.contains(x.getDetectedText().split(" ")[1].toUpperCase())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if (tempList2.size() > 0)
            return tempList2.stream().map(TextDetectionEn::getDetectedText).collect(Collectors.toList());
        return tempList.stream().map(TextDetectionEn::getDetectedText).collect(Collectors.toList());
    }

    private List<String> getNumber(TextDetectionEn[] array) {
        List<Long> xys = rangeXY(array);
        String numberPattern1 = "^[a-zA-Z0-9]{1,4}-[a-zA-Z0-9]{0,4}$";
        String numberPattern2 = "^[0-9]{1,3}$";
        //必须满足的条件
        List<TextDetectionEn> tempList = Arrays.stream(array).filter(x -> {
            //编号在边框上
            boolean isLeft = Arrays.stream(x.getPolygon()).allMatch(y -> y.getX() < xys.get(0));
            boolean isBottom = Arrays.stream(x.getPolygon()).allMatch(y -> y.getY() > xys.get(3));
            boolean isRight = Arrays.stream(x.getPolygon()).allMatch(y -> y.getX() > xys.get(2));
            boolean isTop = Arrays.stream(x.getPolygon()).allMatch(y -> y.getY() < xys.get(1));
            if (!isLeft && !isRight && !isTop && !isBottom)
                return false;

            //校验编号格式
            String text = formatNumber(x.getDetectedText());
            if (!Pattern.matches(numberPattern1, text) && !Pattern.matches(numberPattern2, text))
                return false;
            return true;
        }).collect(Collectors.toList());

        if (tempList.size() == 1)
            return Collections.singletonList("#" + formatNumber(tempList.get(0).getDetectedText()));
        if (tempList.size() == 0)
            return null;

        //补充条件
        List<TextDetectionEn> tempList2 = tempList.stream().filter(x -> {
            //No开头或者#开头
            if (x.getDetectedText().startsWith("No.") || x.getDetectedText().startsWith("NO.") || x.getDetectedText().startsWith("no.") || x.getDetectedText().startsWith("#"))
                return true;
            return false;
        }).collect(Collectors.toList());

        if (tempList2.size() > 0)
            return tempList2.stream().map(x -> "#" + formatNumber(x.getDetectedText())).collect(Collectors.toList());
        return tempList.stream().map(x -> "#" + formatNumber(x.getDetectedText())).collect(Collectors.toList());
    }

    private List<String> getName(TextDetectionEn[] array, List<String> keywords) {
        String numberPattern = ".*\\d+.*";
        //必须满足的条件
        List<TextDetectionEn> tempList = Arrays.stream(array).filter(x -> {
            //过滤含数字的
            if (Pattern.matches(numberPattern, x.getDetectedText()))
                return false;
            //过滤含特殊符号的
            if (x.getDetectedText().contains(",") || x.getDetectedText().contains("'") || x.getDetectedText().contains("\"") || x.getDetectedText().contains(":"))
                return false;
            //过滤空格数量>3的
            if (x.getDetectedText().contains(" ")) {
                int spaceCount = 0;
                for (int i = 0; i < x.getDetectedText().length(); ++i) {
                    if (x.getDetectedText().charAt(i) == ' ')
                        spaceCount++;
                }
                if (spaceCount > 3)
                    return false;
            }
            //过滤首字母小写
            if (Character.isLowerCase(x.getDetectedText().charAt(0)))
                return false;
            return true;
        }).collect(Collectors.toList());

        if (tempList.size() == 1)
            return Collections.singletonList(tempList.get(0).getDetectedText());
        if (tempList.size() == 0)
            return null;

        //补充条件
        List<TextDetectionEn> tempList2 = tempList.stream().filter(x -> {
            try {
                //过滤ES中查不到的关键词
                List<String> tempKeyWordList = new ArrayList<>(keywords);
                tempKeyWordList.add(x.getDetectedText());
                long count = queryCount(tempKeyWordList);
                if (count == 0)
                    return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }).collect(Collectors.toList());
        if (tempList2.size() > 0)
            return tempList2.stream().map(TextDetectionEn::getDetectedText).collect(Collectors.toList());
        return tempList.stream().map(TextDetectionEn::getDetectedText).collect(Collectors.toList());
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

    private String formatNumber(String text) {
        return text.replace("No.", "").replace("NO.", "").replace("no.", "").replace("#", "").trim();
    }

    private long queryCount(List<String> keywords) throws IOException {
        CountRequest countRequest = new CountRequest();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        for (String keyword : keywords) {
            builder.must(QueryBuilders.matchQuery("title.standard", keyword));
        }
        countRequest.query(builder);
        countRequest.indices("comc_basketball");
        CountResponse countResponse = esClient.count(countRequest);
        if (countResponse != null)
            return countResponse.getCount();
        return 0;
    }

    private List<ComcBasketballDTO> getList(List<String> keywords) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("comc_basketball");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        for (String keyword : keywords) {
            builder.must(QueryBuilders.matchQuery("title.standard", keyword));
        }
        sourceBuilder.query(builder);
        sourceBuilder.size(20);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = esClient.search(searchRequest);
        return new ComcBasketballResponseWrapper(searchResponse.getHits()).getList();
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
