/*
 * File: Target3.java
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class RobotFace extends GraphicsProgram {	

	/** Eclipse suggested I add this*/
	private static final long serialVersionUID = 1L;
	
	/**constants for dimensions of head*/
	private static final double  HEAD_WIDTH = 200;
	private static final double  HEAD_HEIGHT = 375;
	
	/**constant for determining dimensions of eyes*/
	private static final double  EYE_RADIUS = 20;
	
	/**constants for determining dimensions of mouth*/
	private static final double MOUTH_WIDTH = 150;
	private static final double MOUTH_HEIGHT = 50;
	
	

	/**draws and adds the the head, eyes and mouth to the canvas*/
	public void run() {
		
		/**local variable in run() to contain head Ojbect so it can be referenced by eyes and mouth*/
		GRect head = drawRect(( 
					  (getWidth()-HEAD_WIDTH)/2 ) , 
					  ( (getHeight()-HEAD_HEIGHT)/2 ),
					  HEAD_WIDTH,
					  HEAD_HEIGHT,
					  Color.GRAY, 
					  Color.BLACK);
		
		/**adds head to canvas*/
		 add(head);
		
		
		/**creates mouth and adds it to canvas*/
		add(drawRect(
		 		head.getX() + (head.getWidth() / 2) - (MOUTH_WIDTH / 2),
		 		head.getY() + head.getHeight() - (head.getHeight() / 4),
		 		MOUTH_WIDTH,
		 		MOUTH_HEIGHT,
		 		Color.WHITE,
		 		Color.WHITE));
		
		/**adds left eye to canvas. uses the GObject getX() & getY() methods to find the x,y, then adds head width & height to center eyes*/
		add(drawEye(EYE_RADIUS, 
					head.getX() + (head.getWidth() / 4) - EYE_RADIUS, 
					head.getY() + (head.getHeight() / 4) - EYE_RADIUS ));
		
		
		/**add right eye to canvas. uses GObject getX() and getY() to find the x,y then adds head height to center on y,
		 * then finally moves the whole thing on the y axis one head length and SUBTRACTS 1/4 of head width to center x. */
		add(drawEye(EYE_RADIUS, 
					(head.getX() + head.getWidth()) - (head.getWidth() / 4) - EYE_RADIUS, 
					head.getY() + (head.getHeight() / 4) - EYE_RADIUS ));
	}
	
	
	
	/**generates rectangles. returns rectangles with specified coordinates and colors*/
	private GRect drawRect(double x, double y, double height, double width, Color fillColor, Color outline) {
		GRect rect = new GRect( x, y, height, width);
		rect.setColor(outline);
		rect.setFilled(true);
		rect.setFillColor(fillColor);
		
		return rect;
	}

	/**generates circles for eyes. requires radius and x & y coords. returns circle with specified coords & colors.*/
	private GOval drawEye (double radius, double x, double y) {
		GOval circle = new GOval( x, y, radius*2, radius*2 );
		circle.setFilled(true);
		circle.setColor(Color.YELLOW);
		circle.setFillColor(Color.YELLOW);
		//add(circle);
		
		return circle;
	}
	
	
			
}