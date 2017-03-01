/**
 * Project: A00973569Lab4
 * File: PlayerReport.java
 * Date: May 17, 2016
 * Time: 9:44:41 PM
 */
package a00973569.lab4.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import a00973569.lab4.data.Player;

/**
 * Create a player report.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 4.0
 */
public class PlayerReport {

	private static final int INITIAL_ID_NUMBER = 0;
	public static final String SEPERATOR = "-------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%2s. %-6s %-13s %-12s %-25s %-16s %-15s%n";
	public static final String PERSONA_FORMAT = "%2d. %06d %-13s %-12s %-25s %-16s %-15s%n";
	public static final DateTimeFormatter dateTimeForatter = DateTimeFormatter.ofPattern("E dd MMM uuuu");

	/**
	 * private constructor to prevent instantiation
	 */
	private PlayerReport() {
	}

	/**
	 * Write the report.
	 * 
	 * @param players
	 *            details that are given for player
	 */
	public static void write(final List<Player> players) {
		System.out.println("Players Report");
		System.out.println(SEPERATOR);
		System.out.format(HEADER_FORMAT, "#", "ID", "First name", "Last name", "Email", "Gamer tag", "Birthdate");
		System.out.println(SEPERATOR);
		int i = INITIAL_ID_NUMBER;
		for (final Player player : players) {
			final LocalDateTime date = player.getBirthDate();
			System.out.format(PERSONA_FORMAT, ++i, player.getId(), player.getFirstName(), player.getLastName(), player.getEmailAddress(), player.getGamerTag(),
					dateTimeForatter.format(date));
		}
	}
}