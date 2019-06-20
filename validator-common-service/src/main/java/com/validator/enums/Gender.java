package com.validator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
 * @author JayaLakshmi.
 *
 */
@AllArgsConstructor
@Getter
public enum Gender {
	MALE("MALE","M"), FEMELE("FEMALE","F");
	private String name;
	private String nameType;

}
