package Custmr;

import java.util.Hashtable;
import java.util.List;

import importing.Initializer;
import orders.Order;

public class searchOrderProgress {
	
    public searchOrderProgress() {
    }

    public void orderProgress(String orderID) {
        // Create an instance of Initializer to access its Hashtable
        Initializer initializer = new Initializer();
        
        // Get the Hashtable containing orders
        Hashtable<String, List<Order>> orderTable = initializer.getOrderTable();
        
        // Search for the customer's order progress
        boolean orderFound = false;
        for (String branchID : orderTable.keySet()) {
            List<Order> orders = orderTable.get(branchID);
            for (Order order : orders) {
                if (order.getOrderID().equals(orderID)) {
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
            System.out.println("No orders found for order ID: " + orderID);
        }
    }

}
