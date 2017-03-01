/**
 * Project: A00973569Lab7
 * File: PlayerDao.java
 * Date: June 5, 2016
 * Time: 7:58:41 PM
 */
package a00973569.lab7.data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import a00973569.lab7.NotFoundException;
import a00973569.lab7.data.Player;
import a00973569.lab7.data.db.Database;

/**
 * Player data access object class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 7.0
 */
public class PlayerDao extends Dao {

	private static final String table_name = "A00973569_Player";
	private static final int STATEMENT_ONE = 1;
	private static final int STATEMENT_TWO = 2;
	private static final int STATEMENT_THREE = 3;
	private static final int STATEMENT_FOUR = 4;
	private static final int STATEMENT_FIVE = 5;
	private static final int STATEMENT_SIX = 6;
	private static final int ROW_COUNT_ZERO = 0;
	private static final int ROW_COUNT_ONE = 1;
	private static final int INDEX_ZERO = 0;
	private static final int INDEX_ONE = 1;
	private static final int PLAYER_SIZE_ZERO = 0;
	private static final int ID_ZERO = 0;

	/**
	 * Initialize a PDAO object
	 * 
	 * @param database
	 *            the database given
	 */
	public PlayerDao(final Database database) {
		super(database, table_name);
	}

	/**
	 * @return a new Player object
	 */
	public Player createValueObject() {
		return new Player();
	}

	/**
	 * Creates a new valueObject
	 * 
	 * @param valueObject
	 *            the valueObject to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public synchronized void create(final Player valueObject) throws SQLException {
		String sql = "";
		PreparedStatement statement = null;

		try {
			final Connection connection = _database.getConnection();
			sql = "INSERT INTO " + table_name + " ( id, firstName, lastName, " + "emailAddress, gamerTag, birthDate) VALUES (?, ?, ?, ?, ?, ?) ";
			statement = connection.prepareStatement(sql);

			statement.setInt(STATEMENT_ONE, valueObject.getId());
			statement.setString(STATEMENT_TWO, valueObject.getFirstName());
			statement.setString(STATEMENT_THREE, valueObject.getLastName());
			statement.setString(STATEMENT_FOUR, valueObject.getEmailAddress());
			statement.setString(STATEMENT_FIVE, valueObject.getGamerTag());
			statement.setDate(STATEMENT_SIX, new Date(valueObject.getBirthDate().getTime().getTime()));

			final int rowcount = update(statement);
			if (rowcount != ROW_COUNT_ONE) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * Reads a valueObject
	 * 
	 * @param valueObject
	 *            the valueObject to set
	 * @throws NotFoundException
	 *             if object is not found
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void read(final Player valueObject) throws NotFoundException, SQLException {
		final String sql = "SELECT * FROM " + table_name + " WHERE (id = ? ) ";
		PreparedStatement statement = null;

		try {
			final Connection connection = _database.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(STATEMENT_ONE, valueObject.getId());

			singleQuery(statement, valueObject);
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * Updates the statement
	 * 
	 * @param statement
	 *            the statement to set
	 * @return the results
	 * @throws SQLException
	 *             if database error occurs
	 */
	protected int update(final PreparedStatement statement) throws SQLException {
		final int result = statement.executeUpdate();

		return result;
	}

	/**
	 * Deletes the valueObject
	 * 
	 * @param valueObject
	 *            the valueObject to set
	 * @throws NotFoundException
	 *             if object is not found
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void delete(final Player valueObject) throws NotFoundException, SQLException {
		final String sql = "DELETE FROM " + table_name + " WHERE (id = ? ) ";
		PreparedStatement statement = null;

		try {
			final Connection connection = _database.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(STATEMENT_ONE, valueObject.getId());

			final int rowcount = update(statement);
			if (rowcount == ROW_COUNT_ZERO) {
				// System.out.println("Object could not be deleted (PrimaryKey not found)");
				throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
			}
			if (rowcount > ROW_COUNT_ONE) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
				throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * @return the gamerTags
	 * @throws SQLException
	 *             if database error occurs
	 */
	public List<String> getGamerTags() throws SQLException {
		final List<Player> searchResults = loadAll();

		final List<String> gamerTags = new ArrayList<String>();
		for (final Player player : searchResults) {
			gamerTags.add(player.getGamerTag());
		}
		return gamerTags;
	}

