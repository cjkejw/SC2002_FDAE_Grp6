package staffSys;

import importing.Record;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import branches.Branch;
import people.Person;

public class Admin {

	private Record record;
	private Hashtable <String, Branch> indivBranchInfo;
	private Hashtable<String, Person> indivStaffInfo;
	private Hashtable<String, List<String>> paymentInfo;
	
	public Admin() {
		this.record = new Record();
		indivBranchInfo = record.getBranchTable();
		indivStaffInfo = record.getPersonTable();
		paymentInfo = record.getPaymentTable();
	}
	
	//for hiring new staff or adding manager to branch 
	public void addStaffAcc(String branchName, String staffName, int staffID, String staffRole, String staffGender, String staffAge, String staffPW) {
		if(record != null) {
			System.out.println("ADDING STAFF ACCOUNT:");
			
			for(String key : indivStaffInfo.keySet()) { //traversing key portion of staff table
				if(key.equalsIgnoreCase(branchName)) { //adding staff account to user input branch
					Person staff = indivStaffInfo.get(key);
					staff.setName(staffName);
					staff.setStaffID(staffID);
					staff.setRole(staffRole);
					staff.setGender(staffGender);
					staff.setAge(staffAge);
					staff.setBranch(branchName);
					staff.setPassword(staffPW);
					//break;
				}else {
					System.out.println("Branch not found");
				}
			}
			record.exportPersonData(indivStaffInfo);
			System.out.println("Staff account successfully added");
			
		}else {
			System.out.println("Records not found");
		}
	}
	
	//for firing staff or removing manager from branch 
	public void removeStaffAcc(String branchName, int staffID){
		if(record != null && indivStaffInfo != null) {
			System.out.println("REMOVING STAFF ACCOUNT:");
			
			for(String key : indivStaffInfo.keySet()) {
				if(key.equalsIgnoreCase(branchName)) {
					Person staff = indivStaffInfo.get(key); //retrieving value portion of staff table
					
					if(staff.getStaffID() == staffID) { //removing staff account according to staff ID
						staff.setName("Invalid");
						staff.setStaffID(Integer.parseInt("Invalid"));
						staff.setRole("Invalid");
						staff.setGender("Invalid");
						staff.setAge("Invalid");
						staff.setBranch("Invalid");
						staff.setPassword("Invalid");
						//break;
					}
				}else {
					System.out.println("Branch not found");
				}
			}
			record.exportPersonData(indivStaffInfo); //updating person text file
			System.out.println("Staff account successfully removed");
			
		}else {
			System.out.println("Records and staff table not found");
		}
	}
	
	//edit staff account
	public void editStaffAcc(String branchName, String staffName, int staffID, String newName, int newID, String newAge, String newPW) {
		if(record != null && indivStaffInfo != null) {
			System.out.println("EDITTING STAFF PERSONAL INFORMATION:");
			for(String key : indivStaffInfo.keySet()) {
				if(key.equalsIgnoreCase(branchName)) {
					Person staff = indivStaffInfo.get(key);
					
					//only can edit staff info if correct staff ID is keyed in
					if(staff.getStaffID() == staffID) {
						if(!staff.getName().equalsIgnoreCase(newName)) { //editing staff name
							staff.setName(newName);
							System.out.println("Staff " + staffName + " changed name to " + newName);
							//break;
						}else if(staff.getStaffID() != newID){ //editing staff ID
							staff.setStaffID(newID);
							System.out.println("Staff " + staffName + " changed ID to " + newID);
						}else if(!staff.getAge().equalsIgnoreCase(newAge)) { //editing staff age
							staff.setAge(newAge);
							System.out.println("Staff " + staffName + " changed age to " + newAge);
							//break;
						}else if(!staff.getPassword().equalsIgnoreCase(newPW)) { //editing staff password
							staff.setPassword(newPW);
							System.out.println("Staff " + staffName + " changed password to " + newPW);
							//break;
						}
					}
					record.exportPersonData(indivStaffInfo); //updating person text file
					System.out.println("Staff personal information successfully editted");
				}else {
					System.out.println("Branch not found");
				}
				
			}
		}else {
			System.out.println("Records and staff table not found");
		}
		
	}
	
