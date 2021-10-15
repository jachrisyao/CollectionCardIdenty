package collection.card.identy.job.es;

import collection.card.identy.job.helper.JacksonUtil;
import collection.card.identy.job.helper.MD5Helper;
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
import java.util.stream.Collectors;

@Service
public class ComcBasketballEsBiz {
    private final static String indexName = "comc_basketball";
    protected static Logger logger = LoggerFactory.getLogger(ComcBasketballEsBiz.class);
    @Autowired
    private EsClient esClient;

    public void update(String url, String title, String frontImgUrl, String backImgUrl, String price) {
        ComcBasketballDTO dto = new ComcBasketballDTO();
        dto.setId(MD5Helper.GenerateMd5Hash(url));
        dto.setUrl(url);
        dto.setTitle(title);
        dto.setFrontImgUrl(frontImgUrl);
        dto.setBackImgUrl(backImgUrl);
        dto.setPrice(price);
        UpdateRequest request = buildUpdateRequest(dto);
        try {
            esClient.update(request);
        } catch (IOException e) {
            logger.error("collection.card.identy.api.es update[url='" + url + "']", e);
        }
    }

    private UpdateRequest buildUpdateRequest(ComcBasketballDTO dto) {
        IndexRequest request = new IndexRequest(indexName);
        String source = JacksonUtil.toJson(dto);
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.id(dto.getId());
        request.source(source, XContentType.JSON);
        UpdateRequest updateRequest = new UpdateRequest(indexName, dto.getId());
        updateRequest.doc(source, XContentType.JSON).upsert(request);
        updateRequest.retryOnConflict(4);
        return updateRequest;
    }

    public UrlResponseWrapper queryUrls(List<String> urlList) {
        List<String> idList = urlList.stream().map(MD5Helper::GenerateMd5Hash).collect(Collectors.toList());
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.must(QueryBuilders.termsQuery("id", idList));
        sourceBuilder.query(builder);
        String[] includes = new String[]{"url"};
        sourceBuilder.fetchSource(includes, new String[]{});
        sourceBuilder.size(1000);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = esClient.search(searchRequest);
            return new UrlResponseWrapper(searchResponse.getHits());
        } catch (IOException e) {
            logger.error("collection.card.identy.api.es queryUrls", e);
        }
        return null;
    }
}
