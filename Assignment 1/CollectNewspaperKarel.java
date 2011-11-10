/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	// You fill in this part
	// Methods Karel executes
	   public void run() {
		   moveToPaper();
		   retrievePaper();
		   returnToStart();
	   }

	   
	// Decomposition: Private method to move Karel from start to Beeper/Paper
	   private void moveToPaper(){
		   move();
		   move();
		   turnToRight(); 
		   move();
		   turnLeft();
		   move();
	   }
	   
	// Make my own turnRight method using for loop
	   private void turnToRight() {
		     for (int i = 0; i<3; i++) {
		    	 turnLeft();
			 };
	   }
	  
	// Decomposition: Private method to allow Karel to pick up Beeper/Paper
	   private void retrievePaper() {
		   pickBeeper();
	   }
	   
    // Decomposition: Private method to return Karel from Beeper location to Starting Point	   
	   private void returnToStart() {
		   turnAround();
		   move();
		   move();
		   move();
		   turnToRight();
		   move();
		   turnRight();
	   }
	   
}
