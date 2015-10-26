import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

/***********************************************************************
 * This class is a subclass to the main class expressionTree.java. With only one method
 * that takes in a postfix string and converts it so that it can scan through 
 * the characters and determine when to push or pop a number/operand onto or 
 * off of the stack.
 *************************************************************************/

public class CalculatorEngine {
	public CalculatorEngine() {

	}

	public String compute(String input) {
		// Process the list into an ArrayList
		List<String> processedList = new ArrayList<String>();
		if (!input.isEmpty()) {
			StringTokenizer st = new StringTokenizer(input);
			while (st.hasMoreTokens()) {
				processedList.add(st.nextToken());
			}
		} else {
			return "Error";
		}
		// A Stack used for the calculation
		Stack<String> tempList = new Stack<String>();
		// Iterate over the whole processed list
		Iterator<String> iter = processedList.iterator();
		while (iter.hasNext()) {
			String temp = iter.next();
			char c[] = temp.toCharArray();
			for (char tempC : c) {
				if (tempC == '0' || tempC == '1' || tempC == '2'
						|| tempC == '3' || tempC == '4' || tempC == '5'
						|| tempC == '6' || tempC == '7' || tempC == '8'
						|| tempC == '9') {
					// If the current item is a number (aka operand), push it
					// onto the stack
					String tempoC = Character.toString(tempC);
					tempList.push(tempoC);
				} else if (tempC == '+' || tempC == '-' || tempC == '/' || tempC == '–'
						|| tempC == '*') {
					// If the current item is an operator we pop off the last
					// two elements of our stack and calculate them using the 
					// operator we are looking at. Push the result onto the stack.
					if (tempC == '*') {
						int rs = Integer.parseInt(tempList.pop());
						int ls = Integer.parseInt(tempList.pop());
						int result = ls * rs;
						tempList.push("" + result);
					} else if (tempC == '-' || tempC == '–' || tempC == '–') {
						int rs = Integer.parseInt(tempList.pop());
						int ls = Integer.parseInt(tempList.pop());
						int result = ls - rs;
						tempList.push("" + result);
					} else if (tempC == '/') {
						int rs = Integer.parseInt(tempList.pop());
						int ls = Integer.parseInt(tempList.pop());
						int result = ls / rs;
						tempList.push("" + result);
					} else if (tempC == '+') {
						int rs = Integer.parseInt(tempList.pop());
						int ls = Integer.parseInt(tempList.pop());
						int result = ls + rs;
						tempList.push("" + result);
					}
				} else {
					return "Error";
				}
			}
			// Return the last element on the Stack.
		}
		return tempList.pop();
	}
}