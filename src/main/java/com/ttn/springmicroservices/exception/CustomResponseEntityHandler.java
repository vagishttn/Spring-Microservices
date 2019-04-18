package com.ttn.springmicroservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomResponseEntityHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest webRequest) {
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setTimeStamp(LocalDateTime.now());
    exceptionResponse.setMessage(ex.getMessage());
    exceptionResponse.setDetails(webRequest.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(
      Exception ex, WebRequest webRequest) {
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setTimeStamp(LocalDateTime.now());
    exceptionResponse.setMessage(ex.getMessage());
    exceptionResponse.setDetails(webRequest.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
}
