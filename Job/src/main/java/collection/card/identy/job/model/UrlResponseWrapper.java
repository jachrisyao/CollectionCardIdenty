package collection.card.identy.job.model;

import org.apache.lucene.search.TotalHits;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UrlResponseWrapper {
    private Long count = 0L;
    private String scrollId;
    private List<String> urlList = new ArrayList<>();

    public UrlResponseWrapper(SearchHits hits) {
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
            String url = sourceAsMap.get("url").toString();
            urlList.add(url);
        }
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public Long getCount() {
        return count;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }
}