	/**
	 * @param gamerTag
	 *            the gamerTag to set
	 * @return the playerResults
	 * @throws SQLException
	 *             if database error occurs
	 * @throws NotFoundException
	 *             if object is not found
	 */
	@SuppressWarnings("unchecked")
	public Player getPlayer(final String gamerTag) throws SQLException, NotFoundException {
		final Player player = new Player();
		player.setGamerTag(gamerTag);
		final List<Player> playerResult = (List<Player>) searchMatching(player);

		if (playerResult.size() == PLAYER_SIZE_ZERO) {
			// System.out.println("Player Object Not Found!");
			throw new NotFoundException("Player Object by tag Not Found!");
		}
		return playerResult.get(INDEX_ZERO);
	}

	/**
	 * @param id
	 *            the id to set
	 * @return the valueObject
	 * @throws NotFoundException
	 *             if object is not found
	 * @throws SQLException
	 *             if database error occurs
	 */
	public Player getObject(final int id) throws NotFoundException, SQLException {
		final Player valueObject = createValueObject();
		valueObject.setId(id);
		read(valueObject);
		return valueObject;
	}

	/**
	 * Loads all the players
	 * 
	 * @return the searchResults
	 * @throws SQLException
	 *             if database error occurs
	 */
	public List<Player> loadAll() throws SQLException {
		final Connection connection = _database.getConnection();
		final String sql = "SELECT * FROM " + table_name + " ORDER BY id ASC ";
		final List<Player> searchResults = listQuery(connection.prepareStatement(sql));

		return searchResults;
	}

