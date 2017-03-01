/**
 * Project: A00973569Gis
 * File: ScoresDialog.java
 * Date: June 24, 2016
 * Time: 8:05:23 PM
 */

package a00973569.ui;

import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.ScoresDAO;
import a00973569.data.Score;

/**
 * Scores Dialog class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
@SuppressWarnings("serial")
public class ScoresDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTable table;
	private final int numberOfColumns = 3;
	private static final Logger LOG = LogManager.getLogger(ScoresDialog.class);

	/**
	 * Constructor to initialize a ScoresDialog
	 */
	public ScoresDialog() {
		LOG.info("Creating scores dialog.");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Scores");
		this.setVisible(true);
		this.setBounds(100, 100, 400, 400);
		this.setLayout(new FlowLayout());

		final String[] columnNames = { "PERSONA ID", "GAME ID", "WIN/LOSE" };
		final List<Score> scores = this.getScores();
		final String[][] data = new String[scores.size()][numberOfColumns];

		for (int i = 0; i < scores.size(); i++) {
			data[i] = new String[numberOfColumns];
			for (int j = 0; j < numberOfColumns; j++) {
				data[i][0] = scores.get(i).getPersonaId();
				data[i][1] = scores.get(i).getGameId();
				data[i][2] = scores.get(i).getWinLose();
			}
		}
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		contentPanel.add(new JScrollPane(table));
		this.add(contentPanel);
		this.pack();
	}

	/**
	 * Gets the scores
	 * 
	 * @return the scores
	 */
	public List<Score> getScores() {
		Database.getDatabaseInstance();
		final ScoresDAO dao = ScoresDAO.getScoresDao();
		List<Score> scores = null;

		try {
			scores = dao.selectAll();
		} catch (final SQLException e) {
			LOG.error("Could not select all scores from database.");
		}
		return scores;
	}
}