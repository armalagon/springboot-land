package ni.jug.greeting.boundary;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/greeting")
public class GoodByeController {

    @GetMapping("/goodBye")
    public ModelAndView goodBye() {
        return new ModelAndView("forward:/greeting/veryFancyGoodBye");
    }

    @GetMapping(value = "veryFancyGoodBye", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String fancyGoodBye() {
        return "good bye my dear friend";
    }
}
