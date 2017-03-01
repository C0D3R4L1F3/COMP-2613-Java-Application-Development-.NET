/**
 * Project: A00973569Gis
 * File: TotalsDialog.java
 * Date: June 24, 2016
 * Time: 10:52:13 PM
 */

package a00973569.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test the Dialog class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
@SuppressWarnings("serial")
public class TotalsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static final Logger LOG = LogManager.getLogger(TotalsDialog.class);

	/**
	 * Constructor to initialize a TotalsDialog
	 */
	public TotalsDialog() {
		LOG.info("Creating totals dialog.");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		{
			final JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				final JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}