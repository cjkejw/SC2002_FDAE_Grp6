package Custmr;

import branches.Branch;
import orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    String orderID = null;
	    
	    while(true) {
	    System.out.print("Select Action: \n");
	    System.out.println("1. New Order ");
	    System.out.println("2. Search Order Progress ");
	    int action = sc.nextInt();
	    sc.nextLine(); // Consume newline
	    switch(action) {
	    
	    case 1:
	    newOrder orderHandler = new newOrder();
	    orderID = newOrder.getOrderID();
	    	
	    // Choose a branch
	    System.out.print("Select branch: \n");
	    System.out.println("1. JE ");
	    System.out.println("2. JP ");
	    System.out.println("3. NTU ");
	    int choice = sc.nextInt();
	    sc.nextLine(); // Consume newline
	    String branchName;
	    switch (choice) {
	        case 1:
	            branchName = "JE";
	            break;
	        case 2:
	            branchName = "JP";
	            break;
	        case 3:
	            branchName = "NTU";
	            break;
	        default:
	            System.out.println("Invalid choice.");
	            return; // Exit the program if the choice is invalid
	    }

	    Branch branch = orderHandler.chooseBranch(branchName);
	    orderHandler.printBranchMenu(branch);

	    // Select orders
	    double totalCost = orderHandler.selectOrder(branch);


        // Gather order items
        List<String[]> orderItems = new ArrayList<>();
        System.out.println("Enter order items (type 'done' to finish):");
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            String[] itemArray = input.split(","); // Split input string into an array of strings
            orderItems.add(itemArray);
        }
        
        // Process payment
        orderHandler.toPay(totalCost);

	    // Save the order
        Order order = new Order(orderID, orderItems, "Preparing");
        orderHandler.addOrderToTable(branchName, order);

	    // Print receipt
        orderHandler.printReceipt(order, totalCost);
        
        break;
	
	    
	    case 2:
	        searchOrderProgress search = new searchOrderProgress();
	        System.out.println("Enter the order ID to search:");
	        orderID = sc.nextLine();
	        search.orderProgress(orderID);
	    	break;
	    
	    default:
		    System.out.println("Invalid selection, please reselect.");
	    	break;
	 
	}
	}
	}

}
