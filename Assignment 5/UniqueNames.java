/*
 * This file reads in a list of names from the user. If the name does not already exist in the ArrayList,
 * the name is added the ArrayList. If user hits [RETURN], the list of names is displayed.
 */

import acm.program.*;
import java.util.*;

public class UniqueNames extends ConsoleProgram {
	
public void run() {

	while (true) {
		String nameEntered = readLine("Enter name: ");
		if (nameEntered.equals("")) break;
		/**if user enters a name that IS NOT already in the ArrayList, add the name to the ArrayList */
		if (!list.contains(nameEntered)) {
			list.add(nameEntered);
		}
	}
	println("Unique name list contains: ");
	printArrayList(list);
}

/*
 * Prints values in ArrayList passed into method
 * 
 * @param:  list   The ArrayList passed in by caller
 */
private void printArrayList(ArrayList list) {
	ArrayList al = list;
	for (int i = 0; i < list.size(); i++) {
		println(al.get(i));
	}
}
	
/*
 * Instance variables
 */
private ArrayList<String> list = new ArrayList<String>();

}
