package login;

import java.util.InputMismatchException;
import java.util.Scanner;

import admin.AdminSys;
import staffSys.BranchManagerSys;
import staffSys.RegularStaffSys;

public class LoginSys {
	
	Scanner scan = new Scanner(System.in) ;
	
	public boolean loginOverall() {
		try {
			System.out.println("\n***** WELCOME *****"); 
			System.out.println("To continue - press any number \nTo end the system - press any any alphabet"); 
			@SuppressWarnings("unused")
			int input = scan.nextInt(); 
			return true; 
		}
		catch (InputMismatchException e) {
			return false; 
		}
	}
	
	public void system() {
		boolean outcome = true; 
		while (outcome) {
			outcome = loginOverall(); 
			if (!outcome) {
				System.out.println("Terminating System now...");
				continue; 
			}
			Login login = new Login(); 
			login.choosingStafforCustomer();
			if (login.personOverall.equals("Staff")) {
				LoginStaff logStaff = new LoginStaff(); 
				logStaff.getUser();
				if (logStaff.getRoleOfStaff().equals("S")) {
					RegularStaffSys staff = new RegularStaffSys(logStaff.getPerson()); 
					staff.options();
				}
				else if (logStaff.getRoleOfStaff().equals("M")) {
					BranchManagerSys manager = new BranchManagerSys(logStaff.getPerson()); 
					manager.options();
					
				}
				else if (logStaff.getRoleOfStaff().equals("A")) {
					AdminSys admin = new AdminSys(logStaff.getNameofStaff()); 
					admin.options();
				}
			}
			else if (login.getPersonOverall().equals("Customer")) {
				LoginCustomer logCust = new LoginCustomer(login.getBranchName(), login.getFullBranchName()); 
				logCust.options();
			}
		}
	}
}
