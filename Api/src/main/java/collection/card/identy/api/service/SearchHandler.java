package collection.card.identy.api.service;

import collection.card.identy.api.es.EsClient;
import collection.card.identy.api.model.ComcBasketballDTO;
import collection.card.identy.api.model.ComcBasketballResponseWrapper;
import collection.card.identy.api.model.SearchRequestType;
import collection.card.identy.api.model.SearchResponseType;
import collection.card.identy.api.proxy.OcrService;
import collection.card.identy.api.similarity.ImageHistogram;
import collection.card.identy.api.similarity.ImagePHash;
import com.tencentcloudapi.ocr.v20181119.models.TextDetectionEn;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchHandler {
    @Autowired
    private OcrService ocrService;
    @Autowired
    private EsClient esClient;
    @Autowired
    private ImageHistogram imageHistogram;
    @Autowired
    private ImagePHash imagePHash;

    public SearchResponseType handler(SearchRequestType request) throws Exception {
        if (request == null)
            return null;

        SearchResponseType response = new SearchResponseType();

        //Step 1 OCR
        TextDetectionEn[] frontKeyWords = ocrService.getKeyWords(request.getFrontUrl());
        TextDetectionEn[] backKeyWords = ocrService.getKeyWords(request.getBackUrl());
        //Step 2 Recognize Year/Name/Number
        List<String> keywords = filterUsefulKeyWords(frontKeyWords, backKeyWords);
        //Step 3 Search in ES
        List<ComcBasketballDTO> list = getList(keywords);
        //Step 4 Similarity
        fillWithScore(list, request.getFrontUrl(), request.getBackUrl());
        return response;
    }

    private List<String> filterUsefulKeyWords(TextDetectionEn[] frontKeyWords, TextDetectionEn[] backKeyWords) {
        return Arrays.asList("#83", "LUIS SCOLA", "2016-17 PANINI");
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

    private void fillWithScore(List<ComcBasketballDTO> list, String frontUrl, String backUrl) throws Exception {
        for (ComcBasketballDTO item : list) {
            System.out.println("============================");
            System.out.println("frontUrl: " + item.getFrontImgUrl());
            System.out.println("backUrl: " + item.getBackImgUrl());
            double frontScore1 = imageHistogram.match(new URL(item.getFrontImgUrl()), new URL(frontUrl));
            System.out.println("histogram front score: " + frontScore1);
            double frontScore2 = imagePHash.distance(new URL(item.getFrontImgUrl()), new URL(frontUrl));
            System.out.println("phash front score: " + frontScore2);
            double backScore1 = imageHistogram.match(new URL(item.getBackImgUrl()), new URL(backUrl));
            System.out.println("histogram back score: " + backScore1);
            double backScore2 = imagePHash.distance(new URL(item.getBackImgUrl()), new URL(backUrl));
            System.out.println("phash back score: " + backScore2);
            //double totalScore = frontScore1 + 1 / frontScore2 + backScore1 + 1 / backScore2;
            //System.out.println(totalScore);
        }
    }
}
