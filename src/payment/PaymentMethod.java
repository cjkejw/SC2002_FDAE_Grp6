package payment;

public interface PaymentMethod {
    boolean processPayment(double totalcost);
    
    default String getDetails() {
    	return this.getClass().getSimpleName();
    }
}
