package com.vass.inditex.domain.exception;

import java.io.Serial;

public class NotFoundPriceException extends PriceException {

  @Serial private static final long serialVersionUID = 1L;

  public NotFoundPriceException(String message) {

    super(404, message);
  }
}