	/**
	 * Fills the players
	 * 
	 * @param players
	 *            the players to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void fillPlayers(final List<Player> players) throws SQLException {
		for (final Player player : players) {
			create(player);
		}
	}

	/**
	 * Saves the players
	 * 
	 * @param valueObject
	 *            the valueObject to set
	 * @throws NotFoundException
	 *             if object is not found
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void save(final Player valueObject) throws NotFoundException, SQLException {
		final String sql = "UPDATE " + table_name + " SET firstName = ?, lastName = ?, emailAddress = ?, " + "gamerTag = ?, birthDate = ? WHERE (id = ? ) ";
		PreparedStatement statement = null;

		try {
			final Connection connection = _database.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(STATEMENT_ONE, valueObject.getFirstName());
			statement.setString(STATEMENT_TWO, valueObject.getLastName());
			statement.setString(STATEMENT_THREE, valueObject.getEmailAddress());
			statement.setString(STATEMENT_FOUR, valueObject.getGamerTag());
			statement.setDate(STATEMENT_SIX, new Date(valueObject.getBirthDate().getTime().getTime()));

			statement.setInt(STATEMENT_SIX, valueObject.getId());

			final int rowcount = update(statement);
			if (rowcount == ROW_COUNT_ZERO) {
				// System.out.println("Object could not be saved! (PrimaryKey not found)");
				throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > ROW_COUNT_ONE) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
				throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * Deletes all the players
	 * 
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void deleteAll() throws SQLException {
		final String sql = "DELETE FROM " + table_name;
		PreparedStatement statement = null;

		try {
			final Connection connection = _database.getConnection();
			statement = connection.prepareStatement(sql);
			@SuppressWarnings("unused")
			final int rowcount = update(statement);
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * Counts all the players
	 * 
	 * @return the allRows
	 * @throws SQLException
	 *             if database error occurs
	 */
	public int countAll() throws SQLException {
		final String sql = "SELECT count(*) FROM " + table_name;
		PreparedStatement statement = null;
		ResultSet result = null;
		int allRows = ROW_COUNT_ZERO;

		try {
			final Connection connection = _database.getConnection();
			statement = connection.prepareStatement(sql);
			result = statement.executeQuery();

			if (result.next()) {
				allRows = result.getInt(INDEX_ONE);
			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return allRows;
	}

	/**
	 * Search and matches the players
	 * 
	 * @param valueObject
	 *            the valueObject to set
	 * @return the searchResults
	 * @throws SQLException
	 *             if database error occurs
	 */
	public List<?> searchMatching(final Player valueObject) throws SQLException {
		final Connection connection = _database.getConnection();
		List<?> searchResults;

		boolean first = true;
		final StringBuffer sql = new StringBuffer("SELECT * FROM " + table_name + " WHERE 1=1 ");

		if (valueObject.getId() != ID_ZERO) {
			if (first) {
				first = false;
			}
			sql.append("AND id = ").append(valueObject.getId()).append(" ");
		}

		if (valueObject.getFirstName() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND firstName LIKE '").append(valueObject.getFirstName()).append("%' ");
		}

		if (valueObject.getLastName() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND lastName LIKE '").append(valueObject.getLastName()).append("%' ");
		}

		if (valueObject.getEmailAddress() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND emailAddress LIKE '").append(valueObject.getEmailAddress()).append("%' ");
		}

		if (valueObject.getGamerTag() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND gamerTag LIKE '").append(valueObject.getGamerTag()).append("%' ");
		}

		if (valueObject.getBirthDate() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND birthDate = '").append(valueObject.getBirthDate()).append("' ");
		}

		sql.append("ORDER BY id ASC ");

		if (first) {
			searchResults = new ArrayList<Player>();
		} else {
			searchResults = listQuery(connection.prepareStatement(sql.toString()));
		}
		return searchResults;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return table_name;
	}

	/**
	 * A single query of a player
	 * 
	 * @param statement
	 *            the statement to set
	 * @param valueObject
	 *            the valueObject to set
	 * @throws NotFoundException
	 *             if object is not found
	 * @throws SQLException
	 *             if database error occurs
	 */
	protected void singleQuery(final PreparedStatement statement, final Player valueObject) throws NotFoundException, SQLException {
		ResultSet result = null;

		try {

			result = statement.executeQuery();

			if (result.next()) {

				valueObject.setId(result.getInt("id"));
				valueObject.setFirstName(result.getString("firstName"));
				valueObject.setLastName(result.getString("lastName"));
				valueObject.setEmailAddress(result.getString("emailAddress"));
				valueObject.setGamerTag(result.getString("gamerTag"));
				valueObject.setBirthDate(result.getDate("birthDate"));

			} else {
				// System.out.println("Player Object Not Found!");
				throw new NotFoundException("Player Object Not Found!");
			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * A List query of players
	 * 
	 * @param statement
	 *            the statement to set
	 * @return the searchResults
	 * @throws SQLException
	 *             if database error occurs
	 */
	protected List<Player> listQuery(final PreparedStatement statement) throws SQLException {
		final ArrayList<Player> searchResults = new ArrayList<Player>();
		ResultSet result = null;

		try {
			result = statement.executeQuery();

			while (result.next()) {
				final Player temp = createValueObject();

				temp.setId(result.getInt("id"));
				temp.setFirstName(result.getString("firstName"));
				temp.setLastName(result.getString("lastName"));
				temp.setEmailAddress(result.getString("emailAddress"));
				temp.setGamerTag(result.getString("gamerTag"));
				temp.setBirthDate(result.getDate("birthDate"));

				searchResults.add(temp);
			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return searchResults;
	}

	/*
	 * (non-Javadoc)
	 * @see a00973569.data.lab7.dao.Dao#create()
	 */
	@Override
	public void create() throws SQLException {
		final String createStatement = "CREATE TABLE  " + table_name
				+ " (id bigint NOT NULL,firstName varchar(255),lastName varchar(255),emailAddress varchar(255),gamerTag varchar(255),birthDate date)";
		super.create(createStatement);
	}
}