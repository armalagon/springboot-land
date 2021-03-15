package ni.jug.demo.greeting.control;

import org.springframework.stereotype.Component;

@Component
public class EnglishGreeting implements Greeting {
    @Override
    public String hello() {
        return "Hello from SpringBoot!";
    }

    @Override
    public String language() {
        return "en";
    }
}
