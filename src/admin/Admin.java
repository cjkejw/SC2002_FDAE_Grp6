package admin;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import branches.Branch;
import dependencyInterface.BranchDependency;
import dependencyInterface.PersonDependency;
import people.Person;
import recordInterfaces.BranchInterface;
import recordInterfaces.PersonInterface;
import staffSys.DisplayingStaffList;
import recordInterfaces.PaymentInterface;

public class Admin extends FilterDisplayStaffList implements DisplayingStaffList, PersonInterface, PaymentInterface, PersonDependency, BranchDependency {

	private Hashtable <String, Branch> branchTable;
	private Hashtable<String, Person> personTable;
	private Hashtable<String, List<String>> paymentTable;
	
	Scanner scan = new Scanner(System.in); 
	
	public Admin() {
		branchTable = BranchInterface.getBranchTable(); 
		personTable = PersonInterface.getPersonTable();
		paymentTable = PaymentInterface.getPaymentTable();
	}
	
	public void adminLoggingOut() {
		BranchInterface.exportBranchTable(branchTable);
		PersonInterface.exportPersonTable(personTable);
		PaymentInterface.exportPaymentTable(paymentTable);
	}
	
	
	public String editStaffID() {
		System.out.println("What is the Staff ID you want to change: ");
		while (true) {
			String ID = scan.next(); 
			scan.nextLine(); 
			if (personTable.containsKey(ID)) {
				while (true) {
					System.out.println("What is the new Staff ID: "); 
					String NewID = scan.next(); 
					scan.nextLine();
					if (!ID.equals(NewID)) {
						Person person = personTable.get(ID); 
						personTable.remove(ID); 
						personTable.put(NewID, person); 
						System.out.println("Successfully Changed StaffID"); 
						return NewID; 
					}
					else {
						System.out.println("Cannot use same ID. Choose again."); 
					}
				}
			}
			else {
				System.out.println("Staff does not exist. Try Again."); 
			}
		}
	}
	
	private int calcNumManagers(int staffNum) { //assigning the number of managers according to staff quota constraints
		if(staffNum <= 4) {
			return 1; //1 manager assigned to 1-4 staffs
		}else if(staffNum <= 8) {
			return 2; //2 managers assigned to 5-8 staffs
		}else {
			return 3; //3 managers assigned to 9-15 staffs
		}
		
	}
	
	private int countStaff(String branchName) {
		int count = 0; 
		for (String key : personTable.keySet()) {
			Person person = personTable.get(key);
			if (person.getBranch().equals(branchName)) {
				if (person.getRole().equals("S")) {
					count++; 
				}
			}
		}
		return count; 
	}
	
	private int countManager(String branchName) {
		int count = 0; 
		for (String key : personTable.keySet()) {
			Person person = personTable.get(key); 
			if (person.getBranch().equals(branchName)) {
				if (person.getRole().equals("M")) {
					count++; 
				}
			}
		}
		return count; 
	}
	
