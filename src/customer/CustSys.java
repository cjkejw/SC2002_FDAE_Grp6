package customer;

import java.util.InputMismatchException;
import java.util.Scanner; 

import payment.PaymentSys;

public class CustSys {
	private String branchName; 
	private String fullBranchName; 
	
	public CustSys(String branchName, String fullBranchName) {
		this.fullBranchName = fullBranchName; 
		this.branchName = branchName; 
	}
	
	Scanner scan = new Scanner(System.in); 
	
	
	public void customerSystem() {
		System.out.println("WELCOME to " + fullBranchName + "\n");
		while (true) {
			try {
				System.out.println("What would you like to do: \n1. Create Order \n2. View Order Progress \n3. Collect Order\n4. Go back"); 
				int input = scan.nextInt();
				scan.nextLine(); 
				
				if (input == 1) {
					System.out.println("Creating order...\n"); 
					NewOrder order = new NewOrder(branchName); 
					String dining = order.diningOption();
					while (true) {
						System.out.println("Would you like to: \n1. Add order \n2. Remove item \n3. Checkout");
						try {
							int choice = scan.nextInt(); 
							scan.nextLine(); 
							if (choice == 1) {
								order.selectOrder();
							}
							else if (choice == 2) {
								order.removeOrder(); 
							}
							else if (choice == 3) {
								System.out.println("Going to CheckOut..."); 
								double cost = order.calcTotalCost(); 
								if (cost == 0.0) {
									System.out.println("The cart is EMPTY. Please add an item before checking out!"); 
									
								}
								else {		
									PaymentSys pay = new PaymentSys(); 
									pay.toPay(cost);
									System.out.println("HERE IS THE RECEIPT"); 
									order.printReceipt(cost); 
									order.addOrderToTable(dining);
									return; 
								}
							}
						}
						catch (InputMismatchException e) {
							System.out.println("Invalid Input! Choose again."); 
							scan.next(); 
						}
					}
				}
				else if (input == 2) {
					System.out.println("Viewing Order Status...\nWhat is your orderID?");
					try {
						int ID = scan.nextInt(); 
						scan.nextLine(); 
						String IDstring = String.valueOf(ID); 
						SearchOrderProgress search = new SearchOrderProgress(); 
						search.orderProgress(branchName, IDstring);
					}
					catch (InputMismatchException e) {
						System.out.println("Invalid Input! Choose again."); 
						scan.next(); 
					}
					
				}
				else if (input == 3) {
					CollectingOrder collect = new CollectingOrder(); 
					if (collect.collectOrder(branchName)) {
						return; 
					}
				}
				else if (input == 4) {
					System.out.println("Going back to menu...\n"); 
					return;
				}
				else {
					System.out.println("Invalid Choice. Choose again."); 
				}
				
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}	
	
}
