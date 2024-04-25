package staffSys;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import importing.Record;
import people.Person;
import branches.Branch;

public class BranchManager extends RegularStaff{
	private Record record;
	private Hashtable<String, Branch> indivBranchInfo; 
	private Hashtable<String, Person> indivStaffInfo;
	
	Scanner scan = new Scanner(System.in);
	
	public BranchManager() {
		this.record = new Record();
		this.indivBranchInfo = record.getBranchTable(); //need to be here instead of before the constructor, cos you have to initialise all this stuffs in the constructor
		this.indivStaffInfo = record.getPersonTable();
	}
	
	//TO DELETE:
	//person table's key is the staffID
	//branch table's key is the branchName
	
	//displaying staff list of branch supervised by specific manager
	public void displayStaffList(String branchName) {
		if(record != null && indivStaffInfo != null) {
			System.out.println("STAFF LIST OF BRANCH " + branchName + ":");
			
			for(String key : indivStaffInfo.keySet()) { //traversing person table
				Person staff = indivStaffInfo.get(key);
				
				// if branch of staff matches user input branchName, print staff name
				if(branchName.equalsIgnoreCase(staff.getBranch())) {
					System.out.println("\t-" + staff.getName());
				}
			}
			
		}else {
			System.out.println("Records and/or staff table not found");
		}
	}
	
	//adding items to menu of specific branch
	//can use array then the items, item price all that in index then after that you can just add to the menu list and then export or smth
	public void addItems(String branchName, String[] menu) {
		if(record != null) {
			System.out.println("ADDING ITEM TO MENU OF BRANCH " + branchName + ":");
			
			for(String key : indivBranchInfo.keySet()) { //traversing the branch table
				Branch branch = indivBranchInfo.get(key);
				List<String[]> menuList = branch.getMenuList();
				
				if(key.equalsIgnoreCase(branchName)) { //if the key matches the user input branchName, add items to the menu
					System.out.println("Name of new item: ");
					menu[0] = scan.nextLine();
					System.out.println("Price of new item: ");
					menu[1] = scan.nextLine();
					System.out.println("Category of new item: ");
					menu[2] = scan.nextLine();
					
					//adding the new item to the menu list of the branch
					menuList.add(menu);
					
					//exporting new menu data to text file
					record.exportBranchData();
				}else {
					System.out.println("Branch not found");
				}
			}
		}else {
			System.out.println("Records not found");
		}
	}
	
	public void removeItem(String branchName, String[] menuDelete) {
		if(record != null && indivBranchInfo != null) {
			System.out.println("REMOVING ITEM FROM MENU OF BRANCH " + branchName + ":");
			
			for(String key : indivBranchInfo.keySet()) {
				Branch branch = indivBranchInfo.get(key); 
				List<String[]> menuList = branch.getMenuList(); //retrieving menu list 
				
				if(key.equalsIgnoreCase(branchName) && menuList != null) {
					System.out.println("Item to be removed: ");
					menuDelete[0] = scan.nextLine();
					System.out.println("Price of this item: ");
					menuDelete[1] = scan.nextLine();
					System.out.println("Category of this item: ");
					menuDelete[2] = scan.nextLine();
					
					//
					menuList.remove(menuDelete);

						
				}else {
					System.out.println("Branch not found and/or menuList not found");
				}
					
					//updating menu data to text file
					
			}
		}else {
			System.out.println("Records and/or branch table not found");
		}
	}
	
	public void editPrice(String branchName, String[] menu) {
		if(record != null && indivBranchInfo != null) {
			System.out.println("EDITTING ITEM PRICE FOR BRANCH " + branchName + ":");
			
			for(String key : indivBranchInfo.keySet()) {
					Branch branch = indivBranchInfo.get(key); 
					List<String[]> menuList = branch.getMenuList();
					
					if(key.equalsIgnoreCase(branchName) && menuList != null) {
						System.out.println("Item whose price you want to change:");
						menu[0] = scan.nextLine();
						
						for(String[] row : menuList)
						if(menu[0] == row[0]) {
							System.out.println("New price:");
							String newPrice = scan.nextLine();
							row[1] = newPrice;
						}
						
					}else {
						System.out.println("Branch and/or menu not found");
					}
				
			}
		}else {
			System.out.println("Records and/or branch table not found");
		}
	}
}
	


	
	
