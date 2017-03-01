/**
 * Project: A00973569Gis
 * File: ResultsSorter.java
 * Date: May 27, 2016
 * Time: 3:01:15 PM
 */
package a00973569.util;

import java.util.Comparator;

import a00973569.data.Result;

/**
 * Compares by games, games descending, game totals, and game totals by descending order
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class ResultsSorter {

	/**
	 * Compares by games
	 */
	public static class CompareByGame implements Comparator<Result> {
		@Override
		public int compare(final Result result1, final Result result2) {
			return result1.getGameName().compareTo(result2.getGameName());
		}
	}

	/**
	 * Compares the games by descending order
	 */
	public static class CompareByGameDesc implements Comparator<Result> {
		@Override
		public int compare(final Result result1, final Result result2) {
			return result2.getGameName().compareTo(result1.getGameName());
		}
	}

	/**
	 * Compares by totals
	 */
	public static class CompareByTotal implements Comparator<Result> {
		@Override
		public int compare(final Result result1, final Result result2) {
			return result1.getTotal() - result2.getTotal();
		}
	}

	/**
	 * Compares the totals by descending order
	 */
	public static class CompareByTotalDesc implements Comparator<Result> {
		@Override
		public int compare(final Result result1, final Result result2) {
			return result2.getTotal() - result1.getTotal();
		}
	}
}
