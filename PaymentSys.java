package Custmr;

import java.util.Scanner;

public class PaymentSys {
    private PaymentMethod paymentMethod;

    public void selectPaymentMethod() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select payment method:");
        System.out.println("1. Debit/Credit Card Payment");
        System.out.println("2. Cash Payment (Placeholder for future implementation do not choose)");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                paymentMethod = new CardPayment();
                break;
            case 2:
                paymentMethod = new CashPayment();
                break;
            default:
                System.out.println("Invalid choice. Defaulting to card payment.");
                paymentMethod = new CardPayment();
                break;
        }
    }
    
    public void setPaymentMethod(PaymentMethod paymentMethod) {
    	this.paymentMethod = paymentMethod;
    }
    
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
	
    public boolean processPayment(PaymentMethod paymentMethod, double totalcost) {
        return paymentMethod.processPayment(totalcost);
    } //if true means payment successful

}
