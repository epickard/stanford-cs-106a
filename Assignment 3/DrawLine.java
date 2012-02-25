
/*
* File: DrawLine.java
* -----------------------
* Allows user to draw a line on a canvas on-screen by clicking, dragging and releasing the mouse.
*/
import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;

public class DrawLine extends GraphicsProgram {

	public void run() {
		addMouseListeners();
	}

	/** method to respond to mouse presses which creates a line with same start/end points like eroberts fig 8-10
	 *  in that spot & adds to canvas */
	public void mousePressed (MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
		
		/** call GLine constructor and assign result to instance variable line */
		line = new GLine(lastX, lastY, lastX, lastY);
		add(line);
		
	}
	
	/** method to respond to mouse drags which sets the endpoint of the line by taking the mouse's latest 
	 *  coordinates */
	public void mouseDragged (MouseEvent e) {
		line.setEndPoint(e.getX(), e.getY());
		
		/** reset instance variables lastX & lastY for next use like eroberts fig 8-9 */
		lastX = e.getX();
		lastY = e.getY();
	}
	
	/** instance variables */
	private double lastX;
	private double lastY;
	private GLine line;
	
}

