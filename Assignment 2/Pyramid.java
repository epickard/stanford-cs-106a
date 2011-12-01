/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {

/**Eclipse added this constant, serialVersionUID*/
	private static final long serialVersionUID = 1L;

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

/*
 * Draw rows of rectangles, where each rectangle is the same width and height (as defined by constants above), 
 * and each row has one fewer rectangles than the last. Then center that stack of decrementing rows of rectangles
 */
	public void run() {
		/**This loop drives the rows */
			for (int i = 0 ; i <= BRICKS_IN_BASE ; i++) {
				int numBricks = BRICKS_IN_BASE - i;
				/**This loop drives the columns*/
				for (int j = numBricks; j >= 0  ; j--) { 
						/**Width calc: get total width, subtract (# of bricks in row * total width of those bricks), that's your x coord */
						double x = (getWidth()  - (j * BRICK_WIDTH));
						/**Height calc: get total height, subtract (# of bricks from bottom * total height of those bricks), that's your y coord */
						double y = getHeight() - (i * BRICK_HEIGHT); 
						GRect rect = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
						/** Center rows halfway across width of window minus half total width of all bricks in that row */
						rect.move( (-((getWidth()/2) - (numBricks*BRICK_WIDTH)/2)) , 0);
						add(rect);
					} 
				}	
	}
}

