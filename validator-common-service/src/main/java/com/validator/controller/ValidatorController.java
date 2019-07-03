package com.validator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.validator.dto.ValidationServiceResponse;
import com.validator.dto.ValidatorSevicesRequest;
import com.validator.exception.ValidationException;
import com.validator.services.ValidationService;

/**
 * 
 * @author jayalakshmi
 *
 */
@RestController
public class ValidatorController {
	@Autowired
	ValidationService validationService;

	/**
	 * validate.
	 * @param request, validate parameters
	 * @return response with message, http status, errors if any.
	 * @throws ValidationException
	 */
	@PostMapping("validate")
	public ResponseEntity<ValidationServiceResponse> validate(@RequestBody ValidatorSevicesRequest request)
			throws ValidationException {
//		String request = httpEntity.getBody();
		ValidationServiceResponse resp = validationService.validate(request.getValue());
		return new ResponseEntity<ValidationServiceResponse>(resp, resp.getHttpStatus());
	}
}
