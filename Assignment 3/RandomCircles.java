
import acm.program.*; 
import acm.graphics.*;
import acm.util.*;
import java.awt.*;



public class RandomCircles extends GraphicsProgram {
	 
	 /* private instance variables for all information that can be re-used for each of the 10 balls */ 
	 private GOval ball; 
	 private double diam_ball;
	 private double radius;
	 private Color color;

	 
	 /* use a for loop to run through the createBall() method 10 times */
	 public void run() {

		 for (int i = 0; i < 10; i++) {
			 radius = rgen.nextDouble(5,50);
			 diam_ball = radius * 2;
			 createBall();
		 }
	 }
	 
	 
	 private GOval createBall() { 
		  ball = new GOval(diam_ball, diam_ball); 
		  ball.setFilled(true);
		  ball.setColor(rgen.nextColor());
		  ball.setLocation(rgen.nextDouble(0, getWidth() - diam_ball), 
				  		   rgen.nextDouble(0, getHeight() - diam_ball));
		  add(ball); 
		  return ball;
		 }

	 
	 /* Private instance variable for random colors & ball sizes */
	 private RandomGenerator rgen = RandomGenerator.getInstance();
}














