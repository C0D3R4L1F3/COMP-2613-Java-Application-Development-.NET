/**
 * Project: A00973569Gis
 * File: Score.java
 * Date: June 20, 2016
 * Time: 12:32:17 PM
 */

package a00973569.data;

/**
 * Score data and information class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Score {

	private String personaId;
	private String gameId;
	private String winLose;

	/**
	 * Constructor to initialize a Score object
	 * 
	 * @param personaId
	 *            given for Score
	 * @param gameId
	 *            given for Score
	 * @param winLose
	 *            given for Score
	 */
	public Score(final String personaId, final String gameId, final String winLose) {
		super();
		this.personaId = personaId;
		this.gameId = gameId;
		this.winLose = winLose;
	}

	/**
	 * @return the personaId
	 */
	public String getPersonaId() {
		return personaId;
	}

	/**
	 * @param personaId
	 *            the personaId to set
	 */
	public void setPersonaId(final String personaId) {
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
	 * @return the winLose
	 */
	public String getWinLose() {
		return winLose;
	}

	/**
	 * @param winLose
	 *            the winLose to set
	 */
	public void setWinLose(final String winLose) {
		this.winLose = winLose;
	}
}