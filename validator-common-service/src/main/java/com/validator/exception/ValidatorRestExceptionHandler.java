package com.validator.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.validator.dto.ValidationServiceResponse;

public class ValidatorRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).status(error)
				.debugMessage(ex.getLocalizedMessage()).build());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.status(builder.substring(0, builder.length() - 2)).debugMessage(ex.getLocalizedMessage()).build());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ValidationServiceResponse response = ValidationServiceResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).status("Validation error")
				.build();
		response.addValidationErrors(ex.getBindingResult().getFieldErrors());
		response.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(response);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.BAD_REQUEST)
				.status("Malformed JSON request").debugMessage(ex.getLocalizedMessage()).build());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildResponseEntity(ValidationServiceResponse.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.status("Error writing JSON output").debugMessage(ex.getLocalizedMessage()).build());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return buildResponseEntity(ValidationServiceResponse
				.builder().httpStatus(HttpStatus.BAD_REQUEST).status(String
						.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()))
				.build());
	}

	private ResponseEntity<Object> buildResponseEntity(ValidationServiceResponse response) {
		return new ResponseEntity<Object>(response, response.getHttpStatus());
	}
}
