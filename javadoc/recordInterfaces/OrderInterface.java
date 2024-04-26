package recordInterfaces;

import java.util.Hashtable;
import java.util.List;

import importing.Record;
import orders.Order;

/**
 * This is an interface that defines methods for interacting with a hash table of Order lists
 * @author FDAE Team 6 
 */
public interface OrderInterface { 
	
	/**
	 * This method retrieves the order table from the Record class
	 * @return The hash table containing order information
	 */
	public static Hashtable<String,List<Order>> getOrderTable() {
		Record record = new Record();
		Hashtable<String, List<Order>> orderTable = record.getOrderTable(); 
		return orderTable; 
	}
	
	/**
	 * This method exports the order table data using the Record class
	 * @param orderTable The hash table containing order information to be exported
	 */
	public static void exportOrderTable(Hashtable<String,List<Order>> orderTable) {
		Record record = new Record();
		record.exportOrderData(orderTable);
	}
}