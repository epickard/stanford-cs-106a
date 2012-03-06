/*
 * File: Breakout.java
 * -------------------
 * 
 * Implements the game of Breakout. Player has three turns to remove all the bricks at the top of the canvas by using a horizontally-movable
 * paddle to hit a ball. When the ball comes in contact with a brick, the brick is removed.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Pause time */
	private static final int PAUSE_TIME = 10;
	
/** Delta for move */
	private static final double DELTA = 3.0;
	
/** Application boundaries with respect to ball dimensions */
	private static final double RIGHT_EDGE = APPLICATION_WIDTH - (BALL_RADIUS * 2);
	private static final double LEFT_EDGE = 0;
	private static final double TOP = 0;
	private static final double BOTTOM = APPLICATION_HEIGHT - (BALL_RADIUS * 2);
	

/* Method: run() */
/** Run the Breakout program. */
	public void run() {
		setup();
		playGame();
	}
	
	
/*
 * Sets up the bricks and the paddle 
 */
	private void setup() {
		drawBricks();
		addMouseListeners();
		drawPaddle();
	}
	
/*
 * Executes game play for either three turns or until all bricks have been hit.
 */
	private void playGame() {
		
		for (int i = NTURNS; i > 0; i--) {
			drawBall();
			waitForClick();
			moveBall();
			remove(ball);
			
			if (i > 1 && bricksRemaining > 0) {
				/** need to create local variable with results of drawMessage() so we can remove it after pause time */
				GLabel continueGameLabel = drawMessage("You have " + (i - 1) + " more turn(s).", Color.BLUE, 2000);
				remove(continueGameLabel);
			} else if (i > 1 && bricksRemaining == 0) {
				drawMessage("Congratulations! You Win!", Color.BLUE, 0);
			} else if (i == 1 && bricksRemaining > 0) {
				GLabel gameLostLabel = drawMessage("Sorry! You Lose. Please Try again!", Color.RED, 0);
			}
		}
	}
		
/*
 * Draws specified number of columns and rows of bricks, placing them at a specified offset from top of canvas. Increments two
 * variables containing number of bricks with each brick drawn.
 */
	public void drawBricks() {
		/** Initialize brickNum to 0 for first loop */
		brickNum = 0;
		
		/**This loop drives the rows */
		for (int i = 0 ; i < NBRICKS_PER_ROW ; i++) {
				
			/**This loop drives the columns. It's also where I've placed the assignment of fill & color. */
			for (int j = NBRICK_ROWS; j > 0  ; j--) { 
				/**Width calc: get total width, subtract (# of bricks in row * (total width of those bricks plus the distance b/w bricks), 
				* Then, to center, ADD the width of the BRICK_SEP value and divide by two. That's your x coord */
				double x = (getWidth()  - (j * (BRICK_WIDTH + BRICK_SEP))) + (BRICK_SEP / 2);

				/**Height calc: defined brick offset from the top PLUS (# of bricks from bottom * total height of those bricks plus the 
				* distance b/w bricks), that's your y coord */	
				double y = BRICK_Y_OFFSET + (i * (BRICK_HEIGHT + BRICK_SEP)); 
						
				GRect rect = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
				rect.setFilled(true);
				rect.setColor(assignColor());
				rect.setFillColor(assignColor());
				add(rect);
				brickNum++;
				bricksRemaining++;
			} 
		}	
	}	
	
