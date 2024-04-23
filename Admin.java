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
	
	//for hiring new staff
	//ok what parameters should we use....
	//add the staff acc to the person table
	public void addStaffAcc(String branchName, String staffName, String staffRole, String staffGender, String staffAge, String staffPW) {
		if(record != null) {
			System.out.println("ADDING STAFF ACCOUNT:");
			
			for(String key : indivStaffInfo.keySet()) {
				if(key.equalsIgnoreCase(branchName)) {
					Person staff = indivStaffInfo.get(key);
					staff.setName(staffName);
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
	
	//for firing staff
	public void removeStaffAcc(String branchName, String staffName){
		if(record != null) {
			System.out.println("REMOVING STAFF ACCOUNT:");
			
			for(String key : indivStaffInfo.keySet()) {
				if(key.equalsIgnoreCase(branchName)) {
					Person staff = indivStaffInfo.get(key);
					
					if(staff.getName() == staffName) {
						staff.setName("Invalid");
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
			record.exportPersonData(indivStaffInfo);
			System.out.println("Staff account successfully removed");
			
		}else {
			System.out.println("Records not found");
		}
	}
	
	//edit staff info (e.g.name, age, password)
	//will only go to that specific if statement to edit IF the personal info is different
	//if you dont wanna edit that particular information, key in the existing info
	public void editStaffAcc(String branchName, String staffName, String newName, String newAge, String newPW) {
		if(record != null) {
			System.out.println("EDITTING STAFF PERSONAL INFORMATION:");
			if(indivStaffInfo != null) {
				for(String key : indivStaffInfo.keySet()) {
					if(key.equalsIgnoreCase(branchName)) {
						Person staff = indivStaffInfo.get(key);
						
						//only if it's the same name then you can start editting their personal info
						if(staff.getName().equalsIgnoreCase(staffName)) {
							if(!staff.getName().equalsIgnoreCase(newName)) {
								staff.setName(newName);
								System.out.println("Staff " + staffName + " changed name to " + newName);
								//break;
							}else if(!staff.getAge().equalsIgnoreCase(newPW)) {
								staff.setAge(newAge);
								System.out.println("Staff " + staffName + " changed age to " + newAge);
								//break;
							}else if(!staff.getPassword().equalsIgnoreCase(newPW)) {
								staff.setPassword(newPW);
								System.out.println("Staff " + staffName + " changed password to " + newPW);
								//break;
							}
						}
						record.exportPersonData(indivStaffInfo);
						System.out.println("Staff personal information successfully editted");
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
	
	//promote staff to manager
	public void promote(String branchName, String staffName) {
		if(record != null) {
			if(indivStaffInfo != null) {
				for(String key : indivStaffInfo.keySet()) {
					if(key.equalsIgnoreCase(branchName)) {
						Person staff = indivStaffInfo.get(key);
						
						if(staff.getRole().equalsIgnoreCase("S")) {
							staff.setRole("M");
							break;
						}
						record.exportPersonData(indivStaffInfo);
						System.out.println("Staff " + staffName + " successfully promoted to Branch Manager");
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
	
	//transfer regStaff among branches
	//need to update staffquota
	//need use branch table and person table
	public void transfer(String branchName, String newBranch, String staffName) {
		if(record != null) {
			if(indivStaffInfo != null && indivBranchInfo != null) {
				for(String key : indivStaffInfo.keySet()) {
					if(key.equalsIgnoreCase(branchName)) {
						Person staff = indivStaffInfo.get(key);
						Branch branch = indivBranchInfo.get(key);						
						if(staff.getName().equalsIgnoreCase(staffName)) {
							staff.setBranch(newBranch);
							branch.setStaffQuota(branch.getStaffQuota() + 1);
							break;
						}
						record.exportBranchData();
						record.exportPersonData(indivStaffInfo);
						System.out.println("Staff sucessfully transferred from " + branchName + " to " + newBranch);
					}else {
						System.out.println("Branch not found");
					}
				}
				
			}else {
				System.out.println("Staff and branch table not found");
			}
		}else {
			System.out.println("Records not found");
		}
	}
	
	//filter by branch
	public void displayStaffListByBranch(String branchName) {
		if(record != null) {
			if(indivStaffInfo != null) {
				for(String key : indivStaffInfo.keySet()) { //traversing the key portion of the hashtable
					Person staff = indivStaffInfo.get(key); //retrieving the value portion of the hashtable
					
					if(key.equalsIgnoreCase(branchName)) {
						System.out.println("Staff list of branch" + key + ":");
						System.out.println("\t" + staff.getName());
						//break;
					}else {
						System.out.println("Branch not found");
					}
					System.out.println("Staff filtered correctly by branch");
				}
			}else {
				System.out.println("Staff not found");
			}
		}else {
			System.out.println("Records not found");
		}
	}
	
	//filter by role, gender, age (need to traverse only the value portion, without caring about the key portion)
	//role must be specified whether it's M, S, A somewhere else, maybe in the main?? or smth 
	//can it be like if you only want to filter by role and not gender and age right, then you just put some invalid gender and age as parameters thent he code just wont run that if or else if statement
	//filtered by age i do age range, i do minAge and maxAge then the user can choose the age range they want
	
	public void displayStaffListByRGA(String role, String gender, int minAge, int maxAge) {
		if(record != null) {
			if(indivStaffInfo != null) {
				Iterator<Person> iterator = indivStaffInfo.values().iterator();
				
				//filter flags to indicate which filter should be applied (each flag is set to be true if it fulfills the criteria provided in the brackets)
				boolean roleFilter = (role != null && !role.isEmpty());
				boolean genderFilter = (gender != null && !gender.isEmpty());
				boolean ageFilter = (minAge>=16 && maxAge>16 && maxAge>minAge);
				
				while(iterator.hasNext()) {
					Person staff = iterator.next();
					int age = Integer.parseInt(staff.getAge());
				
					if(roleFilter && staff.getRole().equalsIgnoreCase(role)) {
						System.out.println("Staff list of all " + staff.getRole() + ":");
						System.out.println("\t" + staff.getName());
					}else if(genderFilter && staff.getGender().equalsIgnoreCase(gender)) {
						System.out.println("All " + staff.getGender() + " staff:");
						System.out.println("\t" + staff.getName());
					}else if(ageFilter && age > minAge && age <= maxAge) {
						System.out.println("All staff between the age of " + minAge + " and " + maxAge + " (inclusive):");
						System.out.println("\t" + staff.getName());
					}
				}
			}else {
				System.out.println("Staff not found");
			}
			System.out.println("Staff filtered correctly by role/ gender/ age");
		}else {
			System.out.println("Records not found");
		}
	}
	
	public void assignManager(String managerName, String branchName, String newBranch) {
		if(record != null) {
			if(indivStaffInfo != null && indivBranchInfo != null) {
				
			}else {
				System.out.println("Staff and branch table not found");
			}
		}else {
			System.out.println("Records not found");
		}
		
	}
	
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
		if(record != null) {
			if(indivBranchInfo != null) {
				//closing branch
				for(String key : indivBranchInfo.keySet()) {
					if(key.equalsIgnoreCase(branchName)){
						Branch branch = indivBranchInfo.get(key);
						if(action == false) {
							System.out.println("Closing branch " + branchName + ":");
							branch.setStaffQuota("Invalid staff quota");
							branch.setLocation("Invalid location");
							branch.setMenuList(null);
							if(branch.getMenuList() == null) {
								System.out.println("Invalid menu"); //putting this if statement here so that it can print invalid for menu list also cos setMenuList returns a list of string[]
							}
							record.exportBranchData();
							System.out.println("Branch successfully closed");
						}
						else {
							System.out.println("Opening new branch " + branchName + "(staff quota, location and menu are to be determined)");
						}
					}else {
						System.out.println("Branch not found");
					}
				}
			}else {
				System.out.println("Branch not found");
			}
		}else {
			System.out.println("Records not found");
		}
		
		
	}

}
