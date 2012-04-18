/*
 * Section handout #4, problem #2.
 */

import acm.program.*;


public class RemoveAllOccurrences extends ConsoleProgram {

/*
 * While the user continues to enter Strings and characters they'd like removed, do so. If user enters no String, exit. 
 * Print the altered String.
 */
	public void run() {
		while (true) {
			String word = readLine("Please enter word to alter and hit [ENTER]: ");
			if (word.equals("")){
				println("Thank you for playing. Goodbye.");
				break;
			}
			String letter = readLine("Please enter letter you'd like removed: ");
			char ch = guessAsCharacter(letter);
			println(removeAllOccurrences(word, ch));
		}
	}

/*
 * Removes all instances of user-specified character from a user-specified String
 * 
 * @param strToManipulate  User-specified String to alter
 * @param charToRemove  User-specified char to remove from the String
 * 
 * @return strToManipulate  The users's original String with all the specified character instances removed.
 */
	private String removeAllOccurrences(String strToManipulate, char charToRemove) {
		int index = strToManipulate.indexOf(charToRemove);		

			while (index != -1) {
				strToManipulate = strToManipulate.substring(0, index)
				+ strToManipulate.substring(index + 1);
				index = strToManipulate.indexOf(charToRemove, index);
			}
			return strToManipulate;
	}
	
	
/*
 * Converts single-character String to Character
 * 
 * @param str single-character String from acceptGuess method.
 * 
 * @return ch  a single char
 */ 
    private Character guessAsCharacter(String str) {
		Character ch = str.charAt(0);
		return ch;
    }

}
