package admin;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

import branches.Branch;
import people.Person;

/**
 * This EditStaff class to edit staff details and managing staff promotions and transfers
 */
public class EditStaff {
	
	Scanner scan = new Scanner(System.in) ;
	
	/**
	 * This method edits the details of the staff account identified by the staff ID
	 * @param personTable The person hash table containing staff account and their details
	 * @param staffID The staff ID of the staff account to be editted
	 * @return The updated Person object, which is the updated staff details, or null if staff member is not found 
	 */
	public Person editStaffAcc(Hashtable<String, Person> personTable, String staffID) {
		if (personTable != null) {
			Person person = personTable.get(staffID); 
			System.out.println("Enter the Staff Name: ");
			String name = scan.next();
			scan.nextLine(); 
			System.out.println("Staff " + person.getName() + " changed name to " + name);
			person.setName(name);
			
			boolean second = true; 
			while (second) {
				System.out.println("Enter the Staff Gender - M, F");
				String gender = scan.next();
				scan.nextLine(); 
				if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) {
					gender = gender.toUpperCase(); 
					second = false; 
					person.setGender(gender);
				}
				else {
					System.out.println("Invalid Input!"); 
				}
			}
			boolean third = true; 
			while (third) {
				System.out.println("Enter the Staff Age: ");
				String age = scan.next();
				scan.nextLine(); 
				try {
					@SuppressWarnings("unused")
					int num = Integer.parseInt(age); 
					third = false;
					System.out.println("Staff " + person.getName() + " changed age to " + age);
					person.setAge(age);
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid Input!"); 
				}
			}
			System.out.println("Success"); 
			return person; 
		}
		else {
			System.out.println("Error"); 
			return null; 
		}
	}
	
	/**
	 * This method promotes a regular staff to manager and updates the person hash table
	 * @param personTable The hash table containing all staff members
	 * @return The updated person hash table of the staff members with the promoted staff, or null if staff member is not found
	 */
	//promote staff to manager
		public Hashtable<String,Person> promote(Hashtable<String, Person> personTable) {
			if (personTable != null) {
				System.out.println("Enter Staff ID :"); 
				String staffID = scan.next(); 
				scan.nextLine(); 
				if (personTable.containsKey(staffID)) {
					Person person = personTable.get(staffID);
					System.out.println("Current Role for " + person.getName() + " is " + person.getRole());
					if (person.getRole().equals("M")) {
						System.out.println(person.getName() + " is already a Manager!"); 
						return null; 
					}
					person.setRole("M");
					System.out.println("Successfully promoted " + person.getName() + "!"); 
					personTable.put(staffID, person); 
					return personTable; 
				}
				else {
					System.out.println("This person does not exist"); 
					return null; 
				}
			}
			else {
				System.out.println("Error");
				return null; 
				
			}
		}
		
		private int countBranchMembers(String branchName, Hashtable<String, Person> personTable) {
			int count = 0;
			for (String key : personTable.keySet()) {
				Person person = personTable.get(key); 
				if (person.getBranch().equals(branchName)) {
					count++; 
				}
			}
			return count; 
		}
		
		/**
		 * This method transfers a staff members to different branch and updates the person hash table
		 * @param branchTable The hash table containing all branches and their details
		 * @param personTable The person hash table containing all staff members
		 * @return The hash table of staff members with the newly transferred staff, or null if staff member not found 
		 */
		public Hashtable<String,Person> transfer(Hashtable <String, Branch> branchTable, Hashtable<String, Person> personTable) {
			System.out.println("Enter Staff ID :"); 
			String staffID = scan.next(); 
			scan.nextLine(); 
			
			if (personTable.containsKey(staffID)) {
				Person person = personTable.get(staffID); 
				Branch branch = branchTable.get(person.getBranch());
				String staffQuota = branch.getStaffQuota(); 
				int Quota = Integer.parseInt(staffQuota); 
				System.out.println("What branch would you like to transfer " + person.getName() + " to : \n1. NTU \n2. JP \n3. JE");
				while (true) {
					try {
						int input = scan.nextInt();
						scan.nextLine(); 
						if (input == 1) {
							if (!person.getBranch().equals("NTU")) {
								int count = countBranchMembers("NTU", personTable); 
								if (count + 1 <= Quota) {
									person.setBranch("NTU");
									personTable.put(staffID, person); 
									System.out.println("Successfully Changed Branch"); 
									return personTable; 
								}
								else {
									System.out.println("Too many people in this branch! Choose another branch");
								}
							}
							else {
								System.out.println("Please choose a branch that is different to the current one!");
							}
						}
						else if (input == 2) {
							if (!person.getBranch().equals("JP")) {
								int count = countBranchMembers("JP", personTable); 
								if (count + 1 <= Quota) {
									person.setBranch("JP");
									personTable.put(staffID, person);
									System.out.println("Successfully Changed Branch"); 
									return personTable; 
								}
								else {
									System.out.println("Too many people in this branch! Choose another branch");
								}
							}
							else {
								System.out.println("Please choose a branch that is different to the current one!");
							}
						}
						else if (input == 3) {
							if (!person.getBranch().equals("JE")) {
								int count = countBranchMembers("JE", personTable); 
								if (count + 1 <= Quota) {
									person.setBranch("JE");
									personTable.put(staffID, person);
									System.out.println("Successfully Changed Branch"); 
									return personTable; 
								}
								else {
									System.out.println("Too many people in this branch! Choose another branch");
								}
							}
							else {
								System.out.println("Please choose a branch that is different to the current one!");
							}
						}
						else {
							System.out.println("Invalid Choice! Choose again"); 
						}
					}
					catch (InputMismatchException e) {
						System.out.println("Invalid Input. Choose again."); 
						scan.next(); 
					}
				}
			}
			System.out.println("This person does not exist!"); 
			return null; 
			
		}
}