	public void checkingManagerCount() {
		String branchName = choosingBranch(); 
		int curManager = countManager(branchName); 
		int curStaff = countStaff(branchName); 
		int needManager = calcNumManagers(curStaff); 
		if (curManager == needManager) {
			System.out.println("The Required Number of Managers are present in " + branchName); 
		}
		while (curManager < needManager) {
			this.personTable = PersonInterface.getPersonTable(); 
			System.out.println("You need to add " + (needManager - curManager) + " managers to " + branchName);
			System.out.println("Would you like to : \n1. Add Managers \n2. Promote Staff"); 
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				if (input == 1 ) {
					StaffAddRemoval func1 = new StaffAddRemoval(); 
					System.out.println("Enter Staff ID: "); 
					String ID = scan.next(); 
					scan.nextLine();
					Person person = func1.addStaffAcc(); 
					personTable.put(ID, person);
					curManager++; 
				}
				else if (input == 2) {
					EditStaff func2 = new EditStaff(); 
					Hashtable<String, Person> personTable = func2.promote(this.personTable); 
					if (personTable != null) {
						this.personTable = personTable; 
						curManager++; 
						curStaff--; 
						needManager = calcNumManagers(curStaff); 
					}
				}
				else {
					System.out.println("Invalid Choice! Choose again."); 
				}
				PersonInterface.exportPersonTable(this.personTable);
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Choose again."); 
				scan.next(); 
			}
		}
		if (needManager < curManager) {
			System.out.println("There are enough Managers."); 
		}
	}
	
	//adding new payment method
	public void addPaymentMethod() {
		System.out.println("ADDING PAYMENT METHOD: ");
		String payment = null; 
		System.out.println("What type of payment to add : \n1. Card\n2. Online"); 
		boolean outcome = true; 
		while (outcome) {
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
					
				if (input == 1) {
					payment = "Card"; 
					outcome = false; 
				}
				else if (input == 2) {
					payment = "Online"; 
					outcome = false; 
				}
				else {
					System.out.println("Invalid Choice! Choose Again."); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
			
		System.out.println("Name of new Method : "); 
		String newMethod = scan.next(); 
		scan.nextLine(); 
		
		for(String key : paymentTable.keySet()) {
			if(key.equalsIgnoreCase(payment)) {
				List<String> methods = paymentTable.get(key);
				methods.add(newMethod);					
				paymentTable.put(key,  methods); 
				PaymentInterface.exportPaymentTable(this.paymentTable);
				System.out.println("New payment method successfully added");
				return; 
			}
		}
		System.out.println("Unsuccsesful"); 
	}
	
	
	public void openOrCloseBranch() {
		boolean action = true; 
		System.out.println("Which action would you like to do : \n1. Open branch \n2. Close branch");
		boolean outcome = true; 
		String branchName = null; 
		while (outcome) {
			try {
				int option = scan.nextInt(); 
				scan.nextLine(); 
				if (option == 1) {
					action = true; 
					outcome = false; 
				}
				else if (option == 2) {
					action = false; 
					outcome = false; 
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
		if (action) {
			System.out.println("Which closed branch would you like to open: "); 
			int count = 0; 
			for (String key : branchTable.keySet()) {
				if (branchTable.get(key).getStatus().equals("CLOSED")) {
					count++; 
					System.out.println(count + ". " + key); 
				}
			}
			if (count == 0) {
				System.out.println("There are no branches to open."); 
				return; 
			}
			while (true) {
				try {
					int choice = scan.nextInt(); 
					if (choice < 0 || choice > count) {
						System.out.println("Invalid Choice. Choose again."); 
					}
					else {
						for (String key : branchTable.keySet()) {
							if (branchTable.get(key).getStatus().equals("CLOSED")) {
								count--; 
								if (count == 0) {
									Branch branch = branchTable.get(key); 
									branch.setStatus("OPEN");
									branchTable.put(key, branch); 
									BranchInterface.exportBranchTable(branchTable);
									System.out.println("Branch successfully open\n");
									return; 
								}
							}
						}
					}
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid Input! Choose again."); 
					scan.next(); 
				}
			}
		}
		else {
			branchName = choosingBranch(); 
		}
		//true means open branch, false means close branch
		if(branchTable != null) {
			//closing branch
			Branch branch = branchTable.get(branchName);
			if(action == false) { //closing branch
				System.out.println("Closing branch " + branchName + ":");
				branch.setStatus("CLOSED");
				branchTable.put(branchName, branch);
				BranchInterface.exportBranchTable(branchTable);
				System.out.println("Branch successfully closed\n");
			}
			else { //opening branch
				System.out.println("Opening new branch " + branchName + "(staff quota, location and menu are to be determined)");
			}
		}else {
			System.out.println("Branch not found");
		}
	}

	
	public void addEditRemove() {
		this.personTable = PersonInterface.getPersonTable(); 
		System.out.println("Would you like to : \n1. Add Staff Account \n2. Edit Staff Account\n3. Remove Staff Account \n4. Go back"); 
		while (true) {
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				if (input == 1) {
					System.out.println("Proceeding to add account..."); 
					StaffAddRemoval add = new StaffAddRemoval(); 
					System.out.println("Enter Staff ID: "); 
					String ID = scan.next(); 
					scan.nextLine();
					Person person = add.addStaffAcc(); 
					personTable.put(ID, person); 
					PersonInterface.exportPersonTable(this.personTable);
					System.out.println("Successfully added Staff Account!"); 	
					return; 
				}
				else if (input == 2) {
					System.out.println("Proceeding to edit account...");
					EditStaff edit = new EditStaff(); 
					String newID = editStaffID(); 
					Person person = edit.editStaffAcc(personTable, newID); 
					if (person != null) {
						personTable.put(newID, person); 
						PersonInterface.exportPersonTable(this.personTable);
						System.out.println("Successfully edited account!"); 
					}
					return; 
				}
				else if (input == 3) {
					System.out.println("Proceeding to remove account...");
					System.out.println("Enter Staff ID: "); 
					String ID = scan.next(); 
					scan.nextLine();
					StaffAddRemoval remove = new StaffAddRemoval(); 
					Hashtable<String, Person> personTable = remove.removeStaffAcc(this.personTable, ID); 
					if (personTable != null) {
						this.personTable = personTable; 
						PersonInterface.exportPersonTable(this.personTable);
						System.out.println("Successfully removed account!");  
					}
					return; 
				}
				else if (input == 4) {
					System.out.println("Going back..."); 
					return; 
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
	
	public void choosingFunction() {
		System.out.println("Do you want to: \n1. Display all \n2. Filter display"); 
		while (true) {
			try {
				int choice = scan.nextInt(); 
				scan.nextLine(); 
				if (choice == 1) {
					displayStaffList(null); 
					return; 
				}
				else if (choice == 2) {
					super.choosingFunction(this.personTable = PersonInterface.getPersonTable());
					return;
				}
				else {
					System.out.println("Invalid Choice! Choose Again."); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}
	
	public void promotingTransferFunction() {
		this.personTable = PersonInterface.getPersonTable();
		System.out.println("Would you like to : \n1. Promote Staff\n2. Transfer Staff");
		EditStaff edit = new EditStaff(); 
		while (true) {
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				if (input == 1) {
					Hashtable<String, Person> personTable = edit.promote(this.personTable);
					if (personTable != null) {
						this.personTable = personTable;
						PersonInterface.exportPersonTable(this.personTable);
						return; 
					}
					return;
				}
				else if (input == 2) {
					Hashtable<String, Person> personTable = edit.transfer(branchTable, this.personTable); 
					if (personTable != null) {
						this.personTable = personTable; 
						PersonInterface.exportPersonTable(this.personTable);
						return; 
					}
					return; 
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
	
	public void displayStaffList(String branchName){
		this.personTable = PersonInterface.getPersonTable();
		if(personTable != null) {
			System.out.println("STAFF LIST FOR ALL: ");
			for(String key : personTable.keySet()) { //traversing the keys of the person table 
				Person staff = personTable.get(key); 
				if (staff != null) {
					if (staff.getName().equals("Name")) {
						continue; 
					}
					System.out.println("\t- " + staff.getName()); 
				}
				else {
					System.out.println("Staff not found");
				}
			}
		}
		else {
			System.out.println("personTable not found"); 
		}
			
	}
		
}

