/**
 * Project: A00973569Gis
 * File: LeaderboardReportFormat.java
 * Date: June 20, 2016
 * Time: 1:02:15 PM
 */

package a00973569.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.data.Leaderboard;
import a00973569.data.Persona;
import a00973569.data.Score;

/**
 * Formats the leaderboard
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class LeaderboardReportFormat {

	private final List<Leaderboard> leaderboardRows;
	private static final Logger LOG = LogManager.getLogger(LeaderboardReportFormat.class);

	/**
	 * Constructor to initialize a LeaderboardReportFormat object
	 */
	public LeaderboardReportFormat() {
		super();
		this.leaderboardRows = this.createLeaderboardItems();
	}

	/**
	 * Sorts the leadboard
	 * 
	 * @param row
	 *            the row to set
	 * @return the row
	 */
	public List<Leaderboard> sortLeaderboard(final List<Leaderboard> row) {
		Collections.sort(row, new Comparator<Leaderboard>() {
			@Override
			public int compare(final Leaderboard first, final Leaderboard second) {
				return first.getGamerTag().compareTo(second.getGamerTag());
			}
		});
		return row;
	}

	/**
	 * Sorts the leaderboard by count
	 * 
	 * @param row
	 *            the row to set
	 * @return the row
	 */
	public List<Leaderboard> sortLeaderboardByCount(final List<Leaderboard> row) {
		Collections.sort(row, new Comparator<Leaderboard>() {
			@Override
			public int compare(final Leaderboard first, final Leaderboard second) {
				return first.wins - second.wins;
			}
		});
		return row;
	}

	/**
	 * Filters the platform
	 * 
	 * @param platform
	 *            the platform to set
	 * @return the filteredByPlatform
	 */
	public List<Leaderboard> filterByPlatform(final String platform) {
		final List<Leaderboard> allLeaderboardRows = this.leaderboardRows;
		final List<Leaderboard> filteredByPlatform = new ArrayList<>();

		for (final Leaderboard row : allLeaderboardRows) {
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
	 * Calculates the scores total
	 * 
	 * @return the gameTitleToTotal
	 */
	public static Map<String, Integer> calculateTotals() {
		final Map<String, Integer> gameTitleToTotal = new HashMap<>();
		final List<Score> listOfScores = ScoreFormat.getListOfScores();

		for (final Score score : listOfScores) {
			final String fullTitle = getTitleOfGameByGameId(score.getGameId());

			if (gameTitleToTotal.containsKey(fullTitle)) {
				gameTitleToTotal.put(fullTitle, gameTitleToTotal.get(fullTitle) + 1);
			} else {
				gameTitleToTotal.put(fullTitle, 1);
			}
		}
		return gameTitleToTotal;
	}

	/**
	 * Creates leaderboard items
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
			final String gameName = LeaderboardReportFormat.getTitleOfGameByGameId(gameId);
			final Persona persona = this.getPersonaById(personaID);
			// Get full title of game by id
			rows.add(new Leaderboard(value[0], value[1], gameName, persona.getGamerTag(), persona.getPlatform()));
		}
		return rows;
	}

	/**
	 * Gets a game name by gamer tag
	 * 
	 * @return the gameScore
	 */
	public Map<String, int[]> getGameNameByGamertag() {
		final Map<String, int[]> gameScore = new HashMap<>();

		try {
			final List<Persona> listOfPersonas = PersonaFormat.createListOfPersonas();
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
			LOG.error(e.getMessage());
		}
		return gameScore;
	}
}