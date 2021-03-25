package ni.jug.greeting.boundary;

import ni.jug.greeting.control.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingResource {

    @Autowired
    GreetingService greetingService;

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello(@RequestHeader(value = "Accept-Language", defaultValue = "*") String lang) {
        return greetingService.sayHello(lang);
    }
}
