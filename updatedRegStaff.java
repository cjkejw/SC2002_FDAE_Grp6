package staffSys;

import importing.Record;
import orders.Order;

import java.util.Hashtable;
import java.util.List;

public class RegStaff {
	private Record record;
	private Hashtable<String, List<Order>> orderTable; 
	
	public RegStaff() {
		this.record = new Record();
		orderTable = record.getOrderTable(); //get the hashtable of all the orders from all the branches 
	}

	/*public void accessAllOrders(String branchName) {
		record.getOrderTable();
	}*/
	
	public void displayNewOrders(String branchName) {
		if(record != null) {
			if(orderTable != null) {
				System.out.println("NEW ORDERS: ");
				for(String key : orderTable.keySet()) { //traversing the key portion of the whole hashtable 
					if(key.equalsIgnoreCase(branchName)) { //if key value == user input branchName
						List<Order> detailsOfOrders = orderTable.get(branchName); //retrieve the value portion of that specific key
						
						//traversing the Order array that contains the order details and print the order ID for those orders
						if(detailsOfOrders != null) {
							for(Order elem : detailsOfOrders) {
								System.out.println("Order ID: " + elem.getOrderID());
							}
						}
						else {
							System.out.println("Orders not found in this branch: " + branchName);
						}
						
					}
					else {
						System.out.println("Branch not found");
					}
					
				}
				
			}
			else {
				System.out.println("Order table not found");
			}
		}
		else {
			System.out.println("Records are empty");
		}
		//System.out.println();
	}
	
	public void viewOrderDetails(String branchName, int orderID) {
		if(record != null) {
			if(orderTable != null) {
				System.out.println("ORDER DETAILS OF ORDER ID " + orderID + ": ");
				for(String key : orderTable.keySet()) {
					if(key.equalsIgnoreCase(branchName)) {
						List<Order> detailsOfOrders = orderTable.get(branchName);
						if(detailsOfOrders != null) {
							for(Order elem : detailsOfOrders) {
								if(Integer.parseInt(elem.getOrderID()) == orderID) {
									System.out.println("Order ID: " + elem.getOrderID());
									
									List<String[]> itemsInOrder = elem.getMenuList();
									if(itemsInOrder != null) {
										for(String[] itemList : itemsInOrder) {
											if(itemList.length >= 3) {
												String item = itemList[0];
												String price = itemList[1];
												String type = itemList[2];
												System.out.println("Item: " + item);
												System.out.println("Price: " + price);
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
							System.out.println("Order details not found");
						}
					}
					else {
						System.out.println("Branch is not found");
					}
				}
			}
			else {
				System.out.println("Order table not found");
			}
		
		}
		else {
			System.out.println("Records not found");
		}
	}
	
	public void processOrder(String branchName, int orderID) {
		if(record != null) {
			if(orderTable != null) {
				System.out.println("PROCESSING ORDER ID: " + orderID);
				for(String key : orderTable.keySet()) {
					if(key.equalsIgnoreCase(branchName)) {
						List<Order> detailsOfOrders = orderTable.get(branchName);
						
						if(detailsOfOrders != null) {
							for(Order elem : detailsOfOrders) {
								if(elem.getOrderStatus().equalsIgnoreCase("Pending")) {
									elem.setOrderStatus("Ready to pick up");
									System.out.println("Order status: "+ elem.getOrderStatus());
									break;
								}
							}
							record.exportOrderData();
							
						}
						else {
							System.out.println("Orders not found");
						}
					}
					else {
						System.out.println("Branch not found");
					}
				
				}
			}
			else {
				System.out.println("Order table not found");
			}
		}
		else {
			System.out.println("Records not found");
		}
	}
		
	
	
}
