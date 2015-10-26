package phonedir;

import java.util.Scanner;

/***********************************************************************
 * Name: Michael Amadasun
 * Date: 3/10/14
 * CSC 3410
 * Assignment 3
 ***********************************************************************/

/***********************************************************************
 * This class is the main class and it contains 2 methods. The main method
 * takes the character returned from the menu method and passes it through
 * a switch statement. In the switch statement, according to the letter
 * chosen from the user a different case will execute.
 * ***********************************************************************/
public class phonedir {
	// create constant character variables to be used for the menu
	public static final char MENU_INVALID = 'x';
	public static final char MENU_SHOW_ALL_RECORDS = 'a';
	public static final char MENU_DELETE_CURRENT_RECORD = 'd';
	public static final char MENU_CHANGE_FIRST_NAME = 'f';
	public static final char MENU_CHANGE_LAST_NAME = 'l';
	public static final char MENU_ADD_NEW_RECORD = 'n';
	public static final char MENU_CHANGE_PHONE_NUMBER = 'p';
	public static final char MENU_QUIT = 'q';
	public static final char MENU_SELECT_RECORD = 's';

	//main method that analyzes the input of the menu characters. 
	public static void main(String[] args) {
		//creating variables
		Scanner scanner = new Scanner(System.in);
		Editcontacts directory = new Editcontacts();
		Contact currentContact = null;
		
		System.out.println("A Program to keep a Phone Directory:\n ");
		
		//will continue to run until quit is true
		boolean exit = false;
		while (!exit) {
			//selection is assigned to the character returned from
			//the menu method.
			char selection = menu(scanner);
			//using a switch statement to analyze the results of the menu.
			switch (selection) {
			case MENU_SHOW_ALL_RECORDS:
				directory.displayAll();
				break;

			case MENU_ADD_NEW_RECORD:
				Contact newContact = buildRecordFromUserInput(scanner);
				directory.addNewContact(newContact);
				currentContact = newContact;
				System.out.println("\nCurrent record is: " 
				+ newContact.getName() + " " + newContact.getPhone());
				break;

			case MENU_SELECT_RECORD:
				String lastName = getStringFromUser(scanner,
						"Enter last name: ");
				Contact foundContact = directory
						.findContactByLastName(lastName);
				if (foundContact != null) {
					currentContact = foundContact;
					System.out.println("Current Record is: " + 
					currentContact.getName() + " " + currentContact.getPhone());
				} else {
					System.out.println("No record found matching " + lastName);
				}
				break;

			case MENU_DELETE_CURRENT_RECORD:
				if (currentContact != null) {
					System.out.println("\nDeleted: " 
				+ currentContact.getName() + " " + currentContact.getPhone());
					directory.deleteContact(currentContact);
					currentContact = null;
				} else {
					System.out.println("No Current Record");
				}
				break;

			case MENU_CHANGE_FIRST_NAME:
				if (currentContact != null) {
					String newFirstName = getStringFromUser(scanner,
							"Enter new first name: ");
					currentContact.setFirstName(newFirstName);
					System.out.println("\nCurrent record is: "
					+ currentContact.getName() + " " + currentContact.getPhone());
				} else {
					System.out.println("No Current Record");
				}
				
				break;

			case MENU_CHANGE_LAST_NAME:
				if (currentContact != null) {
					String newLastName = getStringFromUser(scanner,
							"Enter new last name: ");
					currentContact.setLastName(newLastName);
				} else {
					System.out.println("No Current Record");
				}
				break;

			case MENU_CHANGE_PHONE_NUMBER:
				if (currentContact != null) {
					String newPhoneNumber = getStringFromUser(scanner,
							"Enter new phone number: ");
					currentContact.setPhone(newPhoneNumber);
				} else {
					System.out.println("No Current Record");
				}
				break;

			case MENU_QUIT:
				exit = true;
				break;

			default:
				System.out.print("Illegal command");
				break;
			}
		}
	}

	//Method used to print a message and get the user input for use in main method
	private static String getStringFromUser(Scanner scanner, String message) {
		System.out.print(message);
		return scanner.nextLine();
	}

	
	//Gets record information from the user
	private static Contact buildRecordFromUserInput(Scanner scanner) {
		Contact contact = null;

		System.out.print("Enter first name: ");
		String firstName = scanner.nextLine();
		System.out.print("\nEnter last name: ");
		String lastName = scanner.nextLine();
		System.out.print("\nEnter phone number: ");
		String phone = scanner.nextLine();

		contact = new Contact(firstName, lastName, phone);

		return contact;
	}

	//returns a menu choice in relation to the menu characters that have 
	//been previously defined
	public static char menu(Scanner scanner) {
		StringBuffer buf = new StringBuffer("\n");
		buf.append(MENU_SHOW_ALL_RECORDS + " Show all records\n\n");
		buf.append(MENU_DELETE_CURRENT_RECORD + " Delete the current record\n\n");
		buf.append(MENU_CHANGE_FIRST_NAME
				+ " Change the first name in the current record\n\n");
		buf.append(MENU_CHANGE_LAST_NAME
				+ " Change the last name in the current record\n\n");
		buf.append(MENU_ADD_NEW_RECORD + " Add a new record\n\n");
		buf.append(MENU_CHANGE_PHONE_NUMBER
				+ " Change the phone number in the current record\n\n");
		buf.append(MENU_QUIT + " Quit\n\n");
		buf.append(MENU_SELECT_RECORD + " Select a record from the " +
				"record list to become the current record\n\n");
		buf.append("Enter a command from the list above (q to quit): ");

		char selection = MENU_INVALID;
		System.out.print(buf.toString());
		String input = scanner.nextLine();
		if (input != null && input.length() == 1) {
			selection = input.toLowerCase().charAt(0);
		}

		return selection;
	}

}
