/**
 * Project: A00973569Gis
 * File: ScoreReader.java
 * Date: May 24, 2016
 * Time: 11:19:52 AM
 */
package a00973569.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.ApplicationException;
import a00973569.data.Score;
import a00973569.util.Validator;

/**
 * Reads scores.dat file
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class ScoreReader {

	private static final int ELEMENT_ZERO = 0;
	private static final int ELEMENT_ONE = 1;
	private static final int ELEMENT_TWO = 2;
	private static final int INDEX_ONE = 1;
	public static final String FIELD_DELIMITER = "\\|";
	private static final Logger LOG = LogManager.getLogger(Score.class);

	/**
	 * private constructor to prevent instantiation
	 */
	private ScoreReader() {
		LOG.debug("Score()");
	}

	/**
	 * Reads the scores.dat file
	 * 
	 * @param file
	 *            to read
	 * @return scores
	 * @throws ApplicationException
	 *             if invalid information given
	 */
	public static ArrayList<Score> read(final File file) throws ApplicationException {
		String recordNumber;
		LOG.debug(file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (final FileNotFoundException e) {
			LOG.error("Input file does not exist: %s", file);
		}
		int i = INDEX_ONE;
		final ArrayList<Score> scores = new ArrayList<Score>();
		String row = scanner.nextLine();// skip header
		try {
			while (scanner.hasNext()) {
				row = scanner.nextLine();
				LOG.debug(row);
				final String[] elements = row.split(FIELD_DELIMITER);
				recordNumber = "Record # " + (i++) + ". ";
				if (elements.length != Score.class.getDeclaredFields().length) {
					throw new ApplicationException(
							String.format(recordNumber + "Expected %d but got %d: %s", Score.class.getDeclaredFields().length, elements.length, Arrays.toString(elements)));
				}
				if (!Validator.validateId(elements[ELEMENT_ZERO])) {
					throw new ApplicationException(recordNumber + "Persona Id in the list of scores must be integer");
				}
				final Score score = new Score();
				score.setPersonaId(Integer.parseInt(elements[ELEMENT_ZERO]));
				score.setGameId(elements[ELEMENT_ONE]);
				score.setResult(elements[ELEMENT_TWO]);
				scores.add(score);
				LOG.debug(score);
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return scores;
	}
}