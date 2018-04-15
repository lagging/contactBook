package com.ashish.ws.dtos.error;

import com.ashish.ws.commons.enums.ErrorCode;

public class ErrorResponseDto {

  private ErrorCode errorCode;
  private String message;

  public ErrorResponseDto(ErrorCode errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
  }
  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
