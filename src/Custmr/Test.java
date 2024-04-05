package Custmr;

import java.util.Scanner; 
import branches.Branch; 
import java.util.ArrayList;
import java.util.List; 

public class Test {

	public static void main(String[] args) {
		// Create a CustSys object
	     CustSys custSys = new CustSys();
	     Scanner sc = new Scanner(System.in);

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

	     Branch branch = custSys.chooseBranch(branchName);

	     // Print branch menu
	     custSys.printBranchMenu(branch);

	     // Select orders
	     double totalCost = custSys.selectOrder(branch);

	     // Gather order items
	     List<String> orderItems = new ArrayList<>();
	     System.out.println("Enter order items (type 'done' to finish):");
	     String item;
	     while (true) {
	         item = sc.nextLine();
	         if (item.equalsIgnoreCase("done")) {
	             break;
	         }
	         orderItems.add(item);
	     }

	     // Save the order
	     custSys.saveOrder(branch, orderItems, totalCost);

	     // Print receipt
	     custSys.printReceipt(totalCost);
	 }

}


