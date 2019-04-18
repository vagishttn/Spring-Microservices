package com.ttn.springmicroservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

  private LocalDateTime timeStamp;
  private String message;
  private String details;

    public ExceptionResponse() {

    }
}
