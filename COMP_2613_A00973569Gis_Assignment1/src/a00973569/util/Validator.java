/**
 * Project: A00973569Gis
 * File: Validator.java
 * Date: May 20, 2016
 * Time: 1:54:54 AM
 */
package a00973569.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the email, and ID's.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Pattern pattern;
	private static Matcher matcher;
	static final String INT_REGEX = "^\\d+$";

	/**
	 * 
	 */
	private Validator() {
	}

	/**
	 * Validate an email string.
	 * 
	 * @param email
	 *            the email string.
	 * @return true if the email address is valid, false otherwise.
	 */
	public static boolean validateEmail(final String email) {
		if (pattern == null) {
			pattern = Pattern.compile(EMAIL_PATTERN);
		}

		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Validates an ID integer
	 * 
	 * @param id
	 *            the id integer
	 * @return true if is integer otherwise false
	 */
	public static boolean validateId(final String id) {
		if (id.matches(INT_REGEX)) {
			return true;
		} else {
			return false;
		}
	}
}