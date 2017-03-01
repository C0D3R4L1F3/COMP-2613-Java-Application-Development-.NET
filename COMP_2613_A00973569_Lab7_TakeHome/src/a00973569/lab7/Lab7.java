/**
 * Project: A00973569Lab7
 * File: Lab7.java
 * Date: June 5, 2015
 * Time: 7:00:38 PM
 */
package a00973569.lab7;

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

import a00973569.lab7.data.Player;
import a00973569.lab7.data.dao.tester.PlayerDaoTester;
import a00973569.lab7.player.PlayerReader;
import a00973569.lab7.player.PlayerReport;
import a00973569.lab7.player.PlayerSorters;
import a00973569.lab7.util.StackTraceUtil;

/**
 * To demonstrate knowledge of File IO and (log4j) Logging
 * 
 * Create a commandline program which reads player data, creates Player objects, adds them to a collection, and prints
 * a simple Player report sorted by birthdate
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 7.0
 */
public class Lab7 {

	private static int EXIT_CODE_REGULAR = 0;
	private static int EXIT_CODE_IRREGULAR = -1;
	private static final int ZONE_HOUR_ZERO = 0;
	private static List<Player> players;
	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger(Lab7.class);

	/**
	 * Drives the program.
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static void main(final String[] args) {

		LOG.info("Starting Lab7");
		final Instant start = LocalDateTime.now().toInstant(ZoneOffset.ofHours(ZONE_HOUR_ZERO));
		LOG.info(start);

		try {

			players = PlayerReader.read();
			LOG.info("End PlayerReader.read");

			PlayerDaoTester.retrieveGamerTagsList(players); // retrieves list of gamerTags and there fullName

			Collections.sort(players, new PlayerSorters.CompareByBirthdate()); // sorts players by birth date

			PlayerReport.write(players);
			LOG.info("End PlayerReport.write");

		} catch (final ApplicationException e) {

			LOG.error(e);
			System.exit(EXIT_CODE_IRREGULAR);
			LOG.info("End Lab7. exit code " + EXIT_CODE_IRREGULAR);
		} catch (final Exception e) {

			LOG.error(StackTraceUtil.getStackTrace(e));
			System.exit(EXIT_CODE_IRREGULAR);
			LOG.info("End Lab7. exit code " + EXIT_CODE_IRREGULAR);
		}

		final Instant end = LocalDateTime.now().toInstant(ZoneOffset.ofHours(ZONE_HOUR_ZERO));
		LOG.info(end);
		LOG.info(String.format("Duration: %d ms", ChronoUnit.MILLIS.between(start, end)));

		LOG.info("End Lab7. exit code " + EXIT_CODE_REGULAR);
		System.exit(EXIT_CODE_REGULAR);
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