package ni.jug.greeting.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BeanViewer {

    private static final Logger LOG = LoggerFactory.getLogger(BeanViewer.class);

    @EventListener
    void showBeansRegistered(ApplicationReadyEvent event) {
        String[] beanNames = event.getApplicationContext().getBeanNamesForType(Greeting.class);

        for (String beanName : beanNames) {
            LOG.info("--> {}", beanName);
        }
    }
}
