/**
 * Project: A00973569Gis
 * File: Player.java
 * Date: June 20, 2016
 * Time: 12:11:20 PM
 */

package a00973569.data;

import a00973569.io.PlayerFormat;

/**
 * Player data and information class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Player {

	private String id;
	private String fName;
	private String lName;
	private String email;
	private String gamerTag;
	private String birthdate;
	private int age;

	/**
	 * Constructor to initialize a Player object
	 * 
	 * @param id
	 *            given for Player
	 * @param fName
	 *            given for Player
	 * @param lName
	 *            given for Player
	 * @param email
	 *            given for Player
	 * @param birthDate
	 *            given for Player
	 */
	public Player(final String id, final String fName, final String lName, final String email, final String birthdate) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.birthdate = birthdate;
		this.setAge(birthdate);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(final String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setlName(final String lName) {
		this.lName = lName;
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
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(final String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setAge(final String birthdate) {
		this.age = PlayerFormat.calculateAge(birthdate);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", fName=" + fName + ", lName=" + lName + ", gamerTag=" + gamerTag + ", birthdate=" + birthdate + "]";
	}
}