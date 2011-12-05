/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	/** Constant defining radius for outer circle as defined in assignment */
	private static final double OUTER_RADIUS = 72;
	
	/** Constant defining radius for middle circle as defined in assignment */
	private static final double MIDDLE_RADIUS = 72 * .65;
	
	/** Constant defining radius for inner circle as defined in assignment */
	private static final double INNER_RADIUS = 72 * .3;
	
	/** run method only calls the methods that generate each circle */
	public void run() {
		createOuterCircle();
		createMiddleCircle();
		createInnerCircle();		
	}
	
	/** generate outer circle using radius and colors defined in assignment.*/
	private void createOuterCircle() {
		GOval oval1 = new GOval((OUTER_RADIUS * 2), (OUTER_RADIUS * 2) );
		oval1.setColor(color1());
		oval1.setFilled(true);
		oval1.setFillColor(color1());
		oval1.setLocation( horizontalLocation(oval1), verticalLocation(oval1) );
		add(oval1);
	}
	
	/** generate middle circle using radius and colors defined in assignment.*/
	private void createMiddleCircle() {
		GOval oval2 = new GOval((MIDDLE_RADIUS * 2), (MIDDLE_RADIUS * 2) );
		oval2.setColor(color2());
		oval2.setFilled(true);
		oval2.setFillColor(color2());
		oval2.setLocation( horizontalLocation(oval2), verticalLocation(oval2));
		add(oval2);
	}
	
	/** generate inner circle using radius and colors defined in assignment.*/
	private void createInnerCircle() {
		GOval oval3 = new GOval((INNER_RADIUS * 2), (INNER_RADIUS * 2) );
		oval3.setColor(color1());
		oval3.setFilled(true);
		oval3.setFillColor(color1());
		oval3.setLocation( horizontalLocation(oval3), verticalLocation(oval3) );
		add(oval3);
	}
	
	/* assign the x value used by setLocation, which drives the start point for drawing the oval.
	 * do this by dividing the width of the graphics program window by two AND dividing the oval width
	 * by two AND THEN subtracting the resulting half-oval-width from the resulting half-graphics-program-width.
	 */
	private double horizontalLocation(GOval oval) {
		return ( (getWidth()/2) - (oval.getWidth()/2) );
	}
	
	/*
	 * assign the y value used by setLocation, which drives the start point for drawing the oval.
	 * do this by dividing the height of the graphics program window by two AND dividing the oval height
	 * by two AND THEN subtracting the resulting half-oval-height from the resulting half-graphics-window-height.
	 */
	private double verticalLocation(GOval oval) {
		return ( (getHeight()/2) - (oval.getHeight()/2) );
	}

	/*
	 * there are two alternating colors used in this target. define each color in its own method in
	 * case we want to easily switch out either or both colors later. this method defines color 1 of 2.
	 */
	private Color color1() {
		return Color.RED;
	}
	
	/*
	 * there are two alternating colors used in this target. define each color in its own method in
	 * case we want to easily switch out either or both colors later. this method defines color 2 of 2.
	 */
	private Color color2() {
		return Color.WHITE;
	}
			
}




