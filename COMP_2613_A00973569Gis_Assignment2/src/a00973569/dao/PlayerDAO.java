/**
 * Project: A00973569Gis
 * File: PlayerDAO.java
 * Date: June 23, 2016
 * Time: 8:45:50 PM
 */

package a00973569.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.data.Player;

/**
 * Player data access object class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class PlayerDAO extends Dao {

	public static final String TABLE_NAME = "players";
	private static PlayerDAO playerDao;
	private static final Logger LOG = LogManager.getLogger(PlayerDAO.class);

	/**
	 * Default constructor
	 */
	private PlayerDAO() {
		super(TABLE_NAME);
	}

	/**
	 * Gets the Player data access object
	 * 
	 * @return the playerDao
	 */
	public static PlayerDAO getPlayerDAO() {
		if (playerDao == null) {
			playerDao = new PlayerDAO();
		}
		return playerDao;
	}

	/**
	 * Creates players table
	 * 
	 * @throws SQLException
	 *             if database error occurs
	 */
	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + TABLE_NAME);
		final String createStatement = "create table players(playerId VARCHAR(10) not null, first_name VARCHAR(64), last_name VARCHAR(64), email VARCHAR(64), birthdate VARCHAR(64), primary key (playerId) )";
		super.create(createStatement);
		LOG.info("Executed command: " + createStatement);
	}

	/**
	 * Inserts singular player into database
	 * 
	 * @param player
	 *            the player to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void add(final Player player) throws SQLException {
		Connection connection;
		Statement statement = null;

		try {
			connection = database.connect();
			statement = connection.createStatement();
			final String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s', '%s')", tableName, player.getId(), player.getfName(), player.getlName(),
					player.getEmail(), player.getBirthdate());
			statement.executeUpdate(insertString);
			LOG.info(insertString);
		} finally {
			close(statement);
		}
	}

	/**
	 * Updates record in the database with the given Player object
	 * 
	 * @param player
	 *            the player to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void update(final Player player) throws SQLException {
		Connection connection;
		Statement statement = null;

		try {
			connection = database.connect();
			statement = connection.createStatement();

			final String sqlString = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'", tableName, "first_name", player.getfName(), "last_name",
					player.getlName(), "email", player.getEmail(), "birthdate", player.getBirthdate(), "playerId", player.getId());
			LOG.debug("Update statment: " + sqlString);
			final int rowcount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	/**
	 * Deletes a player from database
	 * 
	 * @param player
	 *            the player to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void delete(final Player player) throws SQLException {
		final String deleteStatement = "DELETE FROM " + TABLE_NAME + " WHERE playerId = " + player.getId();
		super.delete(deleteStatement);
		LOG.info(deleteStatement);
	}

	/**
	 * Retrieves a record from the table player with the given id
	 * 
	 * @param id
	 *            the id to set
	 * @return the player
	 * @throws SQLException
	 *             if database error occurs
	 */
	public Player readById(final String id) throws SQLException {
		Connection connection;
		Statement statement = null;
		Player player = null;

		try {
			connection = database.connect();
			statement = connection.createStatement();
			final String sqlString = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, "playerId", id);
			final ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				player = new Player(resultSet.getString("playerId"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"),
						resultSet.getString("birthdate"));
			}
		} finally {
			close(statement);
		}
		return player;
	}

	/**
	 * Deletes record by given id
	 * 
	 * @param id
	 *            the player to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void deleteById(final String id) throws SQLException {
		Connection connection;
		Statement statement = null;

		try {
			connection = database.connect();
			statement = connection.createStatement();
			final String sqlString = String.format("DELETE from %s WHERE %s='%s'", tableName, "playerId", id);
			final int rowsDeleted = statement.executeUpdate(sqlString);
			LOG.info("Deleted " + rowsDeleted + " rows from " + tableName);
		} finally {
			close(statement);
		}
	}

	/**
	 * Popular the table players with a list of Player objects
	 * 
	 * @param players
	 *            the players to set
	 * @throws SQLException
	 */
	public void insertPlayers(final List<Player> players) throws SQLException {
		for (final Player player : players) {
			add(player);
		}
	}

	/**
	 * Retrieves a list of all gamertags in the database
	 * 
	 * @return the List<String> gamertags
	 * @throws SQLException
	 *             if database error occurs
	 * @throws Exception
	 *             if conditions are not met
	 */
	public List<String> getGamertags() throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		final List<String> gamertags = new ArrayList<>();

		try {
			connection = database.connect();
			statement = connection.createStatement();
			// Execute a statement
			final String sqlString = String.format("SELECT gamertag FROM " + tableName);
			final ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				gamertags.add(resultSet.getString("gamertag"));
			}
		} finally {
			close(statement);
		}
		return gamertags;
	}

	/**
	 * Retrieves the player from the database associated with the given gamertag
	 * 
	 * @param gamertag
	 *            the gamertag to set
	 * @return the player
	 * @throws SQLException
	 *             if database error occurs
	 */
	public Player getPlayer(final String gamertag) throws SQLException {
		Player player = null;
		Connection connection;
		Statement statement = null;
		String player_id = "";

		try {
			connection = database.connect();
			statement = connection.createStatement();
			// Execute a statement
			final String sqlString = "SELECT * FROM personas where gamertag = '" + gamertag + "'";
			LOG.info(sqlString);
			final ResultSet result1 = statement.executeQuery(sqlString);
			while (result1.next()) {
				player_id = result1.getString("player_id");
			}
			final String selectPlayer = "SELECT * FROM players where playerId = '" + player_id + "'";
			final ResultSet resultSet = statement.executeQuery(selectPlayer);

			while (resultSet.next()) {
				player = new Player(resultSet.getString("playerId"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"),
						resultSet.getString("birthdate"));
			}
		} finally {
			close(statement);
		}
		return player;
	}

	/**
	 * Selects all the players
	 * 
	 * @return the players
	 * @throws SQLException
	 *             if database error occurs
	 */
	public List<Player> selectAll() throws SQLException {
		final List<Player> players = new ArrayList<>();
		final ResultSet resultSet = super.selectAllFromDb();

		while (resultSet.next()) {
			players.add(new Player(String.valueOf(resultSet.getInt("playerId")), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"),
					resultSet.getString("birthdate")));
		}
		return players;
	}
}