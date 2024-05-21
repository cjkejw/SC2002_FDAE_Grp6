package admin;

import java.util.Hashtable;
import java.util.Scanner;

import dependencyInterface.PersonDependency;
import people.Person;
import recordInterfaces.PersonInterface;

/**
 * This class is used for adding and removing staff from a branch
 * @author FDAE Team 6
 */
public class StaffAddRemoval implements PersonDependency, PersonInterface{
	
	Scanner scan = new Scanner(System.in); 
	
	/**
	 * This method adds a new staff member with user provided details
	 * @return The newly created Person object, which is the new staff member 
	 */
	Person addStaffAcc() {
		String name = null, role = null, gender = null, age = null, branch = null; 
		System.out.println("Enter the Staff Name: ");
		name = scan.next();
		scan.nextLine(); 
		
		boolean first = true; 
		while (first) {
			System.out.println("Enter the Staff Role - A, M, S");
			role = scan.next();
			scan.nextLine(); 
			if (role.equalsIgnoreCase("A") || role.equalsIgnoreCase("M") || role.equalsIgnoreCase("S")) {
				first = false; 
				role = role.toUpperCase(); 
			}
			else {
				System.out.println("Invalid Input!"); 
			}
		}
		
		boolean second = true; 
		while (second) {
			System.out.println("Enter the Staff Gender - M, F");
			gender = scan.next();
			scan.nextLine(); 
			if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) {
				gender = gender.toUpperCase(); 
				second = false; 
			}
			else {
				System.out.println("Invalid Input!"); 
			}
		}
		
		boolean third = true; 
		while (third) {
			System.out.println("Enter the Staff Age: ");
			age = scan.next();
			scan.nextLine(); 
			try {
				@SuppressWarnings("unused")
				int num = Integer.parseInt(age); 
				third = false;
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid Input!"); 
			}
		}
		
		boolean fourth = true; 
		while (fourth) {
			System.out.println("Enter the Staff's Branch - NTU, JE, JP ");
			branch = scan.next();
			scan.nextLine(); 
			if (branch.equalsIgnoreCase("NTU") || branch.equalsIgnoreCase("JE") || branch.equalsIgnoreCase("JP")) {
				fourth = false; 
				branch = branch.toUpperCase(); 
			}
			else {
				System.out.println("Invalid Input!"); 
			}
		}
		Person person = new Person("password", name, role, gender, age, branch); 
		return person; 
			
	}
	
	/**
	 * This method removes a staff account with the specified staff ID from the person hash table
	 * @param personTable The hash table containing all staff members
	 * @param staffID The staff ID of the staff member to be removed
	 * @return The updated person hash table, or null if removal failed
	 */
	Hashtable<String, Person> removeStaffAcc(Hashtable<String, Person> personTable, String staffID){
		if (personTable != null) {
			if (personTable.containsKey(staffID)) {
				personTable.remove(staffID); 
				System.out.println("Successfully removed with Staff ID " + staffID); 
				return personTable; 
			}
			else {
				System.out.println("This person does not exist"); 
			}
		}
		else {
			System.out.println("Error"); 
		}
		return null; 
	}
}