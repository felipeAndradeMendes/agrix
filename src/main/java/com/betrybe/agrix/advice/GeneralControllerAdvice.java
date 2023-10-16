package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.FarmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleFarmNotFoundException (
      FarmNotFoundException exception
  ) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }
}
