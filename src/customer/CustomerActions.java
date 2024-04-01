package customer;

import loggingInPerson.CustomerLogin;
import java.util.Scanner; 

public class CustomerActions {
	private String branch;
	
	Scanner scan = new Scanner(System.in);
	
	public CustomerActions() {
		CustomerLogin customer = new CustomerLogin();
		this.branch = customer.selectBranch(); 
	}
	
	public void Actions() {
		System.out.println("What would you like to do: \n\t1. Create an order\n\t2. View Current Order Status"); 
		int choice = scan.nextInt(); 
		while (true) {
			if (choice == 1) {
				PostOrder order = new PostOrder(branch); 
				System.out.println("Your OrderID is " + order.getOrderID() +" in " + branch);
				System.out.println(""); 
				order.printMenu();
				System.out.println("What do you wish to do: \n\t1. Add to order\n\t2. Delete from order\n\t3. Checkout"); 
				innerChoice(order); 
				break; 
			}
			
		}
	}
	
	public void innerChoice(PostOrder order) {
		int choice = scan.nextInt(); 
		while (true) {
			if (choice < 0 || choice > 3) {
				System.out.println("Invalid Input. Please input Againt"); 
				choice = scan.nextInt();  
			}
			else if (choice == 1) {
				order.selectingItemtoInesert();
			}
			else if (choice == 2) {
				order.selectItemtoDelete();
			}
			else if (choice == 3) {
				int num = order.viewOrderList(); 
				if (num == 0) {
					System.out.println("There are no items to checkout. Please add items to your cart first.\n");
					System.out.println("What do you wish to do: \n\t1. Add to order\n\t2. Delete from order\n\t3. Checkout");
					choice = scan.nextInt(); 
				}
				else { 
					order.viewCart();
					order.receipt();
					break; 
				}
				
			}
			System.out.println("What do you wish to do: \n\t1. Add to order\n\t2. Delete from order\n\t3. Checkout");
			choice = scan.nextInt(); 
		}
	}
}
