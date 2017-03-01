package a00973569.a3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;
/**
 * 
 * @author Ronnie Manimtim, a00973569
 * @version Jul 15, 2016
 */
@SuppressWarnings("serial")
public class QuickPickFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public QuickPickFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 96);
		setTitle("Quick Pick");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JButton btnQuickPick = new JButton("Quick Pick");
		btnQuickPick.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new QuickPick();
				final String randoms = QuickPick.getRandomNumbers();
				textField.setText(randoms);
			}
		});
		contentPane.add(btnQuickPick, "cell 0 0");
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
	}

}
