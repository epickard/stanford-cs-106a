/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	/**Eclipse wants this*/
	private static final long serialVersionUID = 1L;

	public void run() {
		println("This program displays the Hailstone Sequence for a given number and displays the number of steps to get to 1.");
		
		/**prompt user to enter a value of specified type */
		int a = readInt("Enter a number: ");
		
		/**variable to manipulate in the Hailstone Sequence. initialize it to what user enters. */
		int hailstone = a;
		
		/**variable for counter to count the steps it takes to get to 1 */
		int counter = 0;
		
		/**count number of steps in sequence it takes to get to 1. if entered value is even, 
		 * call isEven(), then replace hailstone value with return. if entered value is odd. call
		 * isOdd(), then replace hailstone value with return. then decrement the counter. do this 
		 * until hailstone value is 1, then break and print the number of steps.*/
		while (true) {
			
			if (hailstone == 1) {
				println("The process took " + counter + " to reach 1");
				break;
			}
			
			if (hailstone % 2 == 0) {
				println(hailstone + " is even, so I take half: " + isEven(hailstone));
				hailstone = isEven(hailstone);
			}
		
			else {
				println(hailstone + " is odd, so I make 3n +1: " + isOdd(hailstone));
				hailstone = isOdd(hailstone);
			}
			
			counter++;
		}
	}
	
	/**if the number is even, divide by 2 */
	private int isEven (int number) {
		int result = number / 2;
		return result;
	}
	
	/**if the number is odd, multiply by 3 and add 1 */
	private int isOdd (int number) {
		int result = (number * 3) + 1;
		return result;
	}
}

