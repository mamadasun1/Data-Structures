/***********************************************************************
 * This teller class contains its constructor and two methods. A boolean
 * method to determine if the teller is done. And an addCustomer method
 * that will place a customer to the teller and change it's availability 
 * to false.
 * ***********************************************************************/


public class Teller {
	//declares the variables
	boolean free;
	Customer customer = new Customer();

	//Teller is automatically set to free in the constructor
	public Teller() {
		free = true;
	}

	//Determines if the Teller is free or busy.
	public boolean isFree() {
		if (free)
			return true;
		if (customer.done()) {
			free = true;
		}
		return free;
	}

	//Adds a customer to the teller and the tellers availability 
	//changes to false.
	public void addCustomer(Customer c) {
		customer = c;
		free = false;
	}
}
