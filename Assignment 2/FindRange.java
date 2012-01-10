/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	/**
	 * 
	 */
	
	/**Eclipse wants this variable installed */
	private static final long serialVersionUID = 1L;
	
	/**sentinel that user can enter to end the run of the program */
	private static final int SENTINEL = 0;
	
	public void run() {
		println("This program finds the largest and smallest numbers.");
		
		int smallest = 0;
		int largest = 0;
		int counter = 0;
		
		
		/**while the user does not enter the sentinel, compare the value entered with the existing
		 * smallest and largest values. when the value entered is either smaller than the smallest
		 * or larger than the largest, replace the smallest/largest with the new value. */
		while (true) {
			
			/**read in an integer from user and increment counter */
			int a = readInt("Type an integer and press [ENTER]. To exit, press '0': ");
			counter +=1;
			
			/**the first time user enters a value, if it's the SENTINEL, exit, per instructions. otherwise, set smallest & largest to value. */
			if (counter == 1) {
				if (a == SENTINEL) {
					println("you've entered the sentinel on your first try. exiting program." );
					break;
				} else {
					smallest = a;
					largest = a;
					}
			} 
			
			/**if the second value entered is the SENTINEL, print out smallest & largest values as the first value input by user, per instructions. */
			if (counter == 2 && a == SENTINEL) {
				/**per assignment, if sentinel is entered on second try, set smallest & largest == the one value entered and exit. so
				 * if counter is 2, go into this IF */
				println("smallest: " + smallest);
				println("largest: " + largest);
				break;
			} 
			
			/**for all values entered after the second value, if the SENTINAL is input, exit. otherwise, compare the input value with
			 * those in the smallest & largest variables. if the new value is smaller or larger, respectively, replace the current value
			 * of the corresponding variable with the new value.
			 */
			else {
					if (a == SENTINEL) {
						/**break out of the loop if the user enters the sentinel */
						println("smallest: " + smallest);
						println("largest: " + largest);
						break;
						}
			
					/**determine whether the value entered for a is either the largest or smallest so far */
					boolean isSmallest = (a < smallest);
					boolean isLargest  = (a > largest);
					
					/**if number entered for a is the smallest, set variable smallest to that value */
					if (isSmallest) {
						smallest =  a;
						println("Resetting smallest value to: " + a);
						}
					
					/**if value entered is smaller than current value of variable smallest, set variable smallest to new value */
					if (isLargest) {
						largest = a;
						println("Resetting largest value to: " + a);
						}
			}
		}	
	}
}
