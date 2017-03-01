/**
 * Project: A00973569Lab2
 * File: Validator.java
 * Date: May 2, 2016
 * Time: 9:17:15 PM
 */
package a00973569.lab2.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator class validates that a correct email format is accepted
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Validator {

	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	/**
	 * Default constructor
	 */
	public Validator() {

		super();
	}

	/**
	 * Method validates if valid email is given
	 * 
	 * @param emailStr
	 *            email given to validate
	 * @return match found if valid email is given
	 */
	public static boolean validate(final String emailStr) {

		final Matcher matcher = EMAIL_PATTERN.matcher(emailStr);
		return matcher.find();
	}
}
