package phonedir;

/***********************************************************************
 * Name: Michael Amadasun
 * Date: 3/10/14
 * CSC 3410
 * Assignment 3
 ***********************************************************************/

/***********************************************************************
 * This class is a helper class to the main class, phonedir.java. This
 * class is simply a compilation of getters and setters that focus on the
 * first name, last name, and a phone number. These methods will be used to
 * add names into a directory and to find names within that directory.  
 * ***********************************************************************/
public class Contact implements Comparable<Contact>
{
	private String firstName;
	private String lastName;
	private String phone;

	//Creates a constructor to store the first and last name, and the phone number
	public Contact(String firstName, String lastName, String phone) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}
	
	//Simple get method to get the first name
	public String getFirstName() 
	{
		return this.firstName;
	}
	
	//Simple set method to set the first name
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	//Simple get method to get the last name
	public String getLastName() 
	{
		return this.lastName;
	}
	
	//Simple set method to set the last name
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	//Gets the full name
	public String getName() 
	{
		return this.firstName + " " + this.lastName;
	}
	
	//Simple get method to get the phone number
	public String getPhone() 
	{
		return this.phone;
	}
	
	//Simple set method to set the phone number
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
	//Passes in a Contact object to compare last names
	public int compareTo(Contact other) 
	{
		if(other == null)
		{
			return 1;
		}
		
		return this.lastName.compareTo(other.getLastName());
	}
	
	
}
