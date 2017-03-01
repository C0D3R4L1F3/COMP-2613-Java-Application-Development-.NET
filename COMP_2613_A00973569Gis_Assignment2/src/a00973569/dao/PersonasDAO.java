/**
 * Project: A00973569Gis
 * File: PersonasDAO.java
 * Date: June 23, 2016
 * Time: 8:30:41 PM
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

import a00973569.data.Persona;

/**
 * Persona data access object class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class PersonasDAO extends Dao {

	public static final String TABLE_NAME = "personas";
	private static PersonasDAO personasDao;
	private static final Logger LOG = LogManager.getLogger(PlayerDAO.class);

	/**
	 * Default constructor
	 */
	private PersonasDAO() {
		super(TABLE_NAME);
	}

	/**
	 * Gets the Persona data access object
	 * 
	 * @return the personasDao
	 */
	public static PersonasDAO getPersonasDAO() {
		if (personasDao == null) {
			personasDao = new PersonasDAO();
		}
		return personasDao;
	}

	/**
	 * Creates persona table
	 * 
	 * @throws SQLException
	 *             if database error occurs
	 */
	@Override
	public void create() throws SQLException {
		LOG.debug("Creating database table " + TABLE_NAME);
		final String createStatement = "create table personas(id VARCHAR(10) not null, player_id VARCHAR(10), gamertag VARCHAR(64), platform VARCHAR(64), primary key (id) )";
		super.create(createStatement);
		LOG.info("Executed statement: " + createStatement);
	}

	/**
	 * Inserts singular persona into the table
	 * 
	 * @param persona
	 *            the persona to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void addPersona(final Persona persona) throws SQLException {
		final String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s')", TABLE_NAME, persona.getId(), persona.getPlayerId(), persona.getGamerTag(),
				persona.getPlatform());
		super.add(insertString);
		LOG.info(insertString);
	}

	/**
	 * Updates the gamer tags in the table
	 * 
	 * @param oldGamertag
	 *            the oldGamertag to set
	 * @param newGamertag
	 *            the newGamertag to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void update(final String oldGamertag, final String newGamertag) throws SQLException {
		Connection connection;
		Statement statement = null;

		try {
			final String sqlString = String.format("UPDATE %s set %s='%s' WHERE %s='%s'", tableName, "gamertag", newGamertag, "gamertag", oldGamertag);
			LOG.debug("Update statment: " + sqlString);
			connection = database.connect();
			statement = connection.createStatement();
			final int rowcount = statement.executeUpdate(sqlString);
			LOG.debug(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	/**
	 * Deletes the persona in the table
	 * 
	 * @param persona
	 *            the persona to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public void delete(final Persona persona) throws SQLException {
		final String deleteStatement = "DELETE FROM " + TABLE_NAME + " WHERE id = " + persona.getId();
		super.delete(deleteStatement);
		LOG.info(deleteStatement);
	}

	/**
	 * Selects all the personas
	 * 
	 * @return the personas
	 * @throws SQLException
	 *             if database error occurs
	 */
	public List<Persona> selectAll() throws SQLException {
		final List<Persona> personas = new ArrayList<>();
		final ResultSet resultSet = super.selectAllFromDb();

		while (resultSet.next()) {
			personas.add(new Persona(String.valueOf(resultSet.getInt("id")), String.valueOf(resultSet.getInt("player_id")), resultSet.getString("gamertag"),
					resultSet.getString("platform")));
		}
		return personas;
	}

	/**
	 * Selects by gamertag in the table
	 * 
	 * @param gamertag
	 *            the gamertag to set
	 * @return the persona
	 * @throws SQLException
	 *             if database error occurs
	 */
	public Persona selectByGamertag(final String gamertag) throws SQLException {
		Connection connection;
		Statement statement = null;
		Persona persona = null;
		String sqlString = "";

		try {
			if (gamertag.trim().equals("")) {
				sqlString = "SELECT * FROM personas";
			} else {
				sqlString = "SELECT * FROM personas WHERE gamertag = '" + gamertag + "'";
			}
			connection = database.connect();
			statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				persona = new Persona(String.valueOf(resultSet.getInt("id")), String.valueOf(resultSet.getInt("player_id")), resultSet.getString("gamertag"),
						resultSet.getString("platform"));
			}
		} finally {
			close(statement);
		}
		return persona;
	}
}