/*
 * Colors and fills the bricks. 
 */
	private Color assignColor() {
		/** First two rows of bricks are RED (that's NBRICKS_PER_ROW x 2), then the next two rows are ORANGE, and so on.
			* The number of bricks is incremented in drawBricks(), above. Bricks in final else{} would be arbitrarily BLACK. */
		if (brickNum < (NBRICKS_PER_ROW * 2)) {
			rcolor = Color.RED;
		} else if ( brickNum >= (NBRICKS_PER_ROW * 2 ) && brickNum < (NBRICKS_PER_ROW * 4) ) {
			rcolor = Color.ORANGE;
		} else if ( brickNum >= (NBRICKS_PER_ROW * 4) && brickNum < (NBRICKS_PER_ROW * 6) ) {
			rcolor = Color.YELLOW;
		} else if ( brickNum >= (NBRICKS_PER_ROW * 6) && brickNum < (NBRICKS_PER_ROW * 8) ) {
			rcolor = Color.GREEN;
		} else if ( brickNum >= (NBRICKS_PER_ROW * 8) && brickNum < (NBRICKS_PER_ROW * 10) ) {
			rcolor = Color.CYAN;
		} else {
			rcolor = Color.black;
		}

		return rcolor;
	}
	
/*
 * Draws Paddle and sets the color.
 */
	private void drawPaddle() {
		paddle = new GRect(100, (getHeight() - PADDLE_Y_OFFSET), PADDLE_WIDTH, PADDLE_HEIGHT); 
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}
	

 /*
  * Retrieves element at location where mouse is pressed.
  */
/** Record info when mouse is pressed */
	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
		gobj = getElementAt (startX, startY);
	}


/*
 * Moves paddle left and right within confines of canvas. Paddle does not move vertically.
 */
/** Record info while mouse is dragged */
	public void mouseDragged(MouseEvent e) {
		/** if user clicks on paddle, drag the paddle the difference of the distance b/w the original X coord and 
		 * the x coord where the object rested when the mouse was depressed. Paddle does not move from predefined Y coord, 
		 * so the delta is 0. 
		 * To prevent paddle from going offscreen, take the difference specified above, then add/subtract the absolute
		 * value of the difference of the x coord where the paddle lands versus where it would need to be in order to
		 * get the paddle to sit at x coord 0 or x coord app width minus paddle width*/
		if (gobj != null) {
			if (paddle.getX() < 0) {
				gobj.move( e.getX() - startX + ( Math.abs(0 - paddle.getX()) ), 0 );
			} else if (paddle.getX() > ( APPLICATION_WIDTH - PADDLE_WIDTH )) {
				gobj.move( e.getX() - startX - ( Math.abs( (APPLICATION_WIDTH - PADDLE_WIDTH) - paddle.getX()) ), 0 );
			} else {
				gobj.move( e.getX() - startX, 0 );
			} 
		}
		/** Set values in startY/X to the current values for the next time the paddle is moved */
		startX = e.getX();
		startY = e.getY();
	}
	

/*
 * Draws ball and places it in the center of the application.
 */
	private void drawBall() {
		ball = new GOval( (APPLICATION_WIDTH / 2 - BALL_RADIUS), (APPLICATION_HEIGHT / 2 - BALL_RADIUS), (BALL_RADIUS * 2), (BALL_RADIUS *2 ) );
		ball.setFilled(true);
		ball.setFillColor(Color.BLACK);
		add(ball);
	}	
	
/*
 * Moves ball within confines of top, left & right sides of canvas. 
 */
	private void moveBall() {
		/** initialize velocity of x to a random double b/w 1.0 & 3.0, then make it negative half the time. */
		vx = rgen.nextDouble(1.0, 3.0); 
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = rgen.nextDouble(1.0, 3.0); 
		
		while (true) {
			/** initial move of the ball */
			ball.move(vx, vy);
			pause(PAUSE_TIME);
			resovleCollisions();
			
			/** when the ball hits either the left or right edge of the app, reverse the x direction, continue on the 
			 * same y direction. */
			while (ball.getX() < LEFT_EDGE || ball.getX() > RIGHT_EDGE) {
				vx = -vx;
				ball.move(vx, vy);
				pause(PAUSE_TIME);
				resovleCollisions();
			}
			
			/** when the ball hits the top, reverse the y direction, continue on the same x direction */
			while (ball.getY() < TOP) {
				vy = -vy;
				ball.move(vx, vy);
				pause(PAUSE_TIME);
				resovleCollisions();
			}
			
			/** when the ball hits the bottom or when no more bricks remain, terminate play */
			if (ball.getY() >= BOTTOM || (bricksRemaining == 0)) {
				break;
			}
		}
	}

