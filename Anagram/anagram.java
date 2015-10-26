package anagram;

import java.io.*;
import java.util.*;

/***********************************************************************
 * Name: Michael Amadasun
 * Date: 2/10/14
 * CSC 3410
 * Assignment 2
 ***********************************************************************/

/***********************************************************************
 * This class is the main class and it contains 3 methods. The punctuation
 * method takes in a String Vector and it removes all punctuation in the vector
 * and changes it to lowercase. The findSig class takes in a String Vector and
 * it finds the signature and prints out both the original vector and the
 * signature vector. Finally the main class determines if the File that is
 * scanned in has 50 or more legitimate words. It also determines any empty
 * files and initializes the vector vect.
 * ***********************************************************************/
public class anagram {
	public static void main(String args[]) {
		// Initialize variables
		final int letters = 12;
		final int words = 50;
		int word = 0;

		Vector<String> vect = new Vector<String>(words);

		// Determines the filename from the user
		// and stores it into usrFile
		System.out.println("Please enter an input file: ");
		Scanner input = new Scanner(System.in);
		String filename = input.nextLine();
		Scanner usrInput = null;
		File usrFile = new File(filename);

		try {

			usrInput = new Scanner(new FileInputStream(usrFile));
			// the do while loop examines the contents of the file
			do {
				// if the word is legitimate (12 characters or less)
				// go to else block. If not it skips the word. Word
				// will not increment
				if (usrInput.next().length() > letters) {
					continue;
				} else {
					word++;
					// if there is more than 50 words the program will
					// give the error message and exit.
					if (word > words) {
						// error message
						System.out
								.println("More than 50 words in the document");
						System.exit(1);
					} // end if
				} // end else

			} while (usrInput.hasNext());// end do-while

			// catches determine if the File exists or if the File is empty
			// finally resets the file passed into it for next try block
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("File is empty!");
			System.exit(1);
		} finally {
			usrInput.reset();
		}

		// New try block to fill the vector and get the signature
		try {
			usrInput = new Scanner(usrFile);

			while (usrInput.hasNext()) {
				// Converts the words in the file to a String to
				// put into the String Vector.
				String inpt = usrInput.nextLine();
				String temp = new String(inpt);
				String enter[] = temp.split("\\s");
				for (int i = 0; i < enter.length; i++) {
					vect.add(enter[i]);
				} // end for
			} // end while

			// start of findSig method
			findSig(vect);

			// Catches determine any errors in the program and if the file
			// can not be located.
			// Finally closes the opened file for reading.
		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.exit(1);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		} finally {
			usrInput.close();
		}
	}

	public static void punctuation(Vector<String> v) {
		// Removes punctuation and makes letters lowercase.
		for (int i = 0; i < v.size(); i++) {
			v.add(v.remove(0).toLowerCase().replaceAll("[^a-zA-Z ]", ""));
		}// end for
	}

	public static void findSig(Vector<String> v) {
		// Create a new vector to store the signatures.
		Vector<String> signature = new Vector<String>(50);

		// Passes the vector into punctuation.
		punctuation(v);

		// Puts vector items into a string array to be modified.
		String origin[] = new String[v.size()];
		v.toArray(origin);

		// PrintWriter so that we can write results to an output file
		PrintWriter pw = null;
		try {
			// PLEASE CHANGE PATH NAME AS NECESSARY
			pw = new PrintWriter(new FileWriter(
					"C:\\Users\\Michael\\Documents\\output.txt"));
			
			// Takes each string in the origin array and converts it
			// to Characters. Sorts the characters and places it back as
			// a string into the String array.
			for (int i = 0; i < origin.length; i++) {
				char[] chars = origin[i].toCharArray();
				Arrays.sort(chars);
				origin[i] = new String(chars);
			}// end for

			// Writing to the console and the output file
			System.out.println("Original\tSignature");
			System.out.println("--------\t-----------");
			pw.println("Original\tSignature");
			pw.println("--------\t-----------");

			// Adding the Strings to the signature array and printing both
			// the original vector and the Signatures.
			// I could not figure out how to convert it back to the original
			// word.
			for (int i = 0; i < origin.length; i++) {
				signature.add(origin[i]);
				System.out.println(v.get(i).toString() + "\t\t"
						+ signature.get(i).toString());
				pw.println(v.get(i).toString() + "\t\t"
						+ signature.get(i).toString());
			}// end for

			// Sorting the signatures and printing them out.
			Collections.sort(signature);
			System.out
					.println("__________________________________\n\nSignatures sorted:\n");
			pw.println("__________________________________\n\n Signatures sorted:\n");

			for (int i = 0; i < signature.size(); i++) {
				System.out.println(signature.get(i).toString());
				pw.println(signature.get(i).toString());
			}// end for

			// Catches locate any errors.
			// Finally closes the file that is written to.
		} catch (FileNotFoundException e) {
			System.out.println("Can not locate file to print to!");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Can not print to file!");
			System.exit(1);
		} finally {
			pw.close();
		}
	}// End findSig method
}
