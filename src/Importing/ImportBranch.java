package Importing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.List; 

public class ImportBranch {
	private String filePath = "src\\branch_list_text.txt"; 
		
	public String[][] readBranchData() {
			
		List<String[]> BranchList = new ArrayList<>(); 
			
		try (Scanner scan = new Scanner(new File(filePath))) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine().trim(); 
					
				if (!line.isEmpty()) {
					String[] linesplit = line.split("\t");
					BranchList.add(linesplit); 
				}
			}
				
			String[][] BranchArray = new String[BranchList.size()][]; 
			for (int j = 0; j < BranchList.size(); j++) {
				BranchArray[j] = BranchList.get(j); 
			}
				
			return BranchArray; 
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

	
