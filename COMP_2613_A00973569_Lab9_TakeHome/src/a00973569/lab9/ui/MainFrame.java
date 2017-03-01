/**
 * Project: A00973569Lab9
 * File: MainFrame.java
 * Date: June 19, 2016
 * Time: 7:50:57 PM
 */
package a00973569.lab9.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00973569.lab9.data.Player;
import a00973569.lab9.util.Validator;

/**
 * Main Frame class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 9.0
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private final JPanel contentPane;

	/**
	 * Create the frame.
	 * 
	 * @param players
	 */
	public MainFrame(final List<Player> players) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Lab9");
		buildMenu(players);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	/**
	 * Builds Menu
	 */
	private void buildMenu(final List<Player> players) {
		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		final JMenuItem mntmPersona = new JMenuItem("Persona");
		mntmPersona.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {

				try {
					Player player = new Player();
					player = players.get(Validator.randomInteger(0, players.size() - 1));
					final PersonaDialog dialog = new PersonaDialog(player);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setTitle("Persona");
					dialog.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
		mntmPersona.setMnemonic('P');
		mnFile.add(mntmPersona);

		final JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setMnemonic('X');
		mnFile.add(mntmExit);

		final JMenu mnHelp = new JMenu("Help");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);

		final JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setMnemonic('A');
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, "Lab9\nBy Ronnie Manimtim A00973569", "About Lab9", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);

	}
}
