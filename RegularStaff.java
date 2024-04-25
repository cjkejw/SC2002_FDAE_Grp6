package staffSys;

import importing.Record;
import orders.Order;

import java.util.Hashtable;
import java.util.List;


public class RegularStaff {
	private Record record;
	private Hashtable<String, List<Order>> orderTable;
	
	public RegularStaff() {
		this.record = new Record();
		orderTable = record.getOrderTable(); //initialising the order table
	}
	
	public void displayNewOrders(String branchName) {
		if(record != null && orderTable != null) {
			System.out.println("NEW ORDERS:");
			
			for(String key : orderTable.keySet()) { //traversing the key portion of orderTable
				if(key.equalsIgnoreCase(branchName)) { //if the key == user input branchName, retrieve the value portion of this key 
					List<Order> orderDetails = orderTable.get(branchName);
					
					//traversing the orderDetails array and print the order ID of those orders
					if(orderDetails != null) {
						for(Order elem : orderDetails) {
							System.out.println("\tNew order ID: " + elem.getOrderID());
						}
						System.out.println("All new orders displayed");
					}else {
						System.out.println("Order details not found");
					}
					break;
				}else {
					System.out.println("Branch not found");
				}
			}
		}else {
			System.out.println("Records and/or order table not found");
		}
	}
	
	public void viewOrderDetails(String branchName, int orderID) {
		if(record != null && orderTable != null) {
			System.out.println("DETAILS OF ORDER ID " + orderID + ":");
			
			for(String key : orderTable.keySet()) { //traversing key portion of order table
				if(key.equalsIgnoreCase(branchName)) { //if key matches user input branchName, retrieve value portion of this key
					List<Order> orderDetails = orderTable.get(branchName);
					
					if(orderDetails != null) {
						for(Order elem : orderDetails) { //traversing the contents of orderDetails
							if(Integer.parseInt(elem.getOrderID()) == orderID) { //if orderID matches user input orderID, print the contents of the order
								
								List<String[]> itemsInOrder = elem.getMenuList();
								
								if(itemsInOrder != null) {
									for(String[] itemList : itemsInOrder) {
										if(itemList.length >= 3) {
											String item = itemList[0];
											String price = itemList[1];
											String type = itemList[2];
											System.out.println("\tItem: " + item);
											System.out.println("\tPrice: " + price);
											System.out.println("\tType: " + type);
										}else {
											System.out.println("Invalid item format");
										}
									}
								}else {
									System.out.println("Items not found");
								}
								System.out.println("\tOrder status: " + elem.getOrderStatus()); //printing order status
								System.out.println("\tDining option: " + elem.getDiningOption()); //printing dining option
								System.out.println();
								break;
							}
						}
						break;
					}else {
						System.out.println("Order details not found");
					}
					break;
					
				}else {
					System.out.println("Branch not found");
				}
				
			}
		}else {
			System.out.println("Records and/or order table not found");
		}
	}
	
	public void processOrder(String branchName, int orderID) {
		if(record != null && orderTable != null) {
			System.out.println("PROCESSING ORDER ID " + orderID + ":");
			for(String key : orderTable.keySet()) {
				if(key.equalsIgnoreCase(branchName)) {
					List<Order> orderDetails = orderTable.get(branchName);
					
					if(orderDetails != null) {
						for(Order elem : orderDetails) {
							if(elem.getOrderStatus().equalsIgnoreCase("Pending")) {
								elem.setOrderStatus("Ready to pick up");
								System.out.println("Order status: "+ elem.getOrderStatus());
								break;
							}
						}
						record.exportOrderData(); 
						System.out.println("Order status sucessfully updated");
						break;
						
					}else {
						System.out.println("Order details not found");
					}
				}else {
					System.out.println("Branch not found");
				}
			}
		}else {
			System.out.println("Records and/or order table not found");
		}
	}

}
