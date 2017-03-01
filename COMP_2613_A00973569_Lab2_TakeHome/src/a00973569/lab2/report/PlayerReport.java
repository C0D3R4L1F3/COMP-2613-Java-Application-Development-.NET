/**
 * Project: A00973569Lab2
 * File: PlayerReport.java
 * Date: May 2, 2016
 * Time: 9:16:26 PM
 */
package a00973569.lab2.report;

import a00973569.lab2.data.Player;

/**
 * PlayerReport class prints out Player details in a certain
 * format and spaces out each column to align with the headers
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class PlayerReport {

	private static final int INDEX_ZERO = 0;

	/**
	 * Default constructor
	 */
	public PlayerReport() {

		super();
	}

	/**
	 * Method prints out the player details
	 * 
	 * @param players
	 *            details that are given
	 */
	public void printReport(final Player[] players) {

		System.out.println("Player Report");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("  #. ID     First name      Last name       Email                     Gamertag");
		System.out.println("----------------------------------------------------------------------------------");
		for (int i = INDEX_ZERO; i < players.length; i++) {
			final int listNumber = players[i].getListNumber();
			final int id = players[i].getId();
			final String firstName = players[i].getFirstName();
			final String lastName = players[i].getLastName();
			final String email = players[i].getEmailAddress();
			final String gamerTag = players[i].getGamerTag();
			System.out.format("%3d. %06d %-15s %-15s %-25s %-20s%n", listNumber, id, firstName, lastName, email, gamerTag);

			// System.out.println(listNumber + ". " + String.format("%06d", id) + " " + String.format("%-15s", firstName) + " " + String.format("%-15s", lastName) + " "
			// + String.format("%-25s", email) + " " + gamerTag);

		}
	}
}
