/**
 * Project: A00973569Gis
 * File: PersonasDialog.java
 * Date: June 23, 2016
 * Time: 9:38:48 PM
 */

package a00973569.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.dao.Database;
import a00973569.dao.PersonasDAO;
import a00973569.dao.PlayerDAO;
import a00973569.data.Persona;
import a00973569.data.Player;

/**
 * Personas Dialog class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
@SuppressWarnings("serial")
public class PersonasDialog extends JDialog {

	private List<Persona> personas;
	@SuppressWarnings("unused")
	private Database db;
	private PersonasDAO dao;
	private PlayerDAO playerDao;
	private static final Logger LOG = LogManager.getLogger(PersonasDialog.class);

	/**
	 * Constructor to initialize a PersonasDialog
	 */
	public PersonasDialog() {
		LOG.info("Creating personas dialog.");
		this.getGamertags();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Personas");
		this.setVisible(true);
		setBounds(100, 100, 450, 300);

		final String[] personasRow = new String[personas.size()];

		for (int i = 0; i < personasRow.length; i++) {
			personasRow[i] = personas.get(i).getGamerTag();
		}
		final JList<String> list = new JList<>(personasRow);

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent evt) {
				@SuppressWarnings("unchecked")
				final JList<String> list = (JList<String>) evt.getSource();
				if (evt.getClickCount() == 2) {
					try {
						editPersona(list.getSelectedValue());
					} catch (final SQLException e) {
						LOG.error(e.getMessage());
					}
				}
			}
		});
		this.add(new JScrollPane(list), BorderLayout.CENTER);
	}

	/**
	 * Gets the gamerTags
	 */
	public void getGamertags() {
		db = Database.getDatabaseInstance();
		dao = PersonasDAO.getPersonasDAO();
		playerDao = PlayerDAO.getPlayerDAO();

		try {
			personas = dao.selectAll();
		} catch (final SQLException e) {
			LOG.error("Could not select all from personas.");
		}
	}

	/**
	 * Edits the persona
	 * 
	 * @param gamertag
	 *            the gamertag to set
	 * @throws SQLException
	 */
	public void editPersona(final String gamertag) throws SQLException {
		final Player player = playerDao.getPlayer(gamertag);

		final JTextField fName = new JTextField(15);
		final JTextField lName = new JTextField(15);
		final JTextField email = new JTextField(15);
		final JTextField persona = new JTextField(15);

		fName.setText(player.getfName());
		lName.setText(player.getlName());
		email.setText(player.getEmail());
		persona.setText(gamertag);

		final JPanel myPanel = new JPanel();
		myPanel.setLayout(new BorderLayout());
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		myPanel.add(new JLabel("First Name:"));
		myPanel.add(fName);

		myPanel.add(new JLabel("Last Name:"));
		myPanel.add(lName);

		myPanel.add(new JLabel("Email:"));
		myPanel.add(email);

		// Gamer Tag or persona
		myPanel.add(new JLabel("Gamer Tag:"));
		myPanel.add(persona);

		final int result = JOptionPane.showConfirmDialog(null, myPanel, "Edit persona", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			// Update persona and player information
			player.setfName(fName.getText());
			player.setlName(lName.getText());
			player.setEmail(email.getText());
			System.out.println(player.toString());

			try {
				playerDao.update(player);
			} catch (final SQLException e) {
				LOG.error("SQL error from PlayerDAO in method update()");
			}
			try {
				dao.update(gamertag, persona.getText());
			} catch (final SQLException e) {
				LOG.error("Cannot update persona information.");
			}
		}
	}
}