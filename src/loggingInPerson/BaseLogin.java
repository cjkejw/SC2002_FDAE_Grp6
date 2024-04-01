package loggingInPerson;

import java.util.Scanner;
import customer.CustomerActions;

public class BaseLogin { 
	
	private int person = -1; 
	
	Scanner scan = new Scanner(System.in);
	
	public int getPerson() {
		return person; 
	}
	
	public int printPerson() {
		System.out.println("Choose your person \n\t1. Customer\n\t2. Staff"); 
		person = scan.nextInt();
		while (person != 1 && person != 2) {
			System.out.println("Invalid Input. Please try again."); 
			person = scan.nextInt(); 
		}
		return person; 
	}
	
	public void LoginMain(int num) {
		if (num == 1) { 
			CustomerActions customer = new CustomerActions(); 
			customer.Actions();
		}
	}
	
	public static void main(String[] args) {
		BaseLogin newlog = new BaseLogin(); 
		int num = newlog.printPerson(); 
		newlog.LoginMain(num);
		
	}
	
}