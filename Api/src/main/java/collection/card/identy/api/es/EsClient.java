package collection.card.identy.api.es;

import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EsClient {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public SearchResponse search(SearchRequest searchRequest) throws IOException {
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse scroll(SearchScrollRequest searchScrollRequest) throws IOException {
        return restHighLevelClient.scroll(searchScrollRequest, RequestOptions.DEFAULT);
    }

    public ClearScrollResponse clearScroll(ClearScrollRequest clearScrollRequest) throws IOException {
        return restHighLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
    }
}
