/**
 * Project: A00973569Lab9
 * File: StackTraceUtil.java
 * Date: June 6, 2016
 * Time: 12:50:16 AM
 */
package a00973569.lab9.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Throwable stack trace tracker
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 9.0
 */
public class StackTraceUtil {

	/**
	 * Gets the throwable stack trace of exceptions
	 * 
	 * @param throwable
	 *            to message throw
	 * @return message thrown
	 */
	public static String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}
}