package Custmr;

import java.util.Scanner;

//just an example of new method implementation
public class OnlinePayment implements PaymentMethod  {
	
	public OnlinePayment() {
		Scanner sc = new Scanner(System.in);
        System.out.println("Select online payment platform:");
        System.out.println("1. PayNow");
        System.out.println("2. Paylah");
        System.out.println("3. PayPal");
        
        int hello = 0;
        
        
        while(hello==0) {
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
	        switch (choice) {
	            case 1:
	                System.out.println("Proceeding to pay with PayNow...Please wait.");
	                hello=1;
	                break;
	            case 2:
	                System.out.println("Proceeding to pay with PayLah...Please wait.");
	                hello=1;
	                break;
	            case 3:
	                System.out.println("Proceeding to pay with PayPal...Please wait.");
	                hello=1;
	                break;
	            default:
	                System.out.println("Invalid choice. Please select again.");
	                break;
	        }
        }
	}

	@Override
	public boolean processPayment(double totalcost) {
        System.out.println("Please scan QR code to make payment of $" + totalcost);
        System.out.println("QR code.jpg");
        System.out.println("Please make payment within 60s.");
        return true;
	}


}
