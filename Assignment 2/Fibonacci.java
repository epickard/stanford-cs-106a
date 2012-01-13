/*
 * File: Fibonacci.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Fibonacci problem.
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	/**Eclipse wants this*/
	private static final long serialVersionUID = 1L;
	
	/** named constant*/ 
	public static final int MAX_TERM_VALUE = 10000;
	
	
	public void run() {
		
		/**declare variables for use in while loop*/
		int term1 = 0;
		int term2 = 1;
		
		while (term1 < MAX_TERM_VALUE) {	
			
			/**print term1 each time through the while loop. first time it will be set to declaration above.
			 * second time it will be set to value in term2's declaration b/c each # moves down a slot each
			 * time the while loop runs*/
			println(term1);

			/**Fib sequence is first term + second term in sequence = third term*/
			int term3 = term1 + term2;
			
			/**move term2's value into term1 slot*/
			term1 = term2;
			
			/**move term3's value into term2 slot*/
			term2 = term3;
		}
	}
}

