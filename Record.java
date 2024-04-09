package importing;

import java.util.Hashtable;

import branches.Branch;
import people.Person;
import orders.Order;
import java.util.LinkedList; 
import java.util.List; 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Record {
	
	private Hashtable<String, Person> personTable;
	private Hashtable<String, Branch> branchTable;
	private Hashtable<String, List<Order>> orderTable; 
	private String personFilePath = "src\\person_system_text.txt"; 
	private String branchFolderPath = "src\\Branch_folder";
	private String orderFolderPath = "src\\Order_Folder"; 
	
	public Record() {
		Initializer initialise = new Initializer(); 
		personTable = initialise.getPersonTable(); 
		branchTable = initialise.getBranchTable(); 	
		orderTable = initialise.getOrderTable(); 
	}
	
	public void exportPersonData(Hashtable<String, Person> personTable) {
		String filePath = personFilePath; 
		int size = personTable.size(); 
		int count = 0; 
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (String key : personTable.keySet()) {
				Person person = personTable.get(key); 
				String personDataMedium = key + "\t" + person.getPassword() + "\t" + person.getName() + "\t" + person.getRole() + "\t" + person.getGender() + "\t" + person.getAge() + "\t"; 
				String personData = null; 
				if (person.getBranch() == null) {
					personData = personDataMedium + " "; 
				}
				else {
					personData = personDataMedium + person.getBranch(); 
				}
				writer.write(personData);
				count++; 
				if (count < size) {
					writer.newLine();
				}
			}
			System.out.println("Data exported."); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Hashtable<String, Person> getPersonTable() {
		return personTable; 
	}
	
	public Hashtable<String, Branch> getBranchTable() {
		return branchTable; 
	}
	
	public Hashtable<String, List<Order>> getOrderTable() {
		return orderTable; 
	}
	
	public void exportBranchData() {
		for (String key : branchTable.keySet()) { 
			String branchFilePath = branchFolderPath + "\\" + key + "_text.txt"; 
			Branch branch = branchTable.get(key); 
			exportBranchToFile(branch, branchFilePath, key); 
		}
	}
	
	private void exportBranchToFile(Branch branch, String filePath, String key) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { 
			writer.write(key);
			writer.newLine();
			writer.write(branch.getLocation());
			writer.newLine();
			writer.write(branch.getStaffQuota()); 
			writer.newLine();
			int size = branch.getMenuList().size(); 
			int count = 0; 
			for (String[] menuItem : branch.getMenuList()) {
				for (int i = 0; i < menuItem.length; i++) {
					writer.write(menuItem[i]);
					if (i < menuItem.length - 1) {
						writer.write("\t");
					}
				}
				if (count < size - 1) {
					writer.write(",");
				}
			}
			System.out.print("Success");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exportOrderData() {
		for (String key : orderTable.keySet()) {
			String orderFilePath = orderFolderPath + "\\" + key + "_order_text.txt"; 
			List<Order> orders = orderTable.get(key); 
			exportOrdertoFile(orders, orderFilePath); 
		}
	}
	
	private void exportOrdertoFile(List<Order> orders, String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			int total_order = orders.size(); 
			int count_total = 0; 
			for (Order order : orders) { 
				writer.write(order.getOrderID());
				writer.newLine();
				int count_size = 0; 
				int size = order.getMenuList().size(); 
				for (String[] menuItem : order.getMenuList()) {
					writer.write(String.join("\t", menuItem));
					if (count_size < size - 1) {
						writer.write(",");
					}
					count_size++; 
				}
				writer.newLine();
				writer.write(order.getOrderStatus()); 
				if (count_total < total_order - 1) {
					writer.newLine(); 
					writer.newLine();
				}
				count_total++;
				System.out.print("Success");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Person getPersonInstance(String Key, String user_password) {
		Person person = personTable.get(Key); 
		if (person == null) {
			System.out.println("The StaffID is wrong. Please try again."); 
			return null; 
		}
		String Password = person.getPassword(); 
		if (!Password.equals(user_password)) {
			System.out.println("The Password is incorrect. Please try again."); 
			return null; 
		}
		System.out.println("Successful");
		return person; 
	}
	
	public Branch getBranchInstance(String key) {
		Branch branch = branchTable.get(key); 
		if (branch == null) {
			System.out.println("Invalid option. Please try again."); 
			return null; 
		}
		System.out.println("Successful");
		return branch; 
	}
	
	public Order getOrderInstance(String key, String orderID) {
		List<Order> orders = orderTable.get(key); 
		for (Order order : orders) {
			if (order.getOrderID().equals(orderID)) {
				System.out.print("Yes");
				return order; 
			}
		}
		System.out.print("Order does not exist");
		return null; 
	}
	
}
