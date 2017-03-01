/**
 * Project: A00973569Gis
 * File: LeaderboardReportWriter.java
 * Date: June 20, 2016
 * Time: 1:32:36 PM
 */

package a00973569.io;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.LeaderboardDAO;
import a00973569.data.Leaderboard;
import a00973569.data.Persona;
import a00973569.data.Score;

/**
 * Writes the leaderboard
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class LeaderboardReportWriter {

	private static final String SORT_BY_GAME = "by_game";
	private static final String SORT_BY_COUNT = "by_count";
	private List<Leaderboard> leaderboardRows;
	private Database db;
	private LeaderboardDAO leaderboardDao;
	private static final Logger LOG = LogManager.getLogger(LeaderboardReportWriter.class);

	/**
	 * Constructor to initialize a LeaderboardReportWriter object
	 * 
	 * @param sortDescending
	 *            given for LeaderboardReportWriter
	 */
	public LeaderboardReportWriter(final boolean sortDescending) {
		super();
	}

	/**
	 * Constructor to initialize a LeaderboardReportWriter object
	 */
	public LeaderboardReportWriter() {
		super();

		try {
			LOG.info("Reading generated leaderboard");
			db = Database.getDatabaseInstance();
			final Connection connection = db.connect();

			if (!Database.tableExists(connection, "leaderboard")) {
				this.leaderboardRows = this.createLeaderboardItems();
				leaderboardDao = LeaderboardDAO.getLeaderboardDao();
				leaderboardDao.create();

				for (final Leaderboard row : this.leaderboardRows) {
					leaderboardDao.addLeaderboardItem(row);
				}
			}
		} catch (final SQLException e) {
			LOG.error("Could not create leaderboard table. Class: LeaderboardReportWriter. Method: Constructor.");
		}
	}

	/**
	 * Gets the leaderboard rows
	 * 
	 * @param sortBy
	 *            the sortBy to set
	 * @return leaderboardRows
	 */
	public List<Leaderboard> getLeaderboardRows(final String sortBy) {
		List<Leaderboard> leaderboardRows = new ArrayList<>();

		try {
			leaderboardRows = leaderboardDao.getLeaderboardRows(null, false);
		} catch (final SQLException e) {
			LOG.error("Cannot get leaderboard rows. Class: Leaderboard Report Writer. Method: getLeaderboardRows.");
		} catch (final Exception e) {
			LOG.error("Class: Leaderboard Report Writer. Method: getLeaderboardRows. ");
		}
		return leaderboardRows;
	}

	/**
	 * Sorts the leaderboard
	 * 
	 * @param row
	 *            the rows to set
	 * @param sortCriteria
	 *            the sortCriteria to set
	 * @param desc
	 *            the desc to set
	 * @return the row
	 */
	public List<Leaderboard> sortLeaderboard(final List<Leaderboard> row, final String sortCriteria, final boolean desc) {
		switch (sortCriteria)

		{
		case "":
			this.sortLeaderboardByGamertag(row, desc);
			break;
		case SORT_BY_COUNT:
			this.sortLeaderboardByCount(row, desc);
			break;
		case SORT_BY_GAME:
			this.sortLeaderboardByGameName(row, desc);
			break;
		default:
			this.sortLeaderboardByGamertag(row, desc);
			break;
		}
		return row;
	}

	/**
	 * Sorts leaderboard by gamer tag
	 * 
	 * @param row
	 *            the row to set
	 * @param desc
	 *            the desc to set
	 * @return the row
	 */
	public List<Leaderboard> sortLeaderboardByGamertag(final List<Leaderboard> row, final boolean desc) {
		Collections.sort(row, new Comparator<Leaderboard>() {
			@Override
			public int compare(final Leaderboard first, final Leaderboard second) {
				if (!desc) {
					return first.getGamerTag().compareTo(second.getGamerTag());
				} else {
					return second.getGamerTag().compareTo(first.getGamerTag());
				}
			}
		});
		return row;
	}

	/**
	 * Sorts leaderboard by count
	 * 
	 * @param row
	 *            the row to set
	 * @param desc
	 *            the desc to set
	 * @return the row
	 */
	public List<Leaderboard> sortLeaderboardByCount(final List<Leaderboard> row, final boolean desc) {
		Collections.sort(row, new Comparator<Leaderboard>() {
			@Override
			public int compare(final Leaderboard first, final Leaderboard second) {
				if (desc) {
					return second.wins - first.wins;
				} else {
					return first.wins - second.wins;
				}
			}
		});
		return row;
	}

	/**
	 * Sorts leaderboard by game name
	 * 
	 * @param row
	 *            the row to set
	 * @param desc
	 *            the desc to set
	 * @return the row
	 */
	public List<Leaderboard> sortLeaderboardByGameName(final List<Leaderboard> row, final boolean desc) {
		Collections.sort(row, new Comparator<Leaderboard>() {
			@Override
			public int compare(final Leaderboard first, final Leaderboard second) {
				if (!desc) {
					return first.gameName.compareTo(second.gameName);
				} else {
					return second.gameName.compareTo(first.gameName);
				}
			}
		});
		return row;
	}

	/**
	 * Filters by platform
	 * 
	 * @param platform
	 *            the platform to set
	 * @param rowsBeforeFilter
	 *            the rowsBeforeFilter to set
	 * @return the filteredByPlatform
	 */
	public List<Leaderboard> filterByPlatform(final String platform, final List<Leaderboard> rowsBeforeFilter) {
		final List<Leaderboard> filteredByPlatform = new ArrayList<>();

		for (final Leaderboard row : rowsBeforeFilter) {
			if (row.getPlatform().equals(platform)) {
				filteredByPlatform.add(row);
			}
		}
		return filteredByPlatform;
	}

	/**
	 * Gets the title of the game by game id
	 * 
	 * @param gameId
	 *            the gameId to set
	 * @return the title
	 */
	public static String getTitleOfGameByGameId(final String gameId) {
		String title = "";
		final Map<String, String> gameIdToName = GamesFormat.getIdGameName();

		if (gameIdToName.containsKey(gameId)) {
			title = gameIdToName.get(gameId);
		}
		return title;
	}

	/**
	 * Gets the persona by id
	 * 
	 * @param id
	 *            the id to set
	 * @return the persona
	 */
	public Persona getPersonaById(final String id) {
		final Map<String, Persona> idToPersona = PersonaFormat.personaIdToPersonaMap();
		Persona persona = null;

		if (idToPersona.containsKey(id)) {
			persona = idToPersona.get(id);
		}
		return persona;
	}

	/**
	 * Calculates the totals of each game played
	 * 
	 * @param list
	 *            the list to set
	 * @return the gameTitleToTotal
	 */
	public static Map<String, Integer> calculateTotals(final List<Leaderboard> list) {
		final Map<String, Integer> gameTitleToTotal = new HashMap<>();

		for (final Leaderboard item : list) {
			final String fullTitle = item.getGameName();
			if (gameTitleToTotal.containsKey(fullTitle)) {
				gameTitleToTotal.put(fullTitle, gameTitleToTotal.get(fullTitle) + item.getWins() + item.getLosses());
			} else {
				gameTitleToTotal.put(fullTitle, item.getLosses() + item.getWins());
			}
		}
		return gameTitleToTotal;
	}

	/**
	 * Writes the totals
	 * 
	 * @param list
	 *            the list to set
	 */
	public void writeTotal(final List<Leaderboard> list) {
		final Map<String, Integer> gameNameToTotal = calculateTotals(list);
		String output = "\n";

		for (final Map.Entry<String, Integer> entry : gameNameToTotal.entrySet()) {
			output += entry.getKey() + ": " + entry.getValue() + "\n";
		}
		LOG.info(output);
	}

	/**
	 * Creates leadboard items
	 * 
	 * @return the rows
	 */
	public List<Leaderboard> createLeaderboardItems() {
		final Map<String, int[]> leaderboardEntry = this.getGameNameByGamertag();
		final List<Leaderboard> rows = new ArrayList<>();

		for (final Map.Entry<String, int[]> entry : leaderboardEntry.entrySet()) {
			final String[] key = entry.getKey().split("\\|");// Key is persona Id | game Id
			final String personaID = key[0];
			final String gameId = key[1];
			final int[] value = entry.getValue();
			final String gameName = LeaderboardReportWriter.getTitleOfGameByGameId(gameId);
			final Persona persona = this.getPersonaById(personaID);
			// Get full title of game by id
			rows.add(new Leaderboard(value[0], value[1], gameName, persona.getGamerTag(), persona.getPlatform()));
		}
		return rows;
	}

	/**
	 * Gets game name by gamer tag
	 * 
	 * @return the gameScore
	 */
	public Map<String, int[]> getGameNameByGamertag() {
		final Map<String, int[]> gameScore = new HashMap<>();
		List<Persona> listOfPersonas;

		try {
			listOfPersonas = PersonaFormat.createListOfPersonas();
			final List<Score> listOfScores = ScoreFormat.getListOfScores();

			for (final Persona persona : listOfPersonas) {
				final String personaId = persona.getId();

				for (final Score score : listOfScores) {
					if (score.getPersonaId().equalsIgnoreCase(persona.getId())) {
						final String key = personaId + "|" + score.getGameId();

						if (gameScore.containsKey(key)) {
							final int[] gameScoreValue = gameScore.get(key);

							if (score.getWinLose().equalsIgnoreCase("WIN")) {
								gameScore.put(key, new int[] { gameScoreValue[0] + 1, gameScoreValue[1] });
							} else {
								gameScore.put(key, new int[] { gameScoreValue[0], gameScoreValue[1] + 1 });
							}
						} else {
							if (score.getWinLose().equalsIgnoreCase("WIN")) {
								gameScore.put(key, new int[] { 1, 0 });
							} else {
								gameScore.put(key, new int[] { 0, 1 });
							}
						}
					}
				}
			}
		} catch (final IOException e) {
			LOG.error("Error creating personas.");
		}
		return gameScore;
	}
}