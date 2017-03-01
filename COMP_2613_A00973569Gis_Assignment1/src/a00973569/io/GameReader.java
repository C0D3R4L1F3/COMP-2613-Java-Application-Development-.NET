/**
 * Project: A00973569Gis
 * File: GameReader.java
 * Date: May 24, 2016
 * Time: 11:19:17 AM
 */
package a00973569.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.ApplicationException;
import a00973569.data.Game;

/**
 * Reads game.dat file
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class GameReader {

	private static final int ELEMENT_ZERO = 0;
	private static final int ELEMENT_ONE = 1;
	private static final int ELEMENT_TWO = 2;
	private static final int INDEX_ONE = 1;
	public static final String FIELD_DELIMITER = "\\|";
	private static final Logger LOG = LogManager.getLogger(GameReader.class);

	/**
	 * private constructor to prevent instantiation
	 */
	private GameReader() {
		LOG.debug("GameReader()");
	}

	/**
	 * Reads the games.dat file
	 * 
	 * @param file
	 *            to read
	 * @return games
	 * @throws ApplicationException
	 *             if invalid information is given
	 */
	public static HashMap<String, Game> read(final File file) throws ApplicationException {
		String recordNumber;
		LOG.debug(file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (final FileNotFoundException e) {
			LOG.error("Input file does not exist: %s", file);
		}
		int i = INDEX_ONE;
		final HashMap<String, Game> games = new HashMap<String, Game>();
		String row = scanner.nextLine();// skip header
		try {
			while (scanner.hasNext()) {
				row = scanner.nextLine();
				LOG.debug(row);
				final String[] elements = row.split(FIELD_DELIMITER);
				recordNumber = "Record # " + (i++) + ". ";
				if (elements.length != Game.class.getDeclaredFields().length) {
					throw new ApplicationException(
							String.format(recordNumber + "Expected %d but got %d: %s", Game.class.getDeclaredFields().length, elements.length, Arrays.toString(elements)));
				}
				final Game game = new Game();
				game.setId(elements[ELEMENT_ZERO]);
				game.setName(elements[ELEMENT_ONE]);
				game.setProducer(elements[ELEMENT_TWO]);
				games.put(elements[ELEMENT_ZERO], game);
				LOG.debug(game);
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return games;
	}
}