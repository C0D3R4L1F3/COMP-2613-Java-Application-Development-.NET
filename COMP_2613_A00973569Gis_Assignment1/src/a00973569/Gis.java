/**
 * Project: A00973569Gis
 * File: Gis.java
 * Date: May 20, 2016
 * Time: 12:19:44 AM
 */
package a00973569;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00973569.data.Game;
import a00973569.data.Persona;
import a00973569.data.Player;
import a00973569.data.Result;
import a00973569.data.Score;
import a00973569.io.GameReader;
import a00973569.io.PersonaReader;
import a00973569.io.PlayerReader;
import a00973569.io.PlayersResultsReport;
import a00973569.io.ResultsReport;
import a00973569.io.ScoreReader;
import a00973569.util.ResultsSorter;
import a00973569.util.StackTraceUtil;

/**
 * Gis driver class to run the program args
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Gis {

	private static final int REGULAR_EXIT = 0;
	private static final int IRREGULAR_EXIT = -1;
	private static final int ARGS_LENGTH_ZERO = 0;
	private static final int ONE_WIN = 1;
	private static final int PLATFORM_ARGS_THREE = 3;
	private static final int INDEX_OF_THREE = 3;
	private static final int INDEX_OF_ONE = 1;
	private static final String PLAYERS_DATA_FILENAME = "players.dat";
	private static final String PERSONAS_DATA_FILENAME = "personas.dat";
	private static final String GAMES_DATA_FILENAME = "games.dat";
	private static final String SCORES_DATA_FILENAME = "scores.dat";
	private static final String REPORT_FILENAME = "leaderboard_report.txt";
	private static final String RESULT_WIN = "WIN";
	private static final String RESULT_LOSS = "LOSE";
	private static final String SORT_BY_COUNT = "by_count";
	private static final String SORT_BY_GAME = "by_game";
	private static final String SORT_ORDER_DESC = "desc";
	private static final String SHOW_TOTAL = "total";
	private static final String FILTER_BY_PLATFORM = "platform";
	private static HashMap<Integer, Player> players;
	private static HashMap<Integer, Persona> personas;
	private static HashMap<String, Game> games;
	private static List<Score> scores;
	private static List<Result> results;
	private static List<Result> reportResults;
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
	 */
	public static void main(final String[] args) {
		final Instant start = Instant.now();
		LOG.info(start);
		LOG.info("Start Assignment 1");
		try {
			players = PlayerReader.read(getfile(PLAYERS_DATA_FILENAME));
			personas = PersonaReader.read(getfile(PERSONAS_DATA_FILENAME));
			games = GameReader.read(getfile(GAMES_DATA_FILENAME));
			scores = ScoreReader.read(getfile(SCORES_DATA_FILENAME));
			prepareResultList();
			if (args.length == ARGS_LENGTH_ZERO) {
				prepareResults(getArgValue(args, "platform=AN|IO|PC|PS|XB"), getArgValue(args, SORT_BY_COUNT), getArgValue(args, SORT_ORDER_DESC));
				displayResults(reportResults, getArgValue(args, "total"));
				LOG.info("Output report successful to leaderboard_report.txt");
			} else if (args[0].equals("players")) {
				/// optional players results report output to "players_report.txt"
				PlayersResultsReport.diplayPlayersReport(results, players);
				LOG.info("Output report successful to players_report.txt");
			} else {
				prepareResults(getArgValue(args, "platform"), getArgValue(args, "sort_by"), getArgValue(args, "desc"));
				displayResults(reportResults, getArgValue(args, "total"));
				LOG.info("Output report successful to leaderboard_report.txt");
			}
		} catch (final ApplicationException e) {
			LOG.error(e.getMessage());
			System.exit(IRREGULAR_EXIT);
		} catch (final Exception e) {
			LOG.error(StackTraceUtil.getStackTrace(e));
			System.exit(IRREGULAR_EXIT);
		}
		LOG.info("End Assignment 1. exit code: " + REGULAR_EXIT);
		final Instant end = Instant.now();
		LOG.info(end);
		LOG.info("Duration: " + Duration.between(start, end).toMillis() + " ms");
		System.exit(REGULAR_EXIT);
	}

	/**
	 * Prepares the results depending on platform, sortBy and order parameters given
	 * 
	 * @param platform
	 *            the platform to prepare
	 * @param sortBy
	 *            the sortBy to prepare
	 * @param order
	 *            the order to prepare
	 */
	private static void prepareResults(final String platform, final String sortBy, final String order) {
		if (platform != null) {
			reportResults = results.stream().filter(p -> p.getPlatform().equalsIgnoreCase(platform)).collect(Collectors.toList());
		} else {
			reportResults = new ArrayList<Result>(results);
		}
		if (sortBy != null) {
			if (sortBy.equalsIgnoreCase(SORT_BY_GAME)) {
				if (order != null && order.equalsIgnoreCase(SORT_ORDER_DESC)) {
					Collections.sort(reportResults, new ResultsSorter.CompareByGameDesc());
				} else {
					Collections.sort(reportResults, new ResultsSorter.CompareByGame());
				}
			}
			if (sortBy.equalsIgnoreCase(SORT_BY_COUNT)) {
				if (order != null && order.equalsIgnoreCase(SORT_ORDER_DESC)) {
					Collections.sort(reportResults, new ResultsSorter.CompareByTotalDesc());
				} else {
					Collections.sort(reportResults, new ResultsSorter.CompareByTotal());
				}
			}
		}
	}

	/**
	 * Prepares the list of results
	 */
	private static void prepareResultList() {
		final List<Result> resultsTemp = new ArrayList<>();
		for (final Score player : scores) {
			resultsTemp.add(new Result(player.getPersonaId(), player.getGameId()));
		}
		final Set<Result> hashsetList = new HashSet<Result>(resultsTemp);
		results = new ArrayList<>(hashsetList);
		// populate results
		for (final Result result : results) {
			for (final Score score : scores) {
				if (result.getPersonaId() == score.getPersonaId() && result.getGameId().equals(score.getGameId())) {
					if (score.getResult().equals(RESULT_WIN)) {
						result.setWin(result.getWin() + ONE_WIN);
					}
					if (score.getResult().equals(RESULT_LOSS)) {
						result.setLoss(result.getLoss() + ONE_WIN);
					}
				}
			}
			result.setGameName(games.get(result.getGameId()).getName());
			result.setGameTag(personas.get(result.getPersonaId()).getGamerTag());
			result.setPlatform(personas.get(result.getPersonaId()).getPlatform());
			result.setPlayerId(personas.get(result.getPersonaId()).getPlayerId());

			LOG.debug(result);
		}
		LOG.debug("Initial list size: " + results.size());
	}

	/**
	 * Gets the data files needed to display
	 * 
	 * @param newFile
	 *            the newFile to get
	 * @throws IOException
	 *             if file is not found
	 */
	private static File getfile(final String newFile) throws IOException {
		final File file = new File(newFile);
		if (!file.exists()) {
			System.exit(IRREGULAR_EXIT);
			throw new IOException("Required " + newFile + " is missing.");
		}
		return file;
	}

	/**
	 * Displays the results of data file to "leaderboard_report.txt"
	 * 
	 * @param results
	 *            the results to display
	 * @param total
	 *            the total to display
	 */
	private static void displayResults(final List<Result> results, final String total) {
		final File inventory = new File(REPORT_FILENAME);
		PrintStream out = null;
		LOG.debug("Leaderboard Report has " + results.size() + " entries");
		try {
			out = new PrintStream(new FileOutputStream(inventory));
			ResultsReport.write(results, out, total);
		} catch (final FileNotFoundException e) {
			LOG.error("File does not exist: " + REPORT_FILENAME);
			System.exit(IRREGULAR_EXIT);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * Gets the Arguments provided in the commandline to sort by given args and param
	 * 
	 * @param args
	 *            the args provided
	 * @param param
	 *            the param provided
	 * @return the result chosen
	 */
	private static String getArgValue(final String[] args, final String param) {
		String result = null;
		switch (param) {
		case "sort_by":
			for (final String input : args) {
				if (input.contains(SORT_BY_COUNT)) {
					result = SORT_BY_COUNT;
				}
				if (input.contains(SORT_BY_GAME)) {
					result = SORT_BY_GAME;
				}
			}
			break;
		case "total":
			for (final String input : args) {
				if (input.contains(SHOW_TOTAL)) {
					result = SHOW_TOTAL;
				}
			}
			break;
		case "desc":
			for (final String input : args) {
				if (input.contains(SORT_ORDER_DESC)) {
					result = SORT_ORDER_DESC;
				}
			}
			break;
		case "platform":
			for (final String input : args) {
				if (input.contains(FILTER_BY_PLATFORM + "=") && input.length() == FILTER_BY_PLATFORM.length() + PLATFORM_ARGS_THREE) {
					result = input.substring(input.indexOf("=") + INDEX_OF_ONE, input.indexOf("=") + INDEX_OF_THREE);
				}
			}
			break;
		}
		LOG.debug("arg - " + result);
		return result;
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
