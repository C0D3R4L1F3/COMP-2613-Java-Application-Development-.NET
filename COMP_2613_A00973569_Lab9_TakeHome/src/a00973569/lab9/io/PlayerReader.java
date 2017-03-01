/**
 * Project: A00973569Lab9
 * File: PlayerReader.java
 * Date: June 5, 2016
 * Time: 7:32:39 PM
 */
package a00973569.lab9.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.lab9.ApplicationException;
import a00973569.lab9.data.Player;
import a00973569.lab9.util.Validator;

/**
 * Read the player input.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 9.0
 */
public class PlayerReader {

	private static String FILE_IN = "players.txt";
	public static final String RECORD_DELIMITER = ":";
	public static final String FIELD_DELIMITER = "\\|";
	private static int EXIT_CODE_IRREGULAR = -1;
	private static final int INDEX_ZERO = 0;
	private static final Logger LOG = LogManager.getLogger(PlayerReader.class);

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
	public static List<Player> read() throws ApplicationException, IOException {

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

				final String yyyyMMdd = elements[index];
				try {
					player.setBirthDate(LocalDate.parse(elements[index++], DateTimeFormatter.ofPattern("yyyyMMdd")));
				} catch (final NumberFormatException e) {
					LOG.error("Invalid date element:" + yyyyMMdd);
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