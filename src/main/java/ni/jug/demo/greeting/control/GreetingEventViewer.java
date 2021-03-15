package ni.jug.demo.greeting.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GreetingEventViewer {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingEventViewer.class);

    @EventListener
    public void onBeforeGreeting(BeforeGreetingEvent event) {
        LOG.info("--> Before greeting event: {}", event.getLanguage());
    }
}
