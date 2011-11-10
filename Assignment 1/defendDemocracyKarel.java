import stanford.karel.SuperKarel;


public class defendDemocracyKarel extends SuperKarel {
	
	public void run() {
		cleanBallot();
	}
	
	/*
	 * Precondition: Karel facing east at the entrance to the first "Ballot Rectangle"
	 * Postcondition: Karel facing east to the right of of the last "Ballot Rectangle"
	 */
	private void cleanBallot() {
		while (frontIsClear()) {
			moveIntoRectangle();
			cleanRectangle();
			moveOutOfRectangle();
		}
	}
	
	/*
	 * Precondition: Karel facing east in the middle of the "Ballot Rectangle".
	 * If no beepers are present, check the other two corners on the "Ballot Rectangle".
	 * If any beepers are present on those chad, pick any and all up. Otherwise do nothing.
	 */
	private void cleanRectangle() {
		if (noBeepersPresent()) {
			turnLeft();
			move();
			turnAround();
			pickAllBeepers();
			move();
			move();
			turnAround();
			pickAllBeepers();
			move();
			turnRight();
		}
		
	}
	
	/*
	 * Get Karel into the rectangle so it can clean it.
	 * Precondition: Karel facing east at corner immediately preceeding Rectangle.
	 * Postcondition: Karel facing east at corner in middle of Rectangle.
	 */
	private void moveIntoRectangle() {
		move();
	}
	
	/*
	 * Get Karel out of the Rectangle
	 * Precondition: Karel facing east in middle of Rectangle.
	 * Postcondition: Karel facing east just to the outside the Rectangle, to the east.
	 */
	private void moveOutOfRectangle() {
		move();
	}
	
	/*
	 * When beepers are present, pick any and all of them up.
	 * Precondition: Karel is standing on a corner with 1 or more beepers present.
	 * Postcondition: Karel is standing on a corner with no beepers present.
	 */
	private void pickAllBeepers(){
		if (beepersPresent()) {
			while (beepersPresent()) {
				pickBeeper();
			}
		}
	}
	

}
