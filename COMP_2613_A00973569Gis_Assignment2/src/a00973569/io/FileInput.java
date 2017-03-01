/**
 * Project: A00973569Gis
 * File: FileInput.java
 * Date: June 25, 2016
 * Time: 9:12:18 PM
 */

package a00973569.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * File input class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class FileInput {

	private static final Logger LOG = LogManager.getLogger(FileInput.class);

	/**
	 * Reads the file
	 * 
	 * @param fileName
	 *            the fileName to set
	 * @return the info
	 * @throws IOException
	 *             if failed I/O operatoin has occurred
	 */
	public static List<String> readFile(final String fileName) throws IOException {
		final List<String> info = new ArrayList<>();

		try {
			final BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			reader.readLine(); // Ignore first line of data file
			String line = null;
			while ((line = reader.readLine()) != null) {
				info.add(line);
			}
			reader.close();
		} catch (final FileNotFoundException e) {
			LOG.error("Could not read from " + fileName);
		}
		return info;
	}
}