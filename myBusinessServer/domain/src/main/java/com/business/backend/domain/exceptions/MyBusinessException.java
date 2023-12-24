package com.business.backend.domain.exceptions;

import lombok.Getter;

@Getter
public class MyBusinessException extends Exception {

  public MyBusinessException(String message) {
    super(message);
  }
}
