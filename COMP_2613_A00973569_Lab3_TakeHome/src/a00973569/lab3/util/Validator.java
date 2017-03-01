/**
 * Project: A00973569Lab3
 * File: Validator.java
 * Date: May 9, 2016
 * Time: 9:26:32 PM
 */
package a00973569.lab3.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validate data.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 3.0
 */
public class Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

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
		final Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
