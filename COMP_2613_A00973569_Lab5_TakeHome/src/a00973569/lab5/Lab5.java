/**
 * Project: A00973569Lab5
 * File: Lab5.java
 * Date: May 23, 2016
 * Time: 9:29:35 PM
 */
package a00973569.lab5;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00973569.lab5.data.Player;
import a00973569.lab5.data.exception.ApplicationException;
import a00973569.lab5.io.PlayerReader;
import a00973569.lab5.io.PlayerReport;
import a00973569.lab5.util.CompareByBirthdate;

/**
 * To demonstrate knowledge of Strings Simple regular expressions Formatting output, Object design, and Jar Files
 * 
 * Create a commandline program which reads player data, creates player objects, adds them to an array, and prints a
 * simple player report
 * 
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 5.0
 */
public class Lab5 {

	private static String playerData;
	private static List<Player> players;
	private static int EXIT_CODE_REGULAR = 0;
	private static int EXIT_CODE_IRREGULAR = -1;
	public static final String LOG4J_CONFIG_FILENAME = "log_config.xml";
	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger(Lab5.class);

	/**
	 * Drives the program.
	 * 
	 * @param args
	 *            command line arguments.
	 * @throws IOException
	 *             if IO not valid
	 */
	public static void main(final String[] args) throws IOException {

		LOG.info("Starting Lab5");
		final Instant start = LocalDateTime.now().toInstant(ZoneOffset.ofHours(0));
		LOG.info(start);

		new Lab5().run();

		final Instant end = LocalDateTime.now().toInstant(ZoneOffset.ofHours(0));
		LOG.info(end);
		LOG.info(String.format("Duration: %d ms", ChronoUnit.MILLIS.between(start, end)));
		LOG.info("End Lab5. exit_code " + EXIT_CODE_REGULAR);
		System.exit(EXIT_CODE_REGULAR);
	}

	/**
	 * Lab5 constructor.
	 */
	public Lab5() {

	}

	/**
	 * Populate the players and print them out.
	 * 
	 * @throws IOException
	 *             if IO not valid
	 */
	private void run() throws IOException {
		try {
			loadPlayers();

			// sort the players by birthdate
			// players.sort((e1, e2)->e1.getBirthDate().compareTo(e2.getBirthDate()));
			Collections.sort(players, new CompareByBirthdate());

			displayPlayers();
			LOG.info("Players data successfully output to players_report.txt");
		} catch (final ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("System will now exit: exit_code " + EXIT_CODE_IRREGULAR);
			System.exit(EXIT_CODE_IRREGULAR);
		}
	}

	/**
	 * Loads the players data
	 * 
	 * @throws ApplicationException
	 *             if invalid email and number of elements are given
	 * @throws IOException
	 *             if IO not valid
	 */
	private void loadPlayers() throws ApplicationException, IOException {
		players = PlayerReader.read(playerData);
	}

	/**
	 * Displays the players data
	 * 
	 * @throws IOException
	 *             if IO not valid
	 */
	private void displayPlayers() throws IOException {
		PlayerReport.write(players);
	}

	/**
	 * Log configuration
	 */
	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (final IOException e) {
			LOG.error(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}

}