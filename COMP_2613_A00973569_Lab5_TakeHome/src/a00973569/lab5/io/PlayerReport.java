/**
 * Project: A00973569Lab5
 * File: PlayerReport.java
 * Date: May 25, 2016
 * Time: 9:44:41 PM
 */
package a00973569.lab5.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.lab5.data.Player;

/**
 * Create a player report.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 5.0
 */
public class PlayerReport {

	private static final int INITIAL_ID_NUMBER = 0;
	public static final String SEPERATOR = "-------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%3s. %-6s %-12s %-12s %-24s %-24s %-15s%n";
	public static final String PERSONA_FORMAT = "%3d. %06d %-12s %-12s %-24s %-24s %-15s%n";
	public static final DateTimeFormatter dateTimeForatter = DateTimeFormatter.ofPattern("E dd MMM uuuu");
	private static final Logger LOG = LogManager.getLogger(PlayerReport.class);
	private static String FILE_OUT = "players_report.txt";

	/**
	 * private constructor to prevent instantiation
	 */
	private PlayerReport() {
	}

	/**
	 * Print the report.
	 * 
	 * @param players
	 *            details that are given for player
	 * @throws IOException
	 *             if IO not valid
	 */
	public static void write(final List<Player> players) throws IOException {
		PrintWriter outputStream = null;
		outputStream = new PrintWriter(new FileWriter(FILE_OUT));

		LOG.info("Starting PlayerReport.write");
		try {
			outputStream.println("Players Report");
			outputStream.println(SEPERATOR);
			outputStream.format(HEADER_FORMAT, "# ", "ID", "First name", "Last name", "Email", "Gamer tag", "Birthdate");
			outputStream.println(SEPERATOR);

			int i = INITIAL_ID_NUMBER;
			for (final Player player : players) {
				final LocalDateTime date = player.getBirthDate();
				outputStream.format(PERSONA_FORMAT, ++i, player.getId(), player.getFirstName(), player.getLastName(), player.getEmailAddress(), player.getGamerTag(),
						dateTimeForatter.format(date));
			}
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
