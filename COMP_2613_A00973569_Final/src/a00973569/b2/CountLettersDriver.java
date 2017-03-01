/**
 * 
 */
package a00973569.b2;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * @author Ronnie Manimtim, a00973569
 * @version Jul 15, 2016
 */
public class CountLettersDriver {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final CountLettersFrame frame = new CountLettersFrame();
					frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});

		try {
			for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (final Exception e) {
			// If Nimbus is not available, use the default.
		}

	}

}
