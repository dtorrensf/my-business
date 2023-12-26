package com.business.backend.domain.exceptions;

public class NotFoundItemException extends MyBusinessException {

  public NotFoundItemException(ErrorItemEnum errorItem, String message) {
    this(
        ErrorTypeEnum.NOT_FOUND,
        errorItem,
        message
    );
  }

  private NotFoundItemException(ErrorTypeEnum errorType, ErrorItemEnum errorItem, String message) {
    super(errorType, errorItem, message);
  }
}