	//promote staff to manager
	public void promote(String branchName, String staffName, int staffID) {
		if(record != null && indivStaffInfo != null) {
			System.out.println("PROMOTING STAFF:");
			for(String key : indivStaffInfo.keySet()) {
				if(key.equalsIgnoreCase(branchName)) {
					Person staff = indivStaffInfo.get(key);
					if(staff.getStaffID() == staffID) {
						if(staff.getRole().equalsIgnoreCase("S")) {
							staff.setRole("M");
							break;
						}
						record.exportPersonData(indivStaffInfo);
						System.out.println("Staff " + staffName + " successfully promoted to Branch Manager");
					}else {
						System.out.println("Staff ID not found");
					}
				}else {
					System.out.println("Branch not found");
				}
				
			}	
		}else {
			System.out.println("Records and/or staff table not found");
		}
	}
	
	//transfer staff
	public void transfer(String branchName, String newBranch, String staffName, int staffID) {
		if(record != null && indivStaffInfo != null && indivBranchInfo != null) {
			for(String key : indivStaffInfo.keySet()) {
				if(key.equalsIgnoreCase(branchName)) {
					Person staff = indivStaffInfo.get(key);
					Branch branch = indivBranchInfo.get(key);						
					if(staff.getStaffID() != staffID) {
						staff.setBranch(newBranch); //setting this staff's branch to new branch
						branch.setStaffQuota(branch.getStaffQuota() + 1); //updating staff quota
						break;
					}
					record.exportBranchData(); //updating branch text file
					record.exportPersonData(indivStaffInfo); //updating person text file
					System.out.println("Staff " + staffName + " sucessfully transferred from " + branchName + " to " + newBranch);
				}else {
					System.out.println("Branch not found");
				}
			}
				
		}else {
			System.out.println("Records and/or staff table and/or branch table not found");
		}
	}
	
	//filter by branch
	public void displayStaffListByBranch(String branchName) {
		if(record != null && indivStaffInfo != null) {
			for(String key : indivStaffInfo.keySet()) { //traversing the key portion of the hashtable
				Person staff = indivStaffInfo.get(key); //retrieving the value portion of the hashtable
				
				if(key.equalsIgnoreCase(branchName)) { //displaying staff list according to user input branch name
					System.out.println("Staff list of branch" + key + ":");
					System.out.println("\t" + staff.getName());
					//break;
				}else {
					System.out.println("Branch not found");
				}
				System.out.println("Staff filtered correctly by branch");
			}
		}else {
			System.out.println("Records and/or staff table not found");
		}
	}
	
	//filtering by role gender and age range
	public void displayStaffListByRGA(String role, String gender, int minAge, int maxAge) {
		if(record != null && indivStaffInfo != null) {
			Iterator<Person> iterator = indivStaffInfo.values().iterator();
			
			//filter flags to indicate which filter should be applied (each flag is set to be true if it fulfills the criteria provided in the brackets)
			boolean roleFilter = (role != null && !role.isEmpty());
			boolean genderFilter = (gender != null && !gender.isEmpty());
			boolean ageFilter = (minAge>=16 && maxAge>16 && maxAge>minAge);
			
			while(iterator.hasNext()) {
				Person staff = iterator.next();
				int age = Integer.parseInt(staff.getAge());
			
				if(roleFilter && staff.getRole().equalsIgnoreCase(role)) { //filter by role
					System.out.println("Staff list of all " + staff.getRole() + ":");
					System.out.println("\t" + staff.getName());
				}else if(genderFilter && staff.getGender().equalsIgnoreCase(gender)) { //filter by gender
					System.out.println("All " + staff.getGender() + " staff:");
					System.out.println("\t" + staff.getName());
				}else if(ageFilter && age > minAge && age <= maxAge) { //filter by age range
					System.out.println("All staff between the age of " + minAge + " and " + maxAge + " (inclusive):");
					System.out.println("\t" + staff.getName());
				}
			}
			System.out.println("Staff filtered correctly by role/ gender/ age");
		}else {
			System.out.println("Records and/or staff table not found");
		}
	}
	
