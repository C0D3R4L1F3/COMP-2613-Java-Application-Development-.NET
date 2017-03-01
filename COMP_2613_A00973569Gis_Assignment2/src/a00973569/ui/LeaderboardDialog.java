/**
 * Project: A00973569Gis
 * File: LeaderboardDialog.java
 * Date: June 24, 2016
 * Time: 7:50:23 PM
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
import a00973569.dao.LeaderboardDAO;
import a00973569.data.Leaderboard;

/**
 * Leaderboard Dialog class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
@SuppressWarnings("serial")
public class LeaderboardDialog extends JDialog {

	private static LeaderboardDAO dao;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private final int numberOfColumns = 4;
	private static final Logger LOG = LogManager.getLogger(LeaderboardDialog.class);

	/**
	 * Constructor to initialize a LeaderboardDialog by gamertag
	 */
	public LeaderboardDialog() {
		try {
			Database.getDatabaseInstance();
			dao = LeaderboardDAO.getLeaderboardDao();
			final List<Leaderboard> rows = dao.getLeaderboardRows("byGame", false);
			final LeaderboardDialog dialog = new LeaderboardDialog(rows);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (final Exception e) {
			LOG.error("Couldn't create leaderboard dialog.");
		}
	}

	/**
	 * Constructor to initialize a LeaderboardDialog
	 * 
	 * @param rows
	 *            the rows to set
	 * @throws SQLException
	 *             if database error occurs
	 */
	public LeaderboardDialog(final List<Leaderboard> rows) throws SQLException {
		LOG.info("Creating leaderboard dialog.");
		Database.getDatabaseInstance();
		dao = LeaderboardDAO.getLeaderboardDao();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Leaderboard");
		this.setVisible(true);
		this.setBounds(100, 100, 400, 400);
		this.setLayout(new FlowLayout());

		final String[] columnNames = { "Wins:Losses", "Game Name", "Gamer Tag", "Platform" };
		final String[][] data = new String[rows.size()][numberOfColumns];

		for (int i = 0; i < rows.size(); i++) {
			data[i] = new String[numberOfColumns];
			for (int j = 0; j < numberOfColumns; j++) {
				data[i][0] = rows.get(i).wins + ":" + rows.get(i).losses;
				data[i][1] = rows.get(i).gameName;
				data[i][2] = rows.get(i).gamerTag;
				data[i][3] = rows.get(i).platform;
			}
		}
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		contentPanel.add(new JScrollPane(table));
		this.add(contentPanel);
		this.pack();
	}
}