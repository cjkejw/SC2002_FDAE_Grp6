package people;

public class Person {
	private String Password; 
	private String Name; 
	private String Role; 
	private String Gender; 
	private String Age; 
	private String Branch;
	
	public Person(String password, String name, String role, String gender, String age, String branch) {
		this.Password = password; 
		this.Name = name; 
		this.Role = role; 
		this.Gender = gender; 
		this.Age = age; 
		this.Branch = (branch != null) ? branch : null;  
	}
	
	public String getPassword() {
		return Password; 
	}
	
	public String getName() {
		return Name; 
	}
	
	public String getRole() {
		return Role; 
	}
	
	public String getGender() {
		return Gender; 
	}
	
	public String getAge() {
		return Age; 
	}
	
	public String getBranch() {
		return Branch; 
	}
	
	public void setAge(String age) {
		this.Age = age; 
	}

	public void setRole(String role) {
		this.Role = role; 
	}
	
	public void setGender(String gender) {
		this.Gender = gender; 
	}
	
	public void setPassword(String password) {
		this.Password = password; 
	}

	public void setBranch(String Branch) {
		this.Branch = Branch; 
	}
	
	public void setName(String name) {
		this.Name = name; 
	}
}

