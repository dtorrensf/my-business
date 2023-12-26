package com.business.backend.api.controllers;

import com.business.backend.api.generated.model.ErrorModel;
import com.business.backend.domain.exceptions.DuplicatedItemException;
import com.business.backend.domain.exceptions.NotFoundItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler({DuplicatedItemException.class})
  public ResponseEntity<ErrorModel> handleDuplicatedException(DuplicatedItemException ex, WebRequest request) {
    var error = new ErrorModel();
    error.setError(ex.getErrorName());
    error.setMessage(ex.getMessage());

    return ResponseEntity
        .badRequest()
        .body(error);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundItemException.class})
  public ResponseEntity<ErrorModel> handleNotFoundItemException(NotFoundItemException ex, WebRequest request) {
    var error = new ErrorModel();
    error.setError(ex.getErrorName());
    error.setMessage(ex.getMessage());

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error);
  }
}
