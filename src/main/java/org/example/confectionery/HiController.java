package org.example.confectionery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HiController {
    @GetMapping("/Hi")
    public String Hi(
            @RequestParam(name="name", required=false,
                    defaultValue="World") String namee, Map<String, Object> model) {
        model.put("name", namee);
        return "Hi";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("some", "Hi, my dear friend");
        return "main";
    }

}
