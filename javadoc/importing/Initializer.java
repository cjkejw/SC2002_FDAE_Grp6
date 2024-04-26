package importing;

import people.Person;
import branches.Branch;
import orders.Order;
import java.util.Hashtable; 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.List; 
import java.util.ArrayList; 

/**
 * This is a class that initializes the system 
 * It reads existing data from the text files and populates the corresponding data structures
 * @author FDAE Team 6
 */ 
public class Initializer {
	private String personFilePath = "src\\person_system_text.txt"; 
	private String branchFolderPath = "src\\Branch_folder"; 
	private String orderFolderPath = "src\\Order_Folder"; 
	private String paymentFolderPath = "src\\Payment_Folder"; 
	private Hashtable<String, Person> personTable = new Hashtable<>(); 
	private Hashtable<String, Branch> branchTable = new Hashtable<>(); 
	private Hashtable<String, List<Order>> orderTable = new Hashtable<>(); 
	private Hashtable<String, List<String>> paymentTable = new Hashtable<>(); 
	
	/**
	 * This is a constructor that initializes the system
	 * It reads the data from the text files
	 */
	public Initializer() {
		readPersonFilePath(personFilePath); 
		traverseFolder(branchFolderPath); 
		traverseFolder(orderFolderPath);
		traverseFolder(paymentFolderPath); 
	}
	
	/**
	 * This method retrieves the person hash table containing information of people 
	 * @return The person hash table
	 */
	public Hashtable<String, Person> getPersonTable() {
		return personTable; 
	}
	
	/**
	 * This method retrieves the branch hash table containing branch information
	 * @return The branch hash table
	 */
	public Hashtable<String, Branch> getBranchTable() {
		return branchTable; 
	}
	
	/**
	 * This method retrieves the hash table containing order information
	 * @return The order hash table 
	 */
	public Hashtable<String, List<Order>> getOrderTable() {
		return orderTable; 
	}
	
	/**
	 * This method retrieves the hash table containing information about payment methods
	 * @return The payment hash table
	 */
	public Hashtable<String, List<String>> getPaymentTable() {
		return paymentTable; 
	}
	
	private void readPersonFilePath(String filePath) {
		try (BufferedReader read = new BufferedReader(new FileReader(filePath))) {
			String row; 
			while ((row = read.readLine()) != null) {
				String[] columns = row.split("\t"); 
				if (columns.length == 7) {
					String key_staffID = columns[0]; 
					String password = columns[1]; 
					String Name = columns[2]; 
					String Role = columns[3];
					String Gender = columns[4]; 
					String Age = columns[5]; 
					String Branch = null; 
					if (!columns[6].trim().equals("")) {
						Branch = columns[6].trim(); 
					}
					Person person = new Person(password, Name, Role, Gender, Age, Branch); 
					personTable.put(key_staffID, person);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readBranchTextFile(File text) {
		try (BufferedReader read = new BufferedReader(new FileReader(text))) {
			String row; 
			String key_name = read.readLine(); 
			String location = read.readLine(); 
			String StaffQouta = read.readLine(); 
			String Status = read.readLine(); 
			List<String[]> menuList = new ArrayList<>(); 
			
			while ((row = read.readLine()) != null && !row.isEmpty()) {
				String[] menuItems = row.split(","); 
				for (int i = 0; i < menuItems.length; i++) {
					String[] menuItemDetails = menuItems[i].split("\t"); 
					menuList.add(menuItemDetails); 
				} 
			}
			
			Branch branch = new Branch(StaffQouta, location, menuList, Status); 
			branchTable.put(key_name, branch); 			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readPaymentTextFile(File text) {
		try (BufferedReader read = new BufferedReader(new FileReader(text))) {
			String row; 
			String key = read.readLine(); 
			List<String> payments = new ArrayList<>(); 
			while ((row = read.readLine()) != null && !row.isEmpty()) {
				payments.add(row.trim()); 
			}
			paymentTable.put(key, payments);
		}
		catch (IOException e) {
			e.getStackTrace(); 
		}
	}
	
	private List<Order> readOrderTextFile(File text) {
		List<Order> orders = new ArrayList<>(); 
		try (BufferedReader read  = new BufferedReader(new FileReader(text))) {
			String orderID; 
			while ((orderID = read.readLine()) != null) {
				List<String[]> menuList = new ArrayList<>(); 
				String row = read.readLine(); 
				String[] menuItems = row.split(",");
				for (String item : menuItems) {
					menuList.add(item.split("\t")); 
				}
				String orderStatus = read.readLine(); 
				String diningOption = read.readLine(); 
				Order order = new Order(orderID, menuList, orderStatus, diningOption); 
				orders.add(order); 
				row = read.readLine(); 
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return orders; 
	}
	
	private void traverseFolder(String folderPath) {
		File folder = new File(folderPath); 
		File[] textFiles = folder.listFiles(); 
		if (textFiles != null) {
			for (File texts : textFiles) {
				if (texts.isFile()) {
					if (folderPath.equals(branchFolderPath)) {
						readBranchTextFile(texts); 
					}
					else if (folderPath.equals(orderFolderPath)) {
						String key = texts.getName().replace("_order_text.txt", "");
						List<Order> orders = readOrderTextFile(texts); 
						orderTable.put(key, orders);
					}
					else if (folderPath.equals(paymentFolderPath)) {
						readPaymentTextFile(texts); 
					}
				}
			}
		}
	}
	
	
}