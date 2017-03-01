/**
 * Project: A00973569Lab3
 * File: PlayerReport.java
 * Date: May 9, 2016
 * Time: 9:25:39 PM
 */
package a00973569.lab3.io;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import a00973569.lab3.data.Player;

/**
 * Create a player report.
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 3.0
 */
public class PlayerReport {

	private static final int INITIAL_ID_NUMBER = 0;
	private static final SimpleDateFormat birthdate = new SimpleDateFormat("E MMM dd yyyy");

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
	public static void write(final Player[] players) {
		System.out.println("Player Report");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("  #. ID     First name      Last name       Email                          Gamertag            Birthdate");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		int i = INITIAL_ID_NUMBER;
		for (final Player player : players) {
			new GregorianCalendar();
			System.out.format("%3d. %06d %-15s %-15s %-30s %-19s %-20s%n", ++i, player.getId(), player.getFirstName(), player.getLastName(), player.getEmailAddress(),
					player.getGamerTag(), birthdate.format(player.getBirthDate().getTime()));
		}
	}
}
