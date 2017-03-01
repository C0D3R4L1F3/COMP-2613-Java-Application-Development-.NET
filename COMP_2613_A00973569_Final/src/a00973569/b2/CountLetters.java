/**
 * 
 */
package a00973569.b2;

/**
 * 
 * 
 * @author Ronnie Manimtim, a00973569
 * @version Jul 15, 2016
 */
public class CountLetters {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 
	 */
	public CountLetters() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Counts each letter in the string
	 * 
	 * @param s
	 *            the s to set
	 * @return the counts
	 */
	public static int[] countLetters(final String s) {
		final int[] counts = new int[ALPHABET.length()];

		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				counts[s.charAt(i) - 'a']++;
			}
		}
		return counts;
	}

	/**
	 * Displays the count of the string
	 * 
	 * @param inputString
	 *            the inputString to set
	 * @return the output + totals
	 */
	public static String displayCount(final String inputString) {
		int total = 0;
		// Invoke the countLetters method to count each letter
		final int[] counts = countLetters(inputString.toLowerCase());

		// Declare and initialize output string
		String output = "";
		String totals = "";
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] != 0) {
				output += (char) ('a' + i) + " appears  " + counts[i] + ((counts[i] == 1) ? " time\n" : " times\n");
				total += counts[i];
				totals = "Total Characters: " + total;
			}
		}
		return output + totals;
	}
}
