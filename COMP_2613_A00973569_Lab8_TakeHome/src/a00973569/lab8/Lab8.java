/**
 * Project: A00973569Lab8
 * File: Lab8.java
 * Date: June 13, 2016
 * Time: 12:03:31 AM
 */
package a00973569.lab8;

/**
 * Tortoise vs Hare
 * 
 * @author Ronnie Manimtim, A00973569
 * @version 1.0
 */
public class Lab8 extends Thread {

	private static int LIMIT = 100;
	private static boolean start = false;
	private static boolean finish = false;
	private Object sync = new Object();
	private final String runnerName;
	private int distance = 0;

	/**
	 * Constructor to initialize Lab8 objects
	 * 
	 * @param runnerName
	 *            the name given
	 * @param sync
	 *            the sync to set
	 */
	public Lab8(final String runnerName, final Object sync) {
		this.sync = sync;
		this.runnerName = runnerName;
	}

	/**
	 * Reports the race progress.
	 * With each iteration the accumulated sum for each thread is displayed.
	 * When one of the Threads reaches or surpasses 100 the program reports who
	 * the winner is and then stops.
	 */
	public void report() {
		start = !start;
		if (start) {
			System.out.println();
		}
		System.out.print(runnerName + " " + distance + " ");
		if (distance >= LIMIT) {
			finish = true;
			System.out.println();
			System.out.println("**** " + runnerName + " IS THE WINNER! ****");

		}
	}

	/**
	 * Random numbers between 1 and 5 are generated and accumulated here.
	 * Each thread should also be made to sleep so we can follow the program flow.
	 * Progress is displayed with each iteration by calling the report() method.
	 * 
	 */
	@Override
	public void run() {
		while (distance < LIMIT || !finish) {
			try {
				synchronized (sync) {
					distance = distance + randomInteger(1, 5);
					Thread.sleep(100);
					sync.notify();
					if (!finish) {
						report();
						sync.wait();
					}
				}
			} catch (final InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Generates random integer
	 * 
	 * @param min
	 *            the minimum integer to set
	 * @param max
	 *            the maximum integer to set
	 * @return randomNumber
	 */
	public static int randomInteger(final int min, final int max) {
		final int randomNumber = min + (int) (Math.random() * ((max - min) + 1));
		return randomNumber;
	}

	/**
	 * Drives the program.
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static void main(final String args[]) {
		// monitor
		final Object sync = new Object();

		// Create tasks
		final Runnable tortoise = new Lab8("Tortoise", sync);
		final Runnable hare = new Lab8("Hare", sync);

		// Create threads
		final Thread thread1 = new Thread(hare);
		final Thread thread2 = new Thread(tortoise);

		// Start threads
		thread1.start();
		thread2.start();
	}
}