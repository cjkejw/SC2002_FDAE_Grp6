package recordInterfaces;

import java.util.Hashtable;

import branches.Branch;
import importing.Record;

/**
 * This is an interface that defines the method for interacting with a hash table of Branch objects
 * @author FDAE Team 6
 */
public interface BranchInterface {
	
	/**
	 * This get method retrieves the branch table from the Record class
	 * @return The hash table containing branch information
	 */
	public static Hashtable<String,Branch> getBranchTable() {
		Record record = new Record(); 
		Hashtable <String, Branch> branchTable = record.getBranchTable(); 
		return branchTable; 
	}
	
	/**
	 * This method exports the branch table data using the Record class
	 * @param branchTable The hash table containing the branch information to be exported
	 */
	public static void exportBranchTable(Hashtable<String,Branch> branchTable) {
		Record record = new Record(); 
		record.exportBranchData(branchTable);
	}
}