package customise;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CustomiseMain extends CustomiseOrder{

	private ArrayList<String> customiseList; 
	
	public CustomiseMain() {
		customiseList = new ArrayList<>(); 
	}
	
	Scanner scan = new Scanner(System.in); 
	
	public ArrayList<String> customise(String category) { 
		int choice, first = 0, second = 0, third = 0; 
		String item; 
		if (category.equals("set meal")) {
			checkSpice(); 
			
			String sauce = super.customise(); 
			customiseList.add(sauce); 
			
			return customiseList; 
		}
		else {
			checkSpice(); 
			
			String sauce = super.customise(); 
			customiseList.add(sauce); 
			
			System.out.println("What would you like to do to your " + category + ": \n1. Remove Lettuce \n2.Remove Pickles \n3.Remove Cheese \n4. Reset Customisation \n5. Continue"); 
			while(true) {
				try {
					choice = scan.nextInt(); 
					scan.nextLine(); 
					if (choice ==  1) { 
						if (first == 1) {
							System.out.println("Lettuce has already been removed"); 
						}
						else{
							first = choice; 
							item = "Remove Lettuce"; 
							customiseList.add(item); 
							System.out.println("Successfully removed lettuce");
						}
					}
					else if (choice == 2) {
						if (second == 2) {
							System.out.println("Pickles has already been removed"); 
						}
						else {
							second = choice; 
							item = "Remove Pickles"; 
							customiseList.add(item); 
							System.out.println("Successfully removed Pickles");
						}
					}
					else if (choice == 3) {
						if (third == 3) {
							System.out.println("Cheese has already been removed"); 
						}
						else {
							second = choice; 
							item = "Remove Cheese"; 
							customiseList.add(item); 
							System.out.println("Successfully removed Cheese");
						}
					}
					else if (choice == 4) {
						first = 0; 
						second = 0; 
						third = 0; 
						if (customiseList.size() > 2) {
							customiseList.subList(2, customiseList.size()).clear();
						}
						System.out.println("Successfully removed ingredient customisation!"); 
					}
					else if (choice == 5) {
						System.out.println("CUSTOMISATION CONFIRMED!"); 
						return customiseList; 
					}
					else {
						System.out.println("Invalid Choice! Choose again"); 
					}
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid Choice! Choose again"); 
					scan.next(); 
				}
			}
		}
	}
	
	public void checkSpice() {
		boolean outcome = true; 
		int choice; 
		System.out.println("Would you like: \n1. Spicy\n2. Non-Spicy"); 
		while (outcome) {
			try {
				choice = scan.nextInt();
				scan.nextLine(); 
				if (choice == 1) {
					customiseList.add("Spicy"); 
					outcome = false; 
				}
				else if (choice == 2) {
					customiseList.add("Non-Spicy"); 
					outcome = false; 
				}
				else {
					System.out.println("Invalid Choice! Choose again"); 
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Choose again"); 
				scan.next(); 
			}
		}
	}

}
