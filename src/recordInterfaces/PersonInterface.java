package recordInterfaces;

import java.util.Hashtable;

import importing.Record;
import people.Person;

public interface PersonInterface {
	
	public static Hashtable<String,Person> getPersonTable() { 
		Record record  = new Record();
		Hashtable<String, Person> personTable = record.getPersonTable(); 
		return personTable; 
	}
	
	public static void exportPersonTable(Hashtable<String,Person> personTable) {
		Record record  = new Record();
		record.exportPersonData(personTable);
	}
}
