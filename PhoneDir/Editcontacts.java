package phonedir;

import java.util.LinkedList;

/***********************************************************************
 * Name: Michael Amadasun
 * Date: 3/10/14
 * CSC 3410
 * Assignment 3
 ***********************************************************************/

/***********************************************************************
 * This class adds contacts to the directory and also deletes Contacts from
 * a directory. It displays all the items in the directory and will find a
 * contact by the last name in a directory.  
 * ***********************************************************************/
public class Editcontacts
{
	//initializes a new LinkedList that passes through a contact object
	private LinkedList<Contact> contacts;
	
	//Creates the constructor for this class and initializes contacts
	public Editcontacts()
	{
		contacts = new LinkedList<Contact>();
	}

	//This method adds a contact to the directory and sorts alphabetically by
	//the last name.
	public void addNewContact(Contact newContact)
	{
		if(newContact != null)
		{
			// If list is empty add the contact.
			if(contacts.isEmpty())
			{
				contacts.add(newContact);
			}
			else
			{
				// the contact has to be added in the right position so that the 
				// contact list is ordered by last name
				boolean added  = false;
				for(int i=0; i<contacts.size(); i++)
				{
					if(newContact.compareTo(contacts.get(i)) < 0)
					{
						contacts.add(i, newContact);
						added = true;
						break;
					}//end if
				}//end for
				if(!added)
				{
					// add at the end
					contacts.add(newContact);
				}//end if
			}//end if-else
		}
	}//end addNewContact method

	
	//Displays all the contacts
	public void displayAll()
	{
		//initializing a StringBuffer object
		StringBuffer buf = new StringBuffer();
		
		//If there is nothing in the LinkedList then add the message to the StringBuffer
		if(contacts.isEmpty())
		{
			buf.append("There are no contacts to display. Try adding new contacts");
		}
		//If there is then get the name and phone number for each person in the 
		//directory using a for loop of the linkedlist and print each on a
		//new line. 
		else
		{
			buf.append(String.format("%-15s %-15s %-15s\n", "First Name", "Last Name", "Phone Number"));
			for(int i=0; i<contacts.size(); i++)
			{
				Contact contact = contacts.get(i);
				String firstName = contact.getFirstName();
				String lastName = contact.getLastName();
				String phone = contact.getPhone();
				buf.append(String.format("%-15s %-15s %-15s\n", firstName, lastName, phone));
			}//end for
	
		}//end if-else
		//print out the String		
		System.out.println(buf.toString());
	}//end displayAll method
	
	//Method to find a contact by the last name.
	public Contact findContactByLastName(String lastName) 
	{
		Contact foundContact = null;
		//Cycle through the contacts list and set it to the new Contacts object.
		//The new object compares the last name to the last name passed through
		//the method.
		for(int i=0; i<contacts.size(); i++)
		{
			Contact current = contacts.get(i);
			if(current.getLastName().compareToIgnoreCase(lastName) == 0)
			{
				foundContact = current;
				break;
			}//end if
		}//end for
		//return the name matching the contact
		return foundContact;
	}//end findContactByLastName method

	//Deletes a contact from the directory.
	public void deleteContact(Contact contactToRemove) 
	{
		contacts.remove(contactToRemove);
	}//end deleteContact method

}
