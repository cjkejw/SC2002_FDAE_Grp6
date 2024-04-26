package recordInterfaces;

import java.util.Hashtable;
import java.util.List;

import importing.Record;

/**
 * This is an interface that defines methods for interacting with hash table of payment methods
 * @author FDAE Team 6
 */
public interface PaymentInterface {
	
	/**
	 * This method retrieves the payment method table from the Record class
	 * @return The payment method hash table containing information about payment methods
	 */
	public static Hashtable<String,List<String>> getPaymentTable() {
		Record record = new Record(); 
		Hashtable<String, List<String>> paymentTable = record.getPaymentTable(); 
		return paymentTable; 
	}
	
	/**
	 * This method exports the payment method hash table using the Record class
	 * @param paymentTable The hash table containing information of the payment methods to be exported
	 */
	public static void exportPaymentTable(Hashtable<String,List<String>> paymentTable) {
		Record record = new Record(); 
		record.exportPaymentData(paymentTable);
	}
}