/**
 * Project: A00973569Gis
 * File: GamesFormat.java
 * Date: June 23, 2016
 * Time: 9:02:26 PM
 */

package a00973569.io;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.GamesDAO;
import a00973569.data.Game;

/**
 * Formats the games
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class GamesFormat {

	private static final String INPUT_FILENAME = "games.dat";
	private final List<Game> listOfGames;
	private final GamesDAO gamesDao;
	private Database database;
	private static final Logger LOG = LogManager.getLogger(GamesFormat.class);

	/**
	 * Constructor to initialize a GamesFormat object
	 */
	public GamesFormat() {
		super();
		this.listOfGames = createGames();
		gamesDao = GamesDAO.getGamesDAO();

		try {
			LOG.info("Reading games.dat file");
			database = Database.getDatabaseInstance();
			if (!Database.tableExists(database.connect(), "games")) {
				gamesDao.create();
				for (final Game game : listOfGames) {
					gamesDao.addGame(game);
				}
			}
		} catch (final SQLException e) {
			LOG.error("Error creating games table. Class: GamesFormat. Method: Constructor.");
		}
	}

	/**
	 * Creates a game
	 * 
	 * @return the listOfGames
	 */
	public static List<Game> createGames() {
		final List<Game> listOfGames = new ArrayList<>();

		try {
			final List<String> gameInfo = FileInput.readFile(INPUT_FILENAME);
			for (final String gameInfoLine : gameInfo) {
				final String[] infoArray = gameInfoLine.split("\\|");
				listOfGames.add(new Game(infoArray[0], infoArray[1], infoArray[2]));
			}
		} catch (final IOException e) {
			LOG.error("Error reading games infomation file.");
		}
		return listOfGames;
	}

	/**
	 * Gets the Id by game name
	 * 
	 * @return the idToGameName
	 */
	public static Map<String, String> getIdGameName() {
		final Map<String, String> idToGameName = new HashMap<>();
		final List<Game> listOfGames = createGames();

		for (final Game game : listOfGames) {
			idToGameName.put(game.getId(), game.getName());
		}
		return idToGameName;
	}
}