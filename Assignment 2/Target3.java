/*
 * File: Target3.java
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target3 extends GraphicsProgram {	

	/** Eclipse suggested I add this */
	private static final long serialVersionUID = 1L;

	/** Constant for radius for outer circle. Assignment specifies radius is 1 INCH which is 72 pixels. */
	private static final double OUTER_RADIUS = 72;
	
	/** Constant for radius for middle circle. Assignment specifies radius is .65 INCHES */
	private static final double MIDDLE_RADIUS = 72 * .65;
	
	/** Constant for radius for inner circle. Assignment specifies radius is .3 INCHES */
	private static final double INNER_RADIUS = 72 * .3;
	

	/** run only makes method calls to my createCircle method, passing in the specific parameters for each circle. */
	public void run() {
		createCircle(OUTER_RADIUS, Color.RED);
		createCircle(MIDDLE_RADIUS, Color.WHITE);
		createCircle(INNER_RADIUS, Color.RED);
	}
	
/*
 * method that returns a filled circular GOval of a specified radius in the center of the graphics window.
 * the specifications indicate the fill color and the outline color should be the same.
 */	
	private GOval createCircle (double radius, Color color) {
		/* 
		 * get x from halving the width of the graphics program and subtracting the radius
		 * get y from halving the height of the graphics program and subtracting the radius
		 * get width of circle by doubling radius, get height of circle by doubling radius
		 */
		GOval circle = new GOval( (getWidth()/2 - radius) , (getHeight()/2 - radius), radius*2, radius*2 );
		
		/** spec says circles are filled in and that the fill & outline colors are the same by circle. so if a circle has
		 * a red outline it will have a red fill. */
		circle.setFilled(true);
		circle.setColor(color);
		circle.setFillColor(color);
		add(circle);
		
		return circle;
	}
			
}