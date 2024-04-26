package branches;

import java.util.List; 

/**
 * This is a class for retrieving the information of a branch
 * @author FDAE Team 6
 */

public class Branch {
	private String StaffQuota; 
	private String Location; 
	private List<String[]> menuList; 
	private String status;
	
	/**
	 * Below is a constructor "Branch" 
	 * It initializes the variables StaffQuota, Location, menuList, status
	 * @param Quota The staff quota
	 * @param location The location
	 * @param menuList The menu
	 * @param status The order status
	 */
	public Branch(String Quota, String location, List<String[]> menuList, String status) {
		this.StaffQuota = Quota; 
		this.Location = location; 
		this.menuList = menuList; 
		this.status = status; 
	}
	
	/**
	 * Below is a get method for StaffQuota
	 * It retrieves the staff quota of the branch
	 * @return StaffQuota The staff quota
	 */
	
	public String getStaffQuota() {
		return StaffQuota; 
	}
	
	/**
	 * Below is a get method for Location
	 * It retrieves the location of the branch
	 * @return Location The location
	 */
	public String getLocation() {
		return Location;
	}
	
	/**
	 * Below is a get method for menuList
	 * It retrieves the menuList of the branch
	 * @return The menu list
	 */
	public List<String[]> getMenuList() {
		return menuList; 
	}
	
	/**
	 * Below is a get method for status
	 * It retrieves the status of an order in the branch
	 * @return status The order status
	 */
	public String getStatus() {
		return status; 
	}
	
	/**
	 * Below is a set method for menuList
	 * It sets the items in the menuList for the branch
	 * @param menu The menu list
	 */
	public void setMenuList(List<String[]> menu) {
		this.menuList = menu; 
	}

	/**
	 * Below is a set method for StaffQuota
	 * It sets the value for the number of staff in the branch
	 * @param quota The staff quota
	 */
	public void setStaffQuota(String quota) {
		this.StaffQuota = quota; 
	}

	/**
	 * Below is a set method for Location
	 * It sets the location of the branch
	 * @param location The location
	 */
	public void setLocation(String location) {
		this.Location = location; 
	}
	
	/**
	 * Below is a set method for status
	 * It sets the order status of an order in the branch
	 * @param status The order status
	 */
	public void setStatus(String status) {
		this.status = status; 
	}
} 