/*
 * Section handout #4, problem #1.
 */

import acm.program.*;

public class Commas extends ConsoleProgram {

/*
 * Run method came written with assignment. Asks user to enter a number, if they enter nothing, break.
 * Otherwise, print the the result of what is returned by the addCommasToNumericString method when the number is passed as a param.
 * 
 */
	public void run() {
		   while (true) {
		      String digits = readLine("Enter a numeric string: ");
		if (digits.length() == 0) break;
		println(addCommasToNumericString(digits));
		   }		   
	}
	
/*
 * Inserts a comma to the left of every third digit.
 * 
 * @param digits  The numerical String entered by user.
 * 
 * @return commaNum  User's inserted number, but with the addition of commas to the left of every third digit.
 */
	private String addCommasToNumericString(String digits) {
		String commaNum = digits;
		
		/** only try to insert commas if the user's number is > 3*/
		if (commaNum.length() > 3) {
			/** since strings are processed L to R, reverse the number to make processing easier */
			String revNo = reverseString(commaNum);
			int index = 3;
				
			while (index < revNo.length()) {
				revNo = revNo.substring(0, index)
				+ ","
				+ revNo.substring(index);
				index = index + 4; //4 and not 3 b/c you have to account for the inclusion of the comma
			}
			/** once comma-processing is complete, reverse the number back to original order */
			commaNum = reverseString(revNo);
		}
		
		return commaNum;
	}

/*
 * Reverse String method, taken directly from Handout #25
 * 
 * @param str  String that will be reversed.
 * 
 * @return result The String passed in as formal param, but in reverse.
 */
	public String reverseString(String str) {
		String result = "";
		
		for(int i = 0 ; i < str.length(); i++) {
			result = str.charAt(i) + result;
		}
		return result;
	}

}
