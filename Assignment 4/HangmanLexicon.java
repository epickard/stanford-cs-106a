/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;

import java.io.*; //for BufferedReader
import java.util.*; //for ArrayLists

public class HangmanLexicon {
	private ArrayList<String> strList;
	
	// HangmanLexicon constructor. Per page 9 of Assignment 4 instructions, it should include 
	//opening the lexicon file using a BufferedReader and reading each line into an ArrayList.
	public HangmanLexicon() {
		strList = new ArrayList<String>();
	
		try {
			BufferedReader rd = openFile();
			
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				strList.add(line);
			}
			rd.close();
			} catch (IOException ex) {
				throw new ErrorException(ex);
		}
	}

/*
 * Opens a file and reads from it. Taken directly from CS106A lecture 15 ~ 36:12.
 * 
 * @return filename  The name of the file, after having error-checked that it exists.
 */
	private BufferedReader openFile() {
		BufferedReader filename = null;
			
		while (filename == null) {
			try {
				filename = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			} catch (IOException ex) {
			}
		}
		return filename;
		}
	
/*
 * Pulls one word pseudorandomly from the Array of words to guess (generated from the lexicon file).
 * 
 * @return wordFromLexicon  Pseudorandomly-chosen word (from the lexicon file).
 */
	public String getWord() {
		String wordFromLexicon;
		
		return wordFromLexicon = strList.get(rgen.nextInt(0, (getWordCount() -1) ));
	}

/*
 * Retrieves the number of elements in the ArrayList
 * 
 * @return result from the method on ArrayList called size().
 */
	private int getWordCount() {
		return strList.size();
	}
	
/*
 * Instance of RandomGenerator for use in randomly picking a word (from the lexicon file).
 */
	private RandomGenerator rgen = new RandomGenerator();

}
