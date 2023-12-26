package com.business.backend.domain.exceptions;

public class DuplicatedItemException extends MyBusinessException {


  public DuplicatedItemException(ErrorItemEnum errorItem, String message) {
    this(
        ErrorTypeEnum.DUPLICATED,
        errorItem,
        message
    );
  }

  private DuplicatedItemException(ErrorTypeEnum errorType, ErrorItemEnum errorItem, String message) {
    super(errorType, errorItem, message);
  }
}
