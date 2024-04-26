package payment;

import java.util.Scanner;

import dependencyInterface.PaymentDependency;
import recordInterfaces.PaymentInterface;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List; 

public class PaymentSys implements PaymentInterface, PaymentDependency {
	
	Scanner scan = new Scanner(System.in);
	
	private Hashtable<String, List<String>> paymentTable; 
	private String key; 
	
    
	public PaymentSys() {
		this.paymentTable = PaymentInterface.getPaymentTable();
	}
	
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