package customer;

import java.util.List;

import branches.Branch;

public class PrintingFunctionsForCustomer {
	
	protected Branch branch; 
	
	public int printNewOrderList(List<String[]> newOrderList) {
    	int count = 1; 
    	for (String[] item : newOrderList) {
    		double price = Double.parseDouble(item[1]);
    		System.out.printf("\t" + count + ". %s ------ $%.2f\n", item[0], price);  
        	count++; 
        	for (int index = 3; index < item.length; index++) {
        		System.out.println("\t - " + item[index]); 
        	}
    	}
    	return count - 1; 
    }
	
	public int printReceipt(double totalCost, List<String[]> newOrderList, String orderIDString) {
    	if (newOrderList.isEmpty()) {
    		System.out.print("The cart is empty. Please add some items first."); 
    		return 0; 
    	}
        System.out.println("Receipt for OrderID " + orderIDString + ":");
        System.out.println("Order Items:");
		printNewOrderList(newOrderList); 
        System.out.printf("Total Cost: $%.2f\n", totalCost); 
        return 1; 
    }
	
	//printBranchMenu is for checking purpose
    public void printBranchMenu() {
        if (branch != null) {
        	System.out.println("Branch " + branch.getLocation());
            System.out.println("Menu for branch " + branch.getLocation() + ":");
            for (String[] menuItem : branch.getMenuList()) {
                System.out.println("Item: " + menuItem[0] + "; Price: $" + menuItem[1] + "; Type: " + menuItem[2]);
            }
        }else {
            System.out.println("Branch not found.");
        }
    }
	
}
