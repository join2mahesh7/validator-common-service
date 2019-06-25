package com.validator.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.validator.dto.ValidationServiceResponse;

@ControllerAdvice
public class ValidatorRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> list = new ArrayList<String>();
		String error = ex.getParameterName() + " parameter is missing";
		list.add(error);
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.BAD_REQUEST)
				.httpStatusCode(HttpStatus.BAD_REQUEST.value()).errors(list).build());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
		List<String> list = new ArrayList<String>();
		list.add(builder.substring(0, builder.length() - 2));
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.httpStatusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).errors(list).build());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> list = new ArrayList<String>();
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		errors.forEach(e -> list.add(e.getDefaultMessage()));
		ValidationServiceResponse response = ValidationServiceResponse.builder().httpStatus(HttpStatus.BAD_REQUEST)
				.httpStatusCode(HttpStatus.BAD_REQUEST.value()).errors(list).build();
		return buildResponseEntity(response);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> list = new ArrayList<String>();
		list.add("Malformed JSON request");
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.BAD_REQUEST)
				.httpStatusCode(HttpStatus.BAD_REQUEST.value()).errors(list).build());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> list = new ArrayList<String>();
		list.add("Error writing JSON output");
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).errors(list).build());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> list = new ArrayList<String>();
		list.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.BAD_REQUEST)
				.httpStatusCode(HttpStatus.BAD_REQUEST.value()).errors(list).build());
	}

	private ResponseEntity<Object> buildResponseEntity(ValidationServiceResponse response) {
		return new ResponseEntity<Object>(response, response.getHttpStatus());
	}
}
