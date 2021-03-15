package ni.jug.demo.greeting.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
public class InfoController {
    private static final String[] PROPERTIES = {
            "spring.datasource.url", "spring.datasource.driverClassName", "spring.datasource.username", "spring.datasource.password",
            "spring.jpa.hibernate.ddl-auto"
    };

    @Autowired
    Environment environment;

    @GetMapping
    public Map<String, String> info() {
        Map<String, String> info = new LinkedHashMap<>();
        for (String property : PROPERTIES) {
            info.put(property, environment.getProperty(property));
        }
        return info;
    }
}
