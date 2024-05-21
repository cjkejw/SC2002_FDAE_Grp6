package payment;

/**
 * This PaymentMethod interface defines methods for processing payments
 * @author FDAE Team 6
 */
public interface PaymentMethod {
	
	/**
	 * This method processes a payment for a given total cost
	 * @param totalcost The total cost of a transaction
	 * @return true if the payment is processed successfully, false if otherwise
	 */
    boolean processPayment(double totalcost);
    
    /**
     * This get method retrieves the details of the payment method
     * @return The name of the payment method
     */
    default String getDetails() {
    	return this.getClass().getSimpleName();
    }
}