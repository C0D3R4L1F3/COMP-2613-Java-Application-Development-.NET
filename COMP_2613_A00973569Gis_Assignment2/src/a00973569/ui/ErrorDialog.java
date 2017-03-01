/**
 * Project: A00973569Gis
 * File: ErrorDialog.java
 * Date: June 26, 2016
 * Time: 3:55:32 PM
 */

package a00973569.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Errors Dialog class
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 2.0
 */
@SuppressWarnings("serial")
public class ErrorDialog extends JDialog {

	/**
	 * Create the dialog.
	 */
	public ErrorDialog(final String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}