/**
 * Project: A00973569Gis
 * File: PlayerFormat.java
 * Date: June 23, 2016
 * Time: 9:00:10 PM
 */

package a00973569.io;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.PlayerDAO;
import a00973569.data.Persona;
import a00973569.data.Player;
import a00973569.data.Score;

/**
 * Formats the players
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class PlayerFormat {

	private static final String INPUT_FILENAME = "players.dat";
	private final List<Player> listOfPlayers;
	private final Database db;
	private final PlayerDAO playerDAO;
	private static final Logger LOG = LogManager.getLogger(PlayerFormat.class);

	/**
	 * Constructor to initialize a PlayerFormat object
	 */
	public PlayerFormat() {
		super();
		final List<Player> listOfPlayers = new ArrayList<>();
		db = Database.getDatabaseInstance();
		final Connection connection = db.connect();
		playerDAO = PlayerDAO.getPlayerDAO();

		try {
			LOG.info("Reading players.dat file");
			if (!Database.tableExists(connection, "players")) {
				playerDAO.create();
				final List<String> arrayOfInfo = FileInput.readFile(INPUT_FILENAME);

				for (final String line : arrayOfInfo) {
					final Player player = this.createPlayer(line);
					listOfPlayers.add(player);
					playerDAO.add(player);
				}
			}
		} catch (final SQLException e1) {
			LOG.error("Cannot create players table. Class: PlayerFormat.");
		} catch (final IOException e) {
			LOG.error("Problem reading player data file.");
		}
		this.listOfPlayers = listOfPlayers;
	}

	/**
	 * Gets list of players
	 * 
	 * @return the listOfPlayers
	 */
	public List<Player> getListOfPlayers() {
		return listOfPlayers;
	}

	/**
	 * Creates a player
	 * 
	 * @param playerInfo
	 *            the playerInfo to set
	 * @return the player
	 */
	public Player createPlayer(final String playerInfo) {
		final String[] playerInfoString = playerInfo.split("\\|");
		final Player player = new Player(playerInfoString[0], playerInfoString[1], playerInfoString[2], playerInfoString[3], playerInfoString[4]);
		return player;
	}

	/**
	 * Calculates the age of a player
	 * 
	 * @param birthday
	 *            the birthday to set
	 * @return p.getYears();
	 */
	public static int calculateAge(final String birthday) {
		final int year = Integer.parseInt(birthday.substring(0, 4));
		final int month = Integer.parseInt(birthday.substring(4, 6));
		final int day = Integer.parseInt(birthday.substring(6));
		final LocalDate today = LocalDate.now();
		final LocalDate birthdayDate = LocalDate.of(year, month, day);
		final Period p = Period.between(birthdayDate, today);
		return p.getYears();
	}

	/**
	 * Calculates the total games played
	 * 
	 * @param player
	 *            the player to set
	 * @return the total
	 */
	public static int calculateTotalGamesPlayed(final Player player) {
		int total = 0;
		final List<Persona> playerPersonas = PersonaFormat.getPersonas(player.getId());
		final List<Score> listOfScores = ScoreFormat.getListOfScores();

		for (final Score score : listOfScores) {
			for (final Persona p : playerPersonas) {
				if (p.getId().equalsIgnoreCase(score.getPersonaId())) {
					total++;
				}
			}
		}
		return total;
	}

	/**
	 * Calculates the total wins
	 * 
	 * @param player
	 *            the player to set
	 * @return the totalWins
	 */
	public static int calculateTotalWins(final Player player) {
		int totalWins = 0;
		final List<Persona> playerPersonas = PersonaFormat.getPersonas(player.getId());
		final List<Score> listOfScores = ScoreFormat.getListOfScores();

		for (final Score score : listOfScores) {
			for (final Persona p : playerPersonas) {
				if (p.getId().equalsIgnoreCase(score.getPersonaId()) && score.getWinLose().equalsIgnoreCase("WIN")) {
					totalWins++;
				}
			}
		}
		return totalWins;
	}
}