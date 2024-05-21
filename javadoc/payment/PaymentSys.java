package payment;

import java.util.Scanner;

import dependencyInterface.PaymentDependency;
import recordInterfaces.PaymentInterface;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List; 

/**
 * This PaymentSys class implements PaymentInterface and extends PaymentDependency interface
 * It provides methods for processing payments
 * @author FDAE Team 6
 */
public class PaymentSys implements PaymentInterface, PaymentDependency {
	
	Scanner scan = new Scanner(System.in);
	
	private Hashtable<String, List<String>> paymentTable; 
	private String key; 
	
    /**
     * This is a constructor that creates a new PaymentSys object and initializes the payment hash table
     */
	public PaymentSys() {
		this.paymentTable = PaymentInterface.getPaymentTable();
	}
	
	/**
	 * This method prompts the customer to choose a payment method from the available payment methods 
	 */
	public void choosePayment() {
		int count = 1; 
		System.out.println("How would you like to pay: "); 
		for (String key : paymentTable.keySet()) {
			System.out.println(count + ". " + key); 
			count++; 
		}
		while (true) {
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				int identify = 1; 
				if (input > 0 && input <= paymentTable.size()) {
					for (String key : paymentTable.keySet()) {
						if (identify == input) {
							this.key = key; 
							return; 
						}
						identify++; 
					}
				}
				else {
					System.out.println("Invalid Input! Choose again."); 
					input = scan.nextInt(); 
					scan.nextLine(); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Choose again"); 
				scan.next();
			}
		}	
	}
	
	/**
	 * This method prompts the customer to choose a specific payment option based on the chosen payment method
	 * @return The specific payment option chosen by the customer
	 */
	public String chooseSpecific() {
		while (true) {
			try {
				if (key.equals("Card")) {
					List<String> payments = paymentTable.get(key); 
					System.out.println("Which Bank Card would you like to use: "); 
					int count = 1; 
					int size = payments.size(); 
					for (String item : payments) {
						System.out.println(count + ". " + item); 
						count++; 
					}
					int input = scan.nextInt(); 
					scan.nextLine(); 
					int identify = 1; 
					if (input > 0 && input <= size) {
						for (String item : payments) {
							if (identify == input) {
								return item; 
							}
							identify++; 
						}
					}
					else {
						System.out.println("Invalid Input. Choose again."); 
					}
				}					
				else {
					List<String> payments = paymentTable.get(key); 
					System.out.println("Which Online Payment would you like to use: "); 
					int count = 1; 
					int size = payments.size(); 
					for (String item : payments) {
						System.out.println(count + ". " + item); 
						count++; 
					}
					int input = scan.nextInt(); 
					scan.nextLine(); 
					int identify = 1; 
					if (input > 0 && input <= size) {
						for (String item : payments) {
							if (identify == input) {
								return item; 
							}
							identify++; 
						}
					}
					else {
						System.out.println("Invalid Input. Choose again."); 
					}
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Choose again"); 
				scan.next();
			}
		}		
	}
	
	/**
	 * This method processes the payment using the chosen payment method and specific payment option
	 * @param totalCost the total cost of the transaction
	 * @return true if the payment is successful, false if otherwise
	 */
	public boolean process(double totalCost) {
		if (key.equals("Card")) {
			CardPayment card = new CardPayment(key); 
			boolean outcome = card.processPayment(totalCost); 
			return outcome; 
		}
		else {
			OnlinePayment online = new OnlinePayment(key); 
			boolean outcome = online.processPayment(totalCost); 
			return outcome; 
		}
	}
	
	/**
	 * This method initiates the payment process for a given total cose
	 * @param totalCost The total cost of the transaction
	 * @return true if the payment is successful, false if otherwise
	 */
	public boolean toPay(double totalCost) {
        choosePayment();
        chooseSpecific(); 
        boolean paymentSuccessful = process(totalCost);
        if (paymentSuccessful) {
            System.out.println("Payment successful! Proceed to print receipt.\n");
            return true; 
        } else {
            System.out.println("Payment failed. Please try again.");
            return false; 
        }
    }
}