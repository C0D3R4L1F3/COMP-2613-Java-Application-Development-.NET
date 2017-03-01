/**
 * Project: A00973569Gis
 * File: PlayerReportWriter.java
 * Date: June 20, 2016
 * Time: 1:45:56 PM
 */
package a00973569.io;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.data.Player;

/**
 * Writes the players report
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class PlayerReportWriter {

	private static String title = "";
	private static String border = "------------------------------------------------------------------------------------------------------------------------";
	private static final Logger LOG = LogManager.getLogger(PlayerReportWriter.class);

	/**
	 * Displays the players
	 * 
	 * @param players
	 *            the players to set
	 */
	public static void displayPlayers(final List<Player> players) {
		LOG.info(title);
		LOG.info(border);
		LOG.info("%-12s%-24s%-24s%-12s%-18s%-12s", "ID", "Full Name", "Email", "Age", "Games Played", "Total Wins");
		LOG.info("");
		LOG.info(border);
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i) != null) {
				LOG.info("%-12s%-24s%-24s%-12s%-18s%-12s", players.get(i).getId(), players.get(i).getfName() + " " + players.get(i).getfName(), players.get(i).getEmail(),
						players.get(i).getAge(), PlayerFormat.calculateTotalGamesPlayed(players.get(i)), PlayerFormat.calculateTotalWins(players.get(i)));
			}
			LOG.info("");
		}
		LOG.info(border);
	}
}