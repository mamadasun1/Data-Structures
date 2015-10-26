import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * Queueing simulation for a bank: one queue, multiple servers (tellers).
 * <br>
 * Sample program for CISC 124, fall 2001.
 * Queens University, Kingston, Ontario.
 *
 * @author Margaret Lamb
 */
public class BankSim {

  /*-------------------------------------------------------------------------**/
  /* GENERAL VARIABLES                                                        */
  /*-------------------------------------------------------------------------**/

  /**
   * A queue representing all the customers waiting to be served in the bank.
   * Each queue element contains an integer, which is the time at which they
   * entered the queue.  This is used to figure out how long they spend in the
   * queue.  Either queue representation may be used in this program, just by
   * changing the following line.  I have chosen LinkedQueue because there isn't
   * a definite upper limit on how lon the queue can get.
   */
  static Queue<Integer> bankQueue = new LinkedList<Integer>();

  /** the number of tellers */
  static int numTellers;

  /**
   * For each teller, we store an integer.  If this integer is zero,
   * the teller is free.  If it is positive, the teller is waiting on a customer
   * and will be busy for this number of minutes.
   */
  static int tellerBusyTime[];

  /**
   * The average number of customers entering the bank each minute.  
   */
  static double avgCustPerMinute;

  /**
   * The limit for how long a teller takes to serve a customer.  The actual time
   * a teller takes to serve an individual customer will be between 1 and this
   * limit.
   */
  static int maxServiceTime;

  /**
   * The number of "minutes" the simulation will run
   */
  static int maxMinutes;

  /**
   * True if the user wants debug output with details of the simulation
   */
  static boolean debug;

  /*-------------------------------------------------------------------------**/
  /* STATISTICS                                                                           */
  /*-------------------------------------------------------------------------**/

  /** The total number of customers who have finished waiting in a queue */
  static int totalFinished = 0;
  /** The total number of minutes all the above customers have waited in the queue */
  static int totalTimeInQueue = 0;
  /** The size of the queue, totalled every minute */
  static int totalQueueSize = 0;

  /*-------------------------------------------------------------------------**/
  /* MINUTE COUNTER                                                           */
  /*-------------------------------------------------------------------------**/

  /**
   * For every "minute" of the simulation, this counter is incremented.  Note
   * that this has absolutely nothing to do with the actual clock time when we
   * run this program.
   */
  static int currentTime;


  /*-------------------------------------------------------------------------**/
  /* RANDOM NUMBER METHODS                                                    */
  /*-------------------------------------------------------------------------**/

  /**
   * Returns the number of customers who enter the bank during one minute.  This
   * number is determined by a Poisson distribution.  Ignore the math details --
   * just accept that this method will produce a number equal to or greater than
   * zero, with the average value near avgCustPerMinute
   *
   * @return the number of customers who enter the bank this minute
   */
  static int randNumCustomers() {
    double em = Math.exp(-avgCustPerMinute);
    double x = Math.random();
    int n = 0;
    while (x > em) {
      n++;
      x = x * Math.random();
    } // end while
    return n;
  } // end randNumCustomers

  /**
   * Returns a random service time for a customer (time spent with the teller).
   * This number will be a random number between 1 and the limit specified by
   * the user.
   *
   * @return a random service time for a customer
   */
  static int randServiceTime() {
    // Math.random() returns a double between 0 and 1 (but less than 1)
    return (int) (Math.random() * maxServiceTime) + 1;
  } // end randServiceTime

  /****************************************************************************************/
  /* SIMULATION METHOD                                                                    */
  /****************************************************************************************/

  /**
   * Simulates one minute of queueing activity in the bank.
   *
   * @param minute the number of the minute being simulated (counting from 1)
   */
  static void simOneMinute(int minute) {
    // the number of customers who enter the bank during this minute
    int numCustomers = randNumCustomers();
    if (debug) System.out.println("minute " + minute + "\n    " + numCustomers +
                                  " customers entering the bank");

    // add the customers to the queue, marked with their arrival time
    for (int i = 0; i < numCustomers; i++) {
      bankQueue.add(minute);
    } // end for

    // Assign each free teller a new customer and decrement the busy times of
    // the busy tellers.
    for (int teller = 0; teller < numTellers; teller++) {
      if (debug) System.out.println("    teller " + teller);
      tellerBusyTime[teller]--;
      if (debug) System.out.println("        busy time " +
        tellerBusyTime[teller]);
      if (tellerBusyTime[teller] <= 0) { // the teller is free
        // if any customers are waiting, assign the first to this teller
        if (!bankQueue.isEmpty()) {
          // the time this customer entered the queue
          int queueTime = bankQueue.remove();
          // Update statistics for this customer.
          // totalTimeInQueue is the number of minutes the customer spent in
          // the queue.
          totalTimeInQueue += (minute - queueTime);
          totalFinished++;
          // get a random number of minutes that this customer will keep the
          // teller busy
          tellerBusyTime[teller] = randServiceTime();
          if (debug) System.out.println(
                     "        assigning customer to teller for time " +
                     tellerBusyTime[teller]);
        } // end if
      } // end if
    } // end for

    // add the length of the queue to the accumulated total
    totalQueueSize += bankQueue.size();

    if (debug) System.out.println("    number of customers in queue: " +
               bankQueue.size());

  } // end simOneMinute


  /****************************************************************************************/
  /* MAIN METHOD                                                                          */
  /****************************************************************************************/

  /**
   * Main method: queries the user for values, runs the simulation, and reports the results.
   */
  public static void main(String args[]) {
    // get limits from the user
	Scanner s = new Scanner(System.in);
    System.out.print("number of tellers: ");
    numTellers = s.nextInt();
    tellerBusyTime = new int[numTellers]; // all initially zero
    System.out.print("number of minutes for simulation: ");
    maxMinutes = s.nextInt();
    System.out.print("average number of customers arriving per minute: ");
    avgCustPerMinute = s.nextDouble();
    System.out.print("maximum service time in minutes: ");
    maxServiceTime = s.nextInt();
    System.out.print("debug output (y/n)? ");
    String answer = s.next();
    debug = (answer == "y" || answer == "Y");

    // run the simulation
    for (currentTime = 1; currentTime <= maxMinutes; currentTime++) {
      simOneMinute(currentTime);
    } // end for

    // print report
    System.out.println();
    System.out.println("number of customers left in the queue: " +
                       bankQueue.size());
    System.out.println("average queue length: " +
                       (double)totalQueueSize / maxMinutes);
    System.out.println("average wait in queue: " +
                       (double)totalTimeInQueue / totalFinished);

  } // end main

} // end class BankSim