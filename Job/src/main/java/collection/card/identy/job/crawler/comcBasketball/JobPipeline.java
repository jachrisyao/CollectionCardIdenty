package collection.card.identy.job.crawler.comcBasketball;

import collection.card.identy.job.es.ComcBasketballEsBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

@Component
public class JobPipeline implements Pipeline {
    protected static Logger logger = LoggerFactory.getLogger(JobPipeline.class);
    @Autowired
    private ComcBasketballEsBiz comcBasketballEsBiz;
    @Autowired
    private JobPageProcessor jobPageProcessor;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //非详情页不处理
        if (!jobPageProcessor.isDetailUrl(resultItems.getRequest().getUrl())) {
            logger.info("get page: " + resultItems.getRequest().getUrl());
            System.out.println("get page: " + resultItems.getRequest().getUrl());
            return;
        }
        //System.out.println("get page: " + resultItems.getRequest().getUrl());

        String title = "";
        String frontImgUrl = "";
        String backImgUrl = "";
        String price = "";
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            //System.out.println(entry.getKey() + ":\t" + entry.getValue());
            if (entry.getKey().equals("title"))
                title = resultItems.get("title").toString();
            if (entry.getKey().equals("frontImgUrl"))
                frontImgUrl = resultItems.get("frontImgUrl").toString().replace("size=original", "size=zoom");
            if (entry.getKey().equals("backImgUrl"))
                backImgUrl = resultItems.get("backImgUrl").toString().replace("size=original", "size=zoom");
            if (entry.getKey().equals("price"))
                price = resultItems.get("price").toString();
        }

        if (title != null) {
            comcBasketballEsBiz.update(jobPageProcessor.urlToUniqueId(resultItems.getRequest().getUrl()), title, frontImgUrl, backImgUrl, price);
        }
    }
}
