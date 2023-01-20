/**
 * @author Shanaya Bharucha
 * 3:30; January 20th
 * Program Name: PartyMethods.java
 * This class contains all the methods called in PartyTester to organize a list of party guests into tables depending on their company. 
 * Its methods include uploading and inputting the party guests, adding more guests, checking that there aren't too many guests, placing guests at tables, 
 * printing rosters by table & company, and giving the table and chair of any given person in the guest list. 
 * */
import java.io.File;  // Import the File class -> From Data Student Activity
import java.io.IOException;  // Import the IOException class to handle errors -> From Data Student Activity
import java.io.FileNotFoundException;  // Import this class to handle errors-> From Data Student Activity
import java.util.*; // Import the Scanner class to read text files & import ArrayList & import Arrays.asList

/*Class PartyMethods contains all the various methods that I will be calling in the PartyTester main. 
This is a way to circumvent the non-static/static errors & keep the details of the program seperate from the big picture.
There will be individual descriptions of what each method does down below.
 */
public class PartyMethods {
	private int numTables = 10;
	private int peoplePerTable = 10;
	private int numCompanies = 16;
		//These private integers stop the program from having magic numbers. It is scaleable just by changing these three numbers
	ArrayList<Person> guestList = new ArrayList <Person>();
	Person [][] peepsAtTable = new  Person[numTables][peoplePerTable]; 
	String[] comps = {"null spot","Wal-Mart", "Kroger", "Amazon", "Lowes", "Best Western", "KMart", "Fusian", "Heinz", "Gucci", "Prada", "Nike", "Dodge", "Maserati", "Razor", "AMD", "Razer" };
		//The null spot at the beginning is because the data refers to Wal-Mart's number as 1, but the first object in an array is at index 0. This shifts the index which makes the code a lot easier to understand
	ArrayList<String>companyList = new ArrayList<String>(Arrays.asList(comps));
	
	/* makeGuestList method imports all of the information in the PartyList.csv file and puts it into an arrayList of people.*/ 
	public void makeGuestList() {
		try {
			File myObj = new File("PartyList.csv"); //get a new file
			Scanner myReader = new Scanner(myObj);//scan in the new file
			while (myReader.hasNextLine()) {//if there is still information in the file
				String data = myReader.nextLine();//data = the next set of information
				//for each of these sections, I am finding where the comma is, setting the necessary variable equal to the data value, and then redeclaring data as the rest of the line
				int comma = data.indexOf(",");//get rid of the numbering system
				data = data.substring(comma+1);
					
				comma = data.indexOf(",");
				String ln = data.substring(0, comma); //get the last name
				data = data.substring(comma+1);
						
				comma = data.indexOf(",");
				String fn = data.substring(0, comma); //get the first name
				data = data.substring(comma+1);
						
				int cn = Integer.parseInt(data);//get the number of the company
					
				guestList.add(new Person(ln, fn, cn));//add a new instance of the Person class to guestList
			}//close while
			myReader.close();
		}//close try 
		catch (FileNotFoundException e) {//just in case the file isn't found
			System.out.println("An error occurred.");
			e.printStackTrace();
		}//close catch
	}//close makeGuestList
	
	/*sort method sorts the inputted arraylist with all the people in it by their company number, starting with 1 and ending with 16*/
	public void sort(ArrayList<Person> list) {
		list.sort((o1, o2) //I learned about sorting lists from Mr. Twyford and W3Schools: https://www.w3schools.com/js/js_array_sort.asp
                  -> o1.getCompanyNum()-o2.getCompanyNum()); //the lambda  "->" seperates the parameters from the actual method. This method sorts by finding the smaller of the two company numbers and moving the smaller one to the left until it no longer smaller
	}//close sort	
	
