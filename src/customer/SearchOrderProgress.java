package customer;

import java.util.Hashtable;
import java.util.List;

import dependencyInterface.OrderDependency;
import orders.Order;
import recordInterfaces.OrderInterface;

public class SearchOrderProgress implements OrderInterface, OrderDependency {

    public void orderProgress(String branchName, String orderID) {
        // Create an instance of Initializer to access its Hashtable
        
        // Get the Hashtable containing orders
        Hashtable<String, List<Order>> orderTable = OrderInterface.getOrderTable();
        
        // Search for the customer's order progress
        List<Order> orders = orderTable.get(branchName); 
        
        for (Order order : orders) {
        	if (order.getOrderID().equals(orderID)) {
        		System.out.println("Order ID: " + order.getOrderID());
                System.out.println("Order Status: " + order.getOrderStatus());
        		return; 
        	}
        }
        
        System.out.println("No orders found for order ID: " + orderID);
    }
}