package co.in.techiesandeep.listeners;

import co.in.techiesandeep.events.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {
    Logger logger = LoggerFactory.getLogger("ProductEventListener");

    @EventListener
    public void onApplicationEvent(ProductEvent productEvent) {
        logger.info("Received product event "+productEvent.getEventType());
        logger.info("Received product from product event "+productEvent.getProduct().toString());
    }
}
