package a00973569.b2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * @author Ronnie Manimtim, a00973569
 * @version Jul 15, 2016
 */
@SuppressWarnings("serial")
public class CountLettersFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public CountLettersFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Counting Letters");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][grow]"));
		
		JLabel lblEnterText = new JLabel("Enter Text");
		contentPane.add(lblEnterText, "cell 0 0,alignx trailing");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		JTextArea textArea = new JTextArea();
		contentPane.add(new JScrollPane(textArea), "cell 1 2,grow");
		
		JButton btnCharacterCounts = new JButton("Character Counts");
		btnCharacterCounts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				final String inputString = textField.getText();
				try {
					CountLetters.displayCount(inputString);
					textArea.setText(CountLetters.displayCount(inputString));
				} catch (final Exception e1) {
					JOptionPane.showMessageDialog(null, "Not a valid text");
				}
			}
		});
		
		contentPane.add(btnCharacterCounts, "cell 1 1");
		
		
	}

}
