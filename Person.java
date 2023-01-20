/**
 * @author Shanaya Bharucha
 * 3:30; January 20th
 * Program Name: Person.java
 * This class defines the class person,  which is used in the PartyMethods and PartyTester programs to store multiple bits of information  about the party guests. 
 * */

/*Person class stores a first name, a last name, and the name of the company they work for*/
public class Person {
	private String lastName = "";
	private String firstName = "";
	private int companyNum = 0;
	
	/*Person constructor to create a new instance of the Person class*/
	public Person(String lastN, String firstN, int CompN) {
		lastName = lastN;
		firstName = firstN;
		companyNum = CompN;
	}	//close Person constructor
	
	/*Returns the last name of a person instance*/
	public String getLastName() {
		return lastName;
	}//close getLastName
	
	/*Returns the first name of a person instance*/
	public String getFirstName() {
		return firstName;
	} //close getFirstName
	
	/*Returns the full name of a person instance*/
	public String getFullName() {
		return (firstName + " " + lastName);
	}//close getFullName
	
	/*Returns the company number of a person instance*/
	public int getCompanyNum() {
		return companyNum;
	}//close getCompanyNum	
	
	/*Returns the full name and company number of a person instance*/	
	public String toString() {
		return ("Name: " + firstName + " " + lastName + ": " + "Company Number: " + companyNum);
	}//close toString	
} //close public class Person
