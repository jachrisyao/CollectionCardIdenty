package collection.card.identy.api.controller;

import collection.card.identy.api.handler.RequestHandler;
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
    @GetMapping("/")
    public String welcome(Map<String, Object> model) throws Exception {
        model.put("time", new Date());
        model.put("message", "welcome");
        return "welcome";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json")
    public String test(HttpServletRequest request) {
        String requestJson = RequestHandler.getRequestPayload(request);
        return "1111";
    }
}
