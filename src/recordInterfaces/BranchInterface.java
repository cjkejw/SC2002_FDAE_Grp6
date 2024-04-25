package recordInterfaces;

import java.util.Hashtable;

import branches.Branch;
import importing.Record;

public interface BranchInterface {
	
	public static Hashtable<String,Branch> getBranchTable() {
		Record record = new Record(); 
		Hashtable <String, Branch> branchTable = record.getBranchTable(); 
		return branchTable; 
	}
	
	public static void exportBranchTable(Hashtable<String,Branch> branchTable) {
		Record record = new Record(); 
		record.exportBranchData(branchTable);
	}
}
