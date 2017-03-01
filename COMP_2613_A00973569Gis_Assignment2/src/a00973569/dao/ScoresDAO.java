/**
 * Project: A00973569Gis
 * File: ScoresDAO.java
 * Date: June 24, 2016
 * Time: 5:55:23 PM
 */

package a00973569.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.data.Score;

/**
 * Scores access object class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class ScoresDAO extends Dao {

	public static final String TABLE_NAME = "scores";
	private static ScoresDAO scoresDao;
	private static final Logger LOG = LogManager.getLogger(PlayerDAO.class);

	/**
	 * Default constructor
	 */
	private ScoresDAO() {
		super(TABLE_NAME);
	}

	/**
	 * Gets the Scores data access object
	 * 
	 * @return the scoresDao
	 */
	public static ScoresDAO getScoresDao() {
		if (scoresDao == null) {
			scoresDao = new ScoresDAO();
		}
		return scoresDao;
	}

	/**
	 * Creates scores table
	 * 
	 * @throws SQLException
	 *             if database error occurs
	 */
	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + TABLE_NAME);
		final String createStatement = "create table scores(persona_id VARCHAR(10) not null, game_id VARCHAR(10), outcome VARCHAR(10) )";
		super.create(createStatement);
		LOG.info("Executed command: " + createStatement);
	}

	/**
	 * Inserts singular score into database
	 * 
	 * @param score
	 *            the score to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void addScore(final Score score) throws SQLException {
		final String addStatement = String.format("insert into %s values('%s', '%s', '%s')", TABLE_NAME, score.getPersonaId(), score.getGameId(), score.getWinLose());
		super.add(addStatement);
		LOG.info(addStatement);
	}

	/**
	 * Update the game table.
	 * 
	 * @param score
	 *            the score to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void update(final Score score) throws SQLException {
		Connection connection;
		Statement statement = null;

		try {
			connection = database.connect();
			statement = connection.createStatement();
			final String sqlString = String.format("UPDATE %s SET %s='%s', %s='%s', %s='%s' WHERE %s=%s", TABLE_NAME, "persona_id", score.getPersonaId(), "game_id",
					score.getGameId(), "outcome", score.getWinLose());
			LOG.debug("Update statment: " + sqlString);
			final int rowcount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	/**
	 * Deletes an id from database
	 * 
	 * @param id
	 *            the id to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	@Override
	public void delete(final String id) throws SQLException {
		final String deleteStatement = "DELETE FROM " + TABLE_NAME + " WHERE persona_id = " + id;
		super.delete(deleteStatement);
		LOG.info(deleteStatement);
	}

	/**
	 * Gets the games totals
	 * 
	 * @return the gamesTotals
	 * @throws SQLException
	 *             if database error occurs
	 */
	public Map<String, Integer> getTotals() throws SQLException {
		Connection connection;
		Statement statement = null;
		final Map<String, Integer> gameTotals = new HashMap<>();

		try {
			connection = database.connect();
			statement = connection.createStatement();
			final String sqlString = String.format("SELECT games.name, COUNT(scores.game_id) AS total FROM scores LEFT JOIN games ON scores.game_id = games.id GROUP BY name");
			final ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				gameTotals.put(resultSet.getString("name"), resultSet.getInt("total"));
			}
		} finally {
			close(statement);
		}
		return gameTotals;
	}

	/**
	 * Selects all the scores
	 * 
	 * @return the scores
	 * @throws SQLException
	 *             if database error occurs
	 */
	public List<Score> selectAll() throws SQLException {
		final List<Score> scores = new ArrayList<>();
		final ResultSet resultSet = super.selectAllFromDb();

		while (resultSet.next()) {
			scores.add(new Score(String.valueOf(resultSet.getInt("persona_id")), resultSet.getString("game_id"), resultSet.getString("outcome")));
		}
		return scores;
	}
}