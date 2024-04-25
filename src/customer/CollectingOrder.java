package customer;

import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import orders.Order;
import recordInterfaces.OrderInterface;

public class CollectingOrder implements OrderInterface {
	
	Scanner scan = new Scanner(System.in);
	
	public boolean collectOrder(String branchName) {
		Hashtable<String, List<Order>> orderTable = OrderInterface.getOrderTable(); 
		System.out.println("What order ID do you want to view: "); 
		int orderID = scan.nextInt();
		scan.nextLine();
		if (orderID > orderTable.get(branchName).size() ) {
			System.out.println("No orderID in " + branchName); 
			return false; 
		}
		else if (orderID < 0) {
			System.out.println("Invalid orderID!"); 
			return false; 
		}
		List<Order> detailsOfOrders = orderTable.get(branchName);
		String orderIDString = String.valueOf(orderID); 
					
		if(detailsOfOrders != null) {
			for(Order elem : detailsOfOrders) {
				if(elem.getOrderID().equals(orderIDString)) {
					if (elem.getOrderStatus().equals("Ready to pick up")) {
						System.out.println("COLLECTING ORDER ID: " + orderID + "/nTHANK YOU");
						detailsOfOrders.remove(elem); 
						elem.setOrderStatus("Collected");
						System.out.println("Order status: "+ elem.getOrderStatus());
						detailsOfOrders.add(elem); 
						orderTable.put(branchName, detailsOfOrders); 
						OrderInterface.exportOrderTable(orderTable);
						return true; 
					}
					else {
						System.out.println("Order ID " + elem.getOrderID() + " is not ready to collect!\n"); 
						return false; 
					}
				}
			}
									
		}
		else {
			System.out.println("There are no orders in this Branch.");
		}
		return false;				
	}
}
