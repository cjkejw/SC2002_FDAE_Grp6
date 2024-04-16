package Custmr;

import java.util.Scanner;

public class CardPayment implements PaymentMethod {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    
    public CardPayment() {
        Scanner sc = new Scanner(System.in);
        
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
    
    
    @Override
    public boolean processPayment(double totalcost) {
        System.out.println("Processing card payment of $" + totalcost);
        return true;
    }


}
