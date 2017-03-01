/**
 * Project: A00973569Gis
 * File: PlayersResultsReport.java
 * Date: May 27, 2016
 * Time: 1:53:58 AM
 */
package a00973569.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.data.Player;
import a00973569.data.PlayerResult;
import a00973569.data.Result;

/**
 * Outputs and displays information to Player Report
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class PlayersResultsReport {

	private static final int BIRTHDATE_HOUR = 0;
	private static final int BIRTHDATE_MINUTES = 0;
	private static List<PlayerResult> playersresults;
	private static String FILE_OUT = "players_report.txt";
	private static final Logger LOG = LogManager.getLogger(PlayersResultsReport.class);

	/**
	 * Default constructor
	 */
	public PlayersResultsReport() {
		super();
		LOG.debug("PlayersResultsReport()");
	}

	/**
	 * Displays a players report
	 * 
	 * @param results
	 *            given for player
	 * @param players
	 *            information given for players
	 * @throws IOException
	 *             if output file is invalid
	 */
	public static void diplayPlayersReport(final List<Result> results, final HashMap<Integer, Player> players) throws IOException {
		playersresults = new ArrayList<>();
		populatePlayersResultList(results, players);
		printReport();
	}

	/**
	 * Populates the Players Result List
	 * 
	 * @param results
	 *            given for player
	 * @param players
	 *            information given for players
	 */
	private static void populatePlayersResultList(final List<Result> results, final HashMap<Integer, Player> players) {
		for (final Map.Entry<Integer, Player> player : players.entrySet()) {
			playersresults.add(new PlayerResult(player.getValue().getId(), player.getValue().getFirstName() + " " + player.getValue().getLastName(), player.getValue().getEmail(),
					player.getValue().getAge(), BIRTHDATE_HOUR, BIRTHDATE_MINUTES));
		}
		// populate results
		for (final PlayerResult player : playersresults) {
			for (final Result result : results) {
				if (player.getPlayerId() == result.getPlayerId()) {
					player.setTotal(player.getTotal() + result.getTotal());
					player.setWon(player.getWon() + result.getWin());
				}
			}
		}
		LOG.debug("List size: " + playersresults.size());
	}

	/**
	 * Prints players report
	 */
	private static void printReport() {
		LOG.info("PlayersResultsReport.printReport(..)");
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileWriter(FILE_OUT));
		} catch (final IOException e) {
			LOG.error("File does not exist: " + FILE_OUT);
		}
		LOG.debug("Player Report has " + playersresults.size() + " entries");
		try {
			final String separator = "------------------------------------------------------------------------------------------------------";
			outputStream.format("%-10s%-20s%-30s%-10s%-20s%-20s%n", "Player ID", "Full name", "Email", "Age", "Total games played", "Total Wins");
			outputStream.println(separator);
			for (final PlayerResult player : playersresults) {
				outputStream.format("        %-2d%-20s%-30s%-27d%-12d%-20d%n", player.getPlayerId(), player.getFullName(), player.getEmail(), player.getAge(), player.getTotal(),
						player.getWon());
			}
			outputStream.println(separator);
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}