package Importing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportMenu {
	private String filePath = "src\\Menu_list_text.txt"; 
	
	public String[][] readMenuData() {
		
		List<String[]> MenuList = new ArrayList<>(); 
		
		try (Scanner scan = new Scanner(new File(filePath))) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine().trim(); 
				
				if (!line.isEmpty()) {
					String[] linesplit = line.split("\t"); 
					MenuList.add(linesplit); 
				}
			}
			
			String[][] MenuArray = new String[MenuList.size()][]; 
			for (int j = 0; j < MenuList.size(); j++) {
				MenuArray[j] = MenuList.get(j); 
			}
			
			return MenuArray; 
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null; 
		} 
	}
	
	public void exportOrderData(String[][] array) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (String[] rows: array) {
				for (int i = 0; i < rows.length; i++) {
					writer.write("\t");
				}
			}
			writer.newLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
