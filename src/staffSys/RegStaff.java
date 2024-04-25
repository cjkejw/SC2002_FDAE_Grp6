package staffSys;

import orders.Order;
import people.Person;

import recordInterfaces.OrderInterface;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import dependencyInterface.OrderDependency;
import dependencyInterface.PersonDependency;

public class RegStaff implements OrderInterface, OrderDependency, PersonDependency {
	
	protected Person person; 
	protected String branchName; 
	protected Hashtable<String, List<Order>> orderTable; 
	
	Scanner scan = new Scanner(System.in) ;
	
	public RegStaff(Person person, String branchName) {
		this.person = person; 
		orderTable = OrderInterface.getOrderTable(); //get the hashtable of all the orders from all the branches 
		this.branchName = branchName; 
	}

	
	public void displayNewOrders(String branchName) {
		if(orderTable != null) {
			System.out.println("NEW ORDERS: ");
			List<Order> detailsOfOrders = orderTable.get(branchName); //retrieve the value portion of that specific key
						
					//traversing the Order array that contains the order details and print the order ID for those orders
			if(detailsOfOrders != null) {
				for(Order elem : detailsOfOrders) {
					if (elem.getOrderStatus().equals("Pending")) {
						System.out.println("Order ID: " + elem.getOrderID());
					}
				}
			}
			else {
				System.out.println("Orders not found in this branch: " + branchName);
			}					
		}
		else {	
			System.out.println("orderTable not found");
		}			
	}		

	
	public void viewOrderDetails(String branchName) {
		this.orderTable = OrderInterface.getOrderTable(); 
		if(orderTable != null) {
			System.out.println("What order ID do you want to view: "); 
			int orderID = scan.nextInt();
			scan.nextLine();
			System.out.println("ORDER DETAILS OF ORDER ID " + orderID + ": ");
			List<Order> detailsOfOrders = orderTable.get(branchName);
			if(detailsOfOrders != null) {
				for(Order elem : detailsOfOrders) {
					if(Integer.parseInt(elem.getOrderID()) == orderID) {
						System.out.println("Order ID: " + elem.getOrderID());
							
						List<String[]> itemsInOrder = elem.getMenuList();
						if(itemsInOrder != null) {
							for(String[] itemList : itemsInOrder) {
								int total = itemList.length; 
								if(itemList.length >= 3) {
									String item = itemList[0];
									String price = itemList[1];
									String type = itemList[2];
									System.out.println("Item: " + item);
									for (int i = 3; i < total; i++) {
										System.out.println("      - " + itemList[i]); 
									}
									double priceDouble = Double.parseDouble(price); 
									System.out.printf("Price: $%.2f\n", priceDouble);
									System.out.println("Type: " + type);
												
								}
								else {
									System.out.println("Invalid item format");
								}
							}
						}
						else {
							System.out.println("Items not found");
						}
						System.out.println("Order status: " + elem.getOrderStatus());
						System.out.println();		
					}					
				}
			}
			else {
				System.out.println("Order details not found");					}
			}
		else {
			System.out.println("orderTable is not found");
		}
	}
		
	
	public void processOrder(String branchName) {
		System.out.println("What order ID do you want to view: "); 
		int orderID = scan.nextInt();
		scan.nextLine();
		if(orderTable != null) {
			System.out.println("PROCESSING ORDER ID: " + orderID);
			List<Order> detailsOfOrders = orderTable.get(branchName);
			String orderIDString = String.valueOf(orderID); 
						
			if(detailsOfOrders != null) {
				for(Order elem : detailsOfOrders) {
					if(elem.getOrderID().equals(orderIDString)) {
						detailsOfOrders.remove(elem); 
						elem.setOrderStatus("Ready to pick up");
						System.out.println("Order status: "+ elem.getOrderStatus());
						detailsOfOrders.add(elem); 
						System.out.println("Successfully processed Order ID " + elem.getOrderID()); 
						orderTable.put(branchName, detailsOfOrders); 
						OrderInterface.exportOrderTable(orderTable);
						return; 
					}
				}
										
			}
			else {
				System.out.println("There are no orders in this Branch.");
			}
		}
		else {
			System.out.println("orderTable not found");
		}				
	}
		
	
	
}