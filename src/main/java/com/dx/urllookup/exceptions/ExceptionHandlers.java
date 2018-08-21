package com.dx.urllookup.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlers {
	private Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
    public ErrorResponse handleUserNotFoundException(final Throwable ex) {
        log.error("API not found");
        return new ErrorResponse("API_NOT_FOUND", "The api you requested was not found");
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex) {
        log.error("Unexpected Error", ex);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occurred");
    }
}
