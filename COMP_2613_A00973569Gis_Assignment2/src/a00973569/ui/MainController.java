/**
 * Project: A00973569Gis
 * File: MainController.java
 * Date: June 25, 2016
 * Time: 6:04:04 PM
 */

package a00973569.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.LeaderboardDAO;
import a00973569.dao.PersonasDAO;
import a00973569.dao.ScoresDAO;
import a00973569.data.Leaderboard;

/**
 * Main controller class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
public class MainController {

	private final MainFrame mainFrame;
	@SuppressWarnings("unused")
	private final Database database;
	private final LeaderboardDAO leaderboardDao;
	private final PersonasDAO personasDao;
	private ScoresDAO scoresDao;
	private static final Logger LOG = LogManager.getLogger(MainController.class);

	/**
	 * Constructor to initialize a LeaderboardDialog object
	 * 
	 * @param mainFrame
	 *            the mainFrame to set
	 */
	public MainController(final MainFrame mainFrame) {
		super();
		database = Database.getDatabaseInstance();
		leaderboardDao = LeaderboardDAO.getLeaderboardDao();
		personasDao = PersonasDAO.getPersonasDAO();
		this.mainFrame = mainFrame;
		this.mainFrame.addByGameActionListener(new ByGameActionListener());
		this.mainFrame.addGamertagActionListener(new GamertagActionListener());
		this.mainFrame.addByCountActionListener(new ByCountActionListener());
		this.mainFrame.addTotalsActionListener(new TotalsActionListener());
	}

	/**
	 * By Game Action Listener class
	 */
	public class ByGameActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			final LeaderboardDAO dao = LeaderboardDAO.getLeaderboardDao();

			try {
				final List<Leaderboard> rows = dao.getLeaderboardRows("byGame", MainFrame.sortDescending);
				new LeaderboardDialog(rows);
			} catch (final SQLException e) {
				LOG.error(e.getMessage());
			} catch (final Exception e) {
				new ErrorDialog("Error generating leaderboard report by game");
			}
		}
	}

	/**
	 * By Gamertag Action Listener class
	 */
	public class GamertagActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			final JTextField gamertagFilter = new JTextField(15);
			final JPanel myPanel = new JPanel();
			myPanel.add(new JLabel("Gamer Tag:"));
			myPanel.add(gamertagFilter);
			final int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Gamer Tag", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				try {
					if (personasDao.selectByGamertag(gamertagFilter.getText().trim()) == null) {
						JOptionPane.showMessageDialog(null, "Gamer Tag not found.");
					} else {
						MainFrame.filterGamertag = gamertagFilter.getText();
					}
				} catch (final SQLException e) {
					LOG.error("Error selecting persona by gamertag");
				}
			}
		}
	}

	/**
	 * By Count Action Listener class
	 */
	public class ByCountActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			try {
				final List<Leaderboard> rows = leaderboardDao.getLeaderboardRows("byCount", MainFrame.sortDescending);
				new LeaderboardDialog(rows);
			} catch (final SQLException f) {
				LOG.error(f.getMessage());
			} catch (final Exception f) {
				LOG.error("Error generating leaderboard report by count.");
			}
		}
	}

	/**
	 * By Totals Action Listener class
	 */
	public class TotalsActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent arg0) {
			scoresDao = ScoresDAO.getScoresDao();
			Map<String, Integer> totals;

			try {
				totals = scoresDao.getTotals();
				final List<String> totalsList = new ArrayList<>();

				for (final Map.Entry<String, Integer> entry : totals.entrySet()) {
					totalsList.add(entry.getKey() + ":" + entry.getValue());
				}
				String[] arrayTotals = new String[totalsList.size()];
				arrayTotals = totalsList.toArray(arrayTotals);
				final JList<String> list = new JList<>(arrayTotals);
				final JPanel myPanel = new JPanel();
				myPanel.setSize(500, 500);
				myPanel.add(list);
				JOptionPane.showMessageDialog(null, myPanel);
			} catch (final SQLException e) {
				LOG.error("SQL error calculating totals");
			}
		}
	}
}