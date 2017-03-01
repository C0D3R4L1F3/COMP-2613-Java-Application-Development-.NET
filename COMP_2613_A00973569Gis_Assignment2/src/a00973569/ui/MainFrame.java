/**
 * Project: A00973569Gis
 * File: MainFrame.java
 * Date: June 25, 2016
 * Time: 7:24:44 PM
 */

package a00973569.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00973569.io.GamesFormat;
import a00973569.io.LeaderboardReportWriter;
import a00973569.io.PersonaFormat;
import a00973569.io.PlayerFormat;
import a00973569.io.ScoreFormat;
import net.miginfocom.swing.MigLayout;

/**
 * Main Frame class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private static final int REGULAR_EXIT = 0;
	private static final int ZONE_HOUR_ZERO = 0;
	public static boolean sortDescending = false;
	public static String filterGamertag = "";
	private final JPanel contentPane;
	private final JMenuItem byGameMenuItem;
	private final JMenuItem byCountMenuItem;
	private final JMenuItem gamertagMenuItem;
	private final JMenuItem totalMenuItem;
	private static final Logger LOG = LogManager.getLogger(MainFrame.class);

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		LOG.info("Starting MainFrame.");
		this.loadData();
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent arg0) {
				LOG.info("End Assignment 2. exit code: " + REGULAR_EXIT);
				final Instant end = LocalDateTime.now().toInstant(ZoneOffset.ofHours(ZONE_HOUR_ZERO));
				LOG.info(end);
				System.exit(REGULAR_EXIT);
			}
		});
		setBounds(100, 100, 695, 473);

		final JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		final JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_1);
		menuBar.add(fileMenu);

		final JMenuItem quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
		quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				LOG.info("End Assignment 2. exit code: " + REGULAR_EXIT);
				final Instant end = LocalDateTime.now().toInstant(ZoneOffset.ofHours(ZONE_HOUR_ZERO));
				LOG.info(end);
				System.exit(REGULAR_EXIT);
			}
		});
		fileMenu.add(quitMenuItem);

		final JMenu listsMenu = new JMenu("Lists");
		listsMenu.setMnemonic(KeyEvent.VK_2);
		menuBar.add(listsMenu);

		final JMenuItem playersMenuItem = new JMenuItem("Players", KeyEvent.VK_P);
		playersMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		playersMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				new PlayersDialog();
			}
		});
		listsMenu.add(playersMenuItem);

		final JMenuItem personasMenuItem = new JMenuItem("Personas", KeyEvent.VK_E);
		personasMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		personasMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				new PersonasDialog();
			}
		});
		listsMenu.add(personasMenuItem);

		final JMenuItem scoresMenuItem = new JMenuItem("Scores", KeyEvent.VK_S);
		scoresMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		scoresMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				new ScoresDialog();
			}
		});
		listsMenu.add(scoresMenuItem);

		final JMenu reportsMenu = new JMenu("Reports");
		reportsMenu.setMnemonic(KeyEvent.VK_3);
		menuBar.add(reportsMenu);

		totalMenuItem = new JMenuItem("Totals", KeyEvent.VK_T);
		totalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
		totalMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				new TotalsDialog();
			}
		});
		reportsMenu.add(totalMenuItem);

		byGameMenuItem = new JMenuItem("By Game", KeyEvent.VK_G);
		byGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.ALT_MASK));

		reportsMenu.add(byGameMenuItem);

		byCountMenuItem = new JMenuItem("By Count", KeyEvent.VK_C);
		byCountMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));

		reportsMenu.add(byCountMenuItem);

		gamertagMenuItem = new JMenuItem("Gamer Tag", KeyEvent.VK_X);
		gamertagMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
		gamertagMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				new LeaderboardDialog();
			}
		});

		reportsMenu.add(gamertagMenuItem);

		final JCheckBoxMenuItem descMenuItem = new JCheckBoxMenuItem("Descending");
		descMenuItem.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(final ItemEvent arg0) {
				if (descMenuItem.isSelected()) {
					sortDescending = true;
				} else {
					sortDescending = false;
				}
			}
		});

		reportsMenu.add(descMenuItem);

		final JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_4);
		menuBar.add(helpMenu);

		final JMenuItem aboutMenuItem = new JMenuItem("About", KeyEvent.VK_U);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Assignment 2 \n by Ronnie Manimtim A00973569");
			}
		});

		helpMenu.add(aboutMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));
	}

	/**
	 * Loads the data
	 */
	public void loadData() {
		new PlayerFormat();
		new PersonaFormat();
		new GamesFormat();
		new ScoreFormat();
		new LeaderboardReportWriter();
	}

	/**
	 * Adds by game action listener
	 * 
	 * @param listener
	 *            the listener to set
	 */
	public void addByGameActionListener(final ActionListener listener) {
		byGameMenuItem.addActionListener(listener);
	}

	/**
	 * Adds by gamerTag action listener
	 * 
	 * @param listener
	 *            the listener to set
	 */
	public void addGamertagActionListener(final ActionListener listener) {
		gamertagMenuItem.addActionListener(listener);
	}

	/**
	 * Adds by count action listener
	 * 
	 * @param listener
	 *            the listener to set
	 */
	public void addByCountActionListener(final ActionListener listener) {
		byCountMenuItem.addActionListener(listener);
	}

	/**
	 * Adds by totals action listener
	 * 
	 * @param listener
	 *            the listener to set
	 */
	public void addTotalsActionListener(final ActionListener listener) {
		totalMenuItem.addActionListener(listener);
	}
}