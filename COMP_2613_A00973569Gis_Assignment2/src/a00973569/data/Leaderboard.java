/**
 * Project: A00973569Gis
 * File: Leaderboard.java
 * Date: June 20, 2016
 * Time: 12:15:16 PM
 */

package a00973569.data;

/**
 * Leaderboard data and information class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Leaderboard {

	public int wins;
	public int losses;
	public String gameName;
	public String gamerTag;
	public String platform;

	/**
	 * Constructor to initialize a Leaderboard object
	 * 
	 * @param wins
	 *            given for Leaderboard
	 * @param losses
	 *            given for Leaderboard
	 * @param gameName
	 *            given for Leaderboard
	 * @param gamerTag
	 *            given for Leaderboard
	 * @param platform
	 *            given for Leaderboard
	 */
	public Leaderboard(final int wins, final int losses, final String gameName, final String gamerTag, final String platform) {
		super();
		this.wins = wins;
		this.losses = losses;
		this.gameName = gameName;
		this.gamerTag = gamerTag;
		this.platform = platform;
	}

	/**
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * @return the losses
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * @param wins
	 *            the wins to set
	 */
	public void setWins(final int wins) {
		this.wins = wins;
	}

	/**
	 * @param losses
	 *            the losses to set
	 */
	public void setLosses(final int losses) {
		this.losses = losses;
	}

	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * @return the gamerTag
	 */
	public String getGamerTag() {
		return gamerTag;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param gameName
	 *            the gameName to set
	 */
	public void setGameName(final String gameName) {
		this.gameName = gameName;
	}

	/**
	 * @param gamerTag
	 *            the gamerTag to set
	 */
	public void setGamerTag(final String gamerTag) {
		this.gamerTag = gamerTag;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(final String platform) {
		this.platform = platform;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Leaderboard [wins=" + wins + " losses=" + losses + ", gameName=" + gameName + ", gamerTag=" + gamerTag + ", platform=" + platform + "]";
	}
}