package com.vass.inditex.domain.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public abstract class PriceException extends Exception {
  @Serial private static final long serialVersionUID = 1L;
  private final int code;

  protected PriceException(int code, String message) {
    super(message);
    this.code = code;
  }
}
