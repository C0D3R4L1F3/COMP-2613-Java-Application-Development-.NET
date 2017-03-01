/**
 * Project: A00973569Gis
 * File: Persona.java
 * Date: May 20, 2016
 * Time: 1:50:20 PM
 */
package a00973569.data;

/**
 * Holds the Persona data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Persona {
	private int id;
	private int playerId;
	private String gamerTag;
	private String platform;

	/**
	 * Default constructor
	 */
	public Persona() {
		super();
	}

	/**
	 * Constructor to initialize Persona object
	 * 
	 * @param id
	 *            given for player
	 * @param playerId
	 *            given for player
	 * @param gamerTag
	 *            given for player
	 * @param platform
	 *            given for player
	 */
	public Persona(final int id, final int playerId, final String gamerTag, final String platform) {
		super();
		this.id = id;
		this.playerId = playerId;
		this.gamerTag = gamerTag;
		this.platform = platform;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [id=" + id + ", playerId=" + playerId + ", gamerTag=" + gamerTag + ", platform=" + platform + "]";
	}
}