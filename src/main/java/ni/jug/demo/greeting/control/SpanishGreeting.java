package ni.jug.demo.greeting.control;

import org.springframework.stereotype.Component;

@Component
public class SpanishGreeting implements Greeting {
    @Override
    public String hello() {
        return "Hola desde SpringBoot!";
    }

    @Override
    public String language() {
        return "es";
    }
}
