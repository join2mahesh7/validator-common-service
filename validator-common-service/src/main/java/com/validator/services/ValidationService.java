package com.validator.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.validator.constants.GlobalConstants;
import com.validator.dto.Data;
import com.validator.dto.Eshcheadertrans;
import com.validator.dto.ValidationServiceResponse;
import com.validator.dto.ValidatorRequest;
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
	public ValidationServiceResponse validate(String requestData) throws ValidationException {
		ValidationServiceResponse response = null;
		ValidatorRequest validateRequest = null;
		List<String> eshHeadersList = new ArrayList<>();
		try {
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			validateRequest = mapper.readValue(requestData, ValidatorRequest.class);
			Data data = validateRequest.get_data();
			List<Eshcheadertrans> eshHeaders = data.getEshc_header_trans();
			eshHeaders.forEach(eshcHeaders -> {
				List<String> list = new ArrayList<>();
				list.add(ValidationUtil.nameValidator(eshcHeaders.getFirstname(), GlobalConstants.FIRST_NAME));
				list.add(ValidationUtil.nameValidator(eshcHeaders.getLastname(), GlobalConstants.LAST_NAME));
				list.add(ValidationUtil.numValidator(eshcHeaders.getConfirmation_number(),
						GlobalConstants.CONFIRMATION_NUMBER));
				list.add(ValidationUtil.genderValidator(eshcHeaders.getGender(), GlobalConstants.GENDER));
				list.add(ValidationUtil.cityValidator(eshcHeaders.getCity(), GlobalConstants.CITY));
				list.add(ValidationUtil.addressValidator(eshcHeaders.getAddressline1(), GlobalConstants.ADDRESS));
				list.add(ValidationUtil.webAddressValidator(eshcHeaders.getWeb_address(), GlobalConstants.WEB_ADDRESS));
				list.removeAll(Collections.singleton(null));
				list.removeAll(Collections.singleton(""));
				eshHeadersList.addAll(list);
			});
			if (eshHeadersList.size() > 0) {
				throw new ValidationException("validation error", eshHeadersList);
			} else {
				response = ValidationServiceResponse.builder().httpStatus(HttpStatus.OK)
						.httpStatusCode(HttpStatus.OK.value()).errors(eshHeadersList).build();
			}
		} catch (JsonParseException e) {
			List<String> list = new ArrayList<>();
			list.add("json parser error");
			list.add(e.getMessage());
			throw new ValidationException("json parser error", list);
		} catch (JsonMappingException e) {
			List<String> list = new ArrayList<>();
			list.add("json parser mapping error\"");
			list.add(e.getMessage());
			throw new ValidationException("json parser mapping error", list);
		} catch (IOException e) {
			List<String> list = new ArrayList<>();
			list.add("json request error");
			list.add(e.getMessage());
			throw new ValidationException("json request error", list);
		}
		return response;
	}

}
