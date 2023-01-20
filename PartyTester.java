/**
 * @author Shanaya Bharucha
 * 3:30; January 20th
 * Program Name: PartyTester.java
 * This class is the main program that runs all the methods listed in PartyMethod. 
 * In this code, a file of guests are uploaded into instances of the person class and put into an ArrayList. 
 * Then the program allows more guests to be added, and checks that there are less than the maximun number of guests in the ArrayList. 
 * This program then places people at tables, and allows the program user to print rosters by table or company, or find the table of a specific person 
 * */

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;//Import ArrayList

/*This is the main class that hosts the party planning program. It calls from PartyMethods, which calls from Person and Partylist*/
public class PartyTester {
	public static void main (String [] args) {
		PartyMethods p1 = new PartyMethods();
		p1.makeGuestList();
		p1.addGuests();
		p1.checkPPerCompany();
		p1.checkTotalPeeps();
		p1.addPtoTables();
		p1.tableRosters();
		p1.companyRosters();
		p1.individualSeating();
		p1.endOfProgram();
	}//close public static void main
}//close Party Tester
