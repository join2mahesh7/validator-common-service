package com.validator.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author JayaLakshmi
 *
 */

@Setter @Getter
public class ValidationServiceRequest {
	@NotBlank(message = "firstName not empty")
	@NotNull(message = "firstName not null")
	private String firstName;
	@NotBlank(message = "lastName not empty")
	@NotNull(message = "lastName not null")
	private String lastName;
	@NotBlank(message = "gender not empty")
	@NotNull(message = "gender not null")
	private String gender;
	@NotBlank(message = "gender not empty")
	@NotNull(message = "gender not null")
	private String confirmNumber;
	@NotBlank(message = "address not empty")
	@NotNull(message = "address not null")
	private String address;
	@NotBlank(message = "city not empty")
	@NotNull(message = "city not null")
	private String city;
	@NotBlank(message = "webAddress not empty")
	@NotNull(message = "webAddress not null")
	private String webAddress;	

}
