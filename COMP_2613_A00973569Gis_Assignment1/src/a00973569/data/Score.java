/**
 * Project: A00973569Gis
 * File: Score.java
 * Date: May 20, 2016
 * Time: 1:50:49 PM
 */
package a00973569.data;

/**
 * Class that hold Score data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Score {

	private int personaId;
	private String gameId;
	private String result;

	/**
	 * Default constructor
	 */
	public Score() {
		super();
	}

	/**
	 * Constructor to initialize a Score object
	 * 
	 * @param personaId
	 *            given for player
	 * @param gameId
	 *            given for player
	 */
	public Score(final int personaId, final String gameId) {
		super();
		this.personaId = personaId;
		this.gameId = gameId;
	}

	/**
	 * @return the personaId
	 */
	public int getPersonaId() {
		return personaId;
	}

	/**
	 * @param personaId
	 *            the personaId to set
	 */
	public void setPersonaId(final int personaId) {
		this.personaId = personaId;
	}

	/**
	 * @return the gameId
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public void setGameId(final String gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(final String result) {
		this.result = result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Score [personaId=" + personaId + ", gameId=" + gameId + ", result=" + result + "]";
	}
}