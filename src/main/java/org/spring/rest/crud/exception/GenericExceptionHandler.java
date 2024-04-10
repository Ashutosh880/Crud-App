package org.spring.rest.crud.exception;

import org.spring.rest.crud.wrapper.GenericResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GenericExceptionHandler {

  private Map<String, String> errorMessage;

  private GenericResponse errorResponse;

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public GenericResponse handleCustomException(GenericUnCheckedException exception, WebRequest request) {

    return GenericResponse.builder().statusMsg(exception.getMessage()).statusCode(HttpStatus.NOT_FOUND.value())
        .statusType(HttpStatus.NOT_FOUND.getReasonPhrase()).localDateTime(LocalDateTime.now())
        .description(request.getDescription(true)).build();
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {

    return exception.getBindingResult().getAllErrors().stream()
        .collect(Collectors.toMap(key -> ((FieldError) key).getField(),
            DefaultMessageSourceResolvable::getDefaultMessage));
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public GenericResponse handleAllTypeOfException(Exception exception, WebRequest request) {

    return GenericResponse.builder().statusMsg(exception.getMessage()).statusCode(HttpStatus.NOT_FOUND.value())
        .statusType(HttpStatus.NOT_FOUND.getReasonPhrase()).localDateTime(LocalDateTime.now())
        .description(request.getDescription(true)).build();
  }
}
