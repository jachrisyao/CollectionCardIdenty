package collection.card.identy.api.controller;

import collection.card.identy.api.handler.JacksonUtil;
import collection.card.identy.api.handler.RequestHandler;
import collection.card.identy.api.model.SearchRequestType;
import collection.card.identy.api.model.SearchResponseType;
import collection.card.identy.api.service.SearchHandler;
import collection.card.identy.api.similarity.ImageHistogram;
import collection.card.identy.api.similarity.ImagePHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Date;
import java.util.Map;

@Controller
public class RestController {
    @Autowired
    private SearchHandler searchHandler;

    @GetMapping("/")
    public String welcome(Map<String, Object> model) throws Exception {
        model.put("time", new Date());
        model.put("message", "welcome");
        return "welcome";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json")
    public SearchResponseType search(HttpServletRequest request) throws Exception {
        String requestJson = RequestHandler.getRequestPayload(request);
        return searchHandler.handler(JacksonUtil.parseFromJson(requestJson, SearchRequestType.class));
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json")
    public String test(HttpServletRequest request) throws Exception {
        return "1111";
    }
}
