package com.theoahga.exception;

public class ZeroException extends Exception {
  private final String message;

  public ZeroException(String s) {
    this.message = s;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
