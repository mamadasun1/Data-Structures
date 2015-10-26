package compare;

import java.util.LinkedList;
import java.util.Scanner;

/***********************************************************************
 * Name: Michael Amadasun
 * Date: 3/25/14
 * CSC 3410
 * Assignment 4
 ***********************************************************************/

/***********************************************************************
 * This class puts random numbers from 1 - 1000000 into a LinkedList. The
 * amount of numbers that is put into the LinkedList is dependent on the 
 * user. Once the numbers are put into the LinkedList two methods are called
 * scanning the list first iteratively and then recursively. Both methods are
 * timed and the time it took to complete both is output to the screen. The
 * reason why one took longer than the other is then analyzed.  
 * ***********************************************************************/

public class compare {
	
	//main class
	public static void main(String[] args) {
		// creates a new Integer LinkedList called randomInts
		LinkedList<Integer> randomInts = new LinkedList<Integer>();
		// initializes two integer variables and a Scanner object
		int numbers, random;
		Scanner n = new Scanner(System.in);
		
		// Prompts user for the amount of numbers to generate
		System.out.println("Enter the number of random numbers you want to generate: ");
		numbers = n.nextInt();

		// Places random numbers into the LinkedList 
		for (int i = 0; i < numbers; i++) {
			random = (int) (Math.random() * 1000000) + 1;
			randomInts.add(random);
		}
		
		//starts the timer for the time it takes to complete
		//iteration. Then after the method is finished catches
		//the time. Endtime is subtracted from the starttime 
		//to find the duration.
		long starttimeI = System.nanoTime();
		iterative(randomInts);
		long endtimeI = System.nanoTime();

		
		long durationI = endtimeI - starttimeI;
		double secondsI = (double)durationI / 1000000000.0;

		//starts the timer for the time it takes to complete
		//recursion. Then after the method is finished catches
		//the time. Endtime is subtracted from the starttime 
		//to find the duration.
		long starttimeR = System.nanoTime();
		recursive(randomInts);
		long endtimeR = System.nanoTime();

		long durationR = endtimeR - starttimeR;
		double secondsR = (double)durationR / 1000000000.0;

		//Prints the time to the screen of both methods
		System.out.println("Iteratively, it took " + secondsI
				+ " seconds to run");
		System.out.println("Recursively, it took " + secondsR
				+ " seconds to run");
		System.out.println("\nIteration takes the shorter amount of time" +
				" because it does not have to execute a method call which slows" +
				" down the recursive method.");
		
	}
		
	//recursive function
	private static void recursive(LinkedList<Integer> l) {
		if (l.getLast() != null) {
			l.removeLast();
		} else {
			return;
		}

	}

	//iterative function
	private static void iterative(LinkedList<Integer> l) {
		for (int i : l) {
			return;
		}
	}
}
