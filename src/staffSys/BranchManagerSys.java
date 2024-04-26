package staffSys;

import java.util.InputMismatchException;
import java.util.Scanner;

import dependencyInterface.PersonDependency;
import people.Person;


public class BranchManagerSys implements PersonDependency { 
	private BranchManager manager;
	private Person managerPerson; 
	
	Scanner scan = new Scanner(System.in);
	public BranchManagerSys(Person person) { 
		this.managerPerson = person; 
		this.manager = new BranchManager(person, managerPerson.getBranch());
	}
	
	public void options() {
		System.out.println("WELCOME " + managerPerson.getName());
		while(true) {
			System.out.println("\nWhat would you like to do: \n"
					+ "1. Display staff list of specific manager\n"
					+ "2. Add item to menu\n"
					+ "3. Remove item from menu\n"
					+ "4. Edit item price\n"
					+ "5. Display new orders\n"
					+ "6. View order details\n"
					+ "7. Process order\n"
					+ "8. Log out\n");
			
			try {
				int input = scan.nextInt();
				scan.nextLine();
				
				if(input == 1) {
					manager.displayStaffList(managerPerson.getBranch());
				}
				else if(input == 2) {
					manager.addItem();
				}
				else if(input == 3) {
					manager.removeItem();
				}
				else if(input == 4) {
					manager.editPrice();
				}
				else if (input == 5) {
					manager.displayNewOrders(managerPerson.getBranch());
				}
				else if (input == 6) {
					manager.viewOrderDetails(managerPerson.getBranch());
				}
				else if (input == 7) {
					manager.processOrder(managerPerson.getBranch());
				}
				else if(input == 8) {
					System.out.println("Logging out now...");
					return;
				}
				else {
					System.out.println("Invalid Choice! Choose again.\n"); 
				}
			}catch(InputMismatchException e){
				System.out.println("Invalid Input! Choose again.\n");
				scan.next();
				
			}
		}
	}
	

}