package ni.jug.greeting.control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingProducer {
    @Bean
    public Greeting italianGreeting() {
        return new Greeting() {
            @Override
            public String hello() {
                return "Ciao SpringBoot!";
            }

            @Override
            public String language() {
                return "IT";
            }
        };
    }

    @Bean
    public Greeting frenchGreeting() {
        return new Greeting() {
            @Override
            public String hello() {
                return "Bonjour SpringBoot!";
            }

            @Override
            public String language() {
                return "FR";
            }
        };
    }
}
