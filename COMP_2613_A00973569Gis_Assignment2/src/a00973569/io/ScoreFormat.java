/**
 * Project: A00973569Gis
 * File: ScoreFormat.java
 * Date: June 24, 2016
 * Time: 8:10:05 PM
 */
package a00973569.io;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.ScoresDAO;
import a00973569.data.Score;

/**
 * Formats the players
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class ScoreFormat {

	private static final String INPUT_FILENAME = "scores.dat";
	private static List<Score> listOfScores;
	private final Database db;
	private final ScoresDAO dao;
	private static final Logger LOG = LogManager.getLogger(ScoreFormat.class);

	/**
	 * Constructor to initialize a ScoreFormat object
	 */
	public ScoreFormat() {
		super();
		db = Database.getDatabaseInstance();
		dao = ScoresDAO.getScoresDao();
		listOfScores = createListOfScores();

		try {
			LOG.info("Reading scores.dat file");
			if (!Database.tableExists(db.connect(), "scores")) {
				dao.create();
				for (final Score score : listOfScores) {
					dao.addScore(score);
				}
			}
		} catch (final SQLException e) {
			LOG.error("Error creating scores table. Class: ScoreFormat. Method: constructor.");
		}
	}

	/**
	 * Gets a list of scores
	 * 
	 * @return the listOfScores
	 */
	public static List<Score> getListOfScores() {
		return listOfScores;
	}

	/**
	 * Creates a list of scores
	 * 
	 * @return the scores
	 */
	public static List<Score> createListOfScores() {
		List<String> info;
		final List<Score> scores = new ArrayList<>();

		try {
			info = FileInput.readFile(INPUT_FILENAME);
			for (final String i : info) {
				final String[] scoreInfoArray = i.split("\\|");
				scores.add(new Score(scoreInfoArray[0], scoreInfoArray[1], scoreInfoArray[2]));
			}
		} catch (final IOException e) {
			LOG.error("Error reading scores data file.");
		}
		return scores;
	}
}