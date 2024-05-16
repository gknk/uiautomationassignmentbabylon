package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for various helper methods.
 *
 * @author Gopal Krushna Nayak
 */
public class Helper {

	public Helper() {		
	}

	/**
	 * Compares a list of strings to check whether the strings are in ascending order
	 * 
	 * @param arrayList A list of strings which represents the texts of a particular column displayed in UI.
	 * @return true if the list is sorted by ascending order or false.
	 */	
	public static boolean isTextSortedByAscending(List<String> arrayList){
		if(arrayList.size() == 0)
			return true;
	    boolean isSorted=true;
	    for(int i=1;i < arrayList.size();i++){
	    	if(arrayList.get(i-1).compareTo(arrayList.get(i)) > 0){
	    		isSorted= false;
	    		break;
	    		}
	    	}
	    return isSorted;
	    }
	
	/**
	 * Compares a list of strings to check whether the strings are in descending order
	 * 
	 * @param arrayList A list of strings which represents the texts of a particular column displayed in UI.
	 * @return true if the list is sorted by descending order or false.
	 */	
	public static boolean isTextSortedByDescending(List<String> arrayList){
		if(arrayList.size() == 0)
			return true;
	    boolean isSorted=true;
	    for(int i=1;i < arrayList.size();i++){
	    	if(arrayList.get(i-1).compareTo(arrayList.get(i)) < 0){
	    		isSorted= false;
	    		break;
	    		}
	    	}
	    return isSorted;
	    }
	
	/**
	 * Compares a list of dates to check whether the dates are in descending order
	 * 
	 * @param arrayList A list of strings which represents the texts(dates) of a particular column displayed in UI.
	 * @return true if the list is sorted by descending order or false.
	 */	
	public static boolean isDateSortedByDescending(List<String> arrayList) {
		if(arrayList.size() == 0)
			return true;
		List<Date> dates = new ArrayList<Date>();
		Date date;
		for(String str : arrayList) {
			String[] tokens = str.split(" ");
			String dateString = tokens[0]+"/"+getIndex(tokens[1])+"/"+tokens[2];			
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
				dates.add(date);
			}catch(ParseException ex) {
				ex.printStackTrace();
			}			
		}		
	    boolean isSorted=true;
	    for(int i=1;i < dates.size();i++){
	    	if(dates.get(i-1).compareTo(dates.get(i)) < 0){
	    		isSorted= false;
	    		break;
	    		}
	    	}
	    return isSorted;
	    }
	
	/**
	 * Compares a list of dates to check whether the dates are in ascending order
	 * 
	 * @param arrayList A list of strings which represents the texts(dates) of a particular column displayed in UI.
	 * @return true if the list is sorted by ascending order or false.
	 */	
	public static boolean isDateSortedByAscending(List<String> arrayList) {
		if(arrayList.size() == 0)
			return true;
		List<Date> dates = new ArrayList<Date>();
		Date date;
		for(String str : arrayList) {
			String[] tokens = str.split(" ");
			String dateString = tokens[0]+"/"+getIndex(tokens[1])+"/"+tokens[2];
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
				dates.add(date);
			}catch(ParseException ex) {
				ex.printStackTrace();
			}				
		}		
	    boolean isSorted=true;
	    for(int i=1;i < dates.size();i++){
	    	if(dates.get(i-1).compareTo(dates.get(i)) > 0){
	    		isSorted= false;
	    		break;
	    		}
	    	}
	    return isSorted;
	    }
	
	/**
	 * Returns the month in mm format
	 * 
	 * @param month A string which represents the month name.
	 * @return month in mm format.
	 */	
	public static String getIndex(String month) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Jan", "01");
		map.put("Feb", "02");
		map.put("Mar", "03");
		map.put("Apr", "04");
		map.put("May", "05");
		map.put("Jun", "06");
		map.put("Jul", "07");
		map.put("Aug", "08");
		map.put("Sep", "09");
		map.put("Oct", "10");
		map.put("Nov", "11");
		map.put("Dec", "12");		
		return map.get(month);
	}
	
	/**
	 * Returns the value of the comapny from the company name
	 * 
	 * @param company A string which represents the company name.
	 * @return value in string format.
	 */	
	public static String getValueForCompany(String company) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("IBM", "13");
		map.put("Xerox", "25");
		map.put("Tandy Corporation", "5");	
		return map.get(company);
	}
}
