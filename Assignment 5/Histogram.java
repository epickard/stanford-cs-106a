import java.io.BufferedReader;

import acm.program.*;
import java.io.*;
import acm.util.*;

public class Histogram extends ConsoleProgram {

	public void run() {
		initializeArray();
		BufferedReader rd = openFile("File Name: ");
		
	  /** read lines from the file until the end of the file */
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				int score = Integer.parseInt(line);
				/** increment the values in the appropriate element if score falls within that particular range */
				if (score >= 0 && score <= 9) {
					scoresArr[0]++;
				} else if (score >= 10 && score <= 19) {
					scoresArr[1]++;
				} else if (score >= 20 && score <= 29) {
					scoresArr[2]++;
				} else if (score >= 30 && score <= 39) {
					scoresArr[3]++;
				} else if (score >= 40 && score <= 49) {
					scoresArr[4]++;
				} else if (score >= 50 && score <= 59) {
					scoresArr[5]++;
				} else if (score >= 60 && score <= 69) {
					scoresArr[6]++;
				} else if (score >= 70 && score <= 79) {
					scoresArr[7]++;
				} else if (score >= 80 && score <= 89) {
					scoresArr[8]++;
				} else if (score >= 90 && score <= 99) {
					scoresArr[9]++;
				} else if (score == 100) {
					scoresArr[10]++;
				} else {
					throw new ErrorException("Score is outside 0-100 range.");
				}
			}
			
			printHistogram(scoresArr);
			rd.close();
		} catch (IOException e) {
			throw new ErrorException(e);
		}
		
	}
	
	
/*
 * Opens a connection to a file with the specified name.
 * 
 * @param prompt The name of the file passed in by the user.
 * 
 * @return rd Connection to the file with a BufferedReader attached.
 */
	private BufferedReader openFile(String prompt) {
		BufferedReader rd = null;
		
		while (rd == null) {
			try {
				String fileName = readLine(prompt);
				rd = new BufferedReader(new FileReader(fileName));
			} catch (IOException e) {
				println("File not found.");
			  }
		}
		return rd;
	}

/*
 * Initializes an Array of eleven elements for the score ranges. Sets all elements values to 0.
 */
	private void initializeArray() {
		scoresArr = new int[11];
		for (int i = 0; i < scoresArr.length; i++) {
			scoresArr[i] = 0;
		}
	}

/*
 * Print the number of asterisks that correspond to the value in each element of scoresArr[]
 */
	private void printHistogram(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int numAst = arr[i];
			if (i==0) {
				print("00-09: ");
			} else if (i==10) {
				print("  100: ");
			} else {
				print((i*10) + "-" + ((i*10)+9) + ": ");
			}
			for (int j = 0; j < numAst; j++) {
				print("*");
			}
			println('\n');
		}
	}

/*
 * Instance variables
 */
private int[] scoresArr;
	
}
