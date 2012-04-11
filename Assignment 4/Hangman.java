/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.*;

public class Hangman extends ConsoleProgram {
	
/** number of incorrect guesses in a round */
private static final int NUM_INCORRECT_GUESSES = 8; //per assignment instructions

/** init added line for line from page 7 of http://see.stanford.edu/materials/icspmcs106a/27-assignment-4-hangman.pdf */
/** "...Since this is a console program, the console is already installed and will therefore show up in the left column.  
    When you add the HangmanCanvas it will occupy the second column..." */
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

    public void run() {
    	hl = new HangmanLexicon(); //Per instructions
    	
    	setup();
    	playGame();
	}
    
    private void setup() {
    	canvas.reset();
    	println("Welcome to Hangman!");
    }
    
/*
 * while player has guesses remaining, run through game's functions. If guesses but no letters remain, player wins. If no guesses remain, 
 * player loses.
 */
    private void playGame() {
    	wordToGuess = hl.getWord(rgen.nextInt(0, hl.getWordCount())); //getWord(4);
    	numLettersRemaining = wordToGuess.length();
    	guessesRemaining = NUM_INCORRECT_GUESSES;
    	playersWord = concatNCopies(wordToGuess.length(), "-");
    	wrongGuessList = "";
    	
    	/** while player has guesses, if letters remain, run through game's functions. otherwise guesses remain 
    	 *  with no letters left, so player wins. */
    	while (guessesRemaining > 0) {
    		if (numLettersRemaining > 0){
    	    	printWord();
    	    	printGuessesRemaining();
    	    	acceptGuess();
    	    	alterWord(wordToGuess, playersWord, "-", letterGuessed);
    		} else {
    			printWord();
    			println("You guessed the word: " + getWordToGuess() + '\n' + "You win.");
    			break;
    		}
    	}
    	
    	/** if player has no remaining guesses, (s)he loses. */
    	if (guessesRemaining == 0) {
    		println("You're completely hung." + '\n' + "The word was: " + getWordToGuess() + '\n' + "You lose.");
    	}
    }
  
    
/*
 * Prints player's word in its current form, both to the console and the display.
 */
    private void printWord() {
    	println("The word now looks like this: " + playersWord);
    	canvas.displayWord(playersWord, wrongGuessList);
    }
    
/*
 * Prints remaining guesses.
 */
    private void printGuessesRemaining() {
    	if (guessesRemaining > 1) {
    		println("You have " + guessesRemaining + " guesses left.");
    	} else if (guessesRemaining == 1) {
    		println("You have only one guess left.");
    	}
    }
    
    
/*
 * Determines validity of player's guess. Guess must be only one letter. No numbers or special characters allowed.   
 */
    private void acceptGuess() {
    	
    	while (true) {
    		letterGuessed = readLine("Your guess: ");
    		
    		if (!isOneCharGuess(letterGuessed) || !Character.isLetter(guessAsCharacter(letterGuessed))) {
    			println("Illegal guess. Please try again.");
    		} else {
        		letterGuessed = letterGuessed.toUpperCase();
        		break;
        	}
    	} 
    }
    
/*
 * Checks whether the letter guessed by the player is present in the game's word. If guessed letter is not present, 
 * returns the player's word unchanged. If it is present, method replaces the dash(es) in the player's word with the letter guessed. 
 * 
 * @param ranWord	word that's being guessed.
 * @param pWord	current state of word player has guessed so far.
 * @param orig	"dash" in player's word that may be replaced.
 * @param repl	letter guessed by player.
 * 
 * @return	playersWord	current state of player's word after each guessed letter. 
 */
    private String alterWord (String ranWord, String pWord, String orig, String repl) {
    	int index = ranWord.indexOf(repl);
    	
    	if (index != -1) {
    		while (index != -1) {
    		pWord = pWord.substring(0, index) 
    			  + repl 
    			  + pWord.substring(index + orig.length());
    		index = ranWord.indexOf(repl, index + 1);
    		numLettersRemaining--;
    		}
    		playersWord = pWord;
    		println("That guess is correct.");
    		return playersWord;
    	} else {
    		println("There are no " + letterGuessed + "'s in the word.");
    		wrongGuessList += letterGuessed;
    		canvas.noteIncorrectGuess(guessAsCharacter(letterGuessed));
    		guessesRemaining--;
    		return playersWord;
    	}
    }
    
    
/*
 * Concatenates specified number of copies of specified string. Taken directly from Art & Sci of Java, eroberts, page 242.
 * 
 * @return result concatenated string of the specified string. Taken directly from Art & Sci of Java, eroberts, page 242.
 */
    private String concatNCopies(int n, String str) {
	    String result = "";
		    for (int i = 0; i < n; i++) {
		    result += str;
    }
		return result;
    }
    
 /*
  * Confirms player's guess is no longer than 1 character
  * 
  * @param str	guess inputted by the user
  * 
  * @return	true if guess contains only one character, false if more than one character OR if no character entered.
  */
    private boolean isOneCharGuess(String str) {
    	int counter = 0;
    	
    	for (int i = 0; i < str.length(); i++) {
    		counter++;
    	}
    	
    	if (counter == 1) return true;
    	else return false;
    }
    
/*
 * Converts single-character String to Character
 * 
 * @param str single-character String from acceptGuess method.
 */ 
    private Character guessAsCharacter(String str) {
		Character ch = str.charAt(0);
		return ch;
    }

    
/*
 * Gets the secret word for caller.
 */
    public String getWordToGuess() {
    	return wordToGuess; 
    }
    
/*
 * Get the number of guesses remaining for caller
 */
    public int getGuessesRemaining() {
    	return guessesRemaining;
    }
    
/*
 * Get the number of guesses remaining for caller
 */
    public int getNumLettersRemaining() {
    	return numLettersRemaining;
        }    
    
/*
 * Instance of RandomGenerator to randomly pick a word from HangmanLexicon
 */
    private RandomGenerator rgen = new RandomGenerator();
    
    
/** Instance variables for console portion of program */
    private HangmanLexicon hl;
    private String letterGuessed;
    private String playersWord;
    private String wrongGuessList;
/** Instance variables for canvas portion of program */
    private HangmanCanvas canvas;
    
    
/** Class variables */
    private static String wordToGuess;
    private static int guessesRemaining;
    private static int numLettersRemaining;
    

}
