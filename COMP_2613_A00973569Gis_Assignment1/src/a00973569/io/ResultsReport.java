/**
 * Project: A00973569Gis
 * File: ResultsReport.java
 * Date: May 27, 2016
 * Time: 2:57:40 PM
 */
package a00973569.io;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.data.Result;

/**
 * Outputs and displays information to Leader Board Report
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class ResultsReport {

	public static final String SEPERATOR = "-----------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = " %-12s %-22s %-22s %-12s%n";
	public static final String PERSONA_FORMAT = " %-12s %-22s %-22s %-12s%n";
	private static final Logger LOG = LogManager.getLogger(ResultsReport.class);

	/**
	 * private constructor to prevent instantiation
	 */
	private ResultsReport() {
		LOG.debug("ResultsReport()");
	}

	/**
	 * Writes the report
	 * 
	 * @param players
	 *            information given for players
	 */

	public static void write(final List<Result> results, final PrintStream out, final String total) {
		LOG.info("ResultsReport.write(..)");
		out.println("Games Report");
		out.println(SEPERATOR);
		out.format(HEADER_FORMAT, "Win:Loss", "Game Name ", "Gamer Tag", "Platform");
		out.println(SEPERATOR);
		for (final Result result : results) {
			out.format(PERSONA_FORMAT, result.getScores(), result.getGameName(), result.getGameTag(), result.getPlatform());
		}
		out.println(SEPERATOR);
		if (total != null) {
			// print total
			for (final Result result : getTotal(results)) {
				out.format("%-25s%-5s%n", result.getGameName(), result.getTotal());
			}
		}
	}

	/**
	 * Gets the total of results
	 * 
	 * @param results
	 *            given to players
	 * @return list
	 */
	public static List<Result> getTotal(final List<Result> results) {
		final Set<Result> setTemp = new HashSet<Result>();
		for (final Result p : results) {
			setTemp.add(new Result(0, p.getGameId()));
		}
		for (final Result game : setTemp) {
			for (final Result score : results) {
				if (game.getGameId().equals(score.getGameId())) {
					game.setWin(game.getWin() + score.getWin());
					game.setLoss(game.getLoss() + score.getLoss());
					game.setGameName(score.getGameName());
				}
			}
		}
		final List<Result> list = new ArrayList<Result>(setTemp);
		return list;
	}
}