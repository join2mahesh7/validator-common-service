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
	private final static String CITY_PATTERN = "(?:[A-Z][a-z.-]+[ ]?)+";
	private final static String ADDRESS_PATTERN = "(?:[A-Z][a-z.-]+[ ]?)+";
	private final static String WEB_ADDRESS_PATTERN = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	/**
	 * nameValidator.
	 * @param string as request parameter
	 * @return name validation message.
	 */
	public static String nameValidator(String name) {
		String value = "";
		Pattern pattern = Pattern.compile(STR_PATTERN);

		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			value = "name should contain only characters";
		}
		return value;
	}
	/**
	 * genderValidator.
	 * @param string as request parameter
	 * @return gender validation message.
	 */
	public static String genderValidator(String gender) {
		String value = "";
		List<String> genderList = new ArrayList<>();
		Gender[] genderValue = Gender.values();
		for (Gender genderVa : genderValue) {
			genderList.add(genderVa.getName());
			genderList.add(genderVa.getNameType());
		}
		String uppGender = gender.toUpperCase();
		if (!StringUtils.isEmpty(gender) && !genderList.contains(uppGender)) {
			value = "gender should contains only male or female";
		}
		return value;
	}

	/**
	 * numValidator.
	 * @param string as request parameter
	 * @return number validation message.
	 */
	public static String numValidator(String num) {
		String value = "";
		Pattern pattern = Pattern.compile(NUM_PATTERN);
		Matcher matcher = pattern.matcher(num);
		if (!matcher.matches()) {
			value = "confirmNumber should contain only digits";
		}
		return value;
	}

	/**
	 * cityValidator.
	 * @param string as request parameter
	 * @return city validation message.
	 */
	public static String cityValidator(String city) {
		String value = "";
		Pattern pattern = Pattern.compile(CITY_PATTERN);
		Matcher matcher = pattern.matcher(city);
		if (matcher.matches()) {
			value = "enter valid city";
		}
		return value;
	}

	/**
	 * addressValidator.
	 * @param string as request parameter
	 * @return city validation message.
	 */
	public static String addressValidator(String add) {
		String value = "";
		Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(add);
		if (matcher.matches()) {
			value = "enter valid address";
		}
		return value;
	}

	/**
	 * webAddressValidator.
	 * @param string as request parameter
	 * @return web address validation message.
	 */
	public static String webAddressValidator(String num) {
		String value = "";
		Pattern pattern = Pattern.compile(WEB_ADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(num);
		if (!matcher.matches()) {
			value = "enter valid web url";
		}
		return value;
	}

}
