package com.business.backend.domain.exceptions;

import lombok.Getter;

@Getter
public class MyBusinessException extends RuntimeException {

  private String errorType;

  public MyBusinessException(String errorType, String message) {

    super(message);
    this.errorType = errorType;
  }
}
