/**
 * Project: A00973569Gis
 * File: GamesDAO.java
 * Date: June 23, 2016
 * Time: 8:30:41 PM
 */

package a00973569.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.data.Game;

/**
 * Games data access object class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class GamesDAO extends Dao {

	private static GamesDAO gamesDao;
	public static final String TABLE_NAME = "games";
	private static final Logger LOG = LogManager.getLogger(PlayerDAO.class);

	/**
	 * Default constructor
	 */
	private GamesDAO() {
		super(TABLE_NAME);
	}

	/**
	 * Gets the Games data access object
	 * 
	 * @return the gamesDao
	 */
	public static GamesDAO getGamesDAO() {
		if (gamesDao == null) {
			gamesDao = new GamesDAO();
		}
		return gamesDao;
	}

	/**
	 * Creates games table
	 * 
	 * @throws SQLException
	 *             if database error occurs
	 */
	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + TABLE_NAME);
		final String createStatement = "create table games(id VARCHAR(10) not null, name VARCHAR(64), producer VARCHAR(64) )";
		super.create(createStatement);
		LOG.info("Executed command: " + createStatement);
	}

	/**
	 * Inserts singular game into the table
	 * 
	 * @param game
	 *            the game to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void addGame(final Game game) throws SQLException {
		final String insertString = String.format("insert into %s values('%s', '%s', '%s')", TABLE_NAME, game.getId(), game.getName(), game.getProducer());
		super.add(insertString);
		LOG.info(insertString);
	}

	/**
	 * Update the game table.
	 * 
	 * @param game
	 *            the game to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void update(final Game game) throws SQLException {
		Connection connection;
		Statement statement = null;

		try {
			connection = database.connect();
			statement = connection.createStatement();
			final String sqlString = String.format("UPDATE %s SET %s='%s', %s='%s', %s='%s' WHERE %s=%s", TABLE_NAME, "id", game.getId(), "name", game.getName(), "producer",
					game.getProducer());
			LOG.debug("Update statment: " + sqlString);
			final int rowcount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	/**
	 * Deletes singular game out of the table
	 * 
	 * @param game
	 *            the game to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void delete(final Game game) throws SQLException {
		final String deleteStatement = "DELETE FROM " + TABLE_NAME + " WHERE id = " + game.getId();
		super.delete(deleteStatement);
		LOG.info(deleteStatement);
	}
}