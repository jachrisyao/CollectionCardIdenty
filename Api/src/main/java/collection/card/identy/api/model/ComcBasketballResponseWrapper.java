package collection.card.identy.api.model;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComcBasketballResponseWrapper {
    private Long count = 0L;
    private List<ComcBasketballDTO> list = new ArrayList<>();

    public ComcBasketballResponseWrapper(SearchHits hits) {
        if (hits == null) {
            return;
        }
        TotalHits totalHits = hits.getTotalHits();
        if (totalHits == null) {
            return;
        }
        this.count = totalHits.value;
        if (this.count == 0) return;

        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // do something with the SearchHit
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            ComcBasketballDTO item = new ComcBasketballDTO();
            item.setId(sourceAsMap.get("id").toString());
            item.setBackImgUrl(sourceAsMap.get("backImgUrl").toString());
            item.setPrice(sourceAsMap.get("price").toString());
            item.setFrontImgUrl(sourceAsMap.get("frontImgUrl").toString());
            item.setUrl(sourceAsMap.get("url").toString());
            item.setTitle(sourceAsMap.get("title").toString());
            list.add(item);
        }
    }

    public List<ComcBasketballDTO> getList() {
        return list;
    }
}
