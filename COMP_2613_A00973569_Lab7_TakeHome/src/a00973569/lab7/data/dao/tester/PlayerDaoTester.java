/**
 * Project: A00973569Lab7
 * File: PlayerDaoTester.java
 * Date: June 6, 2016
 * Time: 4:29:24 PM
 */
package a00973569.lab7.data.dao.tester;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.lab7.ApplicationException;
import a00973569.lab7.NotFoundException;
import a00973569.lab7.data.Player;
import a00973569.lab7.data.dao.PlayerDao;
import a00973569.lab7.data.db.Database;

/**
 * Class to test the PlayerDao class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 7.0
 */
public class PlayerDaoTester {

	private static Properties _properties;
	private static Database database;
	private static final String DB_PROPERTIES_FILENAME = "db.properties";
	private static final Logger LOG = LogManager.getLogger(PlayerDaoTester.class);

	/**
	 * Default constructor
	 */
	public PlayerDaoTester() {
		super();
	}

	/**
	 * Retrieves a gamer tags list and there fullNames
	 * 
	 * 
	 * @param databasePlayers
	 *            the databasePlayers to set
	 * @throws ClassNotFoundException
	 *             if loaded through its String name
	 * @throws SQLException
	 *             if database error occurs
	 * @throws ApplicationException
	 *             if invalid information is given
	 * @throws FileNotFoundException
	 *             if file is not found
	 * @throws IOException
	 *             if IO not valid
	 * @throws NotFoundException
	 *             if object is not found
	 */
	public static void retrieveGamerTagsList(final List<Player> databasePlayers)
			throws ClassNotFoundException, SQLException, ApplicationException, FileNotFoundException, IOException, NotFoundException {
		final File file = new File(DB_PROPERTIES_FILENAME);
		if (!file.exists()) {
			throw new ApplicationException("File DB properties " + DB_PROPERTIES_FILENAME + " doesnt exist");
		}

		_properties = new Properties();
		_properties.load(new FileInputStream(file));

		database = new Database(_properties);
		database.getConnection();
		final PlayerDao playerDao = new PlayerDao(database);

		if (!database.tableExists(playerDao.getTableName())) {
			LOG.info("Table " + playerDao.getTableName() + " doesnt exists");
			playerDao.create();
			LOG.info("Table " + playerDao.getTableName() + " created");
			playerDao.fillPlayers(databasePlayers);
		} else {
			LOG.info("Table " + playerDao.getTableName() + " exists");
			// playerDao.drop(); // for testing
			// LOG.info("Table " + playerDao.getTableName() + " dropped"); // for testing
		}
		LOG.info("Loaded Table " + playerDao.getTableName() + " contains " + playerDao.countAll() + " gamer tags from the database");
		LOG.info("Players - " + playerDao.loadAll().toString());
		for (final String player : playerDao.getGamerTags()) {
			LOG.debug("SELECT * FROM Players WHERE gamerTag: " + String.format("%-13s%-2s%-10s", player, "=", playerDao.getPlayer(player).getFullName()));
		}
		database.shutdown();
		LOG.info(database.getClass() + " stopped");
	}
}
