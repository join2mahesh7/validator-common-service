package com.validator.exception;

import java.util.List;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	private List<String> errors;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String msg, List<String> errors) {
		super(msg);
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

}
