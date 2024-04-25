package branches;

import java.util.List; 

public class Branch {
	private String StaffQuota; 
	private String Location; 
	private List<String[]> menuList; 
	private String status;
	
	public Branch(String Quota, String location, List<String[]> menuList, String status) {
		this.StaffQuota = Quota; 
		this.Location = location; 
		this.menuList = menuList; 
		this.status = status; 
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
	
	public String getStatus() {
		return status; 
	}
	
	public void setMenuList(List<String[]> menu) {
		this.menuList = menu; 
	}

	public void setStaffQuota(String quota) {
		this.StaffQuota = quota; 
	}

	public void setLocation(String location) {
		this.Location = location; 
	}
	
	public void setStatus(String status) {
		this.status = status; 
	}
} 
