package loggingInPerson;

import java.util.Scanner; 
import Importing.ImportBranch; 

public class CustomerLogin {
	private String branch; 
	private int orderID; 
	private String[][] BranchArray; 
	
	
	public CustomerLogin() {
		ImportBranch branchData = new ImportBranch(); 
		this.BranchArray = branchData.readBranchData();
	}
	
	Scanner scan = new Scanner(System.in);
	
	public String getBranch() {
		return branch; 
	}
	
	public int getOrderID() {
		return orderID; 
	}
	
	public String selectBranch() {
		boolean outcome = false; 
		String branchInput = null; 
		
		while (!outcome) {
			System.out.println("Choose the branch: "); 
			boolean first = true; 
			
			if (BranchArray != null) {
				for (String[] row : BranchArray) {
					if (first) {
						first = false; 
					}
					else {
						System.out.println("\t" + row[0]);
					}
				}
			}
			else {
				System.out.println("No data to read");
				return null; 
			}
			
			branchInput = scan.next(); 
			outcome = checkData(branchInput); 
			
			if (!outcome) {
				System.out.println("Invalid input, please try again."); 
			}
		}
		
		this.branch = branchInput.toUpperCase(); 
		return branch; 
		
	}
	
	public boolean checkData(String input) {
		if (BranchArray == null || BranchArray.length == 0) {
			return false; 
		}
		
		input = input.toUpperCase(); 
		
		for (String[] row : BranchArray) {
			if (row.length > 0 && row[0].equals(input)) {
				return true; 
			}
		}
		
		return false; 
	}
	
}
