package recordInterfaces;

import java.util.Hashtable;
import java.util.List;

import importing.Record;

public interface PaymentInterface {
	public static Hashtable<String,List<String>> getPaymentTable() {
		Record record = new Record(); 
		Hashtable<String, List<String>> paymentTable = record.getPaymentTable(); 
		return paymentTable; 
	}
	
	public static void exportPaymentTable(Hashtable<String,List<String>> paymentTable) {
		Record record = new Record(); 
		record.exportPaymentData(paymentTable);
	}
}
