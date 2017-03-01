/**
 * Project: A00973569Gis
 * File: PlayersDialog.java
 * Date: June 23, 2016
 * Time: 11:29:42 PM
 */

package a00973569.ui;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.PersonasDAO;
import a00973569.dao.PlayerDAO;
import a00973569.data.Player;

/**
 * Players Dialog class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
@SuppressWarnings("serial")
public class PlayersDialog extends JDialog {

	private final DefaultListModel<String> listModel;
	public List<Player> playersList;
	public Database db;
	public PersonasDAO dao;
	public PlayerDAO playerDao;
	private static final Logger LOG = LogManager.getLogger(PersonasDialog.class);

	/**
	 * Constructor to initialize a PlayersDialog
	 */
	public PlayersDialog() {
		LOG.info("Creating players dialog.");
		listModel = new DefaultListModel<>();
		this.getPlayers();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Players");
		this.setVisible(true);
		this.setBounds(100, 100, 450, 300);

		final String[] playersFirstLast = new String[playersList.size()];

		for (int i = 0; i < playersFirstLast.length; i++) {
			playersFirstLast[i] = playersList.get(i).getfName() + " " + playersList.get(i).getlName();
			listModel.addElement(playersList.get(i).getfName() + " " + playersList.get(i).getlName());
		}
		final JList<String> list = new JList<>(listModel);
		this.add(new JScrollPane(list), BorderLayout.CENTER);
	}

	/**
	 * Gets the players
	 */
	public void getPlayers() {
		db = Database.getDatabaseInstance();
		playerDao = PlayerDAO.getPlayerDAO();

		try {
			playersList = playerDao.selectAll();
		} catch (final SQLException e) {
			LOG.error("Could not select all players from database.");
		}
	}
}