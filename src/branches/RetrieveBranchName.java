package branches;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner; 
import recordInterfaces.BranchInterface;

import java.util.Set; 

public class RetrieveBranchName implements BranchInterface {
	private Hashtable<String, Branch> branchTable;
	private Branch branchInst; 
	private String key; 
	private String fullName; 
	
	Scanner scan = new Scanner(System.in); 
	
	public RetrieveBranchName() {
		this.branchTable = BranchInterface.getBranchTable(); 
	}
	
	public String chooseBranch() {
		System.out.println("What branch are you choosing?"); 
		Set<String> names = branchTable.keySet(); 
		int count = 0; 
		for (String name : names) {
			if (branchTable.get(name).getStatus().equals("OPEN")) {
				count++; 
				System.out.println(count + ". " + name);
			} 
		}
		
		while (true) {
			try {
				int choice = scan.nextInt(); 
				scan.nextLine();
				
				while (true) {
					if (choice > 0 && choice <= count) {
						count = 1; 
						for (String name : names) {
							if (count == choice) {
								key = name; 
								return name; 
							}
							count++; 
						}
					}
					else {
						System.out.println("Invalid Choice. Choose again."); 
						choice = scan.nextInt(); 
					}
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again"); 
				scan.next(); 
			}
		}
	}
	
	public String getFullName() {
		branchInst = branchTable.get(key);
		fullName = branchInst.getLocation(); 
		return fullName; 
	}
	
	
}
