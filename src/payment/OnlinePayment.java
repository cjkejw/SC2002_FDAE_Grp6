package payment;

import java.util.Scanner;

//just an example of new method implementation
public class OnlinePayment implements PaymentMethod  {
	
	private String pay; 
	
	Scanner sc = new Scanner(System.in);
	
	public OnlinePayment(String pay) {
        this.pay = pay; 
	}

	@Override
	public boolean processPayment(double totalcost) {
		System.out.println("Proceeding with " + pay + "...");
        System.out.printf("Please scan QR code to make payment of $%.2f\n", totalcost);
        System.out.println("QR code.jpg");
        System.out.println("Please make payment within 60s.");
        return true;
	}
}