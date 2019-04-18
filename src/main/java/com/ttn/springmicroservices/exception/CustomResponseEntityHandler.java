package com.ttn.springmicroservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

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

  @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
  public final ResponseEntity<Object> handleUserNotFoundException(
      Exception ex, WebRequest webRequest) {
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setTimeStamp(LocalDateTime.now());
    exceptionResponse.setMessage(ex.getMessage());
    exceptionResponse.setDetails(webRequest.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setTimeStamp(LocalDateTime.now());
    exceptionResponse.setMessage("Validation failed");
    String errorList =
        ex.getBindingResult().getAllErrors().stream()
            .filter(e -> e instanceof FieldError)
            .map(
                e -> {
                  FieldError fieldError = (FieldError) e;
                  return fieldError.getField() + " --> " + fieldError.getDefaultMessage() + "\n";
                })
            .collect(Collectors.joining(System.lineSeparator()));
    exceptionResponse.setDetails(errorList);

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
