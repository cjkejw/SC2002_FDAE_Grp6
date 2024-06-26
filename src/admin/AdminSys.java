package admin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminSys {
	
	private String name; 
	private Admin admin; 
	
	Scanner scan = new Scanner(System.in);
	
	public AdminSys(String name) {
		this.name = name; 
		this.admin = new Admin(); 
	}
	
	public void options() {
		System.out.println("WELCOME " + name); 
		while (true) {
			System.out.println("\nWhat would you like to do: \n1. Add, edit, remove Staff accounts \n2. Display Staff List\n3. Promote or Transfer Staff/Managers\n4. Check Manager Requirements of Branches \n5. Add Payment methods\n6. Open/Close Branch\n7. Log Out"); 
			try {
				int input = scan.nextInt(); 
				scan.nextLine(); 
				if (input == 1) {
					admin.addEditRemove(); 
				}
				else if (input == 2) {
					admin.choosingFunction(); 
				}
				else if (input == 3) {
					admin.promotingTransferFunction(); 
				}
				else if (input == 4) {
					admin.checkingManagerCount(); 
				}
				else if (input == 5) {
					admin.addPaymentMethod(); 
				}
				else if (input == 6) {
					admin.openOrCloseBranch(); 
				}
				else if (input == 7) {
					admin.adminLoggingOut();
					return; 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid Input! Choose again."); 
				scan.next(); 
			}
		}
	}
}
