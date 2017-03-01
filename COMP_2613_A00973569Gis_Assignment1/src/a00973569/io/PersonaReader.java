/**
 * Project: A00973569Gis
 * File: PersonaReader.java
 * Date: May 24, 2016
 * Time: 11:19:33 AM
 */
package a00973569.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.ApplicationException;
import a00973569.data.Persona;
import a00973569.util.Validator;

/**
 * Reads persona.dat file
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class PersonaReader {

	private static final int ELEMENT_ZERO = 0;
	private static final int ELEMENT_ONE = 1;
	private static final int ELEMENT_TWO = 2;
	private static final int ELEMENT_THREE = 3;
	private static final int INDEX_ONE = 1;
	public static final String FIELD_DELIMITER = "\\|";
	private static final Logger LOG = LogManager.getLogger(PersonaReader.class);

	/**
	 * private constructor to prevent instantiation
	 */
	private PersonaReader() {
		LOG.debug("PersonaReader()");
	}

	/**
	 * Reads the persona.dat file
	 * 
	 * @param file
	 *            to read
	 * @return personas
	 * @throws ApplicationException
	 *             if invalid information given
	 */
	public static HashMap<Integer, Persona> read(final File file) throws ApplicationException {
		String reocrdNumber;
		LOG.debug(file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (final FileNotFoundException e) {
			LOG.error("Input file does not exist: %s", file);
		}
		int i = INDEX_ONE;
		final HashMap<Integer, Persona> personas = new HashMap<Integer, Persona>();
		String row = scanner.nextLine();// skip header
		try {
			while (scanner.hasNext()) {
				row = scanner.nextLine();
				LOG.debug(row);
				final String[] elements = row.split(FIELD_DELIMITER);
				reocrdNumber = "Record # " + (i++) + ". ";
				if (elements.length != Persona.class.getDeclaredFields().length) {
					throw new ApplicationException(
							String.format(reocrdNumber + "Expected %d but got %d: %s", Persona.class.getDeclaredFields().length, elements.length, Arrays.toString(elements)));
				}
				final Persona persona = new Persona();
				if (!Validator.validateId(elements[ELEMENT_ZERO])) {
					throw new ApplicationException(reocrdNumber + "Id must be integer");
				}
				if (!Validator.validateId(elements[ELEMENT_ONE])) {
					throw new ApplicationException(reocrdNumber + "Player Id must be integer");
				}
				persona.setId(Integer.parseInt(elements[ELEMENT_ZERO]));
				persona.setPlayerId(Integer.parseInt(elements[ELEMENT_ONE]));
				persona.setGamerTag(elements[ELEMENT_TWO]);
				persona.setPlatform(elements[ELEMENT_THREE]);
				personas.put(Integer.parseInt(elements[ELEMENT_ZERO]), persona);
				LOG.debug(persona);
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return personas;
	}
}