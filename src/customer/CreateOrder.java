package customer;

import Importing.ImportMenu;
import Importing.ImportOrder;
import java.util.Scanner;  
import java.util.Arrays; 

public class CreateOrder {
	protected String branch;
	private String name; 
	private String price; 
	private String Category; 
	protected int numOfMenu = 0;
	protected int numOfCart = 0; 
	protected static int orderID = 0; 
	private String[][] MenuArray; 
	protected String[][] OrderArray; 
	
	Scanner scan = new Scanner(System.in);
	
	public CreateOrder(String branch) {
		this.branch = branch;
		ImportMenu menu = new ImportMenu(); 
		this.MenuArray = menu.readMenuData(); 
		ImportOrder orderList = new ImportOrder(); 
		this.OrderArray = orderList.readOrderData(); 
		CreateOrder.orderID++; 
	}
	
	public int getOrderID() {
		return orderID; 
	}
	
	public String[][] getOrderArray() {
		return OrderArray; 
	}
	
	public void printMenu() {
		int i = 0; 
		System.out.println("This is the items availble in this branch: "); 
		for (String[] rows : MenuArray) {
			if (rows[2].equals(branch)) {
				i++; 
				System.out.print("\t" + i +". ");
				for (int j = 0; j < 4; j++) {
					if (j == 1) {
						System.out.print("$" + rows[j] + ", ");
					}
					else if (j == 2) {
						continue; 
					}
					else {
						System.out.print(rows[j] + ", ");
					}
				} 
				System.out.println(); 
			}
		}
		System.out.println(); 
		numOfMenu = i; 
	}
	
	public void selectingItemtoInesert() {
		System.out.println("What item woudld you wish to add?"); 
		int choice = scan.nextInt(); 
		int count = 0; 
		
		while (choice < 0 || choice > numOfMenu) {
			System.out.println("Invalid Input. Please input again."); 
			choice = scan.nextInt(); 
		}
		
		for (String[] rows: MenuArray) {
			if (rows[2].equals(branch)) {
				count++; 
				if (count == choice) {
					this.name = rows[0]; 
					this.price = rows[1]; 
					this.Category = rows[3]; 
					insertOrderinArray(name, price, Category); 
					break; 
				}
			}
		}
	}
	
	public void insertOrderinArray(String name, String price, String Category) {
		String[] newRow = new String[7];
		String orderNum = Integer.toString(orderID); 
		newRow[0] = orderNum; 
		newRow[1] = name; 
		newRow[2] = price; 
		newRow[3] = branch; 
		newRow[4] = Category; 
		newRow[5] = "Pending"; 
		newRow[6] = "Waiting Payment"; 
		
		String[][] newOrderArray = Arrays.copyOf(OrderArray, OrderArray.length + 1);
	    newOrderArray[newOrderArray.length - 1] = newRow;
	    OrderArray = newOrderArray; 
	    
	    System.out.println("Item succesfully added to cart!\n"); 
	}
	
	public void printOrderList(int orderID) {
		String orderNum = Integer.toString(orderID); 
		int i = 0;  
		System.out.println("Your OrderID is "+ orderNum); 
		for (String[] rows: OrderArray) {
			if (rows[0].equals(orderNum)) {
				i++; 
				System.out.print("\t" + i + ". ");
				for (int j = 1; j < 3; j++) {
					System.out.print(rows[j] + ", ");
				}
				System.out.print(rows[4]);
				System.out.println(); 
			}
		}
		numOfCart = i; 
	}
	
	public void selectItemtoDelete() {
		printOrderList(orderID); 
		if (numOfCart == 0) {
			System.out.println("There are no items in your cart."); 
			return; 
		}
		String orderNum = Integer.toString(orderID); 
		System.out.println("Choose the item to be deleted."); 
		int choice = scan.nextInt(); 
		
		while (choice < 0 || choice > numOfCart) {
			System.out.println("Invalid Input. Please input again."); 
			choice = scan.nextInt(); 
		}
		
		int count = 0; 
		
		for (String[] rows: OrderArray) {
			if (rows[0].equals(orderNum)) {
				count++; 
				if (count == choice) {
					String item = rows[1]; 
					int num = locateIndex(orderNum, item); 
					if (num == OrderArray.length) {
						System.out.print("The item to delete does not exist.");
						return;
					}
					deletingIteminOrder(num); 
					break; 
				}
			}
		}
	}	
	
	
	public void deletingIteminOrder(int index) {
		 String[][] newOrder = new String[OrderArray.length - 1][OrderArray[0].length];
		 for (int i = 0; i < index; i++) {
			 newOrder[i] = OrderArray[i]; 
		 }
		 
		 for (int i = index + 1; i < OrderArray.length; i++ ) {
			 newOrder[i - 1] = OrderArray[i]; 
		 }
		 
		 OrderArray = newOrder; 
		
	}
	
	public int locateIndex(String orderNum, String item) {
		int totalIndex = OrderArray.length, j = 0;
		for (String[] rows: OrderArray) {
			if (rows[0].equals(orderNum) && rows[1].equals(item)) {
				return j; 
			}
			j++; 
		}
		
		return totalIndex; 
	}
	
	public int viewOrderList() {
		int num = 0; 
		String orderNum = Integer.toString(orderID);
		for (String[] rows: OrderArray) {
			if (rows[0].equals(orderNum)) {
				num++; 
			}
		}
		return num; 
	}
}

