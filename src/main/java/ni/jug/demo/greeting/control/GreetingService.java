package ni.jug.demo.greeting.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GreetingService {
    private static final Logger LOG = LoggerFactory.getLogger(GreetingService.class);

    Map<String, Greeting> greetingsByLang;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public GreetingService(List<Greeting> greetings) {
        greetingsByLang = greetings.stream()
                .collect(
                        Collectors.toMap(Greeting::language, greeting -> greeting));
    }

    public String sayHello(String language) {
        LOG.info("--> Language: {}", language);

        eventPublisher.publishEvent(new BeforeGreetingEvent(language));

        if ("*".equals(language)) {
            return sayHelloInAllSupportedLanguages();
        } else {
            return sayHelloFor(language);
        }
    }

    private String sayHelloInAllSupportedLanguages() {
        List<String> responses = new ArrayList<>();
        for (Greeting greeting : greetingsByLang.values()) {
            responses.add(greeting.hello());
        }
        return responses.stream().collect(Collectors.joining("\n"));
    }

    private String sayHelloFor(String language) {
        Greeting greeting = greetingsByLang.get(language);

        if (greeting == null) {
            throw GreetingException.createNotFound(language);
        } else {
            return greeting.hello();
        }
    }
}
