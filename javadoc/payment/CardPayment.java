
package payment;

import java.util.Scanner;

/**
 * This CardPayment class implements PaymentMethod interface to process payments using debit or credit card
 * It prompts the user for the card number, expiry date and CVV before processing the payment 
 * @author FDAE Team 6
 */
public class CardPayment implements PaymentMethod {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    
    Scanner sc = new Scanner(System.in); 
    
    /**
     * This is a constructor that creates a CardPayment object and initiaizes the card number, expiry date and CVV
     * It prompts the customer for these details
     * @param pay The payment method description (e.g. MasterCard, Visa, AmericanExpress)
     */
    public CardPayment(String pay) {
        System.out.println("Proceeding payment with " + pay + "..."); 
        while (true) {
            System.out.println("Enter card number (16 digits):");
            this.cardNumber = sc.nextLine();
            if (this.cardNumber.length() == 16 && this.cardNumber.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid card number. Please enter a 16-digit numeric value.");
            }
        }

        while (true) {
            System.out.println("Enter expiry date (MMYY):");
            this.expiryDate = sc.nextLine();
            if (this.expiryDate.length() == 4 && this.expiryDate.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid expiry date. Please enter a 4-digit numeric value.");
            }
        }

        while (true) {
            System.out.println("Enter CVV (3 digits):");
            this.cvv = sc.nextLine();
            if (this.cvv.length() == 3 && this.cvv.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid CVV. Please enter a 3-digit numeric value.");
            }
        }
    }
    
    /**
     * This method processes the card payment for the total given cost
     * @param totalcost The total cost of the transaction
     * @return true if the payment is processed successfully, false if unsuccessful payment 
     */
    @Override
    public boolean processPayment(double totalcost) {
        System.out.printf("Processing card payment of $%.2f\n", totalcost);
        return true;
    }


}
