package collection.card.identy.job.es;

import collection.card.identy.job.helper.JacksonUtil;
import collection.card.identy.job.model.ComcBasketballDTO;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ComcBasketballEsBiz {
    private final static String indexName = "comc_basketball";
    protected static Logger logger = LoggerFactory.getLogger(ComcBasketballEsBiz.class);
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
            logger.error("es update[id='" + id + "']", e);
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
