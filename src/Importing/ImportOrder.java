package Importing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportOrder {
	
	private String filePath = "src\\Order_list_text.txt"; 
	
	public String[][] readOrderData() {
		
		List<String[]> OrderList = new ArrayList<>(); 
		
		try (Scanner scan = new Scanner(new File(filePath))) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine().trim(); 
				
				if (!line.isEmpty()) {
					String[] linesplit = line.split("\t"); 
					OrderList.add(linesplit); 
				}
			}
			
			String[][] OrderArray = new String[OrderList.size()][]; 
			for (int j = 0; j < OrderList.size(); j++) {
				OrderArray[j] = OrderList.get(j); 
			}
			
			return OrderArray; 
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
