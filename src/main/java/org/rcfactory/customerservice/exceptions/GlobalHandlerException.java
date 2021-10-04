package org.rcfactory.customerservice.exceptions;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.rcfactory.customerservice.exceptions.dto.ApiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalHandlerException {

    @ExceptionHandler
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ApiException handelException(CustomerServiceException exception) {
        log.error(exception.getMessage(), exception.getMessage());
        return new ApiException(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ApiException handelNotFoundException(CustomerNotFountException exception) {
        log.error(exception.getMessage(), exception.getMessage());
        return new ApiException(exception.getMessage());
    }
}
