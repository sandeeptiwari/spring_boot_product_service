package co.in.techiesandeep.controller;

import co.in.techiesandeep.exceptions.HTTP400Exception;
import co.in.techiesandeep.exceptions.HTTP404Exception;
import co.in.techiesandeep.exceptions.RestAPIExceptionInfo;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements ApplicationEventPublisherAware {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    protected ApplicationEventPublisher eventPublisher;
    protected  static final String DEFAULT_PAGE_SIZE = "20";
    protected  static String DEFAULT_PAGE_SIZE_NUMBER = "0";

    Counter http400ExceptionCounter =
            Metrics.counter("co.in.techiesandeep.controller.ProductController.HTTP400");

    Counter http404ExceptionCounter =
            Metrics.counter("co.in.techiesandeep.controller.ProductController.HTTP404");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HTTP400Exception.class)
    public @ResponseBody RestAPIExceptionInfo handleBadRequestException(HTTP400Exception ex,
                                                                       WebRequest request,
                                                                       HttpServletResponse response) {
        logger.info("Received bad request exception "+ex.getLocalizedMessage());
        http400ExceptionCounter.increment();
        return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "Request didn't have correct parameters");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HTTP404Exception.class)
    public @ResponseBody RestAPIExceptionInfo handleResourceNotFoundException(HTTP404Exception ex,
                                                                              WebRequest request,
                                                                              HttpServletResponse response) {
        logger.info("Received resource not found exception "+ex.getLocalizedMessage());
        http404ExceptionCounter.increment();
        return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "Requested resource was not found");
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new HTTP404Exception("Resource Not Found");
        }
        return resource;
    }
}
