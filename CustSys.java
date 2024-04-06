package Custmr;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.Map;
import branches.Branch;
import importing.Initializer;
import importing.Record;
import orders.Order;
import people.Person;

public class CustSys {
	Scanner sc = new Scanner(System.in);
    private Record record;
	private int customerID;
	private boolean ordered;

    public CustSys() {
        record = new Record();
        customerID = 0;
        ordered = false;
    }
	

    public void searchOrderProgress(String customerID) {
        // Create an instance of Initializer to access its Hashtable
        Initializer initializer = new Initializer();
        
        // Get the Hashtable containing orders
        Hashtable<String, List<Order>> orderTable = initializer.getOrderTable();
        
        // Search for the customer's order progress
        boolean orderFound = false;
        for (String branchID : orderTable.keySet()) {
            List<Order> orders = orderTable.get(branchID);
            for (Order order : orders) {
                if (order.getOrderID().equals(customerID)) {
                    System.out.println("Order ID: " + order.getOrderID());
                    System.out.println("Order Status: " + order.getOrderStatus());
                    orderFound = true;
                    break;
                }
            }
            if (orderFound) {
                break;
            }
        }
        
        if (!orderFound) {
            System.out.println("No orders found for customer ID: " + customerID);
        }
    }
    
    public Branch chooseBranch(String branchName) {
        return record.getBranchInstance(branchName);
    }
    
    public void printBranchMenu(Branch branch) {
        if (branch != null) {
            System.out.println("Menu for branch " + branch.getLocation() + ":");
            for (String[] menuItem : branch.getMenuList()) {
                System.out.println(menuItem[0] + ": $" + menuItem[1]); // Assuming the name and price are stored in index 0 and 1 respectively
            }
        }else {
            System.out.println("Branch not found.");
        }
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
                            System.out.println((i + 1) + ". " + menuItem[0] + " - $" + menuItem[1]); // Assuming name and price are stored in index 0 and 1 respectively
                        }
                    }

                    // Select items
                    System.out.println("Enter the item numbers separated by commas (e.g., 1,2,3):");
                    String input = sc.nextLine();
                    String[] selectedItems = input.split(",");

                    // Calculate total cost
                    for (String selectedItem : selectedItems) {
                        int index = Integer.parseInt(selectedItem.trim()) - 1;
                        if (index >= 0 && index < menuList.size()) {
                            String[] menuItem = menuList.get(index);
                            totalCost += Double.parseDouble(menuItem[1]); // Assuming price is stored at index 1
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
    
    // Method to save order to a specific text file for each branch
    public void saveOrder(Branch branch, List<String> orderItems, double totalCost) {
        if (branch != null && orderItems != null && !orderItems.isEmpty()) {
            String branchName = branch.getLocation();
            String fileName = getFileNameForBranch(branchName);
            try (FileWriter writer = new FileWriter(fileName, true)) { // Append mode enabled
                writer.write("Order for Branch: " + branchName + "\n");
                for (String item : orderItems) {
                    writer.write(item + "\n");
                }
                writer.write("Total cost: $" + totalCost + "\n");
                System.out.println("Order saved successfully to " + fileName);
            } catch (IOException e) {
                System.out.println("Failed to save order: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid branch or order items.");
        }
    }

    // Method to get the file name for a specific branch
    private String getFileNameForBranch(String branchName) {
        switch (branchName) {
            case "JE":
                return "JE_order_text.txt";
            case "JP":
                return "JP_order_text.txt";
            case "NTU":
                return "NTU_order_text.txt";
            default:
                throw new IllegalArgumentException("Invalid branch name: " + branchName);
        }
    }
    
    
    public void printReceipt(double totalCost) {
        System.out.println("Receipt for " + customerID + ":");
        System.out.println("Total cost: $" + totalCost);
    }
	
}
