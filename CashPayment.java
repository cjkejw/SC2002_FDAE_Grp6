package Custmr;


//just an example of new method implementation
public class CashPayment implements PaymentMethod  {

	@Override
	public boolean processPayment(double totalcost) {
        System.out.println("Please pay cash payment of $" + totalcost);
        return true;
	}


}
