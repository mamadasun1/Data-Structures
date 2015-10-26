/***********************************************************************
 * This customer class contains its constructors and two methods. A boolean
 * method to determine if the customer is done. And an arrival method to 
 * determine when the customer arrived.
 * ***********************************************************************/

public class Customer {
	//declares the variables
	int arrival;
	int processTime;
	
	public Customer() {
		//arrival and processtime is initiated to 0.
		arrival = 0;
		processTime = 0;
	}
	
	//Declares a random time for the processtime and passes through an
	//arrival time that becomes the new arrival.
	public Customer(int arrival) {
		this.arrival = arrival;
		this.processTime = (int) (Math.random() * 4) + 1;
	}
	
	//If their processTime is greater than 0 they must be done.
	//So it returns true. If not they will not be done.
	public boolean done(){
		return --this.processTime < 0;
	}
	
	//returns arrival time.
	public int arrival(){
		return arrival;
	}
}
