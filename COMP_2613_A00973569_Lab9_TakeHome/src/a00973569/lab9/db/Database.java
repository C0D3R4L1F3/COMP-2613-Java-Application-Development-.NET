/**
 * Project: A00973569Lab9
 * File: Database.java
 * Date: June 5, 2016
 * Time: 8:00:56 PM
 */
package a00973569.lab9.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Database class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 9.0
 */
public class Database {

	private static final String DB_DRIVER_KEY = "db.driver";
	private static final String DB_URL_KEY = "db.url";
	private static final String DB_USER_KEY = "db.user";
	private static final String DB_PASSWORD_KEY = "db.password";
	private static Connection connection;
	private final Properties _properties;
	private static Logger LOG = LogManager.getLogger(Database.class.getName());

	/**
	 * The database properties
	 * 
	 * @param properties
	 *            the properties to set
	 * @throws FileNotFoundException
	 *             if file is not found
	 * @throws IOException
	 *             if IO not valid
	 */
	public Database(final Properties properties) throws FileNotFoundException, IOException {
		LOG.debug("Loading database properties from db.properties");
		_properties = properties;
	}

	/**
	 * @return the connection
	 * @throws SQLException
	 *             if database error occurs
	 */
	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (final ClassNotFoundException e) {
			throw new SQLException(e);
		}
		return connection;
	}

	/**
	 * Connects to driver
	 * 
	 * @throws ClassNotFoundException
	 *             if loaded through its String name
	 * @throws SQLException
	 *             if database error occurs
	 */
	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(_properties.getProperty(DB_DRIVER_KEY));
		LOG.debug("Driver loaded ");
		connection = DriverManager.getConnection(_properties.getProperty(DB_URL_KEY), _properties.getProperty(DB_USER_KEY), _properties.getProperty(DB_PASSWORD_KEY));
		LOG.debug("Database connected " + connection.getMetaData().getDatabaseProductName());
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
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks to if table exists
	 * 
	 * @param tableName
	 *            the tableName to set
	 * @return true if results set otherwise false
	 * @throws SQLException
	 *             if database error occurs
	 */
	public boolean tableExists(final String tableName) throws SQLException {
		final DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = null;
		String resultTableName = null;

		try {
			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				resultTableName = resultSet.getString("TABLE_NAME");
				if (resultTableName.equalsIgnoreCase(tableName)) {
					return true;
				}
			}
		} finally {
			resultSet.close();
		}
		return false;
	}
}