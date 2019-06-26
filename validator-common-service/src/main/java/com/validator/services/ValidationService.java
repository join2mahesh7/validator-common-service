package com.validator.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.validator.constants.GlobalConstants;
import com.validator.dto.ValidationServiceRequest;
import com.validator.dto.ValidationServiceResponse;
import com.validator.exception.ValidationException;
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
	 * 
	 * @param validateData request data
	 * @return ValidationServiceResponse response
	 * @throws ValidationException
	 */
	public ValidationServiceResponse validate(ValidationServiceRequest validateData) throws ValidationException {
		List<String> messages = new ArrayList<>();
		ValidationServiceResponse response = null;
		messages.add(ValidationUtil.nameValidator(validateData.getFirstName(), GlobalConstants.FIRST_NAME));
		messages.add(ValidationUtil.nameValidator(validateData.getLastName(), GlobalConstants.LAST_NAME));
		messages.add(
				ValidationUtil.numValidator(validateData.getConfirmationNumber(), GlobalConstants.CONFIRMATION_NUMBER));
		messages.add(ValidationUtil.genderValidator(validateData.getGender(), GlobalConstants.GENDER));
		messages.add(ValidationUtil.cityValidator(validateData.getCity(), GlobalConstants.CITY));
		messages.add(ValidationUtil.addressValidator(validateData.getAddress(), GlobalConstants.ADDRESS));
		messages.add(ValidationUtil.webAddressValidator(validateData.getWebAddress(), GlobalConstants.WEB_ADDRESS));
		messages.removeAll(Collections.singleton(null));
		messages.removeAll(Collections.singleton(""));
		if (messages.size() > 0) {
			throw new ValidationException("validation error", messages);
		} else {
			response = ValidationServiceResponse.builder().httpStatus(HttpStatus.OK)
					.httpStatusCode(HttpStatus.OK.value()).errors(messages).build();
		}
		return response;
	}

}
