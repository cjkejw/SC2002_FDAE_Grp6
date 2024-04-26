package staffSys;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import people.Person;
import recordInterfaces.BranchInterface;
import recordInterfaces.PersonInterface;
import branches.Branch;

public class BranchManager extends RegStaff implements DisplayingStaffList, BranchInterface, PersonInterface {
	
	private Hashtable<String, Branch> branchTable;
	private Branch branch;
	private Hashtable<String, Person> personTable;
	
	Scanner scan = new Scanner(System.in);
	
	public BranchManager(Person person, String branchName) {
		super(person, branchName); 
		this.personTable = PersonInterface.getPersonTable(); 
		this.branchTable = BranchInterface.getBranchTable(); 
		this.branch = branchTable.get(branchName);  
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
	
	public void displayStaffList(String branchName){
		if(personTable != null) {
			int count = countStaff(branchName); 
			if (count == 0) {
				System.out.println("This manager has no staff in this branch."); 
				return; 
			}
			System.out.println("STAFF LIST OF THIS MANAGER IN BRANCH " + branchName.toUpperCase() + ":");
			for(String key : personTable.keySet()) { 
				Person staff = personTable.get(key); 
				if (staff != null) {
					if (staff.getBranch().equalsIgnoreCase(branchName)) {
						if (staff.getBranch().equalsIgnoreCase(branchName)) {
							if (staff.getRole().equals("S")) {
								System.out.println("\t-" + staff.getName());
							}
						}
					}
				}
			}
			System.out.println("This manager has " + count + " Staff.\n"); 
		}
		else {
			System.out.println("personTable not found"); 
		}
	}
			
			
	
	public void addItem() {			
		System.out.println("ADDING ITEM TO MENU OF BRANCH " + branchName.toUpperCase() + ": ");	
		if (branch != null) {
			List<String[]> branchMenuList = branch.getMenuList();
			String[] menu = new String[3];
			
			System.out.println("Name of new item: ");
			menu[0] = scan.nextLine();
			System.out.println("Price of new item: ");
			menu[1] = scan.nextLine();
			System.out.println("Category of new item: ");
			menu[2] = scan.nextLine();
			for (String[] itemList : branchMenuList) {
				if (itemList[0] == menu[0]) {
					System.out.println("Cannot add item as item already exists!"); 
					return; 
				}
			}
			branchMenuList.add(menu);
			branch.setMenuList(branchMenuList);
			branchTable.put(branchName, branch); 
			BranchInterface.exportBranchTable(branchTable);
			System.out.println("Successfully added item"); 
			}
		else {
				System.out.println("Branch not found");
		}
	}	
	
	public void removeItem() {
		System.out.println("REMOVING ITEM FROM MENU OF BRANCH" + branchName.toUpperCase() + ":");
		if (branch != null) {
			List<String[]> branchMenuList = branch.getMenuList();
			if(branchMenuList != null) {
				
				for (int i = 0; i < branchMenuList.size(); i++) {
					String[] itemList = branchMenuList.get(i); 
					System.out.println("\t" + (i+1) + ". " + itemList[0]); 
				}
				while (true) {
					try {
						System.out.println("What is the item to be removed: "); 
						int option = scan.nextInt(); 
						scan.nextLine(); 
						
						if (option > 0 && option <= branchMenuList.size()) {
							if (branchMenuList.remove(option-1) != null) {
								branch.setMenuList(branchMenuList);
								branchTable.put(branchName, branch); 
								BranchInterface.exportBranchTable(branchTable);
								System.out.println("Successfully removed item!"); 
								return; 
							}
						}
						else {
							System.out.println("Invalid Choice. Choose again."); 
						}
					}
					catch (InputMismatchException e) {
						System.out.println("Invalid Input! Choose again"); 
						scan.next(); 
					}
				}
			}
			else {
				System.out.println("Cannot Remove items as there is no menu!");
			}
		}
		else {
			System.out.println("Branch not found");
		}
	}
				
				
			
			
			
	
	
	public void editPrice() {
		List<String[]> branchMenuList = branch.getMenuList();
		if(branchMenuList != null) {
			System.out.println("EDITTING ITEM PRICE FOR BRANCH " + branchName.toUpperCase() + ":");
			
			for (int i = 0; i < branchMenuList.size(); i++) {
				String[] itemList = branchMenuList.get(i); 
				System.out.println("\t" + (i+1) + ". "  + itemList[0]); 
			}
			while (true) {
				try {
					System.out.println("What is the item to be edited: "); 
					int option = scan.nextInt(); 
					scan.nextLine();
					
					if (option > 0 && option <= branchMenuList.size()) {
						String[] itemList = branchMenuList.get(option-1); 
						branchMenuList.remove(option-1);
						String[] NewItem = new String[3];
						double price = Double.parseDouble(itemList[1]); 
						System.out.printf("Current price of %s is $%.2f\n", itemList[0], price );
						System.out.println("New price (to 2.d.p) :");
						String newPrice = scan.nextLine();
						NewItem[0] = itemList[0]; 
						NewItem[1] = newPrice;
						NewItem[2] = itemList[2];
						if (branchMenuList.add(NewItem)) {
							branch.setMenuList(branchMenuList);
							branchTable.put(branchName, branch); 
							BranchInterface.exportBranchTable(branchTable);
							System.out.println("Successfully changed price!"); 
							return; 
						}
						else {
							System.out.println("Cannot add to menuList"); 
							return; 
						}
					}
					else {
						System.out.println("Invalid Choice! Choose again"); 
					}
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid Input! Choose again"); 
					scan.next(); 
				}
			}
		}
	}
	
}