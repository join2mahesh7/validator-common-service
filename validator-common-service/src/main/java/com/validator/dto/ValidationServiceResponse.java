package com.validator.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 * @author JayaLakshmi
 *
 */
@Getter
@Builder
public class ValidationServiceResponse {
	private HttpStatus httpStatus;
	private int httpStatusCode;
	private List<String> errors;
}

