package Custmr;

import java.util.LinkedList;
import java.util.Scanner;

public class PaymentSys {
    private PaymentMethod paymentMethod;
    private PaymentMethodList paymentMethodList;
    
    public PaymentSys() {
        paymentMethodList = new PaymentMethodList();

    }

    public void selectPaymentMethod() {
        Scanner sc = new Scanner(System.in);
        LinkedList<PaymentMethod> methods = paymentMethodList.getAllPaymentMethods();
        
        System.out.println("Select payment method:");
        int index = 1;
        for (PaymentMethod method : methods) {
            System.out.println(index + ". " + method.getDetails());
            index++;
        }
        
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline
        
        if (choice >= 1 && choice <= methods.size()) {
            paymentMethod = methods.get(choice - 1);
            System.out.println("You selected: " + paymentMethod.getDetails());
        } else {
            System.out.println("Invalid choice. Defaulting to card payment.");
            paymentMethod = new CardPayment(); // Default to card payment
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
    
    public PaymentMethodList getPaymentMethodList() {
        return paymentMethodList;
    }
    
    // Method to add a new payment method
    public void addPaymentMethod(PaymentMethod newPaymentMethod) {
        paymentMethodList.addPaymentMethod(newPaymentMethod);
    }

}
