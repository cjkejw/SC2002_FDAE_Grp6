package customise;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomiseOrder {
	
	Scanner scan = new Scanner(System.in); 
	
	public String customise() {
		String option = null; 
		System.out.println("What Sauce would you like? \n1. Ketchup\n2. Chilli Sauce\n3. Curry Sauce\n4. No Sauce");
		while (true) {
			try {
				int choice = scan.nextInt();
				scan.nextLine(); 
				if (choice == 1) {
					option = "Ketchup"; 
					return option; 
				}	
				else if (choice == 2) {
					option = "Chilli Sauce";
					return option; 
				}
				else if (choice == 3) {
					option = "Curry Sauce"; 
					return option; 
				}
				else if (choice == 4) {
					option = "No Sauce"; 
					return option; 
				}
				else {
					System.out.println("Invalid Choice! Please Select Again."); 
				}
			}
			catch ( InputMismatchException e) {
				System.out.println("Invalid input! Choose again"); 
				scan.next(); 
			}
		}
	}
	
	public ArrayList<String> customise(String category) {
		System.out.println("Choose the customisations that you want!");  
		return null; 
	}
	
	
}
