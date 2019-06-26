package com.validator.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author JayaLakshmi
 *
 */

@Setter
@Getter
public class ValidationServiceRequest {
	private String firstName;
	private String lastName;
	private String gender;
	private String confirmationNumber;
	private String address;
	private String city;
	private String webAddress;

}
