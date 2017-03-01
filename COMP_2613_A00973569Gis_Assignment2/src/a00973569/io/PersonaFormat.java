/**
 * Project: A00973569Gis
 * File: PersonaFormat.java
 * Date: June 23, 2016
 * Time: 8:47:31 PM
 */

package a00973569.io;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.PersonasDAO;
import a00973569.data.Persona;

/**
 * Formats the personas
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class PersonaFormat {

	private static final String FILENAME = "personas.dat";
	private static List<Persona> listOfPersonas;
	private final Database database = Database.getDatabaseInstance();
	private static PersonasDAO personasDao = PersonasDAO.getPersonasDAO();
	private static final Logger LOG = LogManager.getLogger(PersonaFormat.class);

	/**
	 * Constructor to initialize a PersonaFormat object
	 */
	public PersonaFormat() {
		super();
		listOfPersonas = new ArrayList<>();

		try {
			LOG.info("Reading personas.dat file");
			listOfPersonas = createListOfPersonas();
			final Connection connection = database.connect();
			if (!Database.tableExists(connection, "personas")) {
				personasDao.create();
				for (final Persona persona : listOfPersonas) {
					addToDatabase(persona);
				}
			}
		} catch (final IOException e) {
			LOG.error("Error reading personas.dat file.");
		} catch (final SQLException e) {
			LOG.error("Error creating personas table. Class: PersonaFormat. Method: Constructor");
		}

	}

	/**
	 * Creates a list of personas
	 * 
	 * @return the personas
	 * @throws IOException
	 *             if failed I/O operatoin has occurred
	 */
	public static List<Persona> createListOfPersonas() throws IOException {
		final List<String> info = FileInput.readFile(FILENAME);
		final List<Persona> personas = new ArrayList<>();

		for (final String i : info) {
			final String[] personaInfoArray = i.split("\\|");
			final Persona persona = new Persona(personaInfoArray[0], personaInfoArray[1], personaInfoArray[2], personaInfoArray[3]);
			personas.add(persona);
		}
		return personas;
	}

	/**
	 * Adds to the database
	 * 
	 * @param persona
	 *            the persona to set
	 */
	public static void addToDatabase(final Persona persona) {
		try {
			personasDao.addPersona(persona);
		} catch (final SQLException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * Gets persona id to the persona map
	 * 
	 * @return the personaIdToPersona
	 */
	public static Map<String, Persona> personaIdToPersonaMap() {
		final Map<String, Persona> personaIdToPersona = new HashMap<>();
		final List<Persona> personas = listOfPersonas;

		for (final Persona persona : personas) {
			personaIdToPersona.put(persona.getId(), persona);
		}
		return personaIdToPersona;
	}

	/**
	 * Gets the personas
	 * 
	 * @param playerId
	 *            the playerId to set
	 * @return the playerPersonas
	 */
	public static List<Persona> getPersonas(final String playerId) {
		List<Persona> personas = null;

		try {
			personas = PersonaFormat.createListOfPersonas();
		} catch (final IOException e) {
			LOG.error("Error reading personas.dat file.");
		}
		final List<Persona> playerPersonas = new ArrayList<>();

		for (final Persona persona : personas) {
			if (persona.getPlayerId().equalsIgnoreCase(playerId)) {
				playerPersonas.add(persona);
			}
		}
		return playerPersonas;
	}
}