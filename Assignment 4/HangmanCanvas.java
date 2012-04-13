/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

/*
 * Resets the display so that only the scaffold appears 
 */
	public void reset() {
		hang = new Hangman();

		/* initialize the ivars (80 was determined by trial and error, might be a better way to define ropeStartingY) */
		ropeStartingY = 80;
		canvasXCenter = getWidth() / 2;
		bodyMidpointY = ropeStartingY + (HEAD_RADIUS * 2) + BODY_LENGTH;
		nextBodyPart = 0;
		
		removeAll();
		buildScaffold();
		generateLabels();
	}

/*
 * Updates the word on the screen to correspond to the current state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 * 
 * @param word	The player's word
 * @param wrongGuessList	List of incorrectly-guessed letters
 */
	public void displayWord(String word, String wrongGuessList) {
		
		if (hang.getNumLettersRemaining() == 0) {
			/* set the word at the bottom of the canvas to the secret word */
			playerLabel.setLabel(word); 
			playerLabel.setFont("Helvetica-20");
			/* tell the player they've won */
			youWin.setLabel("Congratulations! You Win!");
			youWin.setFont("Helvetica-20");
			youWin.setColor(Color.GREEN);
			youWin.setLocation( (canvasXCenter - (youWin.getWidth() / 2)), getHeight() /2 );
			/* ask player if they want to play again */
			playAgain.setLabel("Play again? y/n");
			playAgain.setFont("Helvetica-20");
			playAgain.setColor(Color.GREEN);
			playAgain.setLocation((canvasXCenter - (playAgain.getWidth() / 2)), (getHeight() /2 + 50) );
			
			
		} else {
			playerLabel.setLabel(word);
			playerLabel.setFont("Helvetica-20");
		}
		
		incorrectGuesses.setLabel(wrongGuessList);
		incorrectGuesses.setFont("Helvetica-15");
	}

/*
 * Updates the display to correspond to an incorrect guess by the user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect guesses that appears at the bottom of the window.
 * 
 * @param letter	Incorrectly-guessed letter.
 */
	public void noteIncorrectGuess(char letter) {
		addNextBodyPart(nextBodyPart);
		wrongLetters += letter + "";
		
		/* When the last body part is drawn, indicate player has lost */
		if (nextBodyPart == 8) {
			youLose.setLabel("Sorry! You Lose! Play Again? y/n");
			youLose.setFont("Helvetica-20");
			youLose.setColor(Color.RED);
			youLose.setLocation( (canvasXCenter - (youLose.getWidth() / 2)), getHeight() /2 );
			
			secretWordForDisplay.setLabel("The word was: " + hang.getWordToGuess());
			secretWordForDisplay.setFont("Helvetica-20");
			secretWordForDisplay.setColor(Color.RED);
			secretWordForDisplay.setLocation( (canvasXCenter - (secretWordForDisplay.getWidth() / 2)), getHeight() /1.75 );
		}
	}
	
/*
 * Builds scaffold consisting of 3 parts: Pole, beam & rope. Scaffold never changes during the course of the game.
 */
	private void buildScaffold() {
		int ropeStartingX = canvasXCenter;
		int ropeFinalX = canvasXCenter;
		int ropeFinalY = ropeStartingY - ROPE_LENGTH;
		int beamFinalX = ropeFinalX - BEAM_LENGTH;
		int poleFinalY = ropeFinalY + SCAFFOLD_HEIGHT;
		
		/** build scaffold based on the rope's coordinates, which are at center of canvas on x-axis */
		GLine rope = new GLine(ropeStartingX, ropeStartingY, ropeFinalX, ropeFinalY);
		add(rope);
		
		GLine beam = new GLine(ropeFinalX, ropeFinalY, beamFinalX, ropeFinalY);
		add(beam);
		
		GLine pole = new GLine(beamFinalX, ropeFinalY, beamFinalX, poleFinalY );
		add(pole);
	}
	
