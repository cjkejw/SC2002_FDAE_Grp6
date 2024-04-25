package admin;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

import branches.Branch;
import people.Person;
import recordInterfaces.BranchInterface;

public class FilterDisplayStaffList implements BranchInterface {
	
	Scanner scan = new Scanner(System.in) ;

	private void displayStaffListAge(String age, Hashtable<String, Person> personTable) {
		System.out.println("STAFF LIST FOR STAFF WITH AN AGE GREATER THAN " + age); 
		if (personTable != null) {
			int filterAge = Integer.parseInt(age); 
			for (String key : personTable.keySet()) {
				Person person = personTable.get(key); 
				String realAge = person.getAge(); 
				int AgeNum = Integer.parseInt(realAge); 
				if (AgeNum >= filterAge) {
					System.out.println("\t- " + person.getName()); 
				}
			}
		}
		else {
			System.out.println("ERROR"); 
		}
	}
	
	private void displayStaffListGender(String gender, Hashtable<String, Person> personTable) {
		if (gender.equals("F")) {
			System.out.println("STAFF LIST FOR STAFF WHO ARE FEMALES");
		} 
		else {
			System.out.println("STAFF LIST FOR STAFF WHO ARE MALES");
		}
		if (personTable != null) {
			for (String key : personTable.keySet()) {
				Person person = personTable.get(key); 
				if (gender.equals(person.getGender())) {
					System.out.println("\t- " + person.getName()); 
				}
			}
		}
		else {
			System.out.println("ERROR"); 
		}
	}
	
	public void displayStaffListRole(String role, Hashtable<String, Person> personTable) {
		if (role.equals("M")) {
			System.out.println("STAFF LIST FOR STAFF WHO ARE MANAGERS");
		} 
		else if (role.equals("S")) {
			System.out.println("STAFF LIST FOR STAFF WHO ARE REGULAR STAFF");
		}
		if (personTable != null) {
			for (String key : personTable.keySet()) {
				Person person = personTable.get(key); 
				if (role.equals(person.getRole())) {
					System.out.println("\t- " + person.getName()); 
				}
			}
		}
		else {
			System.out.println("ERROR"); 
		}
	}
	
	private void displayStaffListBranch(String branchName, Hashtable<String, Person> personTable) {
		String BRANCHNAME = branchName.toUpperCase(); 
		System.out.println("STAFF LIST FOR STAFF WHO ARE IN " + BRANCHNAME);
		if (personTable != null) {
			for (String key : personTable.keySet()) {
				Person person = personTable.get(key); 
				if (person.getBranch().equals(branchName)) {
					System.out.println("\t- " + person.getName()); 
				}
			}
		}
		else {
			System.out.println("ERROR"); 
		}
	}
	
	protected void choosingFunction(Hashtable<String, Person> personTable) {
		System.out.println("Would you like to filter display by : \n1. Branch\n2. Role\n3. Gender\n4. Age\n 5. Go back");
		while (true) {
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				if (input == 1) {
					String branchName = choosingBranch(); 
					displayStaffListBranch(branchName, personTable); 
					return; 
				}
				else if (input == 2) {
					String role = choosingRole(); 
					displayStaffListRole(role, personTable); 	
					return; 
				}
				else if (input == 3) {
					String gender = choosingGender(); 
					displayStaffListGender(gender, personTable);
					return; 
				}
				else if (input == 4) {
					String age = chooseAge(); 
					displayStaffListAge(age, personTable); 
					return; 
				}
				else if (input == 5) {
					System.out.println("Going back..."); 
					return; 
				}
				else {
					System.out.println("Invalid Choice"); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}
	
	protected String choosingBranch() {
		Hashtable<String, Branch> branchTable = BranchInterface.getBranchTable();
		System.out.println("Which branch to choose: ");
		int count = 1;
		for (String key : branchTable.keySet()) {
			System.out.println(count + ". " + key); 
			count++; 
		}
		while (true) {
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				
				if (input > 0 && input <= branchTable.size()) {
					int find = 1; 
					for (String key : branchTable.keySet()) {
						if (input == find) {
							return key; 
						}
						else {
							find++; 
						}
					}
				}
				else {
					System.out.println("Invalid Choice. Choose again."); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}
	
	private String choosingRole() {
		System.out.println("What role do you want to choose : \n1. Staff\n2. Manager"); 
		while (true) {
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				if (input == 1) {
					return "S"; 
				}
				else if (input == 2) {
					return "M"; 
				}
				else {
					System.out.println("Invalid Choice! Choose again."); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}
	
	private String choosingGender() {
		System.out.println("What Gender do you want to choose : \n1. Male\n2. Female"); 
		while (true) {
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				if (input == 1) {
					return "M"; 
				}
				else if (input == 2) {
					return "F"; 
				}
				else {
					System.out.println("Invalid Choice! Choose again."); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}
	
	private String chooseAge() {
		System.out.println("Choose a certain age. Staff with who are OLDER will be displayed"); 
		while (true) {
			try {
				int age = scan.nextInt(); 
				String ageString = String.valueOf(age); 
				return ageString; 
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}
}
