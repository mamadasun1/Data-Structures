/***********************************************************************
 * Name: Michael Amadasun
 * Date: 1/20/14
 * CSC 3410
 * Assignment 1
 ***********************************************************************/
/***********************************************************************
 * This class is the main class and it asks the user how many tickets
 * they want to purchase. It cannot be more than 5 tickets or less 
 * than 1. If it is greater than 5 or less than 1 the program returns
 * an error message and starts over. Depending on how many tickets user 
 * purchases the program will return a ticket number with 6 numbers. 
 * The first group of five numbers are sorted in ascending order and the 
 * second group contains one number which is the powerball. The first 
 * group only returns a number from 1-56 and the second group returns a 
 * number from 1-46.
 * Once the program returns the ticket numbers it asks the user if it 
 * wants to repeat. if "yes" then the program will repeat. If "no" 
 * the program shall terminate. 
 * ***********************************************************************/

import java.util.*;

public class powerBall {
	public static void main(String args[]){
		start();
	}
	
	public static void start(){
		//creating necessary variables
		int ticketNum;
		String confirm;
		Scanner input = new Scanner(System.in);
		Scanner con = new Scanner(System.in);
		
		//Asks user to enter how many tickets h/she wants to purchase
		System.out.println("How many tickets do you want to purchase? ");
		ticketNum = input.nextInt();
		
		//Checks to make sure they only buy less than 5 tickets
		if(ticketNum < 1 || ticketNum > 5){
			System.out.println("Sorry you can not purchase more than 5 tickets");
			start();
		}else{
			//runs the lottery for the ticket amount purchased
			for(int i = 0; i < ticketNum; i++) 
				LottoNumbers();
		}
		
		//asks if they would like to buy more tickets
		System.out.println("Would you like to buy more tickets?");
		confirm = con.nextLine();
		switch (confirm){
		case "yes":
			start();
			break;
		case "no":
			System.exit(0);
			break;
		default:
			System.out.println("please enter \"yes\" or \"no\"");
		}
	}
	
	
	public static void LottoNumbers(){
		int usrTicket1[] = new int[5];	//creates an array for the first group
		int usrTicket2[] = new int[1];	//creates an array for the second group
		
		//using a for loop to fill both groups with random numbers
			for(int i = 0; i < usrTicket1.length; i++){
				usrTicket1[i] = (int) (Math.random() * 57) + 1;
			}
			for(int i = 0; i < usrTicket2.length; i++){
				usrTicket2[i] = (int) (Math.random() * 47) + 1;
			}
		
		//sorts the first group so that it can be printed out in ascending order
		Arrays.sort(usrTicket1);
		
		//using a for loop with an if statement to check for duplicates in the array
		for(int i = 1; i < usrTicket1.length; i++){
			if(usrTicket1[i] == usrTicket1[i - 1]) {
		        System.out.println("There was a duplicate number " +
		        		"in your ticket, use this one instead!");
				LottoNumbers();
		    }	
		}
		
		//prints out the lottery numbers
		for(int n : usrTicket1)
			System.out.print(n + " ");
		
		int powB = usrTicket2[0];
		System.out.print(powB);
		System.out.println("\n");
		
	}
}
