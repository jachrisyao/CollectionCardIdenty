package collection.card.identy.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/mask")
public class MaskController {
    @GetMapping("/cardhobby")
    public String cardhobby(Map<String, Object> model, Long productId) throws Exception {
        return "cardhobby";
    }
}
