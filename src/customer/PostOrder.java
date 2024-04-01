package customer;

import Importing.ImportPayment;

public class PostOrder extends CreateOrder{
	private String orderNum; 
	private String[] paymentArray; 
	
	public PostOrder(String branch) {
		super(branch); 
		this.orderNum = Integer.toString(orderID); 
		ImportPayment paymentArray = new ImportPayment(); 
		this.paymentArray = paymentArray.readPaymentData(); 
	}

	public void eatingOption() {
		System.out.println("Do you wish to: \n\t1. Dine-in\n\t2. Takeaway");
		int choice = scan.nextInt(); 
		
		while (choice < 0 || choice > 2) {
			System.out.print("Invalid Input. Please try Again.");
			choice = scan.nextInt(); 
		}
		changingOption(choice); 
	}
	
	public void printOrderNUm() {
		System.out.print(orderNum + "\n\n");
	}
	
	public void changingOption(int num) {
		String option = null; 
		if (num == 1) {
			option = "Dine-in"; 
		}
		else {
			option = "Takeaway"; 
		}
		
		for (String[] rows: OrderArray) {
			if (rows[0].equals(orderNum)) {
				rows[5] = option; 
			}
		}
	}
	
	public void payment() {
		int i = 0; 
		boolean first = true; 
		System.out.println("What payment options would you like: "); 
		for (String rows: paymentArray) {
			if (first) {
				first = false; 
			}
			else {
				i++; 
				System.out.print("\t" + i + ". ");
				System.out.println(rows);				
			}			
		}
		int choice = scan.nextInt(); 
		while (choice < 0 || choice > paymentArray.length) {
			System.out.println("Invalid input. Please try again."); 
			choice = scan.nextInt(); 
		}
		
		int j = 0; 
		for (String rows: paymentArray) {
			if (j == choice) {
				System.out.print("Payment by " + rows + "\n");
			}
			j++; 
		}
		
	}
	
	public void paymentComplete() {
		for (String[] rows: OrderArray) {
			if (rows[0].equals(orderNum)) {
				rows[6] = "Preparing"; 
			}
		}
		System.out.println("Payment is Complete!\n"); 
	}
	
	
	
	
	public double priceCalc() {
		double price = 0.0; 
		for (String[] rows: OrderArray) {
			if (rows[0].equals(orderNum)) {
				double num = Double.parseDouble(rows[2]); 
				price += num;  
			}
		}
		return price; 
	}
	
	public void printOrderList() {
		super.printOrderList(orderID); 
		System.out.print("Eating Option: ");
		for (String[] rows: OrderArray) {
			if (rows[0].equals(orderNum)) {
				System.out.println(rows[5]);
				break; 
			}
		}
		double price = priceCalc(); 
		System.out.println("Total Price: $" + price + "\n");
	}
	
	public void viewCart() {
		eatingOption(); 
		System.out.println("This is your cart: \n"); 
		printOrderList(); 
		payment();
		paymentComplete(); 
	}
	
	public void receipt() {
		System.out.println("This is your final receipt: "); 
		printOrderList(); 
	}
	
	
}
