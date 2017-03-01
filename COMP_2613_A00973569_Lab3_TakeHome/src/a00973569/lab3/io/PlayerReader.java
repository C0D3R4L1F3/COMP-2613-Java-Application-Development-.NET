/**
 * Project: A00973569Lab3
 * File: PlayerReader.java
 * Date: May 9, 2016
 * Time: 9:24:52 PM
 */
package a00973569.lab3.io;

import java.util.GregorianCalendar;

import a00973569.lab3.data.Player;
import a00973569.lab3.data.exception.ApplicationException;
import a00973569.lab3.util.Validator;

/**
 * Read the player input.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 3.0
 */
public class PlayerReader {

	private static final int EXPECTED_NUMBER_OF_ELEMENTS = 5;
	private static final int INDEX_ZERO = 0;
	private static final int DATA_VALUE_FIVE = 5;
	private static final int SUBSTRING_VALUE_ZERO = 0;
	private static final int SUBSTRING_VALUE_FOUR = 4;
	private static final int SUBSTRING_VALUE_SIX = 6;
	private static final int SUBSTRING_VALUE_EIGHT = 8;
	private static final int FIRST_MONTH = 1;

	/**
	 * private constructor to prevent instantiation
	 */
	private PlayerReader() {
	}

	/**
	 * Read the player input data.
	 * 
	 * @param data
	 *            The input data.
	 * @return An array of players.
	 * @throws ApplicationException
	 *             if invalid email and number of elements are given
	 */
	public static Player[] read(final String data) throws ApplicationException {
		final String[] rows = data.split(":");
		final Player[] players = new Player[rows.length];
		final int numberOfElements = EXPECTED_NUMBER_OF_ELEMENTS;
		if (rows.length == numberOfElements) {
			int i = INDEX_ZERO;
			for (final String row : rows) {
				final String[] elements = row.split("\\|");
				final Player player = new Player();
				int index = INDEX_ZERO;
				player.setId(Integer.parseInt(elements[index++]));
				player.setFirstName(elements[index++]);
				player.setLastName(elements[index++]);
				final String email = elements[index++];
				if (!Validator.validateEmail(email)) {
					throw new ApplicationException("a00973569.ApplicationException: '" + email + "' is an invalid email address");
				}
				player.setEmailAddress(email);
				player.setGamerTag(elements[index++]);
				player.setBirthDate(new GregorianCalendar(Integer.parseInt(elements[DATA_VALUE_FIVE].substring(SUBSTRING_VALUE_ZERO, SUBSTRING_VALUE_FOUR)),
						Integer.parseInt(elements[DATA_VALUE_FIVE].substring(SUBSTRING_VALUE_FOUR, SUBSTRING_VALUE_SIX)) - FIRST_MONTH,
						Integer.parseInt(elements[DATA_VALUE_FIVE].substring(SUBSTRING_VALUE_SIX, SUBSTRING_VALUE_EIGHT))));
				players[i++] = player;
			}
			return players;
		} else {
			throw new ApplicationException("Expected " + numberOfElements + " elements but got " + rows.length);
		}
	}
}
