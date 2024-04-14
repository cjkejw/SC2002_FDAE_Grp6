package staffSys;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import importing.Record;
import people.Person;
import branches.Branch;

public class Manager extends RegStaff {
	private Record record;
	//private Branch menu; //do i need this
	//private List<String[]> menuList = menu.getMenuList();//do i need this
	//so menuList gives you the menu of the specific branch
	
	private Hashtable<String, Branch> indivBranchInfo = record.getBranchTable();
	
	private Branch numOfStaff;
	private String staffQuota = numOfStaff.getStaffQuota();
	
	private Hashtable<String, Person> indivStaffInfo = record.getPersonTable(); //accessing the staff list
	//cos the hashtable for staff is one person, not a whole list of the staff, so the getPersonTable is getting the details of one person, not the whole staff list
	
	public Manager() {
		this.record = new Record();
	}

	//rmb to display who is managing the branch also 
	//maybe you can try print like manager: name (next line) admin: (list of admin staff) (next line) regular staff: (list of reg staff)
	//then you need do for each loop for each manager, admin, staff???
	public void displayStaffList(String branchName){
		if(record != null) {
			if(indivStaffInfo != null) {
				System.out.println("STAFF LIST FOR BRANCH: ");
				for(String key : indivStaffInfo.keySet()) { //traversing the keys of the person table 
					if(key.equalsIgnoreCase(branchName)) { //matching the user input branchName to the key (which contains the branch name)
						Person staff = indivStaffInfo.get(branchName); //retrieving the indiv staff details
						if(staff.getRole().equalsIgnoreCase("M")) {
							System.out.println("Manager: " + staff.getName());
							//break; //exits the inner most loop and resumes execution after this loop
						}else if(staff.getRole().equalsIgnoreCase("A")){
							System.out.println("Admin: " + staff.getName());
						}else if(staff.getRole().equalsIgnoreCase("S")) {
							System.out.println("Regular staff: " + staff.getName());
						}
					
					}else {
						System.out.println("Branch not found");
					}
				}
			}else {
				System.out.println("Staff not found");
			}
		}else {
			System.out.println("Records not found");
		}
	}
	
	public void addItem(String branchName, String item, String itemPrice, String itemType) {
		if(record != null) {
			System.out.println("ADDING ITEM TO MENU OF BRANCH " + branchName + ": ");
			
			for(String key : indivBranchInfo.keySet()){
				if(key.equalsIgnoreCase(branchName)) {
					Branch branch = indivBranchInfo.get(branchName);
					List<String[]> branchMenuList = branch.getMenuList();
					
					//creating a new string array to be added to the menu
					String[] newStuff = new String[]{item, itemPrice, itemType};
					branchMenuList.add(newStuff);
					
				}else {
					System.out.println("Branch not found");
				}
			}
			
		}else {
			System.out.println("Records not found");
		}
		
		
	}
	
	public void removeItem(String branchName, String item, String itemPrice, String itemType) {
		if(record != null) {
			System.out.println("REMOVING ITEM FROM MENU OF BRANCH" + branchName + ":");
			if(indivBranchInfo != null) {
				for(String key : indivBranchInfo.keySet()) { //traversing the key of the branch table
					if(key.equalsIgnoreCase(branchName)) {
						Branch branch = indivBranchInfo.get(branchName); //retrieving all the branch info (includes all the staffQuota, location, menuList)
						List<String[]> branchMenuList = branch.getMenuList(); //retrieving just the menu list of the branch info 
						if(branchMenuList != null) {
							for(String[] menuList : branchMenuList) {
								if(menuList.length >= 3) {
									String item1 = menuList[0];
									String price = menuList[1];
									String type = menuList[2];
									
									if(item1.equalsIgnoreCase(item) && price.equalsIgnoreCase(itemPrice) && type.equalsIgnoreCase(itemType)) { //if the actual item1 and price in the menu matches the user input item and itemPrice
										item1 = "item removed";
										price = "price of " + item1 + " removed";
										type = "type of " + item1 + " removed";
									}else {
										System.out.println("Item not found OR Price not found OR Type not found");
									}
									
								}
							}
							record.exportBranchData();
						}else {
							System.out.println("Branch menu is not found");
						}
						
					}else {
						System.out.println("Branch not found");
					}
				}
			
				
			}else {
				System.out.println("Branch info not found");
			}
			
		}else {
			System.out.println("Records not found");
		}
		
	}
	
	
	public void editPrice(String branchName, String itemOfPriceChange, String newPrice) {
		if(record != null) {
			if(indivBranchInfo != null) {
				System.out.println("EDITING PRICE OF ITEM IN BRANCH" + branchName + ":");
				for(String key : indivBranchInfo.keySet()) {
					if(key.equalsIgnoreCase(branchName)) {
						Branch branch = indivBranchInfo.get(branchName);
						List<String[]> branchMenuList = branch.getMenuList();
						
						if(branchMenuList != null) {
							for(String[] menuList : branchMenuList) {
								if(menuList.length >= 3) {
									String item1 = menuList[0];
									String price = menuList[1];
									
									if(item1.equalsIgnoreCase(itemOfPriceChange) && !price.equalsIgnoreCase(newPrice)) {
										price = newPrice;
									}else {
										System.out.println("Item not found OR Price not found");
									}
								}
							}
							record.exportBranchData();
						}else {
							System.out.println("Branch menu is not found");
						}
					}else {
						System.out.println("Branch not found");
					}
				}
			}else {
				System.out.println("Branch info not found");
			}
		}else {
			System.out.println("Records not found");
		}
	}
	
}
