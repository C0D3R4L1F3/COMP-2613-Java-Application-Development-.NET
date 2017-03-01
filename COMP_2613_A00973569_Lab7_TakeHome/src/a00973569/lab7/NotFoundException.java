/**
 * Project: A00973569Lab7
 * File: NotFoundException.java
 * Date: June 5, 2016
 * Time: 8:26:42 PM
 */
package a00973569.lab7;

/**
 * A Class that throws exceptions when an object is not found
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 7.0
 */
@SuppressWarnings("serial")
public class NotFoundException extends Exception {

	/**
	 * Default constructor
	 */
	public NotFoundException() {
	}

	/**
	 * @param message
	 *            displayed at exception
	 */
	public NotFoundException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 *            of exception
	 */
	public NotFoundException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            displayed at exception
	 * @param cause
	 *            of exception
	 */
	public NotFoundException(final String message, final Throwable cause) {
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
	public NotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}