package branches;

import java.util.List; 

public class Branch {
	private String StaffQuota; 
	private String Location; 
	private List<String[]> menuList; 
	
	public Branch(String Quota, String location, List<String[]> menuList) {
		this.StaffQuota = Quota; 
		this.Location = location; 
		this.menuList = menuList; 
	}
	
	public String getStaffQuota() {
		return StaffQuota; 
	}
	
	public String getLocation() {
		return Location;
	}
	
	public List<String[]> getMenuList() {
		return menuList; 
	}
} 
