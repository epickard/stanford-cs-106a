/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		putBeeper();
		while (frontIsClear()) {
			moveWestToEast();
			moveEastToWest();
		}
	}
	
	
	private void moveWestToEast() {
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			} 
		}
		if (leftIsClear()) { 
			if (beepersPresent()) {
				turnLeft();
				move();
				turnLeft();
				move();
			} else {
				turnLeft();
				move();
				turnLeft();
			}
		} else { //if left is not clear
			turnLeft();
		}
	}
	
	private void moveEastToWest() {
		while (frontIsClear()) {
			putBeeper();
			move();
			if (frontIsClear()) {
				move();
			} 
		}
		if (rightIsClear()) { 
			if (beepersPresent()) {
				turnRight();
				move();
				turnRight();
				move();
			} else {
				turnRight();
				move();
				putBeeper();
				turnRight();
			}
		} else { //if right is not clear
			if (facingWest()) { //because MoveEastToWest always comes last.
				turnRight();
			}
		}
	}
	
}
