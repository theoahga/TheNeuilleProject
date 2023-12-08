package com.theoahga.exception;

public class GetRequestException extends Exception {
  private final String url;

  public GetRequestException(String url) {
    this.url = url;
  }

  @Override
  public String getMessage() {
    return "An error appeared during the following GET Request : " + url;
  }
}
