package com.theoahga.exception;

public class PostRequestException extends Throwable {
  private final String url;
  public PostRequestException(String url) {
    this.url = url;
  }
  @Override
  public String getMessage() {
    return "An error appeared during the following POST Request : " + url;
  }

}
