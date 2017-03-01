/**
 * Project: A00973569Gis
 * File: StackTraceUtil.java
 * Date: May 27, 2016
 * Time: 2:59:38 PM
 */
package a00973569.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Throwable stack trace tracker
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
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
