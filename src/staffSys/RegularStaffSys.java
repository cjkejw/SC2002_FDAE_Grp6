package staffSys;

import java.util.InputMismatchException;
import java.util.Scanner;

import people.Person;


public class RegularStaffSys {
	private String name;
	private RegStaff regStaff;
	private String branchName; 
	
	Scanner scan = new Scanner(System.in);
	
	public RegularStaffSys(Person person) {
		this.name = person.getName(); 
		this.branchName = person.getBranch(); 
		this.regStaff = new RegStaff(person, branchName);
	}
	
	public void options() {
		System.out.println("WELCOME " + name);
		
		while(true) {
			System.out.println("\nWhat would you like to do: \n"
					+ "1. Display new orders\n"
					+ "2. View order details\n"
					+ "3. Process order\n"
					+ "4. Log out\n");
			
			try {
				int choice = scan.nextInt();
				scan.nextLine();
				
				if(choice == 1) {
					regStaff.displayNewOrders(branchName);
				}
				else if(choice == 2) {
					regStaff.viewOrderDetails(branchName);
				}
				else if(choice == 3) {
					regStaff.processOrder(branchName);
				}
				else if(choice == 4) {
					System.out.println("Logging out now...");
					return;
				}
				else {
					System.out.println("Invalid Choice! Choose again.\n"); 
				}
			}catch(InputMismatchException e) {
				System.out.println("Invalid input! Choose again\n");
				scan.next();
			}
		}
	}
	
}