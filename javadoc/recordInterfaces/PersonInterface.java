package recordInterfaces;

import java.util.Hashtable;

import importing.Record;
import people.Person;

/**
 * This is an interface that defines methods for interacting with a hash table of persons
 * @author FDAE Team 6
 */
public interface PersonInterface {
	
	/**
	 * This method retrieves the person table from the Record class
	 * @return The hash table containing person information
	 */
	public static Hashtable<String,Person> getPersonTable() { 
		Record record  = new Record();
		Hashtable<String, Person> personTable = record.getPersonTable(); 
		return personTable; 
	}
	
	/**
	 * This method exports the person table data using the Record class
	 * @param personTable The hash table containing person information to be exported 
	 */
	public static void exportPersonTable(Hashtable<String,Person> personTable) {
		Record record  = new Record();
		record.exportPersonData(personTable);
	}
}