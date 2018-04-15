package com.ashish.ws.api.controllers;

import com.ashish.ws.commons.enums.ErrorCode;
import com.ashish.ws.commons.exceptions.DuplicateException;
import com.ashish.ws.commons.exceptions.ResourceNotFoundException;
import com.ashish.ws.dtos.error.ErrorResponseDto;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(
      EntityNotFoundException ex) {
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.ENTITY_NOT_FOUND
        , ex.getMessage());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(
      ResourceNotFoundException ex) {
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.ENTITY_NOT_FOUND,
        ex.getMessage());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateException.class)
  public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(DuplicateException ex) {
    ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.BAD_REQUEST,
        ex.getMessage());
    return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
  }

}
