/**
 * Project: A00973569Gis
 * File: PlayerReader.java
 * Date: May 24, 2016
 * Time: 1:53:38 AM
 */
package a00973569.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.ApplicationException;
import a00973569.data.Player;
import a00973569.util.Validator;

/**
 * Reads players.dat file
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class PlayerReader {

	private static final int ELEMENT_ZERO = 0;
	private static final int ELEMENT_ONE = 1;
	private static final int ELEMENT_TWO = 2;
	private static final int ELEMENT_THREE = 3;
	private static final int ELEMENT_FOUR = 4;
	private static final int INDEX_ONE = 1;
	public static final String FIELD_DELIMITER = "\\|";
	private static final Logger LOG = LogManager.getLogger(PlayerReader.class);

	/**
	 * private constructor to prevent instantiation
	 */
	private PlayerReader() {
		LOG.debug("PlayerReader()");
	}

	/**
	 * Reads the players.dat file
	 * 
	 * @param file
	 *            to read
	 * @return players
	 * @throws ApplicationException
	 *             if invalid information given
	 */
	public static HashMap<Integer, Player> read(final File file) throws ApplicationException {
		String recordNumber;
		LocalDate date;
		LOG.debug(file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (final FileNotFoundException e) {
			LOG.error("Input file does not exist: %s", file);
		}
		int i = INDEX_ONE;
		final HashMap<Integer, Player> players = new HashMap<Integer, Player>();
		String row = scanner.nextLine();// skip header
		try {
			while (scanner.hasNext()) {
				row = scanner.nextLine();
				LOG.debug(row);
				final String[] elements = row.split(FIELD_DELIMITER);
				recordNumber = "Record # " + (i++) + ". ";
				if (elements.length != Player.class.getDeclaredFields().length) {
					throw new ApplicationException(
							String.format(recordNumber + "Expected %d but got %d: %s", Player.class.getDeclaredFields().length, elements.length, Arrays.toString(elements)));
				}
				final Player player = new Player();
				if (!Validator.validateEmail(elements[ELEMENT_THREE])) {
					throw new ApplicationException(recordNumber + "'" + elements[ELEMENT_THREE] + "' is an invalid email address");
				}
				if (!Validator.validateId(elements[ELEMENT_ZERO])) {
					throw new ApplicationException(recordNumber + "Id in the list of players must be integer");
				}
				try {
					date = LocalDate.parse(elements[ELEMENT_FOUR], DateTimeFormatter.ofPattern("yyyyMMdd"));
				} catch (final DateTimeParseException e) {
					throw new ApplicationException(recordNumber + elements[ELEMENT_FOUR] + " - wrong date format. must be yyyyMMdd");
				}
				player.setId(Integer.parseInt(elements[ELEMENT_ZERO]));
				player.setFirstName(elements[ELEMENT_ONE]);
				player.setLastName(elements[ELEMENT_TWO]);
				final String email = elements[ELEMENT_THREE];
				player.setEmail(email);
				player.setDateOfBirth(date);
				players.put(Integer.parseInt(elements[ELEMENT_ZERO]), player);
				LOG.debug(player);
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return players;
	}
}