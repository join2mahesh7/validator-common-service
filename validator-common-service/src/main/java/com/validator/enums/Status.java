package com.validator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author JayaLakshmi
 *
 */
@AllArgsConstructor
@Getter
public enum Status {
	SUCCESS("validation Successful"), FAIL("validation fail");

	private String value;
}
