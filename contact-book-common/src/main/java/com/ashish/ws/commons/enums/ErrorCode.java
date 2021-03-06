package com.ashish.ws.commons.enums;

public enum ErrorCode {

  INTERNAL_SERVER_ERROR,
  AUTHENTICATION_FAILED,
  AUTHORIZATION_FAILED,
  ENTITY_NOT_FOUND,
  PAGE_NOT_FOUND,
  METHOD_NOT_SUPPORTED,
  CONTENT_TYPE_NOT_SUPPORTED,
  PARAM_MISSING,
  PARAM_INVALID,
  REQUEST_NOT_READABLE,
  SERVICE_UNAVAILABLE,
  BAD_REQUEST,
  TOO_MANY_REQUESTS,
  ILLEGAL_ARGUMENTS,
  UNPROCESSABLE_ENTITY,
  OAUTH2_USER_CANCELLED,
  OAUTH2_EMAIL_MISMATCH,
  DUPLICATE_EMAIL,
  INVALID_PINCODE,
  DUPLICATE_MOBILE,
  REFERNCE_MOBILE_SAME_AS_USER,
  LENDER_EMPTY_LIST,
  LENDER_NOT_IN_POSSIBLE_LIST,
  LENDER_UPDATE_FAILURE,
  LENDER_UPDATE_DENIED_INVALID_APPLICATION_STATUS;

  @Override
  public String toString() {
    return this.name();
  }
}
