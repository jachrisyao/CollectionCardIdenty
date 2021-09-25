package collection.card.identy.es;

import collection.card.identy.helper.JacksonUtil;
import collection.card.identy.model.ComcBasketballDTO;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ComcBasketballEsBiz {
    private final static String indexName = "comc_basketball";

    @Autowired
    private EsClient esClient;

    public void update(String id, String title, String frontImgUrl, String backImgUrl, String price) {
        ComcBasketballDTO dto = new ComcBasketballDTO();
        dto.setId(id);
        dto.setTitle(title);
        dto.setFrontImgUrl(frontImgUrl);
        dto.setBackImgUrl(backImgUrl);
        dto.setPrice(price);
        UpdateRequest request = buildUpdateRequest(id, dto);
        try {
            esClient.update(request);
        } catch (IOException e) {
            e.printStackTrace();
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
}
