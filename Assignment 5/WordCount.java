import java.io.*;
import acm.util.*;
import acm.program.*;


public class WordCount extends ConsoleProgram {


	public void run() {
		BufferedReader rd = openFile("File Name: ");
		int lineCount = 0;
		int characters = 0;
		
	/** while there are lines being returned from the file, increment the line & character counters and
		 call the countWords method. once there are no more lines, close the connection to the file. */
		try {
			while(true) {
				String line = rd.readLine();
				if (line == null) break;
				lineCount++;
				countWords(line);
				characters = characters + line.length(); //does not include the character(s) at the end of the line
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
		/** print the counter values */
		println("Lines: " + lineCount);
		println("Words: " + wordCount);
		println("Characters: " + characters);

	}

/*
 * Attempts to open a file based on the filename that gets passed in.
 * 
 * @param prompt The name of the file that gets passed in from the openFile() method (from Buffered Reader).
 * 
 * @return rd  The contents of the file with a BufferedReader attached.
 */
	
private BufferedReader openFile(String prompt) {
	BufferedReader rd = null;
	while (rd == null) {
		try {
		  String fileName = readLine(prompt);
		  rd = new BufferedReader(new FileReader(fileName));
		} catch (IOException ex) {
			println("That filename cannot be found. Please try again.");
		}
		
	}
	return rd;
}

/*
 * Counts the number of words in a file.
 * 
 * @param str The line that gets passed in from a file.
 */
	private void countWords(String str) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!Character.isLetterOrDigit(ch) && Character.isWhitespace(ch)) { //isWhitespace() method from p 235 of Art & Science of Java
				wordCount++;
			}
		}
	}

/*
 * Class Variables
 */
int wordCount = 0;

}
