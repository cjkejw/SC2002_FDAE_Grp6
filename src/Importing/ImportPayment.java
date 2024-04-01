package Importing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportPayment {
	private String filePath = "src\\payment_list_text.txt"; 
	
	public String[] readPaymentData() {
		List<String> paymentList = new ArrayList<>(); 
		
		try (Scanner scan = new Scanner(new File(filePath))) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine().trim(); 
				
				if (!line.isEmpty()) {
					String[] linesplit = line.split("\t"); 
					for (String item : linesplit) {
						paymentList.add(item); 
					}
				}
			}
			return paymentList.toArray(new String[0]); 
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null; 
		} 
	}
	
	public void exportPaymentData(String[] array) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int i = 0; i < array.length; i++) {
				writer.write(array[i]);
				if (i < array.length - 1) {
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
