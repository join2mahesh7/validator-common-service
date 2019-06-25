package com.validator.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.validator.dto.ValidationServiceRequest;
import com.validator.dto.ValidationServiceResponse;
import com.validator.util.ValidationUtil;

/**
 * 
 * @author jayalakshmi
 *
 */
@Service
public class ValidationService {

	/**
	 * validate.
	 * @param validateData request data
	 * @return ValidationServiceResponse response
	 */
	public ValidationServiceResponse validate(ValidationServiceRequest validateData) {
		List<String> messages = new ArrayList<>();
		ValidationServiceResponse response = null;
		try {
		messages.add(ValidationUtil.nameValidator(validateData.getFirstName()));
		messages.add(ValidationUtil.nameValidator(validateData.getLastName()));
		messages.add(ValidationUtil.numValidator(validateData.getConfirmNumber()));
		messages.add(ValidationUtil.genderValidator(validateData.getGender()));
		messages.add(ValidationUtil.cityValidator(validateData.getCity()));
		messages.add(ValidationUtil.addressValidator(validateData.getAddress()));
		messages.add(ValidationUtil.webAddressValidator(validateData.getWebAddress()));
		messages.removeAll(Collections.singleton(null));
		messages.removeAll(Collections.singleton(""));
		if (messages.size() > 0) {
			response = ValidationServiceResponse
					.builder()
					.httpStatus(HttpStatus.BAD_REQUEST)
					.httpStatusCode(HttpStatus.BAD_REQUEST.value())
					.errors(messages)
					.build();
		} else {
			response = ValidationServiceResponse
					.builder()
					.httpStatus(HttpStatus.OK)
					.httpStatusCode(HttpStatus.OK.value())
					.errors(messages)
					.build();
		}
		} catch (Exception e) {
			response = ValidationServiceResponse
					.builder()
					.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.errors(messages)
					.build();
		}
		return response;
	}

}
