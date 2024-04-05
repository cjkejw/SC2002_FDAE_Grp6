package orders;

import java.util.ArrayList; 
import java.util.List; 

public class Order {
	private List<String[]> menuList; 
	private String orderID; 
	private String orderStatus; 
	
	public Order(String orderID, List<String[]> menuList, String orderStatus) {
		this.menuList = menuList;
		this.orderID = orderID; 
		this.orderStatus = orderStatus; 
	}
	
	public List<String[]> getMenuList() {
		return menuList; 
	}
	
	public String getOrderID() {
		return orderID; 
	}
	
	public String getOrderStatus() {
		return orderStatus; 
	}
}
