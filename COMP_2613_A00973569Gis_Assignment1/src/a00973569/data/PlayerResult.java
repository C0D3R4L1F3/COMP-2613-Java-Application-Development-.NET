/**
 * Project: A00973569Gis
 * File: PlayerResult.java
 * Date: May 26, 2016
 * Time: 2:55:52 PM
 */
package a00973569.data;

/**
 * Class that hold Players Results data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class PlayerResult {

	private int playerId;
	private String fullName;
	private String email;
	private int age;
	private int total;
	private int won;

	/**
	 * Default constructor
	 */
	public PlayerResult() {
		super();
	}

	/**
	 * Constructor to initialize a Player Result object
	 * 
	 * @param playerId
	 *            given for player
	 * @param fullName
	 *            given for player
	 * @param email
	 *            given for player
	 * @param age
	 *            given for player
	 * @param total
	 *            given for player
	 * @param won
	 *            given for player
	 */
	public PlayerResult(final int playerId, final String fullName, final String email, final int age, final int total, final int won) {
		super();
		this.playerId = playerId;
		this.fullName = fullName;
		this.email = email;
		this.age = age;
		this.total = total;
		this.won = won;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(final int age) {
		this.age = age;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(final int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(final String fullName) {
		this.fullName = fullName;
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
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(final int total) {
		this.total = total;
	}

	/**
	 * @return the won
	 */
	public int getWon() {
		return won;
	}

	/**
	 * @param won
	 *            the won to set
	 */
	public void setWon(final int won) {
		this.won = won;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlayerResult [playerId=" + playerId + ", fullName=" + fullName + ", email=" + email + ", age=" + age + ", total=" + total + ", won=" + won + "]";
	}
}