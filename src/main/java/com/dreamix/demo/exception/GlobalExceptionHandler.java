package com.dreamix.demo.exception;

import com.dreamix.demo.model.ErrorContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseBody
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorContainer resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        log.info("ResourceNotFoundExceptionHandler:", ex);
        return ErrorContainer.builder()
                .code("NO_SUCH_ITEM")
                .message("Not found")
                .build();
    }


}
