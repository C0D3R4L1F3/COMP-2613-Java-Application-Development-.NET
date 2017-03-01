/**
 * Project: A00973569Lab5
 * File: Player.java
 * Date: May 23, 2016
 * Time: 9:32:39 PM
 */
package a00973569.lab5.data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class that hold players data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 5.0
 */
public class Player {

	private static final int BIRTHDATE_HOUR = 0;
	private static final int BIRTHDATE_MINS = 0;
	public static final int ATTRIBUTE_COUNT = 6;
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String gamerTag;
	private LocalDateTime birthDate;

	/**
	 * Default Constructor
	 */
	public Player() {
	}

	/**
	 * Constructor to initialize a player object
	 * 
	 * @param idNumber
	 *            given for player
	 * @param firstName
	 *            given for player
	 * @param lastName
	 *            given for player
	 * @param emailAddress
	 *            given for player
	 * @param gamerTag
	 *            given for player
	 * @param birthdate
	 *            given for player
	 */
	public Player(final int id, final String firstName, final String lastName, final String emailAddress, final String gamerTag) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.gamerTag = gamerTag;
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
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the gamerTag
	 */
	public String getGamerTag() {
		return gamerTag;
	}

	/**
	 * @param gamerTag
	 *            the gamerTag to set
	 */
	public void setGamerTag(final String gamerTag) {
		this.gamerTag = gamerTag;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(final LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	@Deprecated
	public void setBirthDate(final Date date) {
		// this.birthDate = new GregorianCalendar(date);
	}

	/**
	 * Set the birthdate
	 * 
	 * @param year
	 *            the year, includes the century, ex. 1967
	 * @param month
	 *            the month - must be 0-based
	 * @param day
	 *            the day of the month - 1-based
	 */
	public void setBirthDate(final int year, final int month, final int day) {
		birthDate = LocalDateTime.of(year, month, day, BIRTHDATE_HOUR, BIRTHDATE_MINS);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", gamerTag=" + gamerTag + ", birthDate=" + birthDate
				+ "]";
	}
}