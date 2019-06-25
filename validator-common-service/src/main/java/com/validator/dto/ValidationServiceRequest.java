package com.validator.dto;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author JayaLakshmi
 *
 */

@Setter @Getter
public class ValidationServiceRequest {
	@NotBlank(message = "firstName should not empty and should not null")
	private String firstName;
	@NotBlank(message = "lastName should not empty and should not null")
	private String lastName;
	@NotBlank(message = "gender should not empty and should not null")
	private String gender;
	@NotBlank(message = "gender should not empty and should not null")
	private String confirmNumber;
	@NotBlank(message = "address should not empty and should not null")
	private String address;
	@NotBlank(message = "city should not empty and should not null")
	private String city;
	@NotBlank(message = "webAddress should not empty and should not null")
	private String webAddress;	

}
