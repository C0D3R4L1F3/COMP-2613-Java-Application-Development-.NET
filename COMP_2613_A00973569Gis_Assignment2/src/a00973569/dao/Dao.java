/**
 * Project: A00973569Gis
 * File: Dao.java
 * Date: June 23, 2016
 * Time: 7:58:41 PM
 */

package a00973569.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Data access object class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public abstract class Dao {

	protected final Database database;
	protected final String tableName;
	private static final Logger LOG = LogManager.getLogger(Dao.class);

	/**
	 * Initialize a DAO object
	 * 
	 * @param tableName
	 *            the name given
	 */
	protected Dao(final String tableName) {
		this.database = Database.getDatabaseInstance();
		this.tableName = tableName;
	}

	/**
	 * Abstract method to create database
	 * 
	 * @throws SQLException
	 *             if database error occurs
	 */
	public abstract void create() throws SQLException;

	/**
	 * Creates statement
	 * 
	 * @param createStatement
	 *            the statement to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	protected void create(final String createStatement) throws SQLException {
		Statement statement = null;

		try {
			final Connection connection = database.connect();
			statement = connection.createStatement();

			// Only create the table if it doesn't exist already
			if (!Database.tableExists(connection, tableName)) {
				statement.executeUpdate(createStatement);
			}
		} finally {
			close(statement);
		}
	}

	/**
	 * Adds an updated statement
	 * 
	 * @param updateStatement
	 *            the updated statement
	 * @throws SQLException
	 *             if database error occurs
	 */
	protected void add(final String updateStatement) throws SQLException {
		Statement statement = null;

		try {
			final Connection connection = database.connect();
			statement = connection.createStatement();
			statement.executeUpdate(updateStatement);
		} finally {
			close(statement);
		}
	}

	/**
	 * Deletes a statement
	 * 
	 * @param deleteStatement
	 *            the delete statement
	 * @throws SQLException
	 *             if database error occurs
	 */
	protected void delete(final String deleteStatement) throws SQLException {
		Statement statement = null;

		try {
			final Connection connection = database.connect();
			statement = connection.createStatement();
			statement.executeUpdate(deleteStatement);
		} finally {
			close(statement);
		}
	}

	/**
	 * Checks if drop table exist
	 * 
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void drop() throws SQLException {
		Statement statement = null;

		try {
			final Connection connection = database.connect();
			statement = connection.createStatement();
			if (Database.tableExists(connection, tableName)) {
				statement.executeUpdate("drop table " + tableName);
			}
		} finally {
			close(statement);
		}
	}

	/**
	 * Closes the statement
	 * 
	 * @param statement
	 *            the statement to close
	 */
	protected void close(final Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (final SQLException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * Selects all statements from resultSet
	 * 
	 * @return the resultSet
	 */
	protected ResultSet selectAllFromDb() {
		Connection connection;
		Statement statement = null;
		connection = database.connect();
		ResultSet resultSet = null;

		try {
			statement = connection.createStatement();
			final String sqlString = String.format("SELECT * FROM " + tableName);
			resultSet = statement.executeQuery(sqlString);
		} catch (final SQLException e) {
			LOG.error(e.getMessage());
		}
		return resultSet;
	}
}