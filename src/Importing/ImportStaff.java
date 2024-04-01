package Importing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportStaff {
	
	private String filePath = "src\\staff_list_text.txt"; 
	
	public String[][] readMenuData() {
		
		List<String[]> StaffList = new ArrayList<>(); 
		
		try (Scanner scan = new Scanner(new File(filePath))) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine().trim(); 
				
				if (!line.isEmpty()) {
					String[] linesplit = line.split("\t"); 
					StaffList.add(linesplit); 
				}
			}
			
			String[][] StaffArray = new String[StaffList.size()][]; 
			for (int j = 0; j < StaffList.size(); j++) {
				StaffArray[j] = StaffList.get(j); 
			}
			
			return StaffArray; 
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
					writer.write(rows[i]);
					if (i < rows.length -1 ) {
						writer.write("\t");
					}
				}
				writer.newLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
