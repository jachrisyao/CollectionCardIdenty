package collection.card.identy.job.es;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EsClient {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public UpdateResponse update(UpdateRequest updateRequest) throws IOException {
        return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse search(SearchRequest searchRequest) throws IOException {
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }
}
