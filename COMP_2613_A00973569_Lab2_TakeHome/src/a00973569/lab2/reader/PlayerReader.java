/**
 * Project: A00973569Lab2
 * File: PlayerReader.java
 * Date: May 2, 2016
 * Time: 9:14:56 PM
 */
package a00973569.lab2.reader;

import a00973569.lab2.data.Player;
import a00973569.lab2.validator.Validator;

/**
 * PlayerReader class reads and accepts input of Player details and it must
 * have a correct format for email if not it will display N/A
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class PlayerReader {

	private static final int INDEX_ZERO = 0;
	private static final int DATA_VALUE_ZERO = 0;
	private static final int DATA_VALUE_ONE = 1;
	private static final int DATA_VALUE_TWO = 2;
	private static final int DATA_VALUE_THREE = 3;
	private static final int DATA_VALUE_FOUR = 4;
	private String[] initialPlayers;
	private Player[] playerReaderList;

	/**
	 * Default constructor
	 */
	public PlayerReader() {

		super();
	}

	/**
	 * Method to read what is inputed for the program
	 * 
	 * @param input
	 *            that is given for player details
	 * @return the out put of input
	 */
	public Player[] read(final String input) {

		initialPlayers = input.split(":");
		playerReaderList = new Player[initialPlayers.length];
		int i = INDEX_ZERO;
		for (final String players : initialPlayers) {
			final String[] playerDetail = players.split("\\|");
			final Player player = new Player();

			final int list = Integer.parseInt(playerDetail[DATA_VALUE_ZERO]);
			player.setListNumber(list);
			final int id = Integer.parseInt(playerDetail[DATA_VALUE_ZERO]);
			player.setId(id);
			player.setFirstName(playerDetail[DATA_VALUE_ONE]);
			player.setLastName(playerDetail[DATA_VALUE_TWO]);
			player.setEmailAddress(playerDetail[DATA_VALUE_THREE]);

			if (Validator.validate(playerDetail[DATA_VALUE_THREE]) == true) {
				player.setEmailAddress(playerDetail[DATA_VALUE_THREE]);
			} else {
				player.setEmailAddress("N/A");
			}

			player.setGamerTag(playerDetail[DATA_VALUE_FOUR]);
			playerReaderList[i] = player;
			i++;
		}
		return playerReaderList;
	}
}
