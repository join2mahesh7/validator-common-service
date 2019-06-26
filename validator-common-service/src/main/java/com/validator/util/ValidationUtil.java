package com.validator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.validator.enums.Gender;

/**
 * 
 * @author JavaLakshmi
 *
 */
public class ValidationUtil {
	private final static String STR_PATTERN = "^[A-Za-z,]++$";
	private final static String NUM_PATTERN = "^[1-9]\\d*$";
	private final static String CITY_PATTERN = "^[A-Za-z,]++$";
	private final static String ADDRESS_PATTERN = "(?:[A-Z][a-z.-]+[ ]?)+";
	private final static String WEB_ADDRESS_PATTERN = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	/**
	 * nameValidator.
	 * 
	 * @param string as request parameter
	 * @return name validation message.
	 */
	public static String nameValidator(String name, String fieldName) {
		String message = emptyNullCheck(name, fieldName);
		if (StringUtils.isEmpty(message)) {
			Pattern pattern = Pattern.compile(STR_PATTERN);
			Matcher matcher = pattern.matcher(name);
			if (!matcher.matches()) {
				message = fieldName + " should contain only characters";
			}
		}
		return message;

	}

	/**
	 * genderValidator.
	 * 
	 * @param string as request parameter
	 * @return gender validation message.
	 */
	public static String genderValidator(String gender, String fieldName) {
		String message = emptyNullCheck(gender, fieldName);
		if (StringUtils.isEmpty(message)) {
			List<String> genderList = new ArrayList<>();
			Gender[] genderValue = Gender.values();
			for (Gender genderVa : genderValue) {
				genderList.add(genderVa.getName());
				genderList.add(genderVa.getNameType());
			}
			String uppGender = gender.toUpperCase();
			if (!StringUtils.isEmpty(gender) && !genderList.contains(uppGender)) {
				message = "gender should contains only male or female";
			}
		}
		return message;
	}

	/**
	 * numValidator.
	 * 
	 * @param string as request parameter
	 * @return number validation message.
	 */
	public static String numValidator(String num, String fieldName) {
		String message = emptyNullCheck(num, fieldName);
		if (StringUtils.isEmpty(message)) {
			Pattern pattern = Pattern.compile(NUM_PATTERN);
			Matcher matcher = pattern.matcher(num);
			if (!matcher.matches()) {
				message = fieldName + " should contain only digits";
			}
		}
		return message;
	}

	/**
	 * cityValidator.
	 * 
	 * @param string as request parameter
	 * @return city validation message.
	 */
	public static String cityValidator(String city, String fieldName) {
		String message = emptyNullCheck(city, fieldName);
		if (StringUtils.isEmpty(message)) {
			Pattern pattern = Pattern.compile(CITY_PATTERN);
			Matcher matcher = pattern.matcher(city);
			if (!matcher.matches()) {
				message = "enter valid " + fieldName;
			}
		}
		return message;
	}

	/**
	 * addressValidator.
	 * 
	 * @param string as request parameter
	 * @return city validation message.
	 */
	public static String addressValidator(String address, String fieldName) {
		String message = emptyNullCheck(address, fieldName);
		if (StringUtils.isEmpty(message)) {
			Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
			Matcher matcher = pattern.matcher(address);
			if (matcher.matches()) {
				message = "enter valid " + fieldName;
			}
		}
		return message;
	}

	/**
	 * webAddressValidator.
	 * 
	 * @param string as request parameter
	 * @return web address validation message.
	 */
	public static String webAddressValidator(String webAddress, String fieldName) {
		String message = emptyNullCheck(webAddress, fieldName);
		if (StringUtils.isEmpty(message)) {
			Pattern pattern = Pattern.compile(WEB_ADDRESS_PATTERN);
			Matcher matcher = pattern.matcher(webAddress);
			if (!matcher.matches()) {
				message = "enter valid " + fieldName;
			}
		}
		return message;
	}

	/**
	 * isNotBlank.
	 * 
	 * @param field
	 * @return String , message
	 */
	private static String emptyNullCheck(String field, String fieldName) {
		String msg = "";
		if (field == null) {
			msg = fieldName + " should not null";
		} else if (field.trim().length() <= 0) {
			msg = fieldName + " should not empty";
		}
		return msg;
	}
}
