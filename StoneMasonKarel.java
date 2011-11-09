/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	//turn left
	//ascend to the wall
	//place beepers where there are none
	//turn around
	//descend wall
	//turn left
	//advance to next column
	//repeat until last one
	//then do all except advance
	
	// You fill in this part
	public void run() {
		fixColumn();
	}
	
	private void fixColumn() {
		while (frontIsClear()) {
		turnLeft();
		moveToWall();
		turn();
		moveToWall();
		turn();
		advance();
		}
		turnLeft();
		moveToWall();
		turn();
		moveToWall();
		turn();
	}
	
	private void moveToWall() {
		while (frontIsClear()) {
			layStone();
			move();
		}
		if (frontIsBlocked()) {
			layStone();
		}
	}
	
	private void layStone() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}
	
	
	private void turn() {
		if (facingNorth()) {
			turnAround();
		} else if (facingSouth()) {
			turnLeft();
		} 
	}
	
	private void advance() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}
	

}
	
