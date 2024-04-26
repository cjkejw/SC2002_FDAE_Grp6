package customise;

import java.util.InputMismatchException;
import java.util.Scanner; 

public class CustomiseDrink extends CustomiseOrder {

	Scanner scan = new Scanner(System.in);
	
	public String customise() {
		String option = null; 
		System.out.println("Would you like: \n 1. Ice\n 2. Less Ice\n 3. No ice"); 
		while (true) {
			try {
				int choice = scan.nextInt(); 
				scan.nextLine(); 
				if (choice == 1) {
					option = "Ice"; 
					return option; 
				}
				else if (choice == 2) {
					option = "Less Ice"; 
					return option; 
				}
				else if (choice == 3) {
					option = "No ice"; 
					return option; 
				}
				else {
					System.out.println("Invalid Choice! Please choose again.");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Choose again"); 
				scan.next(); 
			}
		}
	}
}
