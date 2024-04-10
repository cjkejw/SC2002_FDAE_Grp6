package Custmr;

public class PaymentSys {
	
    public static boolean processPayment(PaymentMethod paymentMethod, double amount) {
        return paymentMethod.processPayment(amount);
    } //if true means payment successful

}
