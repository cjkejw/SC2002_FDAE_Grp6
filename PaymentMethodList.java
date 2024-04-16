package Custmr;

import java.util.LinkedList;

public class PaymentMethodList {
    private LinkedList<PaymentMethod> paymentMethods;

    public PaymentMethodList() {
        paymentMethods = new LinkedList<>();
    }

    // Method to add a payment method to the list
    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
    }

    // Method to remove a payment method from the list
    public boolean removePaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethods.remove(paymentMethod);
    }

    // Method to get all payment methods
    public LinkedList<PaymentMethod> getAllPaymentMethods() {
        return paymentMethods;
    }
}
