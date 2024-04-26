package customise;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomiseSys {
	
	public String[] addingCustomisation(String[] item, String category) {
    	if (category.equals("side")) {
    		CustomiseSide side = new CustomiseSide(); 
    		String custom = side.customise(); 
    		String[] updatedItem = Arrays.copyOf(item, item.length + 1);
    		updatedItem[item.length] = custom; 
    		item = updatedItem; 
    		return item; 
    	}
    	else if (category.equals("drink")) {
    		CustomiseDrink drink = new CustomiseDrink(); 
    		String custom = drink.customise(); 
    		String[] updatedItem = Arrays.copyOf(item, item.length + 1);
    		updatedItem[item.length] = custom; 
    		item = updatedItem; 
    		return item; 
    	}
    	else {
    		CustomiseMain main = new CustomiseMain(); 
    		ArrayList<String> custom = main.customise(category); 
    		String[] updated = new String[item.length + custom.size()]; 
    		System.arraycopy(item, 0, updated, 0, item.length);
    		int index = item.length; 
    		for (String a : custom) {
    			updated[index] = a; 
    			index++; 
    		}
    		return updated;
    	}
    }
}
