/**
 * Project: A00973569Lab7
 * File: PlayerReport.java
 * Date: June 5, 2016
 * Time: 7:32:39 PM
 */
package a00973569.lab7.player;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.lab7.data.Player;

/**
 * Prints a formated Players report.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 7.0
 */
public class PlayerReport {

	private static String FILE_OUT = "players_report.txt";
	private static final int INITIAL_ID_NUMBER = 0;
	public static final String SEPERATOR = "-------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%3s. %-6s %-12s %-12s %-24s %-24s %-15s%n";
	public static final String PERSONA_FORMAT = "%3d. %06d %-12s %-12s %-24s %-24s %-15s%n";
	public static final DateTimeFormatter dateTimeForatter = DateTimeFormatter.ofPattern("E dd MMM uuuu");
	private static final Logger LOG = LogManager.getLogger(PlayerReport.class);

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
				final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy");
				outputStream.format(PERSONA_FORMAT, ++i, player.getId(), player.getFirstName(), player.getLastName(), player.getEmailAddress(), player.getGamerTag(),
						sdf.format(player.getBirthDate().getTime()));
			}
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
