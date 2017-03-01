/**
 * Project: A00973569Lab7
 * File: PlayerSorters.java
 * Date: June 5, 2016
 * Time: 9:41:19 PM
 */
package a00973569.lab7.player;

import java.util.Comparator;

import a00973569.lab7.data.Player;

/**
 * Class to sort players
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 7.0
 */
public class PlayerSorters {

	public static class CompareByBirthdate implements Comparator<Player> {
		@Override
		public int compare(final Player player1, final Player player2) {
			return player1.getBirthDate().compareTo(player2.getBirthDate());
		}
	}
}
