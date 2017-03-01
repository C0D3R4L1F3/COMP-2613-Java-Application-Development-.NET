/**
 * Project: A00973569Lab3
 * File: Lab3.java
 * Date: May 9, 2016
 * Time: 9:07:42 PM
 */
package a00973569.lab3;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import a00973569.lab3.data.Player;
import a00973569.lab3.data.exception.ApplicationException;
import a00973569.lab3.io.PlayerReader;
import a00973569.lab3.io.PlayerReport;

/**
 * To demonstrate knowledge of Strings Simple regular expressions Formatting output, Object design, and Jar Files
 * 
 * Create a commandline program which reads player data, creates player objects, adds them to an array, and prints a
 * simple player report
 * 
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 3.0
 */
public class Lab3 {

	private static final int ARGS_VALUE_ZERO = 0;
	private static final int ARGS_LENGTH_ZERO = 0;
	private static final int SYSTEM_EXIT_ONE = 1;

	private final String playerData;
	private Player[] players;

	/**
	 * Lab3 constructor. Initialized the players collection.
	 */
	public Lab3(final String playerData) {
		this.playerData = playerData;
	}

	/**
	 * Populate the players and print them out.
	 * 
	 * @throws ApplicationException
	 *             if invalid email and number of elements are given
	 */
	private void run() throws ApplicationException {
		players = PlayerReader.read(playerData);
		PlayerReport.write(players);
	}

	/**
	 * Drives the program.
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static void main(final String[] args) {
		if (args.length == ARGS_LENGTH_ZERO) {
			System.out.println("Input data is missing. Expecting player data.");
			System.exit(-SYSTEM_EXIT_ONE);
		}
		final Instant start = LocalDateTime.now().toInstant(ZoneOffset.ofHours(0));
		System.out.println(start);
		try {
			new Lab3(args[ARGS_VALUE_ZERO]).run();
		} catch (final ApplicationException error) {
			System.out.println(error.getMessage());
		} finally {
			final Instant end = LocalDateTime.now().toInstant(ZoneOffset.ofHours(0));
			System.out.println(end);
			System.out.println(String.format("Duration: %d ms", ChronoUnit.MILLIS.between(start, end)));
		}
	}
}
