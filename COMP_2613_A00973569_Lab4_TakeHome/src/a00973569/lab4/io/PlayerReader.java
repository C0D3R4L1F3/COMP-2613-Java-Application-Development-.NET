/**
 * Project: A00973569Lab4
 * File: PlayerReader.java
 * Date: May 17, 2016
 * Time: 9:43:18 PM
 */
package a00973569.lab4.io;

import java.util.ArrayList;
import java.util.Arrays;

import a00973569.lab4.data.Player;
import a00973569.lab4.data.exception.ApplicationException;
import a00973569.lab4.util.Validator;

/**
 * Read the player input.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 4.0
 */
public class PlayerReader {

	private static final int EXPECTED_NUMBER_OF_ELEMENTS = 5;
	public static final String RECORD_DELIMITER = ":";
	public static final String FIELD_DELIMITER = "\\|";
	private static final int INDEX_ZERO = 0;
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
	 * @return An ArrayLIst of players.
	 * @throws ApplicationException
	 *             if invalid email and number of elements are given
	 */
	public static ArrayList<Player> read(final String data) throws ApplicationException {
		final ArrayList<String> rows = new ArrayList<>(Arrays.asList(data.split(RECORD_DELIMITER)));
		final ArrayList<Player> players = new ArrayList<>();
		final int numberOfElements = EXPECTED_NUMBER_OF_ELEMENTS;
		if (rows.size() == numberOfElements) {
			for (final String row : rows) {
				final ArrayList<String> elements = new ArrayList<>(Arrays.asList(row.split(FIELD_DELIMITER)));
				if (elements.size() != Player.ATTRIBUTE_COUNT) {
					throw new ApplicationException(String.format("Expected %d attributes but got %d: %s", Player.ATTRIBUTE_COUNT, elements.size(), elements));
				}
				final Player player = new Player();
				int index = INDEX_ZERO;
				player.setId(Integer.parseInt(elements.get(index++)));
				player.setFirstName(elements.get(index++));
				player.setLastName(elements.get(index++));
				final String email = elements.get(index++);
				if (!Validator.validateEmail(email)) {
					throw new ApplicationException(String.format("Invalid email: %s", email));
				}
				player.setEmailAddress(email);
				player.setGamerTag(elements.get(index++));
				final String yyyymmdd = elements.get(index++);
				try {
					player.setBirthDate(Integer.parseInt(yyyymmdd.substring(SUBSTRING_VALUE_ZERO, SUBSTRING_VALUE_FOUR)),
							Integer.parseInt(yyyymmdd.substring(SUBSTRING_VALUE_FOUR, SUBSTRING_VALUE_SIX)) - FIRST_MONTH,
							Integer.parseInt(yyyymmdd.substring(SUBSTRING_VALUE_SIX, SUBSTRING_VALUE_EIGHT)));
				} catch (final NumberFormatException e) {
					System.out.println("Invalid date element:" + yyyymmdd);
				}
				players.add(player);
			}
			return players;
		} else {
			throw new ApplicationException(String.format("Expected %d elements but got %d", numberOfElements, rows.size()));
		}
	}
}