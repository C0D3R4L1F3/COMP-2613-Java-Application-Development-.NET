/**
 * Project: A00973569Gis
 * File: Result.java
 * Date: May 26, 2016
 * Time: 2:47:34 PM
 */
package a00973569.data;

/**
 * Class that hold Players Results data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Result {

	private static final int PRIME_VALUE = 31;
	private static final int RESULT_VALUE_ONE = 1;
	private static final int RESULT_VALUE_ZERO = 0;
	private int personaId;
	private String gameId;
	private String gameName;
	private String gameTag;
	private String platform;
	private int win = 0;
	private int loss = 0;
	private int playerId;

	/**
	 * Default constructor
	 */
	public Result() {
		super();
	}

	/**
	 * Constructor to initialize a Player Result object
	 * 
	 * @param personaId
	 *            given for player
	 * @param gameId
	 *            given for player
	 */
	public Result(final int personaId, final String gameId) {
		super();
		this.personaId = personaId;
		this.gameId = gameId;
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
	 * @return the total
	 */
	public int getTotal() {
		return this.win + this.loss;
	}

	/**
	 * @return the scores
	 */
	public String getScores() {
		return this.win + ":" + this.loss;
	}

	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * @param gameName
	 *            the gameName to set
	 */
	public void setGameName(final String gameName) {
		this.gameName = gameName;
	}

	/**
	 * @return the gameTag
	 */
	public String getGameTag() {
		return gameTag;
	}

	/**
	 * @param gameTag
	 *            the gameTag to set
	 */
	public void setGameTag(final String gameTag) {
		this.gameTag = gameTag;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(final String platform) {
		this.platform = platform;
	}

	/**
	 * @return the win
	 */
	public int getWin() {
		return win;
	}

	/**
	 * @param win
	 *            the win to set
	 */
	public void setWin(final int win) {
		this.win = win;
	}

	/**
	 * @return the loss
	 */
	public int getLoss() {
		return loss;
	}

	/**
	 * @param loss
	 *            the loss to set
	 */
	public void setLoss(final int loss) {
		this.loss = loss;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Result [personaId=" + personaId + ", gameId=" + gameId + ", gameName=" + gameName + ", gameTag=" + gameTag + ", platform=" + platform + ", win=" + win + ", loss="
				+ loss + ", total=" + getTotal() + ", scores=" + getScores() + "playerId=" + playerId + "]";
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = PRIME_VALUE;
		int result = RESULT_VALUE_ONE;
		result = prime * result + ((gameId == null) ? RESULT_VALUE_ZERO : gameId.hashCode());
		result = prime * result + personaId;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Result other = (Result) obj;
		if (gameId == null) {
			if (other.gameId != null) {
				return false;
			}
		} else if (!gameId.equals(other.gameId)) {
			return false;
		}
		if (personaId != other.personaId) {
			return false;
		}
		return true;
	}
}