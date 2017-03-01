/**
 * Project: A00973569Gis
 * File: Game.java
 * Date: June 20, 2016
 * Time: 12:02:15 PM
 */

package a00973569.data;

/**
 * Game data and information class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class Game {

	private String id;
	private String name;
	private String producer;

	/**
	 * Constructor to initialize a Game object
	 * 
	 * @param id
	 *            given for Game
	 * @param name
	 *            given for Game
	 * @param producer
	 *            given for Game
	 */
	public Game(final String id, final String name, final String producer) {
		super();
		this.id = id;
		this.name = name;
		this.producer = producer;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * @param producer
	 *            the producer to set
	 */
	public void setProducer(final String producer) {
		this.producer = producer;
	}
}