package co.in.techiesandeep.aspects;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestControllerAspect {
    Logger logger = LoggerFactory.getLogger(RestControllerAspect.class.getName());

    Counter productCreatedCounter = Metrics.counter("co.in.techiesandeep.model.product.created");

    @Before("execution(public * co.in.techiesandeep.controller.*Controller.*(..))")
    public void generalAllMethodAspect() {
      logger.info("All method calls invok this general aspect methos");
    }

    @AfterReturning("execution(public * co.in.techiesandeep.controller.*Controller.createProduct(..))")
    public void getCalledOnProductSave() {
        logger.info("Invok on controller's  save method get called");
        productCreatedCounter.increment();
    }
}
