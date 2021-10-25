package collection.card.identy.api.controller;

import collection.card.identy.api.handler.JacksonUtil;
import collection.card.identy.api.handler.RequestHandler;
import collection.card.identy.api.model.OcrSearchRequestType;
import collection.card.identy.api.model.OcrSearchResponseType;
import collection.card.identy.api.model.TextSearchRequestType;
import collection.card.identy.api.model.TextSearchResponseType;
import collection.card.identy.api.service.OcrSearchHandler;
import collection.card.identy.api.service.TextSearchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class RestController {
    @Autowired
    private OcrSearchHandler ocrSearchHandler;
    @Autowired
    private TextSearchHandler textSearchHandler;

    @GetMapping("/")
    public String welcome(Map<String, Object> model) throws Exception {
        model.put("time", new Date());
        model.put("message", "welcome");
        return "welcome";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json")
    public String test(HttpServletRequest request) throws Exception {
        return "1111";
    }

    @ResponseBody
    @RequestMapping(value = "/ocrSearch", method = RequestMethod.POST, produces = "application/json")
    public OcrSearchResponseType ocrSearch(HttpServletRequest request) throws Exception {
        String requestJson = RequestHandler.getRequestPayload(request);
        return ocrSearchHandler.handler(JacksonUtil.parseFromJson(requestJson, OcrSearchRequestType.class));
    }

    @ResponseBody
    @RequestMapping(value = "/textSearch", method = RequestMethod.POST, produces = "application/json")
    public TextSearchResponseType textSearch(HttpServletRequest request) throws Exception {
        String requestJson = RequestHandler.getRequestPayload(request);
        return textSearchHandler.handler(JacksonUtil.parseFromJson(requestJson, TextSearchRequestType.class));
    }
}
