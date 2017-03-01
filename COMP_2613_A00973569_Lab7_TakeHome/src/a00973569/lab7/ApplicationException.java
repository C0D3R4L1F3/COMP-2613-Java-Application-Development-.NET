/**
 * Project: A00973569Lab7
 * File: ApplicationException.java
 * Date: June 5, 2015
 * Time: 7:00:38 PM
 */
package a00973569.lab7;

/**
 * A Class that throws exceptions when an invalid information is given
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 7.0
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	/**
	 * Default constructor
	 */
	public ApplicationException() {
	}

	/**
	 * @param message
	 *            displayed at exception
	 */
	public ApplicationException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 *            of exception
	 */
	public ApplicationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            displayed at exception
	 * @param cause
	 *            of exception
	 */
	public ApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 *            displayed at exception
	 * @param cause
	 *            cause of exception
	 * @param enableSuppression
	 *            for exception
	 * @param writableStackTrace
	 *            for exception
	 */
	public ApplicationException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}