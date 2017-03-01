/**
 * Project: A00973569Lab5
 * File: PlayerReader.java
 * Date: May 24, 2016
 * Time: 9:43:18 PM
 */
package a00973569.lab5.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.lab5.data.Player;
import a00973569.lab5.data.exception.ApplicationException;
import a00973569.lab5.util.Validator;

/**
 * Read the player input.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 5.0
 */
public class PlayerReader {

	public static final String RECORD_DELIMITER = ":";
	public static final String FIELD_DELIMITER = "\\|";
	private static String FILE_IN = "players.txt";
	private static final Logger LOG = LogManager.getLogger(PlayerReader.class);
	private static int EXIT_CODE_IRREGULAR = -1;
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
	 * @return A list of players.
	 * @throws ApplicationException
	 *             if invalid email and number of elements are given
	 * @throws IOException
	 *             if IO not valid
	 */
	public static List<Player> read(final String data) throws ApplicationException, IOException {

		BufferedReader inputStream = null;

		final List<Player> players = new ArrayList<>();
		LOG.info("Starting PlayerReader.read");
		try {
			inputStream = new BufferedReader(new FileReader(FILE_IN));

			String playerData;

			while ((playerData = inputStream.readLine()) != null) {

				final String[] elements = playerData.split(FIELD_DELIMITER);
				if (elements.length != Player.ATTRIBUTE_COUNT) {
					throw new ApplicationException(String.format("Expected %d but got %d: %s", Player.ATTRIBUTE_COUNT, elements.length, Arrays.toString(elements)));
				}

				final Player player = new Player();
				int index = INDEX_ZERO;
				player.setId(Integer.parseInt(elements[index++]));
				player.setFirstName(elements[index++]);
				player.setLastName(elements[index++]);
				final String email = elements[index++];
				if (!Validator.validateEmail(email)) {
					throw new ApplicationException(String.format("Invalid email: %s", email));
				}
				player.setEmailAddress(email);
				player.setGamerTag(elements[index++]);

				final String yyyymmdd = elements[index];
				try {
					player.setBirthDate(Integer.parseInt(yyyymmdd.substring(SUBSTRING_VALUE_ZERO, SUBSTRING_VALUE_FOUR)),
							Integer.parseInt(yyyymmdd.substring(SUBSTRING_VALUE_FOUR, SUBSTRING_VALUE_SIX)) - FIRST_MONTH,
							Integer.parseInt(yyyymmdd.substring(SUBSTRING_VALUE_SIX, SUBSTRING_VALUE_EIGHT)));
				} catch (final NumberFormatException e) {
					LOG.error("Invalid date element:" + yyyymmdd);
				}
				players.add(player);
			}
		} catch (final IOException e) {
			LOG.error("player.txt does not exist system will now exit: exit_code " + EXIT_CODE_IRREGULAR);
			System.exit(EXIT_CODE_IRREGULAR);

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		return players;
	}
}