/**
 * Project: A00973569Lab9
 * File: Lab9.java
 * Date: June 19, 2016
 * Time: 7:39:54 PM
 */
package a00973569.lab9;

import java.awt.EventQueue;
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

import a00973569.lab9.dao.tester.PlayerDAOTester;
import a00973569.lab9.data.Player;
import a00973569.lab9.io.PlayerReader;
import a00973569.lab9.io.PlayerReport;
import a00973569.lab9.io.PlayerSorters.CompareByBirthdate;
import a00973569.lab9.ui.MainFrame;
import a00973569.lab9.util.StackTraceUtil;

/**
 * Driver class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 9.0
 */
public class Lab9 {

	private static int EXIT_CODE_REGULAR = 0;
	private static int EXIT_CODE_IRREGULAR = -1;
	private static final int ZONE_HOUR_ZERO = 0;
	private static List<Player> players;

	private static final String LAB_NO = "Lab9";
	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger(Lab9.class);

	/**
	 * Drives the program.
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static void main(final String[] args) {
		LOG.info("Starting " + LAB_NO);
		final Instant start = LocalDateTime.now().toInstant(ZoneOffset.ofHours(ZONE_HOUR_ZERO));
		LOG.info(start);
		try {

			players = PlayerReader.read();
			LOG.info("End PlayerReader.read()");

			PlayerDAOTester.retrieveGamerTagsList(players); // retrieves list of gamerTags and there fullName

			Collections.sort(players, new CompareByBirthdate()); // sorts players by birth date

			PlayerReport.write(players);
			LOG.info("End PlayerReport.write()");

			LOG.info("GUI started");
			createGUI();

		} catch (final ApplicationException e) {
			LOG.error(e);
			System.exit(EXIT_CODE_IRREGULAR);
			LOG.info("End Lab9. exit code " + EXIT_CODE_IRREGULAR);
		} catch (final Exception e) {
			LOG.error(StackTraceUtil.getStackTrace(e));
			System.exit(EXIT_CODE_IRREGULAR);
			LOG.info("End Lab9. exit code " + EXIT_CODE_IRREGULAR);
		}

		final Instant end = LocalDateTime.now().toInstant(ZoneOffset.ofHours(ZONE_HOUR_ZERO));
		LOG.info(end);
		LOG.info(String.format("Duration: %d ms", ChronoUnit.MILLIS.between(start, end)));

		LOG.info("End " + LAB_NO + ". Exit code " + EXIT_CODE_REGULAR);
	}

	/**
	 * Shows GUI
	 */
	private static void createGUI() throws ApplicationException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				final MainFrame frame = new MainFrame(players);
				frame.setVisible(true);
			}
		});
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