package collection.card.identy.api.service;

import collection.card.identy.api.es.EsClient;
import collection.card.identy.api.handler.JacksonUtil;
import collection.card.identy.api.model.*;
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
    private final String seriesListJSON = "[\"23KT\",\"7-ELEVEN\",\"7-UP\",\"ABL\",\"ACC\",\"ACMI\",\"AMA\",\"APBA\",\"AT&T\",\"ABSOLUTE\",\"ACTION\",\"AD\",\"ADOLESCENT\",\"ADRENALYN\",\"AFICIONADO\",\"AKEEM\",\"ALBUM\",\"ALL\",\"ALL-ROOKIE\",\"ALL-STAR\",\"ANONYMOUS\",\"ANTONIO\",\"APEX\",\"ARCHIVES\",\"AREA\",\"ARENA\",\"ARIZONA\",\"ARKANSAS\",\"ARTICLE\",\"ASCENSION\",\"ASES\",\"ATLANTA\",\"AUBURN\",\"AUTHENTIC\",\"AUTHENTIX\",\"AUTHORITY\",\"AUTOGRAPHED\",\"AUTOGRAPHICS\",\"AVANT\",\"AVIA\",\"B-LEAGUE\",\"BASF\",\"BBM\",\"BC\",\"BIG3\",\"BAIO\",\"BALLPARK\",\"BANK\",\"BANK/COCA-COLA\",\"BASKET\",\"BASKETBALL\",\"BASKETCROMOS\",\"BAY\",\"BAZOOKA\",\"BEAR\",\"BEARCATS\",\"BECK\",\"BEHIND\",\"BELL\",\"BEST\",\"BIG\",\"BIJAN\",\"BIMBO\",\"BLACK\",\"BLEACHERS\",\"BLUE\",\"BOARD\",\"BOB\",\"BOILERMAKERS\",\"BOLLYCAO\",\"BOSTON\",\"BOWMAN\",\"BOWMAN'S\",\"BOX\",\"BRAVO\",\"BREAD\",\"BREYERS\",\"BRIGHAM\",\"BRILLIANCE\",\"BRILLIANTS\",\"BUGLES\",\"BULLDOGS\",\"BUTTONS\",\"CAO\",\"CBA\",\"CACHE\",\"CALGARY\",\"CANASTA\",\"CARD\",\"CARDINAL\",\"CARDS\",\"CAREUNIT\",\"CARL'S\",\"CARNATION\",\"CAROLINA\",\"CARVEL\",\"CAVALIERS\",\"CENTURY\",\"CERTIFIED\",\"CHAMPIONS\",\"CHAMPIONSHIP\",\"CHARLES\",\"CHARLOTTE\",\"CHICAGO\",\"CHINA\",\"CHINESE\",\"CHROME\",\"CHRONICLES\",\"CINCINNATI\",\"CIRCLE\",\"CITY\",\"CLASSIC\",\"CLASSICS\",\"CLEAR\",\"CLEARLY\",\"CLEO\",\"CLEVELAND\",\"CLYDE\",\"CO-SIGNERS\",\"COCA-COLA\",\"COCA-COLA/WPLO\",\"COCOA\",\"COLLECT-A-CARD\",\"COLLECTION\",\"COLLECTION/COCA-COLA\",\"COLLECTOR'S\",\"COLLECTORS\",\"COLLEGE\",\"COLLEGIATE\",\"COMIC\",\"COMMONWEALTH\",\"COMPLETE\",\"CONTEMPORARY\",\"CONTENDERS\",\"CONVENTION\",\"CONVERSE\",\"CORNERSTONES\",\"CORNHUSKERS\",\"COUNTY\",\"COURT\",\"COURTSIDE\",\"COUSY\",\"CREEK\",\"CROWN\",\"CRUNCH\",\"CRUSADE\",\"DALLAS\",\"DAME\",\"DAVID\",\"DAY\",\"DEPAUL\",\"DECK\",\"DECKS\",\"DEE\",\"DENNY'S\",\"DENVER\",\"DERRICK\",\"DETROIT\",\"DIAMOND\",\"DISCS\",\"DISPLAY\",\"DOMINION\",\"DOMINIQUE\",\"DONRUSS\",\"DRAFT\",\"DRUG\",\"DUNK!\",\"DUNKIN'\",\"E-X\",\"E-XL\",\"EX\",\"EX2000\",\"EX2001\",\"EAGLE\",\"ECHELON\",\"EDGE\",\"EL\",\"ELEVATION\",\"ELITE\",\"EMBOSSED\",\"EMINENCE\",\"EMOTION\",\"EMPORIUM\",\"ENCASED\",\"ENTENMANN'S\",\"EQUAL\",\"ESSENTIALS\",\"ESTRELLAS\",\"ETERNAL\",\"EUROPEAN\",\"EXCALIBUR\",\"EXCHANGE\",\"EXCLUSIVE\",\"EXPRESS\",\"EXQUISITE\",\"FAMOUS\",\"FARMER\",\"FAST\",\"FATHEAD\",\"FATHER'S\",\"FELLOWSHIP\",\"FIGHTING\",\"FINAL\",\"FINALS\",\"FINEST\",\"FINITE\",\"FIRST\",\"FLAIR\",\"FLASHBACKS\",\"FLAWLESS\",\"FLEER\",\"FLEER/SKYBOX\",\"FOCUS\",\"FOILS\",\"FOODLAND\",\"FOODS\",\"FOODTOWN\",\"FORCE\",\"FOREST\",\"FOUNDATION\",\"FOURNIER\",\"FRAGRANCES\",\"FRANZ\",\"FRESH\",\"FRITO\",\"FRONT\",\"FROSTED\",\"FULL\",\"FUTERA\",\"FUTURES\",\"GE\",\"GALA\",\"GALLERY\",\"GAME\",\"GENUINE\",\"GEORGETOWN\",\"GEORGIA\",\"GIANT\",\"GIGANTES\",\"GLASS\",\"GLOBE\",\"GO\",\"GOLD\",\"GOLDEN\",\"GONZAGA\",\"GOODWILL\",\"GRAND\",\"GREATEST\",\"GREATS\",\"GRIZZLIES\",\"GRIZZLIES/TORONTO\",\"HALL\",\"HALLMARK\",\"HANDYMAN\",\"HANES\",\"HARDWOOD\",\"HARLEM\",\"HASBRO\",\"HAWAII\",\"HAWKEYES\",\"HEAT\",\"HERITAGE\",\"HERO\",\"HIGH\",\"HIGHLAND\",\"HIT\",\"HOME\",\"HOOPS\",\"HOSPITAL\",\"HOT\",\"HOYAS\",\"HUT\",\"IBM\",\"IHSA\",\"ICEE\",\"ILLINOIS\",\"ILLUSIONS\",\"ILLUSTRATED\",\"IMAGES\",\"IMMACULATE\",\"IMPACT\",\"IMPECCABLE\",\"INDIANA\",\"INDUSTRY\",\"INFINITE\",\"INKREDIBLE\",\"INNOVATION\",\"INSIDE\",\"INSTANT\",\"INTERACTIVE\",\"INTRIGUE\",\"IOWA\",\"ISIAH\",\"ITAL\",\"JMS\",\"JACK\",\"JAM\",\"JAMES\",\"JAYHAWKS\",\"JERSEY\",\"JOHN\",\"JORDAN\",\"JR.\",\"JULIUS\",\"JUMBO\",\"K\",\"KOS/JEZ\",\"KANSAS\",\"KARL\",\"KAYO\",\"KAZAAM\",\"KELLOGG'S\",\"KENNER\",\"KENTUCKY\",\"KEVIN\",\"KID'S\",\"KING'S\",\"KINGS\",\"KIWANIS\",\"KOBE\",\"KODAK\",\"KRAFT\",\"KUDOS\",\"L.E.\",\"LDDS\",\"LNB\",\"LSU\",\"LADY\",\"LAMAR\",\"LANCASTER\",\"LARRY\",\"LAY\",\"LEAF\",\"LEBRON\",\"LEGACY\",\"LEGENDARY\",\"LEGENDS\",\"LETTERMAN\",\"LIBERTY\",\"LIFEBUOY\",\"LIME\",\"LIMITED\",\"LINE\",\"LINEUP\",\"LITE\",\"LITTLE\",\"LOS\",\"LUCCA\",\"LUXE\",\"LUXURY\",\"MSA\",\"MAGIC\",\"MAGNETS\",\"MAGS\",\"MAJEUR\",\"MARK\",\"MARQUEE\",\"MARYLAND\",\"MATH\",\"MAXIMUM\",\"MCDONALD'S\",\"MEDALLIONS\",\"MEMORABILIA\",\"MEMPHIS\",\"MERCHANTE\",\"METAL\",\"MEXICO\",\"MIAMI\",\"MICHAEL\",\"MICHIGAN\",\"MILLENNIUM\",\"MILWAUKEE\",\"MINI\",\"MINNESOTA\",\"MINT\",\"MISSISSIPPI\",\"MISSOURI\",\"MOLTEN\",\"MOMENTUM\",\"MONEY\",\"MOSAIC\",\"MOTION\",\"MOUNTAIN\",\"MUFLON\",\"MULTIAD\",\"MUNDO\",\"MURRAY\",\"MYSTIQUE\",\"N.C.\",\"NABC\",\"NBA\",\"NBL\",\"NATIONAL\",\"NEBRASKA\",\"NEW\",\"NEWS\",\"NEWS/HANDY\",\"NIKE\",\"NIKETOWN\",\"NOIR\",\"NORTH\",\"NORTHWESTERN\",\"NORWEST\",\"NOTRE\",\"NOVA\",\"NUGGETS\",\"O'NEAL\",\"O-PEE-CHEE\",\"OBSIDIAN\",\"OHIO\",\"OKLAHOMA\",\"OLD\",\"OMNI\",\"ONE\",\"OPULENCE\",\"ORANGEMEN\",\"OREGON\",\"ORIGINS\",\"ORLANDO\",\"PBA\",\"PACERS\",\"PACIFIC\",\"PACKED\",\"PANCHERO'S\",\"PANGOS\",\"PANINI\",\"PAPA\",\"PAPER\",\"PARAMOUNT\",\"PARKSIDE\",\"PASS\",\"PAST\",\"PATCHWORKS\",\"PATRICK\",\"PEPSI\",\"PEPSI-COLA\",\"PEPSI/7-ELEVEN\",\"PHILADELPHIA\",\"PHIPPS\",\"PHOENIX\",\"PHONE\",\"PICK-UP\",\"PICS\",\"PICTURE\",\"PINNACLE\",\"PISTONS\",\"PITT\",\"PITTSBURGH\",\"PIZZA\",\"PLATINUM\",\"PLAYER\",\"PLAYMAKERS\",\"PLAYOFF\",\"PORTLAND\",\"POSTCARD\",\"POWER\",\"PREFERRED\",\"PREMIER\",\"PREMIUM\",\"PRESS\",\"PRESTIGE\",\"PREVIEW\",\"PRIDE\",\"PRIME\",\"PRISM\",\"PRISM/JEWEL\",\"PRISTINE\",\"PRIVATE\",\"PRIZM\",\"PRO\",\"PROCARDS\",\"PROMO\",\"PROMOS\",\"PROVIDENCE\",\"PURDUE\",\"QDOBA/CHILDREN'S\",\"REAL\",\"REEBOK\",\"REFLECTIONS\",\"RELIC\",\"REPLAY\",\"REPUBLIC\",\"RESERVE\",\"RETRO\",\"REVOLUTION\",\"REWIND\",\"RITTENHOUSE\",\"ROCK\",\"ROOKIE\",\"ROOKIES\",\"ROOX\",\"ROW\",\"ROYALE\",\"RULERS\",\"RUNNIN'\",\"SAGE\",\"SP\",\"SPX\",\"SACRAMENTO\",\"SAFEWAY\",\"SAGE\",\"SAINT\",\"SAN\",\"SCHICK\",\"SCHOLASTIC\",\"SCHOOL\",\"SCORE\",\"SEARS\",\"SEASON\",\"SEATTLE\",\"SELECT\",\"SEQUENTIALLY\",\"SERIES\",\"SERIOUS\",\"SERVICE\",\"SHAMROCK\",\"SHAQ\",\"SHAQUILLE\",\"SHAWN\",\"SHELBY\",\"SHOEBOX\",\"SHOMA\",\"SHOP\",\"SHOWCASE\",\"SHOWDOWN\",\"SIGNATURE\",\"SIGNATURES\",\"SIGNINGS\",\"SILVER\",\"SKYBOX\",\"SKYBOX\",\"SLAM\",\"SMOKEY\",\"SMOKEY'S\",\"SOUTHERN\",\"SPACE\",\"SPECIAL\",\"SPECTRA\",\"SPORT\",\"SPORTS\",\"SPORTSCARDS\",\"SPREE\",\"SPRINT\",\"SPRITE\",\"SPRITE/MINYARD\",\"STADIUM\",\"STAMPS\",\"STANFORD\",\"STAR\",\"STARS\",\"STARTING\",\"STATE\",\"STATUS\",\"STELLAR\",\"STEP\",\"STERLING\",\"STICKER\",\"STICKERS\",\"STICKITO\",\"STUDIO\",\"SUN\",\"SUPER\",\"SUPERIOR\",\"SUPERSONICS\",\"SWEET\",\"SYRACUSE\",\"T-51\",\"TCMA\",\"TACO\",\"TARGET\",\"TEAM\",\"TECH\",\"TED\",\"TELECOM\",\"TEN\",\"TENNESSEE\",\"TERRAPINS\",\"TEXACO\",\"THE\",\"THREADS\",\"THROWBACKS\",\"THUNDER\",\"TICKET\",\"TIGERS\",\"TIM\",\"TIMBERWOLVES\",\"TIME\",\"TIMELESS\",\"TIP-OFF\",\"TIPOFF\",\"TITANIUM\",\"TOM\",\"TONY'S\",\"TOP\",\"TOPPS\",\"TOPPS/UPPER\",\"TOTAL\",\"TOTALSPORT\",\"TOTALLY\",\"TOURNAMENT\",\"TRADEABLES\",\"TRADEMARK\",\"TRADITION\",\"TRAILBLAZERS\",\"TREASURES\",\"TREASURY\",\"TRIPLE\",\"TROJANS\",\"TRUMPS\",\"TURKEY\",\"TYSON\",\"UD\",\"UD3\",\"UNLV\",\"USA\",\"USC\",\"ULTIMATE\",\"ULTRA\",\"UNIVERSE\",\"UNIVERSITY\",\"UNO\",\"UNOCAL\",\"UPDATE\",\"UPPER\",\"UTAH\",\"VANCOUVER\",\"VANGUARD\",\"VICTORY\",\"VINCENT-SAINT\",\"VIRGINIA\",\"VISIONS\",\"VOLUNTEERS\",\"WNBA\",\"WAKE\",\"WEBER\",\"WENDY'S\",\"WHEATIES\",\"WHEELS\",\"WICHITA\",\"WILD\",\"WILDCAT\",\"WILDCATS\",\"WILLIAMS\",\"WOLVERINES\",\"WONDER\",\"WORLDCOM\",\"WORTH\",\"WR\",\"WRAPPER\",\"WRIGHT\",\"XL\",\"XPECTATIONS\",\"YOGUR\",\"YOUNG\",\"Z\",\"Z-FORCE\",\"ZALGIRIS\",\"ADIDAS\",\"DEL\",\"ETOPPS\"]";
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

        KeyWordDTO keyWordDTO = new KeyWordDTO();
        List<String> keywords = new ArrayList<>();
        List<String> yearSeriesKeyWords = getYearSeries(backKeyWords);
        if (yearSeriesKeyWords != null) {
            keywords.addAll(yearSeriesKeyWords);
            keyWordDTO.setYearKeyWords(yearSeriesKeyWords);
        }
        List<String> numberKeyWords = getNumber(backKeyWords);
        if (numberKeyWords != null) {
            keywords.addAll(numberKeyWords);
            keyWordDTO.setNumberKeyWords(numberKeyWords);
        }
        TextDetectionEn[] array = new TextDetectionEn[]{};
        if (frontKeyWords != null)
            array = ArrayUtils.concat(array, frontKeyWords, TextDetectionEn.class);
        if (backKeyWords != null)
            array = ArrayUtils.concat(array, backKeyWords, TextDetectionEn.class);
        List<String> nameKeywords = getName(array, keywords);
        keyWordDTO.setOtherKeyWords(nameKeywords);
        if (keywords.size() > 0)
            response.setCardList(getList(keywords, nameKeywords));
        else
            response.setCardList(new ArrayList<>());

        keywords.addAll(nameKeywords);
        response.setKeywords(keyWordDTO);
        return response;
    }

    private List<String> getYearSeries(TextDetectionEn[] array) {
        if (array == null)
            return new ArrayList<>();

        //?????????????????????
        String yearPattern1 = "[1,2]{1}[0-9]{3}([^\"]+)";
        String yearPattern2 = "[1,2]{1}[0-9]{3}-[0-9]{2}([^\"]+)";
        String yearPattern3 = "[1,2]{1}[0-9]{3}-[0-9]{4}([^\"]+)";
        List<TextDetectionEn> tempList = Arrays.stream(array).filter(x -> {
            //????????????4
            if (x.getDetectedText().length() < 5)
                return false;
            //???4????????????
            if (!Pattern.matches(yearPattern1, x.getDetectedText()))
                return false;
            //???????????????1940-2030
            int year = Integer.parseInt(x.getDetectedText().substring(0, 4));
            if (year < 1940 || year > 2030)
                return false;
            //??????????????????
            if (x.getDetectedText().toCharArray()[4] == '-') {
                String seriesPart = x.getDetectedText().split(" ")[0].split("-")[1];
                if (seriesPart.length() != 2 && seriesPart.length() != 4)
                    return false;
                //?????????????????????2015-16
                if (seriesPart.length() == 2) {
                    if (!Pattern.matches(yearPattern2, x.getDetectedText()))
                        return false;
                    //-????????????????????????+1
                    if (!String.valueOf(year + 1).substring(2).equals(seriesPart))
                        return false;
                } else {
                    if (!Pattern.matches(yearPattern3, x.getDetectedText()))
                        return false;
                    //-????????????????????????+1
                    if (!String.valueOf(year + 1).equals(seriesPart))
                        return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        if (tempList.size() == 1)
            return Collections.singletonList(tempList.get(0).getDetectedText());
        if (tempList.size() == 0)
            return null;

        List<String> seriesList = JacksonUtil.parseListFromJson(seriesListJSON, String.class);
        //????????????
        List<TextDetectionEn> tempList2 = tempList.stream().filter(x -> {
            //?????????????????????????????????
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
        if (array == null)
            return new ArrayList<>();

        List<Long> xys = rangeXY(array);
        String numberPattern1 = "^[a-zA-Z0-9]{1,4}-[a-zA-Z0-9]{0,4}$";
        String numberPattern2 = "^[0-9]{1,3}$";
        //?????????????????????
        List<TextDetectionEn> tempList = Arrays.stream(array).filter(x -> {
            //???????????????
            boolean isLeft = Arrays.stream(x.getPolygon()).allMatch(y -> y.getX() < xys.get(0));
            boolean isBottom = Arrays.stream(x.getPolygon()).allMatch(y -> y.getY() > xys.get(3));
            boolean isRight = Arrays.stream(x.getPolygon()).allMatch(y -> y.getX() > xys.get(2));
            boolean isTop = Arrays.stream(x.getPolygon()).allMatch(y -> y.getY() < xys.get(1));
            if (!isTop)
                return false;

            //??????????????????
            String text = formatNumber(x.getDetectedText());
            if (!Pattern.matches(numberPattern1, text) && !Pattern.matches(numberPattern2, text))
                return false;
            return true;
        }).collect(Collectors.toList());

        if (tempList.size() == 1)
            return Collections.singletonList("#" + formatNumber(tempList.get(0).getDetectedText()));
        if (tempList.size() == 0)
            return null;

        //????????????
        List<TextDetectionEn> tempList2 = tempList.stream().filter(x -> {
            //No????????????#??????
            if (x.getDetectedText().contains("No.") || x.getDetectedText().contains("NO.") || x.getDetectedText().contains("no.") || x.getDetectedText().contains("#"))
                return true;
            return false;
        }).collect(Collectors.toList());

        if (tempList2.size() > 0)
            return tempList2.stream().map(x -> "#" + formatNumber(x.getDetectedText())).collect(Collectors.toList());
        return tempList.stream().map(x -> "#" + formatNumber(x.getDetectedText())).collect(Collectors.toList());
    }

    private List<String> getName(TextDetectionEn[] array, List<String> keywords) {
        String numberPattern = ".*\\d+.*";
        //?????????????????????
        List<TextDetectionEn> tempList = Arrays.stream(array).filter(x -> {
            //??????????????????
            if (Pattern.matches(numberPattern, x.getDetectedText()))
                return false;
            //????????????????????????
            if (x.getDetectedText().contains(",") || x.getDetectedText().contains("'") || x.getDetectedText().contains("\"") || x.getDetectedText().contains(":"))
                return false;
            //??????????????????>3???
            if (x.getDetectedText().contains(" ")) {
                int spaceCount = 0;
                for (int i = 0; i < x.getDetectedText().length(); ++i) {
                    if (x.getDetectedText().charAt(i) == ' ')
                        spaceCount++;
                }
                if (spaceCount > 3)
                    return false;
            }
            //?????????????????????
            if (Character.isLowerCase(x.getDetectedText().charAt(0)))
                return false;
            return true;
        }).collect(Collectors.toList());

        if (tempList.size() == 1)
            return Collections.singletonList(tempList.get(0).getDetectedText());
        if (tempList.size() == 0)
            return new ArrayList<>();

        //????????????
        List<TextDetectionEn> tempList2 = tempList.stream().filter(x -> {
            try {
                //??????ES????????????????????????
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
        BigDecimal rate = new BigDecimal("0.15");
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
        return text.replace("CARD", "").replace("Card", "").replace("No.", "").replace("NO.", "").replace("no.", "").replace("#", "").trim();
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

    private List<ComcBasketballDTO> getList(List<String> mustKeywords, List<String> shouldKeywords) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("comc_basketball");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        for (String keyword : mustKeywords) {
            builder.must(QueryBuilders.matchQuery("title.standard", keyword));
        }
        for (String keyword : shouldKeywords) {
            builder.should(QueryBuilders.matchQuery("title.standard", keyword));
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
//            String[] seriesArr = series.split(" ");
//            if (!seriesList.contains(seriesArr[1]))
//                seriesList.add(seriesArr[1]);
//            if (seriesArr.length > 2 && !seriesList.contains(seriesArr[2]))
//                seriesList.add(seriesArr[2]);
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
