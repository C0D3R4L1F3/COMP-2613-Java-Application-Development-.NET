/**
 * 
 */
package a00973569.a3;

import java.awt.EventQueue;

/**
 * @author Ronnie Manimtim, a00973569
 * @version Jul 15, 2016
 */
public class QuickPickDriver {

	/**
	 * 
	 */
	public QuickPickDriver() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuickPickFrame frame = new QuickPickFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
