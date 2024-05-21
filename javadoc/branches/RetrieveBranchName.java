package branches;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner; 
import recordInterfaces.BranchInterface;

import java.util.Set; 

/**
 * This RetireveBranchName class implements the BranchInterface 
 * It provides methods to select and retrieve information of a branch from the hash tables of branches
 * @author FDAE Team 6
 */

public class RetrieveBranchName implements BranchInterface {
	private Hashtable<String, Branch> branchTable;
	private Branch branchInst; 
	private String key; 
	private String fullName; 
	
	Scanner scan = new Scanner(System.in); 
	
	/**
	 * Below is a constructor to construct a new RetrieveBranchObject
	 * It initializes the branch table using the getBranchTable() method from BranchInterface
	 */
	public RetrieveBranchName() {
		this.branchTable = BranchInterface.getBranchTable(); 
	}
	
	/**
	 * Below is a method that displays a list of available branches (with status "OPEN")
	 * It allows user to choose one branch by entering an integer
	 * @return The name of the chosen branch
	 */
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
	
	/**
	 * Below is a method that retrieves the location of the selected branch
	 * It uses the key (branch name) obtained from chooseBranch() 
	 * @return The name of the location of the selected branch
	 */
	public String getFullName() {
		branchInst = branchTable.get(key);
		fullName = branchInst.getLocation(); 
		return fullName; 
	}
	
	
}