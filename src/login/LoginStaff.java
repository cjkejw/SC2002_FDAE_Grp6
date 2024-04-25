package login;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

import dependencyInterface.PersonDependency;
import people.Person;
import recordInterfaces.PersonInterface;

public class LoginStaff implements PersonInterface, PersonDependency {
	private Person person; 
	private String role; 
	private String name; 
	private Hashtable<String, Person> personTable;
	
	Scanner scan = new Scanner(System.in) ;
	
	public LoginStaff() {
		this.personTable = PersonInterface.getPersonTable(); 
	}
	
	public void getUser() {
		while (true) {
			try {
				System.out.println("Enter your Username: "); 
				String username = scan.next();
				scan.nextLine(); 
				System.out.println("Enter your password: "); 
				String password = scan.next(); 
				scan.nextLine(); 
				if (personTable.containsKey(username)) {
					Person person = personTable.get(username); 
					if (person.getPassword().equals(password)) {
						this.person = person; 
						System.out.println("Successful Login!");
						if (this.person.getPassword().equals("password")) {
							changePassword(username); 
						}
						this.role = this.person.getRole(); 
						this.name = this.person.getName(); 
						return; 
					}
					else {
						System.out.println("Invalid Password!"); 
						continue; 
					}
				}
				else {
					System.out.println("Invalid Username!"); 
					continue; 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}
	
	public void changePassword(String username) { 
		try {
			while (true) {
				System.out.println("You are required to change your password\nPlease enter the new password: ");
				String NewPassword = scan.next(); 
				if (NewPassword.equals(person.getPassword())) {
					System.out.println("Cannot use same password!"); 
					continue; 
				}
				System.out.println("Please enter the new password again for confirmation: "); 
				String confirm = scan.next(); 
				if (NewPassword.equals(confirm)) {
					person.setPassword(NewPassword);
					personTable.put(username, person);
					PersonInterface.exportPersonTable(personTable);
					System.out.println("Successfully Changed Password!\n"); 
					return; 
				}
				else {
					System.out.println("You did not put the same password!");  
				}
			}
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid Input! Choose again."); 
			scan.next(); 
		}	
	}
	
	public String getRoleOfStaff() {
		return role; 
	}
	
	public String getNameofStaff() {
		return name; 
	}
	
	public Person getPerson() {
		return this.person; 
	}
}
