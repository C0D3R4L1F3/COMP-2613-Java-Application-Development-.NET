/**
 * Project: A00973569Lab1
 * File: Player.java
 * Date: Apr 26, 2016
 * Time: 9:40:13 PM
 */
package a00973569.lab1.data;

/**
 * Class that hold players data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Player {

	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String gamerTag;

	/**
	 * Default constructor
	 */
	public Player() {

		super();
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
	 */
	public Player(int id, String firstName, String lastName, String emailAddress, String gamerTag) {

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
	 *            the id to set for player
	 */
	public void setId(int id) {

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
	 *            the firstName to set for player
	 */
	public void setFirstName(String firstName) {

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
	 *            the lastName to set for player
	 */
	public void setLastName(String lastName) {

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
	 *            the emailAddress to set for player
	 */
	public void setEmailAddress(String emailAddress) {

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
	 *            the gamerTag to set for player
	 */
	public void setGamerTag(String gamerTag) {

		this.gamerTag = gamerTag;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", gamerTag=" + gamerTag + "]";
	}
}