/** Check for collisions */
/*
 * Detects whether the ball has come into contact with an object on the canvas, excluding walls.
 * 
 * @return collisionObject if an object is detected, otherwise null.
 */
	private GObject getCollidingObject() {
		ballTopLeftX = ball.getX();
		ballTopRightX = ball.getX() + (BALL_RADIUS * 2);
		ballBottomLeftY = ball.getY() + (BALL_RADIUS * 2);
		ballBottomRightX = ball.getX() + (BALL_RADIUS * 2);
		ballBottomRightY = ball.getY() + (BALL_RADIUS * 2);
		
		/** If an object is detected on any of the bounding rectangle's four corners, top left, top, right, bottom left,
		 * bottom right, respectively, get the top left point of the element and return. If no object, return null. */
		if (getElementAt(ballTopLeftX, ball.getY()) != null) {
			collisionObject = getElementAt(ballTopLeftX, getY());
			return collisionObject;
		} else if (getElementAt(ballTopRightX, ball.getY()) != null) {
			collisionObject = getElementAt(ballTopRightX, getY());
			return collisionObject;
		} else if (getElementAt(ball.getX(), ballBottomLeftY) != null) {
			collisionObject = getElementAt(ball.getX(), ballBottomLeftY);
			return collisionObject;
		} else if (getElementAt(ballBottomRightX, ballBottomRightY) != null) {
			collisionObject = getElementAt(ballBottomRightX, ballBottomRightY);
			return collisionObject;
		} else {
			return null;
		}
	}
	
/** Resolve collisions */
/*
 * Removes objects that come into contact with the ball.
 */
	private void resovleCollisions() {
		/** Create local variable and assign it the result of the getCollidingObject() method, per assignment instructions */
		GObject collider = getCollidingObject();
		
		/** If a colliding object is detected against the ball and it is the paddle, just change ball's direction. Otherwise, if 
		 * a colliding object is detected against the ball it must be a brick, so remove the object and change ball's direction. */
		if (collider != null) {
			if (collider == paddle) {
				vy = -vy;
			} else {
				remove(collider);
				bricksRemaining--;
				vy = -vy;
			}
		}
	}
	
	

/** Draw informational messages after play */
/*
 * Creates a label with specified text, color and amount of time to pause after display. Pause time is used when the label will
 * later be removed: It gives the player time to read the message before removal. 
 * 
 * @param text String to be displayed
 * @param color Color to set the font
 * @param pauseTime milliseconds of time to pause program for message display
 * 
 * @return GLabel message with specified text, color and pause time
 */
	private GLabel drawMessage(String text, Color color, int pauseTime) {
		GLabel message = new GLabel (text);
		message.setFont("Arial-26");
		message.setColor(color);
		add(message, ( (getWidth() - message.getWidth()) / 2), (getHeight() / 10) );
		pause(pauseTime);
		return message;
	}
	
	
	
/** Instance variables for bricks & border */
	private Color rcolor;
	private int brickNum;
	private int bricksRemaining;
		
/** Instance variables for paddle*/
	private GRect paddle;
	private GObject gobj;
	private double startX;
	private double startY;
	
/** Instance variables for ball */
	private GOval ball;
	private double vx; 
	private double vy;

/** Instance variables for collision */
	private GObject collisionObject;
	private double ballTopLeftX;
	private double ballTopRightX;
	private double ballBottomLeftY;
	private double ballBottomRightX;
	private double ballBottomRightY;
	
/** Pseudorandom number generator */
	private RandomGenerator rgen = new RandomGenerator();
}
