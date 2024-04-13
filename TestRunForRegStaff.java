package staffSys;

import java.util.Scanner;


public class TestRunForRegStaff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		RegStaff regStaffAction = new RegStaff();
		String branchName;
		int branchChoice;
		
		do
		{
			System.out.println("Enter branch: ");
			System.out.println("(1)NTU\n"
					+ "(2)JE\n"
					+ "(3)JP\n"
					+ "(4)Quit");
			branchChoice = scan.nextInt();
			
			switch(branchChoice)
			{
				case 1:
					branchName = "NTU";
					performRegStaffAction(regStaffAction, branchName);
					break;
				/*case 2: 
					branchName ="JE";
					performRegStaffAction(regStaffAction, branchName);
					break;
				case 3: 
					branchName = "JP";
					performRegStaffAction(regStaffAction, branchName);
					break;*/
			}
		}while(branchChoice>0 && branchChoice <4);
	}
	
	private static void performRegStaffAction(RegStaff regStaffAction, String branchName)
	{
		Scanner scan1 = new Scanner(System.in);
		int actionChoice;
		
		do
		{
			System.out.println("\nChoice of action:\n"
					+ "(1)Display new orders\n"
					+ "(2)View order details\n"
					+ "(3)Process order\n"
					+ "(4)Quit");
			
			actionChoice = scan1.nextInt();
			
			switch(actionChoice)
			{
				case 1: 
					regStaffAction.displayNewOrders(branchName);
					break;
				case 2: 
					System.out.println("Enter order ID: ");
					regStaffAction.viewOrderDetails(branchName, scan1.nextInt());
					break;
				case 3: 
					System.out.println("Enter order ID: ");
					regStaffAction.processOrder(branchName, scan1.nextInt());
					break;
			}
		}while(actionChoice>0 && actionChoice<4);
	}

}
