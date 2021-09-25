package collection.card.identy.crawler.comcBasketball;

import collection.card.identy.es.ComcBasketballEsBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class JobPipeline implements Pipeline {
    @Autowired
    private ComcBasketballEsBiz comcBasketballEsBiz;

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());

        String title = "";
        String frontImgUrl = "";
        String backImgUrl = "";
        String price = "";
        if (resultItems.get("title") != null)
            title = resultItems.get("title").toString();
        if (resultItems.get("frontImgUrl") != null)
            frontImgUrl = resultItems.get("frontImgUrl").toString();
        if (resultItems.get("backImgUrl") != null)
            backImgUrl = resultItems.get("backImgUrl").toString();
        if (resultItems.get("price") != null)
            price = resultItems.get("price").toString();
        System.out.println("id: " + resultItems.getRequest().getUrl().replace("https://www.comc.com/Cards/Basketball", ""));
        System.out.println("title: " + title);
        System.out.println("frontImgUrl: " + frontImgUrl);
        System.out.println("backImgUrl: " + backImgUrl);
        System.out.println("price: " + price);
        if (title != null) {
            comcBasketballEsBiz.update(resultItems.getRequest().getUrl().replace("https://www.comc.com/Cards/Basketball", ""), title, frontImgUrl, backImgUrl, price);
        }
    }
}
