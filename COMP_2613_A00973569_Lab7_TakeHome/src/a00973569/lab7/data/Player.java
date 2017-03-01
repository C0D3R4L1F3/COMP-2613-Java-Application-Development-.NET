/**
 * Project: A00973569Lab7
 * File: Player.java
 * Date: June 5, 2016
 * Time: 7:32:39 PM
 */
package a00973569.lab7.data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class that hold players data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 7.0
 */
public class Player {

	public static final int ATTRIBUTE_COUNT = 6;
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String gamerTag;
	private GregorianCalendar birthDate;

	/**
	 * Default Constructor
	 */
	public Player() {
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
	 * @param emailAddress
	 *            given for player
	 * @param gamerTag
	 *            given for player
	 * @param birthdate
	 *            given for player
	 */
	public Player(final int id, final String firstName, final String lastName, final String emailAddress, final String gamerTag, final GregorianCalendar birthDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.gamerTag = gamerTag;
		this.birthDate = birthDate;
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
	 * 
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
	public GregorianCalendar getBirthDate() {
		return birthDate;
	}

	/**
	 * @param date
	 *            the birthDate to set
	 */
	public void setBirthDate(final GregorianCalendar date) {
		this.birthDate = date;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(final java.sql.Date date) {
		final Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		this.birthDate = (GregorianCalendar) calendar;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(final LocalDate birthDate) {
		this.birthDate = GregorianCalendar.from(birthDate.atStartOfDay(ZoneId.systemDefault()));
	}

	/**
	 * @return the first and last name
	 */
	public String getFullName() {
		return firstName + " " + lastName;
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