	/*addGuest method allows the user to add more people into the ArrayList and the main roster, and then uses the sort method to sort the entire array*/
	public void addGuests() {
		boolean addP = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("\nWould you like to add a guest? Input 'true' or 'false': ");
		Boolean check = scan.nextBoolean();//possible problem,: if the user inputs something other than true or false, the program will break
		addP = check;
		while (addP == true) { //keeps running while more guests need to be added, ends when no more need to be added
			System.out.println("\nWhat's the guest's last name?");
			String x = scan.nextLine();
			String lastname = scan.nextLine();
			System.out.println("\nWhat's the guest's first name?");
			String firstname = scan.nextLine();
			System.out.println("\nWhat's the company's number? Refer to this list to know the company's ID (Wal-Mart is 1, Kroger is 2, etc. )");
			System.out.println(companyList);
			int companynumber = scan.nextInt();
			guestList.add(new Person(lastname, firstname, companynumber));//add a new instance of the Person class to guestList	
			System.out.println("\nWould you like to add another guest? Input 'true' or 'false': ");
			Boolean checker = scan.nextBoolean();
			addP = checker;
		}//close while (addP == true)
		sort(guestList); //calling the sort method only after all the guests have been added
	}//close addGuests
	
	/*checkPPerCompany method goes through each company to check if that there aren't more people per company than there are tables available.
	 If there are too many people in one company, the last people from that company will be removed until they meet the limit*/
	public void checkPPerCompany() {
		System.out.println("Now that the roster is compelete, I will check that you don't have too many people from the same company. If you do, I will be removing the last ones");
		SleepMs(500);
		for (int i = 1; i<=numCompanies; i++) {
			int ppc = 0;
			for (int j = 0; j<guestList.size(); j++) {
				if (i == guestList.get(j).getCompanyNum()) {
					ppc++; //a counter of all the employees from one company
				}//close if loop
				if (ppc>numTables) {
					ppc--; //cbecause one person is being removed
					guestList.remove(j);//remove the person at the index
					j--;//make sure the movement of the guestList is taken into account
				}	//close if loop
			}//close for loop
		}//close for loop
		System.out.println("Now you have the right number of people per company\n");
		SleepMs(500);
	}//close check PPerCompany
	
	/*checkTotalPeeps method makes sure that there aren't more people than the total number of seats (number of tables * number of seats per table).
	 If there are, people will be deleted from the end of the roster until there is room for everyone remaining.*/
	public void checkTotalPeeps() {
		System.out.println("Now I'm checking that you don't have too many people. If you do, I will keep removing from the end until you don't");
		SleepMs(500);
		int totalP = numTables*peoplePerTable; //this gives the total number of people that could attend the party given the constraints of chairs and tables
		int p = guestList.size()-1;
		while (guestList.size()>totalP) {
			guestList.remove(p);
			p--; //make sure the movement of the guestList is taken into account
		}//close while loop	
		System.out.println("Now you have the right number of total people");
		SleepMs(500);
		//System.out.println(guestList);
	}//close checkTotalPeeps	
	
	/*addPtoTables method goes through the ArrayList of all people and assigns each person into a 2D array. 
	 The 2D array's rows represents the tables of the party, while the columns contain each seat from said table. */
	public void addPtoTables() {
		boolean placed = false;
		int temp = 0;
		int numOfSameC = 0;
		for (Person p : guestList) {
			placed = false; //the placed being false means that once a person has been placed, it breaks all the inner loops and moves them onto the next person
				int comnum = p.getCompanyNum();
				numOfSameC = 0;
				for (int t = 0; t<numTables && placed == false; t++) {
					numOfSameC = 0;
					t = (t+temp)%10; 
						//This is necessary to make sure that tables stay as free as possible, because some companies have 10 employees, which means they need a chair free on every table.
						//This code accomplishes this by checking where the last table at someone was added to was, and incrementing from there. This was pretty hard to work out!
					for (int s = 0; s<peoplePerTable && placed == false; s++) {
						if (peepsAtTable[t][s] !=null) { //if someone is already sitting in that seat
							if (peepsAtTable[t][s].getCompanyNum() == comnum) {
								numOfSameC++;//there is already someone from the same company as the selected guest, so they cannot go here
							}//close if	
						}//close if 	
						else {//if the seat is empty/null
							if (numOfSameC == 0)  {//no one else from the company
								temp = t+1;
								peepsAtTable[t][s] = p;
								placed = true;//break out of all the inner loo[s
							}//close if 
						}//close else	
					}//close for loop going thru each seat	
				}//close for loop going thru each table	
		}//close enhanced for loop	
	}//close addPtoTables	
	
