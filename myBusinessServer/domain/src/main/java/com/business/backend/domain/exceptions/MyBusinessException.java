package com.business.backend.domain.exceptions;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class MyBusinessException extends RuntimeException {

  private final ErrorItemEnum errorItem;
  private final ErrorTypeEnum errorType;

  public MyBusinessException(ErrorTypeEnum errorType, ErrorItemEnum errorItem, String message) {
    super(message);
    this.errorType = errorType;
    this.errorItem = errorItem;
  }

  public String getErrorName() {
    return String.format("%s-%s", this.errorType, this.errorItem);
  }
}
