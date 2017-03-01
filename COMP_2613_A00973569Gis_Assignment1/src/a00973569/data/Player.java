/**
 * Project: A00973569Gis
 * File: Player.java
 * Date: May 20, 2016
 * Time: 1:40:29 AM
 */
package a00973569.data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class that hold Players data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Player {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private GregorianCalendar dateOfBirth;

	/**
	 * Default constructor
	 */
	public Player() {
		super();
	}

	/**
	 * Constructor to initialize a Player object
	 * 
	 * @param id
	 *            given for player
	 * @param firstName
	 *            given for player
	 * @param lastName
	 *            given for player
	 * @param email
	 *            given for player
	 * @param gamerTag
	 *            given for player
	 * @param dateOfBirth
	 *            given for player
	 */
	public Player(final int id, final String firstName, final String lastName, final String email, final String gamerTag, final GregorianCalendar dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the dateOfBirth
	 */
	public GregorianCalendar getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param date
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(final LocalDate dateOfBirth) {
		this.dateOfBirth = GregorianCalendar.from(dateOfBirth.atStartOfDay(ZoneId.systemDefault()));
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Deprecated
	public void setId(final String id) {
		this.id = Integer.valueOf(id);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		final Calendar now = new GregorianCalendar();
		return now.get(Calendar.YEAR) - this.getDateOfBirth().get(Calendar.YEAR);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}