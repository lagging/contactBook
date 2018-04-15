package com.ashish.ws.commons.exceptions;

import com.ashish.ws.commons.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateException extends RuntimeException {

  private String resourceName;
  private String fieldName;
  private Object fieldValue;

  public DuplicateException(String resourceName, String fieldName, Object fieldValue) {
    super(String.format("%s  found with %s : '%s'", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  public String getResourceName() {
    return resourceName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public Object getFieldValue() {
    return fieldValue;
  }

}
