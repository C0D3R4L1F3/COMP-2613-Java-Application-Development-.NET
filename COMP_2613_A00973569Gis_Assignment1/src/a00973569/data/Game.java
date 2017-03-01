/**
 * Project: A00973569Gis
 * File: Game.java
 * Date: May 20, 2016
 * Time: 1:50:37 PM
 */
package a00973569.data;

/**
 * Holds the Games data and information
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Game {

	private String id;
	private String name;
	private String producer;

	/**
	 * Default constructor
	 */
	public Game() {
		super();
	}

	/**
	 * Constructor to initialize Game object
	 * 
	 * @param id
	 *            given for player
	 * @param name
	 *            given for player
	 * @param producer
	 *            given for player
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", producer=" + producer + "]";
	}
}
