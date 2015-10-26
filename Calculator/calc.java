package calc;

import java.util.Scanner;

/***********************************************************************
 * Name: Michael Amadasun
 * Date: 4/6/14
 * CSC 3410
 * Assignment 5
 ***********************************************************************/

/***********************************************************************
 * This class uses two subclasses in InfixtoPostfix.java and
 * EvaluatePostfix.java in order to convert an infix expression to a postfix
 * expression and then the postfix expression. It also prompts the user for a
 * value of x to replace the x values in the infix expression. It continues to 
 * ask for x until the value of x is not a number. It then returns the result.
 * ***********************************************************************/

public class calc {
	public static void main(String args[]) {
		//Prompts the user for the infix expression and
		//stores in a string that removes all spaces.
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Infix expression: ");
		String infix = s.nextLine();
		infix = infix.replaceAll("\\s", "").trim();
		
		//Calls the InfixtoPostfix class which passes in
		//a string which is infix and the returns a 
		//string which is the postfix.
		InfixtoPostfix convert = new InfixtoPostfix(infix);
		String postfix = convert.toPostFix();
		System.out.println("Converted Expression: " + postfix);
		
		//Initializes a boolean statement to continuously ask
		//for x until it receives anything that is not a number.
		//The while loop then terminates.
		boolean run = true;
		while(run){
		System.out.println("Enter a value for x: ");
		Scanner x = new Scanner(System.in);
		String var = x.next();
		if(!var.matches("[0-9]*")){
			System.exit(1);
			run = false;
		}
		char var1 = var.charAt(0);
		postfix = postfix.replace('x', var1);
		
		//Calls the EvaluatePostfix class and the result
		//is saved into the String answer. Then it 
		//prints out the answer.
		EvaluatePostfix result = new EvaluatePostfix();
		String answer = result.compute(postfix);
		System.out.println("The result is: " + answer);
		}
	}
}
