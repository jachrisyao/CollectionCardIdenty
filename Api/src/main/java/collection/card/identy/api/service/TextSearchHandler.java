package collection.card.identy.api.service;

import collection.card.identy.api.es.EsClient;
import collection.card.identy.api.model.ComcBasketballDTO;
import collection.card.identy.api.model.ComcBasketballResponseWrapper;
import collection.card.identy.api.model.TextSearchRequestType;
import collection.card.identy.api.model.TextSearchResponseType;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TextSearchHandler {
    @Autowired
    private EsClient esClient;

    public TextSearchResponseType handler(TextSearchRequestType request) throws Exception {
        if (request == null)
            return null;

        TextSearchResponseType response = new TextSearchResponseType();
        response.setCardList(getList(request.getTitle()));
        return response;
    }

    private List<ComcBasketballDTO> getList(String keyword) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("comc_basketball");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(QueryBuilders.matchQuery("title.standard", keyword));
        sourceBuilder.query(builder);
        sourceBuilder.size(20);
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = esClient.search(searchRequest);
        return new ComcBasketballResponseWrapper(searchResponse.getHits()).getList();
    }
}
