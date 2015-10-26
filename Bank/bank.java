import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

/***********************************************************************
 * Name: Michael Amadasun
 * Date: 4/12/14
 * CSC 3410
 * Assignment 6
 ***********************************************************************/

/***********************************************************************
 * This bank class, initiates a Vector of Tellers and a Queue of Customers.
 * Each Customer is first initially assigned to 5 tellers before randomly 
 * having customers assigned to them over the course of 120 seconds or two
 * minutes. It then prints out the total amount of customers that visited the
 * bank, the amount of customers each teller helped, the time a teller was
 * occupied and the total amount of customers that did not see a teller.
 * once it has run. It asks the user if they want to run the program again.
 * ***********************************************************************/

public class bank {
	public static void main(String args[]) {
		//Initializing the necessary variables and objects.
		int customers = 0, customerPerTeller = 0, unseenCustomer = 0;
		final int numberofTellers = 5, seconds = 120;
		double totalWait = 0;
		Vector<Teller> teller = new Vector<Teller>(numberofTellers);
		Queue<Customer> line = new LinkedList<Customer>();

		Scanner s = new Scanner(System.in);
		
		//We use a boolean to ask the user if they want to continue 
		//running the program through a while loop.
		boolean run = true;
		while (run) {
			//Will cycle through the number of tellers and
			//initially assign a customer to each one.
			//Before the two minutes of random assignment.
			for (int j = 0; j < numberofTellers; j++) {
				Customer first = new Customer(0);
				Teller start = new Teller();
				start.addCustomer(first);
				teller.add(start);
			}//end for
			
			//This for loop simulates 120 seconds and each iteration
			//assigns a user randomly to a teller.
			for (int time = 0; time < seconds; time++) {
				Customer c = new Customer(time);
				int temp = (int) (Math.random() * 9) + 1;
				if (temp <= 9) {
					line.add(c);
				} else {
					unseenCustomer++;
				}//end if else. If a customer is not added we increment 
				//unseen cusotmer.
				
				//This for loop assigns a customer to a Teller, and
				//increments the customers seen after the customer 
				//is done.
				for (int i = 0; i < numberofTellers; i++) {
					if (teller.elementAt(i).isFree() && !line.isEmpty()) {
						Customer frontCustomer = c;
						line.add(frontCustomer);
						customers++;
						totalWait += (time - frontCustomer.arrival());
						teller.elementAt(i).addCustomer(frontCustomer);
						customerPerTeller++;
						line.poll();
					}//end if
				}//end for
			}//end for
			
			//Adds the customers seen before the simulation
			customers += 5;
			customerPerTeller+=5;
			customerPerTeller /= 5;
			unseenCustomer = (customers - customerPerTeller*5);
			
			//Prints to the console.
			System.out
					.println("The total amount of customers that visited the bank: "
							+ customers);
			System.out.println("Total amount of customers each teller helped: "
					+ customerPerTeller);
			System.out.println("Total amount of time a teller was occupied: "
					+ totalWait);
			System.out
					.println("Total amount of customers that did not see a teller "
							+ unseenCustomer);
			
			//Determine if the program runs again.
			System.out.println("\nDo you want to run the program again?");
			String answer = s.next();
			if (answer.equalsIgnoreCase("yes")) {
				run = true;
			} else if (answer.equalsIgnoreCase("no")) {
				run = false;
			} else {
				System.out.println("Please answer with \"yes\" or \"no\"");
				run = false;
			}//end if block
		}//end While
	}// end main

}//end class
