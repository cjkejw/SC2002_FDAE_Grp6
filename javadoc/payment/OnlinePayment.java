package payment;

import java.util.Scanner;

/**
 * The OnlinePayment class implements the PaymentMethod interface to process online payments
 * @author FDAE Team 6
 */
//just an example of new method implementation
public class OnlinePayment implements PaymentMethod  {
	
	private String pay; 
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * This is a constructor that creates an OnlinePayment object with the specified payment method
	 * @param pay The payment method description (e.g. PayNow, PayLah, PayPal) 
	 */
	public OnlinePayment(String pay) {
        this.pay = pay; 
	}

	/**
	 * This method processes the payment 
	 * It prompts the customer to scan the QR code and make the payment within the specified time frame
	 * @param totalcost The total cost of the transaction
	 * @return true if the payment is processed successfully, false if otherwise
	 */
	@Override
	public boolean processPayment(double totalcost) {
		System.out.println("Proceeding with " + pay + "...");
        System.out.printf("Please scan QR code to make payment of $%.2f\n", totalcost);
        System.out.println("QR code.jpg");
        System.out.println("Please make payment within 60s.");
        return true;
	}
}