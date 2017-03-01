/**
 * 
 */
package a00973569.a3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Ronnie Manimtim, a00973569
 * @version Jul 15, 2016
 */
public class QuickPick {
	private static List<Integer> randomNumbers;
	private static int MAXIMUM_NUMBERS = 5;
	private static int MAXIMUM_VALUE = 49;
	private static int NUMBER_STARTING_POINT = 1;

	public QuickPick() {
		Random random = new Random();
		randomNumbers = new ArrayList<>();
		for (int i = 0; i <= MAXIMUM_NUMBERS; i++) {
			randomNumbers.add(random.nextInt(MAXIMUM_VALUE) + NUMBER_STARTING_POINT);
		}
	}

	/**
	 * To return 1 random number.
	 * 
	 * @return 1 random number.
	 */
	public static int getRandomNumber() {
		Random randomNumber = new Random();
		int number = randomNumber.nextInt(MAXIMUM_VALUE) + NUMBER_STARTING_POINT;
		return number;
	}
	
	/**
	 * Finds duplicate numbers
	 */
	public void isDuplicate() {
		for (int i = 0; i < randomNumbers.size(); i++) {
			if (randomNumbers.contains(randomNumbers.get(i))) {
				System.out.println(randomNumbers.get(i) + " is a duplicate.");
			}
		}
	}
	
	/**
	 * Gets the random numbers in a string
	 * 
	 * @return the builder.toString()
	 */
	public static String getRandomNumbers() {
		Collections.shuffle(randomNumbers);
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < randomNumbers.size(); i++) {
			builder.append(randomNumbers.get(i) + " ");
		}
		return builder.toString();
	}

}

