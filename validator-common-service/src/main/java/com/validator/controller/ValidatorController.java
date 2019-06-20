package com.validator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.validator.dto.ValidationServiceResponse;
import com.validator.dto.ValidationServiceRequest;
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
	 * 
	 * @param request, validate parameters
	 * @return response with message, http status, errors if any.  
	 */
	@PostMapping("validate")
	public ResponseEntity<ValidationServiceResponse> validate(@Valid @RequestBody ValidationServiceRequest request) {
		ValidationServiceResponse resp = validationService.validate(request);
		return new ResponseEntity<ValidationServiceResponse>(resp, resp.getHttpStatus());
	}
}
