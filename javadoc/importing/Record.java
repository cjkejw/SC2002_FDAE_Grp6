
package importing;

import java.util.Hashtable;

import branches.Branch;
import people.Person;
import orders.Order;
import java.util.List; 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This Record class provides methods to export data to text files
 * It also provides methods to retrieve instances of Person, Branch and Order objects
 * @author FDAE Team 6
 */
public class Record {
	
	private Hashtable<String, Person> personTable;
	private Hashtable<String, Branch> branchTable;
	private Hashtable<String, List<Order>> orderTable; 
	private Hashtable<String, List<String>> paymentTable; 
	private String personFilePath = "src\\person_system_text.txt"; 
	private String branchFolderPath = "src\\Branch_folder";
	private String orderFolderPath = "src\\Order_Folder"; 
	private String paymentFolderPath = "src\\Payment_Folder"; 
	
	/**
	 * This is a constructor that initializes the Record class
	 * It retrieves data from the Initializer class
	 */
	public Record() {
		Initializer initialise = new Initializer(); 
		personTable = initialise.getPersonTable(); 
		branchTable = initialise.getBranchTable(); 	
		orderTable = initialise.getOrderTable(); 
		paymentTable = initialise.getPaymentTable(); 
	}
	
	/**
	 * This method exports person hash table to a 
	 * @param personTable The hash table containing information about a person
	 */
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
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The get method retrieves the person hash table 
	 * @return The personn hash table
	 */
	public Hashtable<String, Person> getPersonTable() {
		return personTable; 
	}
	
	/**
	 * This get method retrieves the branch hash table
	 * @return The branch hash table 
	 */
	public Hashtable<String, Branch> getBranchTable() {
		return branchTable; 
	}
	
	/**
	 * This get method retrieves the order hash table
	 * @return The order hash table
	 */
	public Hashtable<String, List<Order>> getOrderTable() {
		return orderTable; 
	}
	
	/**
	 * This get method retrieves the payment method table
	 * @return The payment method hash table
	 */
	public Hashtable<String, List<String>> getPaymentTable() {
		return paymentTable; 
	}
	
	/**
	 * This method exports branch data to the branch text files
	 * @param branchTable The hash table containing branch data
	 */
	public void exportBranchData(Hashtable<String, Branch> branchTable) {
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
			writer.write(branch.getStatus());
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
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method exports payment method data to payment method text files
	 * @param paymentTable The hash table containing data of the payment methods
	 */
	public void exportPaymentData(Hashtable<String, List<String>> paymentTable) {
		for (String key : paymentTable.keySet()) {
			String paymentFilePath = paymentFolderPath + "\\" + key + "_text.txt"; 
			List<String> payments = paymentTable.get(key);
			exportPaymenttoFile(payments, paymentFilePath, key); 
		}
	}
	
	private void exportPaymenttoFile(List<String> payments, String filePath, String key) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(key);
			writer.newLine();
			int total = payments.size(); 
			int count = 0; 
			for (String item : payments) {
				writer.write(item);
				if (count < total - 1) {
					writer.newLine();
				}
				count++; 
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method exports order data to the order text files
	 * @param orderTable The hash table containing order information
	 */
	public void exportOrderData(Hashtable<String, List<Order>> orderTable) {
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
				writer.newLine();
				writer.write(order.getDiningOption());
				if (count_total < total_order - 1) {
					writer.newLine(); 
					writer.newLine();
				}
				count_total++;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This get method retrieves a Person instance based on the provided key and user password
	 * @param Key The staff ID 
	 * @param user_password The staff password
	 * @return The Person instance if found, or null if not found, or if password is incorrect
	 */
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
	
	/**
	 * This get method retrieves a Branch instance based on the provided key
	 * @param key The branch name
	 * @return The Branch instance if found, or null if not found
	 */
	public Branch getBranchInstance(String key) {
		Branch branch = branchTable.get(key); 
		if (branch == null) {
			System.out.println("Invalid option. Please try again."); 
			return null; 
		}
		return branch; 
	}
	
	/**
	 * This get method retrieves an Order instance based on the provided key 
	 * @param key The branch name where the order is placed
	 * @param orderID The order ID
	 * @return The Order instance if found, or null if not found
	 */
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
