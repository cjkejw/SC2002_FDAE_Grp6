package customer;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.InputMismatchException;

import branches.Branch;
import orders.Order;
import recordInterfaces.BranchInterface;
import recordInterfaces.OrderInterface;
import customise.CustomiseSys;

public class NewOrder extends PrintingFunctionsForCustomer implements OrderInterface, BranchInterface{
	Scanner sc = new Scanner(System.in);
    private String branchName; 
	private String orderIDString;
	private List<String[]> newOrderList; 
	private Hashtable<String, List<Order>> orderTable;
	private Hashtable<String, Branch> branchTable;

    public NewOrder(String branchName) {
        this.orderTable = OrderInterface.getOrderTable();
        this.branchTable = BranchInterface.getBranchTable();
        this.branchName = branchName; 
        findNewOrderID(); 
        this.branch = branchTable.get(branchName);
        this.newOrderList = new ArrayList<>(); 
    }
    
    public String getOrderID() {
    	return orderIDString;
    }
    
    public Branch getBranch() {
    	return branch; 
    }
    
    public void findNewOrderID() {
    	int max = 0; 
    	List<Order> orders = orderTable.get(branchName); 
    	if (orders != null && !orders.isEmpty()) {
    		for (Order orderIdiv : orders) {
    			String id = orderIdiv.getOrderID(); 
    			int orderID = Integer.parseInt(id); 
    			if (orderID > max) {
    				max = orderID; 
    			}
    		}
    		int newOrderID = max + 1; 
    		String newOrderIDString = String.valueOf(newOrderID); 
    		this.orderIDString = newOrderIDString; 
    	}
    	else {
    		this.orderIDString = "1"; 
    	}
    	
    }
    
    public String diningOption() {
    	String diningOption; 
        while(true) {
        	System.out.println("Please select dining option:");
        	System.out.println("1. Dine-in");
        	System.out.println("2. Takeaway");
        	try {
        		int dineOpt = sc.nextInt();
                sc.nextLine(); // Consume newline
                
                switch(dineOpt) {
                case 1:
                	diningOption = "Dine-in"; 
                	System.out.println("Dine-in option selected.\n");
                	return diningOption; 
                case 2:
                	diningOption = "Takeaway"; 
                	System.out.println("Takeaway option selected.\n"); 
                	return diningOption; 
                default:
                	System.out.println("Invalid option, please reselect.");
                }
        	}
        	catch (InputMismatchException e) {
        		System.out.println("Invalid Choice! Choose again"); 
				sc.next();
        	}
        }
    }
    

    public void selectOrder() {
    	try {
    		if (branch != null) {
                List<String[]> menuList = branch.getMenuList();
                
                if (menuList != null && !menuList.isEmpty()) {
                    System.out.println("Select a category:");
                    List<String> categories = getCategoryList(branch);
                    for (int i = 0; i < categories.size(); i++) {
                        System.out.println((i + 1) + ". " + categories.get(i));
                    }
                    boolean first = true; 
                    int categoryIndex = 0; 
                    while (first) {
                    	try {
                    		categoryIndex = sc.nextInt();
                            sc.nextLine();
                            first = false; 
                    	}
                    	catch (InputMismatchException e) {
                    		System.out.println("Invalid Input! Choose again."); 
                    		sc.next(); 
                    	}
                    }

                    if (categoryIndex >= 1 && categoryIndex <= categories.size()) {
                        String selectedCategory = categories.get(categoryIndex - 1);
                        System.out.println("Category selected: " + selectedCategory);

                        // Display items from the selected category
                        System.out.println("Items in category " + selectedCategory + ":");
                        int count = 0; 
                        for (int i = 0; i < menuList.size(); i++) {
                            String[] menuItem = menuList.get(i);
                            if (menuItem[2].contains(selectedCategory)) {
                                System.out.println((count + 1) + ". " + menuItem[0] + " - $" + menuItem[1]);
                                count++; 
                            }
                        }

                        // Select items
                        System.out.println("Enter the item serial number :");
                        boolean second = true; 
                        int input = 0; 
                        while (second) {
                        	try {
                        		input = sc.nextInt(); 
                                sc.nextLine(); 
                               
                                
                                if (input > 0 && input <= count) {
                                	count = input; 
                                	for (int i = 0; i < menuList.size(); i++) {
                                		String[] menuItem = menuList.get(i); 
                                		if (menuItem[2].contains(selectedCategory)) {
                                			count--; 
                                			if (count == 0) {
                                				CustomiseSys customSys = new CustomiseSys(); 
                                				String[] menuItemUpdate = customSys.addingCustomisation(menuItem, selectedCategory); 
                                				newOrderList.add(menuItemUpdate); 
                   
                                				System.out.println("Order added Successfully"); 
                                				second = false; 
                                			}
                                		}
                                	}
                                }
                                else {
                                	System.out.println("Invalid Choice! Choose agian.");
                                }
                        	}
                        	catch (InputMismatchException e) {
                            	System.out.println("Invalid Input! Choose again."); 
                        		sc.next(); 
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
    	}
    	catch (InputMismatchException e) {
    		System.out.println("Invalid input! Choose again"); 
			sc.next(); 
    	}
        
    }
    
    
    
    public double calcTotalCost() {
    	double totalCost = 0.0; 
    	if (newOrderList.isEmpty()) {
    		return 0.0; 
    	}
    	for (String[] item : newOrderList) {
    		double price = Double.parseDouble(item[1]); 
    		totalCost += price; 
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
    
    
    public void removeOrder() {
    	if (newOrderList.isEmpty()) {
    		System.out.println("There are currently no items in your cart. Please add something!"); 
    		return;
    	}
    	
    	System.out.println("The items in your cart are: "); 
    	int num = printNewOrderList(newOrderList); 
    	System.out.println("Which item would you like to remove?\n If you would like to go back, press " + (num+1)); 
    	while (true) {
    		try {
        		int index = sc.nextInt();
            	sc.nextLine(); 
            	if (index > 0 && index <= num) {
               		newOrderList.remove(index-1); 
               		System.out.println("Successfully Removed."); 
               		return; 
               	}
               	else if (index == num + 1) {
               		System.out.println("Going back!"); 
               		return; 
               	}
               	else {
               		System.out.println("Invalid Choice. Choose again."); 
               	}
           	} 
        	catch (InputMismatchException e) {
        		System.out.println("Invalid input! Choose again"); 
    			sc.next(); 
        	}
    	}   	
    }
    
    public int printReceipt(double cost) {
    	super.printReceipt(cost, newOrderList, orderIDString); 
    	return 1; 
    }
    
    public void addOrderToTable(String diningOption) {
        // Get the order table

        // Retrieve the list of orders for the specified branch
        List<Order> orders = orderTable.get(branchName);
        
        Order newOrder = new Order(orderIDString, newOrderList, "Pending", diningOption);

        // Add the new order to the list
        orders.add(newOrder);
        orderTable.put(branchName, orders); 

        // Export the updated order table
        OrderInterface.exportOrderTable(orderTable);
    }
	
}