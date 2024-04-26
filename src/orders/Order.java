package orders;

import java.util.List; 

public class Order {
	private List<String[]> menuList; 
	private String orderID; 
	private String orderStatus;
	private String diningOption; 
	
	public Order(String orderID, List<String[]> menuList, String orderStatus, String diningOption) {
		this.menuList = menuList;
		this.orderID = orderID; 
		this.orderStatus = orderStatus; 
		this.diningOption = diningOption; 
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
	
	public void setOrderStatus(String option) {
		this.orderStatus = option; 
	}
	
	public String getDiningOption() {
		return diningOption; 
	}
}
