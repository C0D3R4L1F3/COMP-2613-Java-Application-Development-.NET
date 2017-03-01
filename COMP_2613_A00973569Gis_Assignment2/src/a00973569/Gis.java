/**
 * Project: A00973569Gis
 * File: Gis.java
 * Date: June 20, 2016
 * Time: 12:19:44 AM
 */

package a00973569;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00973569.ui.MainController;
import a00973569.ui.MainFrame;

/**
 * Gis driver class to run the program args
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Gis {

	public static final String LOG4J_CONFIG_FILENAME = "log_config.xml";
	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger(Gis.class);

	/**
	 * Drives the program.
	 * 
	 * @param args
	 *            command line arguments.
	 * @throws IOException
	 *             if failed or interrupted I/O has occurred
	 */
	public static void main(final String[] args) throws IOException {
		LOG.info("Starting main method");
		final Instant start = Instant.now();
		LOG.info(start);
		LOG.info("Start Assignment 2");

		try {
			for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (final Exception e) {
			LOG.error("Could not set Nimbus Look and Feel.");
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new MainController(new MainFrame());
				} catch (final Exception e) {
					LOG.error(e.getMessage());
				}
			}
		});
	}

	/**
	 * Configures the logging for the project
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