/*
 * Adds the next body part to the scaffold when player guesses incorrectly. 
 *  
 *  @param nextBodyPart	number representing the next body part that should be drawn if player guesses incorrectly.
 */
	private void addNextBodyPart(int nextPart) {
		switch (nextPart) {
			case 0: 
				drawHead(); 
				nextBodyPart = 1;
				break;
			case 1:  //body
				drawNonHeadBodyParts(canvasXCenter, bodyMidpointY, canvasXCenter, (bodyMidpointY - BODY_LENGTH) ); //body
				drawNonHeadBodyParts(canvasXCenter - (HIP_WIDTH / 2), bodyMidpointY, canvasXCenter + (HIP_WIDTH / 2), bodyMidpointY); //bodyMidpoint
				nextBodyPart = 2;
				break;
			case 2: //left arm
				drawNonHeadBodyParts(canvasXCenter, (bodyMidpointY - BODY_LENGTH + ARM_OFFSET_FROM_HEAD), (canvasXCenter - UPPER_ARM_LENGTH), (bodyMidpointY - BODY_LENGTH + ARM_OFFSET_FROM_HEAD) ); //upper left arm
				drawNonHeadBodyParts((canvasXCenter - UPPER_ARM_LENGTH), (bodyMidpointY - BODY_LENGTH + ARM_OFFSET_FROM_HEAD), (canvasXCenter - UPPER_ARM_LENGTH), ((bodyMidpointY - BODY_LENGTH + ARM_OFFSET_FROM_HEAD) + LOWER_ARM_LENGTH) ); //lower left arm
				nextBodyPart = 3;
				break;
			case 3: //right arm
				drawNonHeadBodyParts( canvasXCenter, (bodyMidpointY - BODY_LENGTH + ARM_OFFSET_FROM_HEAD), (canvasXCenter + UPPER_ARM_LENGTH),  (bodyMidpointY - BODY_LENGTH + ARM_OFFSET_FROM_HEAD) ); //upper right arm
				drawNonHeadBodyParts((canvasXCenter + UPPER_ARM_LENGTH), (bodyMidpointY - BODY_LENGTH + ARM_OFFSET_FROM_HEAD), (canvasXCenter + UPPER_ARM_LENGTH), ((bodyMidpointY - BODY_LENGTH + ARM_OFFSET_FROM_HEAD) + LOWER_ARM_LENGTH) ); //lower right arm
				nextBodyPart = 4;
				break;
			case 4: //left leg
				drawNonHeadBodyParts(canvasXCenter - (HIP_WIDTH / 2), bodyMidpointY, canvasXCenter - (HIP_WIDTH / 2), bodyMidpointY + LEG_LENGTH); 
				nextBodyPart = 5;
				break;
			case 5: //right leg
				drawNonHeadBodyParts(canvasXCenter + (HIP_WIDTH / 2), bodyMidpointY, canvasXCenter + (HIP_WIDTH / 2), bodyMidpointY + LEG_LENGTH); //right leg
				nextBodyPart = 6;
				break;
			case 6: //left foot
				drawNonHeadBodyParts(canvasXCenter - (HIP_WIDTH / 2), bodyMidpointY + LEG_LENGTH, canvasXCenter - (HIP_WIDTH / 2) - FOOT_LENGTH, bodyMidpointY + LEG_LENGTH);
				nextBodyPart = 7;
				break;
			case 7: //right foot
				drawNonHeadBodyParts(canvasXCenter + (HIP_WIDTH / 2), bodyMidpointY + LEG_LENGTH, canvasXCenter + (HIP_WIDTH / 2) + FOOT_LENGTH, bodyMidpointY + LEG_LENGTH); //right foot
				nextBodyPart = 8;
				break;
			case 8: //default
			default: throw new ErrorException("no more body parts");
		}
	}
	
/*
 * Draws the head, which is a GOval.
 */
	private void drawHead() {
		GOval head = new GOval( (canvasXCenter - HEAD_RADIUS), ropeStartingY, HEAD_RADIUS * 2, HEAD_RADIUS * 2 );
		add(head);
	}

/*
 * Draws the non-head body parts, which are all GLines
 * 
 * @param x1	integer for x-coord of first point in line
 * @param y1	integer for y-coord of first point in line
 * @param x2	integer for x-coord of second point in line
 * @param y2	integer for y-coord of second point in line
 */
	private void drawNonHeadBodyParts(int x1, int y1, int x2, int y2) {
		GLine bodyPart = new GLine(x1, y1, x2, y2);
		add(bodyPart);
	}
	
/*
 * Generates the player's word and incorrect guesses labels
 */
	private void generateLabels() {
		playerLabel = new GLabel( "", getWidth() / 8, (getHeight() - (getHeight() / 12)) );
		add(playerLabel);
		
		incorrectGuesses = new GLabel( "", getWidth() / 8, (getHeight() - (getHeight() / 20)) );
		add(incorrectGuesses);
		
		youWin = new GLabel( "", getWidth() / 8, (getHeight() - (getHeight() / 20)) );
		add(youWin);
		
		youLose = new GLabel( "", getWidth() / 8, (getHeight() - (getHeight() / 20)) );
		add(youLose);

		secretWordForDisplay = new GLabel( "", getWidth() / 8, (getHeight() - (getHeight() / 20)) );
		add(secretWordForDisplay);
		
		playAgain = new GLabel( "", getWidth() / 2, getHeight() / 2 );
		add(playAgain);
	}

/*
 * ToString method, requirement from Lecture 9 for all non-program classes we write to have a toString() method
 */
	public String toString() {
		return "This class draws the hangman if the secret word is not guessed. The secret word this time is " + secretWordForDisplay;
	}
	

/* Instance Variables */
	private int nextBodyPart;
	private int canvasXCenter;
	private int bodyMidpointY;
	private int ropeStartingY;
	private String wrongLetters;
	private GLabel playerLabel;
	private GLabel incorrectGuesses;
	private GLabel youLose;
	private GLabel youWin;
	private Hangman hang;
	private GLabel secretWordForDisplay;
	private GLabel playAgain;
	
}