	/*tableRosters method print out the rosters of who will be sitting where divided by which table they are sitting at*/
	public void tableRosters() {
		System.out.print("\n");	
		System.out.print("\n");	
		System.out.println("Roster of Guests by Tables");
		SleepMs(1000);
		for (int t = 0; t<numTables; t++) {
			System.out.println("Table " + (t+1));
			for (int s = 0; s<peoplePerTable; s++) {//just printing out each row of the table, as each row holds the information of one table
				if (peepsAtTable[t][s] != null) {
					System.out.println(peepsAtTable[t][s].getFullName() + ": " + companyList.get(peepsAtTable[t][s].getCompanyNum()));
				}//close if
			}//close for seat loop
			System.out.print("\n");	
			System.out.print("\n");	
		}//close for table loop	
	}//close tableRosters
	
	/*companyRosters method prints out a roster of the party that is divided by what company the guests go to*/
	public void companyRosters() {
		System.out.println("Roster of Guests by Company: ");
		SleepMs(1000);
		for (int i = 1; i<=numCompanies; i++) { //iterating by company
			System.out.println("\nRoster for " + companyList.get(i)+ ": ");
			for (int t = 0; t<numTables; t++) {
				for (int s = 0; s<peoplePerTable; s++) {
					if (peepsAtTable[t][s] != null) {
						if (peepsAtTable[t][s].getCompanyNum()== i) {//if they are from the selected company, print them out as part of the roster
							System.out.println(peepsAtTable[t][s].getFullName() + " is sitting at Table " + (t+1) + " at Seat " + (s+1));
						}//close if loop
					}//close if loop
				}//close for seat loo[	
			}//close for table loop	
			System.out.print("\n");
		}//close for loop	
	}//close companyRosters
	
	/*This method takes in the user's input of a guest and outputs the table and seat number of said guest*/
	public void individualSeating() {
		boolean ind = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("\nWould you like to find a guest's spot? Input 'true' or 'false': ");
		Boolean check = scan.nextBoolean();
		ind = check;
		while (ind == true) {
			System.out.println("\nWhat's the guest's last name?");
			String x = scan.nextLine();
			String lastname = scan.nextLine();
			System.out.println("\nWhat's the guest's first name?");
			String firstname = scan.nextLine();
			for (int t = 0; t<numTables; t++) {
				for (int s = 0; s<peoplePerTable; s++) {
					if (peepsAtTable[t][s] != null) {
						if (peepsAtTable[t][s].getLastName().equals(lastname) && peepsAtTable[t][s].getFirstName().equals(firstname)) { //scanning to find where the person is stored in the 2D array
							System.out.println("\n" + peepsAtTable[t][s].getFullName() + " is sitting at Table "+ (t+1) + " at Seat " +(s+1) );
						}//close if
					}//close if		
				}//close for seat 
			}//close for table	
			System.out.println("\nWould you like to find another guest? Input 'true' or 'false': ");
			Boolean checker = scan.nextBoolean();
			ind = checker;
		}//close while (addP == true)
	}//close individualSeating			
	
	
	/*endOfProgram method just tells the user that the program is over*/
	public void endOfProgram() {
		System.out.println("\n"+"I hope this program was useful!");
	}//close endOfProgram	
	
	/*SleepMs method will pause the program for the number of milliseconds in the arguement*/
	public void SleepMs(int t){ 
		try { //attempt to run this program
			Thread.sleep(t);
		} // close try
		catch (Exception e) { //catch the intrupted exception error
			System.out.println(e);
		}	//close catch	
	} // close SleepMs	
	
}//close PartyMethods
