package login;

import java.util.InputMismatchException;
import java.util.Scanner; 

import branches.RetrieveBranchName;

public class Login {
	
	private String branchName; 
	private String fullBranchName; 
	public String personOverall; 
	private RetrieveBranchName branching; 
	
	public Login() {
		this.branching = new RetrieveBranchName(); 
		this.branchName = branching.chooseBranch(); 
		this.fullBranchName = branching.getFullName(); 
	}
	
	Scanner scan = new Scanner(System.in);
	
	public void choosingStafforCustomer() {
		System.out.println("Are you a: \n1. Staff Member\n2. Customer"); 
		while (true) {
			try {
				int option = scan.nextInt(); 
				scan.nextLine(); 
				if (option == 1) {
					this.personOverall = "Staff"; 
					return; 
				}
				else if (option == 2) {
					this.personOverall = "Customer"; 
					return; 
				}
				else {
					System.out.println("Invalid Choice. Choose again."); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Option! Choose again."); 
				scan.next(); 
			}
		}
	}
	
	public String getPersonOverall() {
		return personOverall; 
	}
	
	public String getBranchName() {
		return branchName; 
	}
	
	public String getFullBranchName() {
		return fullBranchName; 
	}
	
	
}
