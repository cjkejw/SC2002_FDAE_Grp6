package Custmr;


//just an example of new method implementation
public class CashPayment implements PaymentMethod  {

	@Override
	public boolean processPayment(double amount) {
        System.out.println("Please pay cash payment of $" + amount);
        return true;
	}


}
