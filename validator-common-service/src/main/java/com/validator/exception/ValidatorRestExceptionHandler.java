package com.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.validator.dto.ValidationServiceResponse;

@ControllerAdvice
public class ValidatorRestExceptionHandler {
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> customException(ValidationException ex, WebRequest request) {
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.BAD_REQUEST)
				.httpStatusCode(HttpStatus.BAD_REQUEST.value()).errors(ex.getErrors()).build());
	}
	private ResponseEntity<Object> buildResponseEntity(ValidationServiceResponse response) {
		return new ResponseEntity<Object>(response, response.getHttpStatus());
	}
}
