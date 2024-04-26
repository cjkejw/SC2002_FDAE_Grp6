package recordInterfaces;

import java.util.Hashtable;
import java.util.List;

import importing.Record;
import orders.Order;

public interface OrderInterface { 
	
	public static Hashtable<String,List<Order>> getOrderTable() {
		Record record = new Record();
		Hashtable<String, List<Order>> orderTable = record.getOrderTable(); 
		return orderTable; 
	}
	
	public static void exportOrderTable(Hashtable<String,List<Order>> orderTable) {
		Record record = new Record();
		record.exportOrderData(orderTable);
	}
}
