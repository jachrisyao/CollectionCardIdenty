package collection.card.identy.job.es;

import collection.card.identy.job.helper.JacksonUtil;
import collection.card.identy.job.model.ComcBasketballDTO;
import collection.card.identy.job.model.UrlResponseWrapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ComcBasketballEsBiz {
    private final static String indexName = "comc_basketball";
    protected static Logger logger = LoggerFactory.getLogger(ComcBasketballEsBiz.class);
    @Autowired
    private EsClient esClient;

    public void update(String url, String title, String frontImgUrl, String backImgUrl, String price) {
        ComcBasketballDTO dto = new ComcBasketballDTO();
        dto.setUrl(url);
        dto.setTitle(title);
        dto.setFrontImgUrl(frontImgUrl);
        dto.setBackImgUrl(backImgUrl);
        dto.setPrice(price);
        UpdateRequest request = buildUpdateRequest(url, dto);
        try {
            esClient.update(request);
        } catch (IOException e) {
            logger.error("es update[url='" + url + "']", e);
        }
    }

    private UpdateRequest buildUpdateRequest(String id, ComcBasketballDTO dto) {
        IndexRequest request = new IndexRequest(indexName);
        String source = JacksonUtil.toJson(dto);
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.id(id);
        request.source(source, XContentType.JSON);
        UpdateRequest updateRequest = new UpdateRequest(indexName, id);
        updateRequest.doc(source, XContentType.JSON).upsert(request);
        updateRequest.retryOnConflict(4);
        return updateRequest;
    }

    public UrlResponseWrapper queryUrls(List<String> urlList) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(QueryBuilders.termsQuery("url", urlList));
        sourceBuilder.query(builder);
        String[] includes = new String[]{"url"};
        sourceBuilder.fetchSource(includes, new String[]{});
        sourceBuilder.size(1000);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = esClient.search(searchRequest);
            return new UrlResponseWrapper(searchResponse.getHits());
        } catch (IOException e) {
            logger.error("es queryUrls", e);
        }
        return null;
    }
}
