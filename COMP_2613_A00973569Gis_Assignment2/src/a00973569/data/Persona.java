/**
 * Project: A00973569Gis
 * File: Persona.java
 * Date: June 20, 2016
 * Time: 12:30:39 PM
 */

package a00973569.data;

/**
 * Persona data and information class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Persona {

	private String id;
	private String playerId;
	private String gamerTag;
	private String platform;

	/**
	 * Constructor to initialize a Persona object
	 * 
	 * @param id
	 *            given for Persona
	 * @param playerId
	 *            given for Persona
	 * @param gamerTag
	 *            given for Persona
	 * @param platform
	 *            given for Persona
	 */
	public Persona(final String id, final String playerId, final String gamerTag, final String platform) {
		super();
		this.id = id;
		this.playerId = playerId;
		this.gamerTag = gamerTag;
		this.platform = platform;
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
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(final String playerId) {
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