/**
 * Project: A00973569Lab2
 * File: Lab2.java
 * Date: May 2, 2016
 * Time: 9:19:43 PM
 */
package a00973569.lab2;

import a00973569.lab2.data.Player;
import a00973569.lab2.reader.PlayerReader;
import a00973569.lab2.report.PlayerReport;

/**
 * Lab2 class runs the methods if valid args are passed if not it will
 * display an error message
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Lab2 {

	private static final int ARGS_VALUE_ZERO = 0;
	private static final int ARGS_LENGTH_ZERO = 0;
	private static final int SYSTEM_EXIT_ONE = 1;

	private final String input;
	private Player[] players;

	/**
	 * Lab2 constructor. Initialized the players collection.
	 */
	public Lab2(final String input) {

		this.input = input;

	}

	/**
	 * Runs the method for PlayerRead read method
	 * Runs the method for PlayerReport printReport method
	 */
	private void run() {

		players = new PlayerReader().read(input);
		new PlayerReport().printReport(players);
	}

	/**
	 * Drives the program.
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static void main(final String[] args) {

		if (args.length == ARGS_LENGTH_ZERO) {
			System.err.println("A string must be passed to the program.");
			System.exit(-SYSTEM_EXIT_ONE);
		}

		new Lab2(args[ARGS_VALUE_ZERO]).run();
	}
}