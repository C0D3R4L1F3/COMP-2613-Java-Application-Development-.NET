/**
 * Project: A00973569Gis
 * File: LeaderboardDAO.java
 * Date: June 24, 2016
 * Time: 7:00:45 PM
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

import a00973569.data.Leaderboard;
import a00973569.ui.MainFrame;

/**
 * Leaderboard data access object class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class LeaderboardDAO extends Dao {

	public static final String TABLE_NAME = "leaderboard";
	private static LeaderboardDAO leaderboardDao;
	private static final Logger LOG = LogManager.getLogger(LeaderboardDAO.class);

	/**
	 * Default constructor
	 */
	private LeaderboardDAO() {
		super(TABLE_NAME);
	}

	/**
	 * Gets the Leaderboard data access object
	 * 
	 * @return the leaderboardDao
	 */
	public static LeaderboardDAO getLeaderboardDao() {
		if (leaderboardDao == null) {
			leaderboardDao = new LeaderboardDAO();
		}
		return leaderboardDao;
	}

	/**
	 * Creates leaderboard table
	 * 
	 * @throws SQLException
	 *             if database error occurs
	 */
	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + TABLE_NAME);
		final String createStatement = "create table leaderboard(wins VARCHAR(10), losses VARCHAR(10), game_name VARCHAR(64), gamertag VARCHAR(64), platform VARCHAR(64) )";
		super.create(createStatement);
		LOG.info("Executed statement: " + createStatement);
	}

	/**
	 * Inserts singular item into table
	 * 
	 * @param item
	 *            the item to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void addLeaderboardItem(final Leaderboard item) throws SQLException {
		final String insertString = String.format("insert into %s (wins, losses, game_name, gamertag, platform) values('%s', '%s', '%s', '%s', '%s')", TABLE_NAME, item.getWins(),
				item.getLosses(), item.getGameName(), item.getGamerTag(), item.getPlatform());
		super.add(insertString);
		LOG.info(insertString);
	}

	/**
	 * Update the leaderboard table.
	 * 
	 * @param item
	 *            the item to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void update(final Leaderboard item) throws SQLException {
		Connection connection;
		Statement statement = null;

		try {
			connection = database.connect();
			statement = connection.createStatement();
			final String sqlString = String.format("UPDATE %s SET %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s=%s", TABLE_NAME, "wins", item.getWins(), "losses",
					item.getLosses(), "game_name", item.getGameName(), "gamertag", item.getGamerTag(), "platform", item.getPlatform());
			LOG.debug("Update statment: " + sqlString);
			final int rowcount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	/**
	 * Deletes singular item out of the table
	 * 
	 * @param item
	 *            the item to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void delete(final Leaderboard item) throws SQLException {
		final String deleteStatement = "DELETE FROM " + TABLE_NAME + " WHERE gamertag = " + item.getGamerTag();
		super.delete(deleteStatement);
		LOG.info(deleteStatement);
	}

	/**
	 * Retrieves a list of all Leaderboard rows in the table
	 * 
	 * @param order
	 *            the order to set
	 * @param desc
	 *            the desc to set
	 * @return the List<Leaderboard> rows
	 * @throws SQLException
	 *             if database error occurs
	 * @throws Exception
	 *             if conditions are not met
	 */
	public List<Leaderboard> getLeaderboardRows(final String order, final boolean desc) throws SQLException, Exception {
		final String selectString = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME, "gamertag", order);
		LOG.debug(selectString);
		Connection connection;
		Statement statement = null;
		ResultSet resultSet = null;
		final List<Leaderboard> leaderboardRows = new ArrayList<>();

		try {
			connection = database.connect();
			statement = connection.createStatement();
			String sqlString = "";

			if (order == null) {
				sqlString = String.format("SELECT * FROM " + TABLE_NAME);
			} else {
				if (order.equals("byGame")) {
					if (MainFrame.filterGamertag.equals("")) {
						sqlString = String.format("SELECT * FROM " + TABLE_NAME + " ORDER BY game_name");
					} else {
						sqlString = String.format("SELECT * FROM " + TABLE_NAME + " WHERE gamertag = '" + MainFrame.filterGamertag + "' ORDER BY game_name");
					}
				} else {
					if (MainFrame.filterGamertag.equals("")) {
						sqlString = String.format("SELECT * FROM " + TABLE_NAME + " ORDER BY (wins||losses)");
					} else {
						sqlString = String.format("SELECT * FROM " + TABLE_NAME + " WHERE gamertag= '" + MainFrame.filterGamertag + "' ORDER BY (wins||losses)");
					}
				}
				if (desc) {
					sqlString += " DESC";
				}
			}
			resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				leaderboardRows.add(new Leaderboard(resultSet.getInt("wins"), resultSet.getInt("losses"), resultSet.getString("game_name"), resultSet.getString("gamertag"),
						resultSet.getString("platform")));
			}
		} finally {
			close(statement);
		}
		return leaderboardRows;
	}
}