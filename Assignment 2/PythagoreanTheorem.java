/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	/**eclipse wants me to put in this serialVersionUID.*/
	private static final long serialVersionUID = 1L;
	/*
	 * In run() prompt user to input an int and hit  [ENTER], then repeat.
	 * program then uses the Math.sqrt() function on a squared plus b squared,
	 * then prints the result to the console.
	 */
	public void run() {
		int a = readInt("Enter the first number (int) and press [ENTER]: ");
		int b = readInt("Enter the second number (int) and press [ENTER]: ");
		double c = Math.sqrt( (a*a) + (b*b));
		println("The square root is = " + c);
	}
}
