package Custmr;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Hashtable;
import branches.Branch;
import importing.Record;
import orders.Order;

public class newOrder {
	Scanner sc = new Scanner(System.in);
    private Record record;
    private PaymentSys paymentSys;
	private static int orderID=0;
	static String orderIDString;

    public newOrder() {
        record = new Record();
        paymentSys = new PaymentSys();
        orderID = ++orderID;
        this.orderIDString = String.valueOf(orderID);
    }
    
    public static String getOrderID() {
    	return orderIDString;
    }
        
    public Branch chooseBranch(String branchName) {
        return record.getBranchInstance(branchName);
    }
    
    //printBranchMenu is for checking purpose
    public void printBranchMenu(Branch branch) {
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
    
    //may need to edit order file
    public void diningOption() {
    	int abc= 0;
        while(abc==0) {
        	System.out.println("Please select dining option:");
        	System.out.println("1. Dine-in");
        	System.out.println("2. Takeaway");
        	int dineOpt = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            switch(dineOpt) {
            case 1:
            	System.out.println("Dine-in option selected.");
            	abc=1;
            	break;
            case 2:
            	System.out.println("Takeaway option selected.");
            	abc=1;
            	break;
            default:
            	System.out.println("Invalid option, please reselect.");
            }
        }
    }
    
    public void customiseOrder() {
    	System.out.println("Customisations to order?");
    	//drink - pepsi no ice?
    	//WHAT ELSE ARE THERE
    }

    public double selectOrder(Branch branch) {
        double totalCost = 0.00;
        if (branch != null) {
            List<String[]> menuList = branch.getMenuList();
            if (menuList != null && !menuList.isEmpty()) {
                System.out.println("Select a category:");
                List<String> categories = getCategoryList(branch);
                for (int i = 0; i < categories.size(); i++) {
                    System.out.println((i + 1) + ". " + categories.get(i));
                }
                int categoryIndex = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (categoryIndex >= 1 && categoryIndex <= categories.size()) {
                    String selectedCategory = categories.get(categoryIndex - 1);
                    System.out.println("Category selected: " + selectedCategory);

                    // Display items from the selected category
                    System.out.println("Items in category " + selectedCategory + ":");
                    for (int i = 0; i < menuList.size(); i++) {
                        String[] menuItem = menuList.get(i);
                        if (menuItem[2].contains(selectedCategory)) {
                            System.out.println((i + 1) + ". " + menuItem[0] + " - $" + menuItem[1]);
                        }
                    }

                    // Select items
                    System.out.println("Enter the item serial numbers shown, separated by commas (e.g., 1,2,3):");
                    String input = sc.nextLine();
                    String[] selectedItems = input.split(",");

                    // Calculate total cost
                    for (String selectedItem : selectedItems) {
                        int index = Integer.parseInt(selectedItem.trim()) - 1;
                        if (index >= 0 && index < menuList.size()) {
                            String[] menuItem = menuList.get(index);
                            totalCost += Double.parseDouble(menuItem[1]);
                            System.out.println("Added " + menuItem[0] + " to your order. Current total: $" + totalCost);
                        } else {
                            System.out.println("Invalid item number: " + selectedItem);
                        }
                    }
                } else {
                    System.out.println("Invalid category selection.");
                }
            } else {
                System.out.println("No menu items available.");
            }
        } else {
            System.out.println("Branch not found.");
        }
        return totalCost;
    }

    // Method to get a list of categories
    private List<String> getCategoryList(Branch branch) {
        List<String> categories = new ArrayList<>();
        for (String[] menuItem : branch.getMenuList()) {
            String[] itemCategories = menuItem[2].split(",");
            for (String category : itemCategories) {
                String trimmedCategory = category.trim();
                if (!categories.contains(trimmedCategory)) {
                    categories.add(trimmedCategory);
                }
            }
        }
        return categories;
    }
    
    public void toPay(double totalCost) {
        paymentSys.selectPaymentMethod();
        boolean paymentSuccessful = paymentSys.processPayment(paymentSys.getPaymentMethod(), totalCost);
        if (paymentSuccessful) {
            System.out.println("Payment successful! Proceed to print receipt.");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }
    
    //only if payment is successful
    public void printReceipt(Order order, double totalCost) {
        System.out.println("Receipt for " + orderID + ":");
        System.out.println("Order Items:");
        for (String[] menuItem : order.getMenuList()) {
            //System.out.println("- Item: " + menuItem[0] + " ------- Price: $" + menuItem[1]);
            if (menuItem.length >= 2) {
                System.out.println("- Item: " + menuItem[0] + " ------- Price: $" + menuItem[1]);
            } else {
                System.out.println("Invalid menuItem array: " + Arrays.toString(menuItem));
            }
        }
        System.out.println("Total cost: $" + totalCost);
    }
    
    public void addOrderToTable(String branchName, Order order) {
        // Get the order table
        Hashtable<String, List<Order>> orderTable = record.getOrderTable();

        // Retrieve the list of orders for the specified branch
        List<Order> orders = orderTable.get(branchName);

        // If no orders exist for this branch, create a new list
        if (orders == null) {
            orders = new ArrayList<>();
            orderTable.put(branchName, orders);
        }

        // Add the new order to the list
        orders.add(order);

        // Export the updated order table
        record.exportOrderData();
    }
	
}
