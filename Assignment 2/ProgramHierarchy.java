/*
 * File: ProgramHierarchy.java
 * Name: Emily Pickard
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final double RECT_WIDTH = 150;
	
	private static final double RECT_HEIGHT = 75;

	public void run() {

		/**create local variable instances of rect so they can be referred to in methods to draw the lines and labels */
		GRect rect1 = drawRectangle( 1.0, 0.75 );
		GRect rect2 = drawRectangle( 0.5, 1.25 );
		GRect rect3 = drawRectangle( 1.0, 1.25 );
		GRect rect4 = drawRectangle( 1.5, 1.25 );
		
		/**add the rects to the canvas */
		add(rect1);
		add(rect2);
		add(rect3);
		add(rect4);
		
		/**add lines to canvas. since the instances aren't referred to anywhere, don't need to create local variables */
		add( drawLine(getXBound(rect1), getYBound(rect1), getXBound(rect2), getYBound(rect2)) );
		add( drawLine(getXBound(rect1), getYBound(rect1), getXBound(rect3), getYBound(rect3)) );
		add( drawLine(getXBound(rect1), getYBound(rect1), getXBound(rect4), getYBound(rect4)) );
		
		/**add labels to canvas. since the instances aren't referred to anywhere, don't need to create local variables */
		add( drawLabel("Program", rect1) );
		add( drawLabel("GraphicsProgram", rect2) );
		add( drawLabel("ConsoleProgram", rect3) );
		add( drawLabel("DialogProgram", rect4) );

	}
	
	
	/*
	 * draws rectangles by calling the xOfRect & yOfRect methods to find the x & y coordinates. it uses the named constants
	 * BRICK_WIDTH & BRICK_HEIGHT to determine how wide and how tall the rectangles should be.
	 */
	private GRect drawRectangle( double x, double y) {
		GRect rect = new GRect( setXOfRect(x) , setYOfRect(y), RECT_WIDTH, RECT_HEIGHT);
		
		return rect;
	}
	
	/*
	 * draws the lines by:
	 * determining (x,y) for the start-point as the x coord of the first rect plus half the first rect width (to center line on rect) 
	 * and the y coord of the rect plus the whole rect height to start each line at bottom of first rect.
	 * determining (x,y) for the end-point as the x coord of the second rect plus half the second rect width (same as start point)
	 * and the y coord of the second rect to end each line at the top of the second rect.
	 */
	private GLine drawLine(double a, double b, double x, double y) { 
		GLine line = new GLine ( (a + (RECT_WIDTH * 0.5)) , (b + RECT_HEIGHT), (x + (RECT_WIDTH * 0.5)), (y));
		
		return line;
	}
	
	
	/*
	 * draw labels by first creating a label at 0,0 to determine width and height, then uses the setLocation() method on GObject to
	 * center the label in the rect by 1. finding the x coord and subtracting the rect width to the label width and dividing by two; 
	 * and 2. finding the y coord of rect and adding the rect height and adding the label height and dividing by 2.
	 */
	private GLabel drawLabel(String text, GRect rect) {
		GLabel label = new GLabel(text, 0, 0);
		label.setLocation( (( getXBound(rect) + ((rect.getWidth() - label.getWidth()) / 2)) ),(( getYBound(rect) + ((rect.getHeight() + label.getHeight()) / 2)) ) );
		
		return label;
	}
	
	/*
	 * determines x coordinate of a rectangle by first finding where x would need to be for that rectangle to be centered in the 
	 * graphics program on x axis. then, depending on the argument passed into the formal parameter, the x coordinate is adjusted 
	 * left or right of center by that amount.
	 */
	private double setXOfRect (double xFromCenter) {
		return ( ((xFromCenter * getWidth()) - RECT_WIDTH ) /2 );
	}
	
	/*
	 * determines the y coordinate of a rectangle by first finding where y would need to be for that
	 * rectangle to be centered in the graphics program on the y axis. then, depending on the argument 
	 * passed into the formal parameter, the y coordinate is adjusted up or down from center by that amount.
	 */
	private double setYOfRect (double yFromCenter) {
		return ( ((yFromCenter * getHeight()) - RECT_HEIGHT ) /2 );
	}
	
	/*
	 * gets the x coordinate (aka boundary) of the rectangle that is passed in to the formal parameters.
	 */
	private static double getXBound(GRect rect) {
		double xVal = rect.getX();
		
		return xVal;
	}
	
	/*
	 * gets the y coordinate (aka boundary) of the rectangle that is passed in to the formal parameters.
	 */
	private static double getYBound(GRect rect) {
		double yVal = rect.getY();
		
		return yVal;
	}

}


