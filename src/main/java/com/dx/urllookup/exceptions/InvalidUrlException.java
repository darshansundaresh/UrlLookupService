package com.dx.urllookup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid url passed")
public class InvalidUrlException extends RuntimeException{

}
