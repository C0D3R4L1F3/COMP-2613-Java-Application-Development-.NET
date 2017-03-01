/**
 * Project: A00973569Lab9
 * File: Dao.java
 * Date: June 5, 2016
 * Time: 7:58:41 PM
 */
package a00973569.lab9.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.lab9.db.Database;

/**
 * Data access object class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 9.0
 */
public abstract class Dao {

	protected final Database _database;
	protected final String _tableName;
	private static final Logger LOG = LogManager.getLogger(PlayerDao.class);

	/**
	 * Initialize a DAO object
	 * 
	 * @param database
	 *            the database given
	 * @param tableName
	 *            the name given
	 */
	protected Dao(final Database database, final String tableName) {
		LOG.info("Starting " + getClass().toString());
		_database = database;
		_tableName = tableName;
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
			final Connection connection = _database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(createStatement);
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
			final Connection connection = _database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(updateStatement);
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
			final Connection connection = _database.getConnection();
			statement = connection.createStatement();
			if (_database.tableExists(_tableName)) {
				statement.executeUpdate("drop table " + _tableName);
			}
		} finally {
			close(statement);
		}
	}

	/**
	 * Closes the statement
	 * 
	 * @param statement
	 *            the to close
	 */
	protected void close(final Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
}