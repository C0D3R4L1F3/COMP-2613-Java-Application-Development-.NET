/**
 * Project: A00973569Lab1
 * File: Lab1.java
 * Date: Apr 26, 2016
 * Time: 9:46:30 PM
 */
package a00973569.lab1;

import java.util.Arrays;

import a00973569.lab1.data.Player;

/**
 * Lab1 driver class contains a constructor that initializes number of players by the parameter count
 * if no integer value is entered in the command line system will exit if an integer is entered
 * then a list of players will be displayed from the value of the integer that is entered.
 *
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */

public class Lab1 {

	private static final int PLAYER_LENGTH_ZERO = 0;
	private static final int SYSTEM_EXIT_ONE = 1;
	private static final int INDEX_ZERO = 0;
	private static final int ID_COUNT = 1;
	private static final int ARGS_VALUE_ZERO = 0;
	private static final int ARGS_LENGTH_ZERO = 0;

	private final Player[] players;

	/**
	 * Constructor to initialize array of players
	 * 
	 * @param count
	 *            integer value entered
	 */
	public Lab1(final int count) {

		players = new Player[count];
		if ((players != null) && (players.length != PLAYER_LENGTH_ZERO)) {
			for (int i = INDEX_ZERO; i < players.length; i++) {
				players[i] = new Player(i + ID_COUNT, "Wayne", "Gretzky", "gretzky@live.ca", "TheGreatOne");
			}
		}
	}

	/**
	 * Displays players without using a loop
	 */
	private void displayPlayers() {

		System.out.println(Arrays.toString(players));
	}

	/**
	 * Drives the program.
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static void main(final String[] args) {

		if ((args.length == ARGS_LENGTH_ZERO) || (args == null)) {
			System.out.println("ERROR: You've entered an invalid arugment! System will now exit! Please enter a valid argument.");
			System.exit(-SYSTEM_EXIT_ONE);
		} else {
			final int count = Integer.parseInt(args[ARGS_VALUE_ZERO].trim());
			new Lab1(count).displayPlayers();
		}
	}
}
