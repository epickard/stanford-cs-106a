/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	/*
	 * Run method will contain just one sub-method.
	 */
	public void run() {
		moveToMidpoint();
	}
	
	/*
	 * Expected input: Karel at 1st Street & 1st Avenue. Infinite beepers in bag. Empty world.
	 * Expected output: Karel at either exact midpoint of 1st Street and <whatever> Avenue if the
	 * number of Avenues is odd OR Karel at either of the middle-most Avenues on 1st Street if the 
	 * number of Avenues is even.
	 */
	private void moveToMidpoint() {
		aggregateBeepers();
		distributeBeepers();
	}
	
	/*
	 * Expected input: Karel @ corner of 1st Ave & 1st Street. No beeper on corner. 
	 * Infinite # of beepers in bag.
	 * 
	 * Expected output: Karel @ final corner on 1st Street. The total # of beepers on 
	 * this corner is equal to the number of Avenues Karel crossed to arrive here.
	 */
	
	private void aggregateBeepers() {
		putBeeper();
		while (frontIsClear()) {
			move();
			putBeeper();
			turnAround();
			move();
			while (beepersPresent()) {
				pickBeeper();
				turnAround();
				move();
				putBeeper();
				turnAround();
				move();
			}
			if (noBeepersPresent()) {
				turnAround();
				move();  //at this point Karel faces East
			}
		}
	}
	
	/*
	 * Expected Input: Karel facing East on the final Avenue corner for 1st Street.
	 * The number of beepers on the corner is equal to the number of
	 * Avenues on the map.
	 */
	private void distributeBeepers() {
		while (beepersPresent()) {
			pickBeeper();
			if (beepersPresent()) {
				pickBeeper();
				faceWest();
				move();
				putBeeper();
				turnAround();
				move();
			}
			if (noBeepersPresent()) {
				faceWest();
				move();
			}
		}
		putBeeper(); //drop the beeper from the spec
	}
	
	/*
	 * Expected Input: Karel facing any direction.
	 * Expected Output: If Karel came in facing East, it will face West. Else, no change.
	 */
	private void faceWest() {
		if (facingEast()) {
			turnAround();
		}
	}
	
}
