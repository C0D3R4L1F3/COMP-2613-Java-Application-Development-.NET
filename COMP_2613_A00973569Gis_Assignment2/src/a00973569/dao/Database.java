/**
 * Project: A00973569Gis
 * File: Database.java
 * Date: June 23, 2016
 * Time: 8:10:41 PM
 */

package a00973569.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Database class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Database {

	private static Database database;
	private static final Logger LOG = LogManager.getLogger(Database.class);

	/**
	 * SQL credentials
	 */
	// public static final String DB_DRIVER_KEY = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	// public static final String DB_URL_KEY = "jdbc:microsoft:sqlserver://142.232.110.196";
	// public static final String DB_USER_KEY = "jspweb";
	// public static final String DB_PASSWORD_KEY = "jspweb";

	/**
	 * Derby credentials
	 */
	public static final String DB_DRIVER_KEY = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String DB_URL_KEY = "jdbc:derby:gis_db;create=true";
	public static final String DB_USER_KEY = "admin";
	public static final String DB_PASSWORD_KEY = "admin";

	private static Connection connection;

	/**
	 * Default Constructor
	 */
	private Database() {
		super();
	}

	/**
	 * Runs connection
	 */
	public void run() {
		connect();
	}

	/**
	 * Gets Instance of database
	 * 
	 * @return the database
	 */
	public static Database getDatabaseInstance() {
		if (database == null) {
			database = new Database();
		}
		return database;
	}

	/**
	 * Handles the connection
	 * 
	 * @return the connection
	 */
	public Connection connect() {
		try {
			Class.forName(DB_DRIVER_KEY);
			if (connection != null) {
				return connection;
			}
			connection = DriverManager.getConnection(DB_URL_KEY);
		} catch (final ClassNotFoundException e) {
			LOG.error("Class couldn't be loaded.");
		} catch (final SQLException e) {
			LOG.error("SQL syntax error.");
		}
		return connection;
	}

	/**
	 * Shuts down the connection
	 */
	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (final SQLException e) {
				LOG.error("Connection could not be closed.");
			}
		}
	}

	/**
	 * Checks to if table exists
	 * 
	 * @param connection
	 *            the connection to set
	 * @param tableName
	 *            the tableName to set
	 * @return true if results set otherwise false
	 * @throws SQLException
	 *             if database error occurs
	 */
	public static boolean tableExists(final Connection connection, final String tableName) throws SQLException {
		final DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = null;
		String rsTableName = null;

		try {
			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				rsTableName = resultSet.getString("TABLE_NAME");
				if (rsTableName.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultSet.close();
		}
		return false;
	}
}