	public void assignManager(String manager, String managerName, int managerID, String managerGender, String managerAge, String managerPW,  String branchName, String newBranch) {
		if(record != null && indivBranchInfo != null) {
			Branch oldBranch = indivBranchInfo.get(branchName); //retrieving value portion of the old branch
			Branch targetBranch = indivBranchInfo.get(newBranch); //retrieving value portion of the new branch
			
			if(oldBranch != null && targetBranch != null) {
				removeStaffAcc(branchName, managerID);
				addStaffAcc(newBranch, managerName, managerID, "M", managerGender, managerAge, managerPW);
				
				//calculating the existing staff quota and the required number of managers for the old branch
				int oldBranchStaffQuota = Integer.parseInt(oldBranch.getStaffQuota());
				int oldRequiredNumOfM = calcNumManagers(oldBranchStaffQuota);
				
				//calculating the new staff quota and the required number of managers for new branch
				int newBranchStaffQuota = Integer.parseInt(targetBranch.getStaffQuota());
				int newRequiredNumOfM = calcNumManagers(newBranchStaffQuota);
				
				adjustManagers(oldBranch, oldRequiredNumOfM, branchName, managerName, managerID, managerGender, managerAge, managerPW);
				adjustManagers(targetBranch, newRequiredNumOfM, newBranch, managerName, managerID, managerGender, managerAge, managerPW);
				
				record.exportBranchData();
				System.out.println("Manager " + managerName + "succefully assigned to " + newBranch);
				
			}else {
				System.out.println("Branches not found");
			}
		}else {
			System.out.println("Records not found");
		}
		
	}
	
	private int calcNumManagers(int staffQuota) { //assigning the number of managers according to staff quota constraints
		if(staffQuota <= 4) {
			return 1; //1 manager assigned to 1-4 staffs
		}else if(staffQuota <= 8) {
			return 2; //2 managers assigned to 5-8 staffs
		}else {
			return 3; //3 managers assigned to 9-15 staffs
		}
		
	}
	
	private void adjustManagers(Branch branchToAdjust, int requiredNumManagers, String branchName, String managerName, int managerID, String managerGender, String managerAge, String managerPW) {
		//branchToAdjust must be the same branch as branchName
		int currentNumManagers = Integer.parseInt(branchToAdjust.getStaffQuota()); 
		int managersToAdd = requiredNumManagers - currentNumManagers;
		
		if(managersToAdd > 0) { //if got managers to add, add managers to branch one by one
			for(int i=0; i<managersToAdd; i++) {
				addStaffAcc(branchName, managerName, managerID, "M", managerGender, managerAge, managerPW);
			}
		}else if(managersToAdd < 0) { //if have managers to be removed, remove them one by one
			for(int i=0; i< -managersToAdd; i++) {
				removeStaffAcc(branchName, managerID);
			}
		}
	}
	
	//adding new payment method
	public void addPaymentMethod(String branchName, String newMethod) {
		if(record != null) {
			System.out.println("ADDING PAYMENT METHOD: ");
			
			for(String key : paymentInfo.keySet()) {
				if(key.equalsIgnoreCase(branchName)) {
					List<String> methods = paymentInfo.get(key);
					methods.add(newMethod);
					paymentInfo.put(key,  methods); //puts the updated list of payment methods back in the payment hashtable according to the branchName
					//break;
				}else {
					System.out.println("Branch not found");
				}
			}
			record.exportPaymentData();
			System.out.println("New payment method successfully added");
		}else {
			System.out.println("Records not found");
		}
	}
	
	public void openOrCloseBranch(String branchName, boolean action) {
		//true means open branch, false means close branch
		if(record != null && indivBranchInfo != null) {
			//closing branch
			for(String key : indivBranchInfo.keySet()) {
				if(key.equalsIgnoreCase(branchName)){
					Branch branch = indivBranchInfo.get(key);
					if(action == false) { //closing branch
						System.out.println("Closing branch " + branchName + ":");
						branch.setStaffQuota("Invalid staff quota");
						branch.setLocation("Invalid location");
						branch.setMenuList(null); //removing menu list
						if(branch.getMenuList() == null) {
							System.out.println("Invalid menu"); //putting this if statement here so that it can print invalid for menu list also cos setMenuList returns a list of string[]
						}
						record.exportBranchData();
						System.out.println("Branch successfully closed");
					}
					else { //opening branch
						System.out.println("Opening new branch " + branchName + "(staff quota, location and menu are to be determined)");
					}
				}else {
					System.out.println("Branch not found");
				}
			}
		}else {
			System.out.println("Records and/or branch table not found");
		}
		
		
	}

}
