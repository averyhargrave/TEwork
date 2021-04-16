package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;        // include the code to support a HashMao
import java.util.LinkedHashMap;  // include the code to support a LindedHashMap
import java.util.Map;            // include the code to support a generic Map
import java.util.Set;            // include the code to support a Set
import java.util.TreeMap;        // include the code to support a TreeMap

public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();

// Map is a Collections class to hold and access key-value pairs
// The key is a unique value that identifies the pair
// The value is what you want associated with the key
// Map is also know as an "associative array"
//
// a key must be unique within the Map
		
// Types of Maps:
//            HashMap - entries are stored in an unknown order
//            TreeMap - entries are stored in key sequence
//            LinkedHashMap - entries are stored in the order they are added to the Map
		
// To define a Map:  
//                  Map<key-data-type, value-data-type> name-of-map = new type-of-map();
//                  type-of-map<ket-data-type, value-data-type> name-of-map = type-of-map();
//
// Define a Map where the key is a String and the value is a String
//
//                  Map<String, String> myMap     = new HashMap();
//                  HashMap<String, String> myMap = new HashMap();
//                  Map<String, String> myMap = new HashMap<String,String>();
//                  HashMap<String, String> myMap = new HashMap<String,String>();

		
// Define a Map to associate a person's name to where they live = ex. "Frank" - "Mayfield"
// key   - person's names  - a String
// value - where they live - a String 

		//   key  , value
		//   type , type    name           type-of-Map()
		Map<String,String> residence = new LinkedHashMap();  // LinkedHashMap stores in entry sequence - best when entry sequence is important
		                                                     // TreeMap stores in key sequence - best when doing a lot of searching
		                                                     // HashMap stores in an unknown sequence - est when you don't care about the sequence
		
// Let's add a few people to the Map
//  .put(key, value) will add an entry to a Map
		residence.put("Frank","Mayfield");
		residence.put("Jeff","Istanbul");
		residence.put("Brianna","North Tonawanda");
		residence.put("D","Wakanda");
		residence.put("Sam","Youngstown");
		residence.put("Avery","Cleveland Heights");
		residence.put("Daniel","Lakewood");
		residence.put("Wally","Mayfield");
		
// Use .get(key-value) to access a Map using the key - the value associated with the key is returned
//     .get returns a null if the key is not in the Map
		
		System.out.println("D lives in: " + residence.get("D"));
		System.out.println("Brianna lives in " + residence.get("Brianna"));
		System.out.println("Jeff lives in " + residence.get("Jeff"));
			
		String name = "Sam";
 
		System.out.println(name + " lives in: " + residence.get(name));
		
		System.out.println("------------------------------------------------------------------------------");

// To process all the entries in a Map you need to get a list of keys in the Map and use the process the entries of Map
//
// We need to get the list of keys out of the Map and then iterate through the list of keys using some sort of for-loop
//
//      .keySet() - returns the list of keys in a Map as a Set object
//
// A Set object is an instance of the Collection class Set
//
// Set class is an unordered set up elements that must be unique
//   (different from ArrayList because elements in ArrayList do not have to be unique)
//
// to define a Set: Set<data-type> 
//
// Get the list of keys in our Map
		Set<String> theKeys = residence.keySet(); //  String is used because keys are Strings
		
// Go through the Set of keys using for-each loop since we want to process all the keys since we want to process all the keys
//      for(data-type name of an element : object-with-elements)
		for(String anElement : theKeys) {  // Loop through theKeys one at a time assigning teh current key to anElement
			String theValue = residence.get(anElement);   // get the value for current key and store it in theValue
			System.out.println(anElement + " lives in " + theValue);  // Display the person's name and where they live	
		}
		System.out.println("------------------------------------------------------------------------------");
		
// .remove(key-value) will remove an entry from the Map
		residence.remove("Frank"); // Remove the entry with the key Frank
		
		System.out.println("Frank lives in: " + residence.get("Frank"));

		System.out.println("------------------------------------------------------------------------------");

// Since keys must unique in a Map, if  you try to add a key that already exists, the value for the key will be updated
		
		System.out.println("Try to add Daniel living in Timbuktu");
		
		residence.put("Daniel","Timbuktu");  // If Daniel alreadyin the Mpa, the value is updated (a new entry is not added)
		
		for(String anElement : theKeys) {  // Loop through theKeys one at a time assigning teh current key to anElement
			String theValue = residence.get(anElement);   // get the value for current key and store it in theValue
			System.out.println(anElement + " lives in " + theValue);  // Display the person's name and where they live	
		}
		System.out.println("------------------------------------------------------------------------------");
	
// Miscellaneous Map methods
//
//  .containsValue(aValue) - returns true is any entry in the Map is the value specified
//
//  .values - returns all the values in the Map
		
		System.out.println("Does anyone live in Lakewood? " + residence.containsValue("Lakewood"));
		System.out.println("Does anyone live in Timbuktu? " + residence.containsValue("Timbuktu"));
		
		
		System.out.println("The values in the Map are: " + residence.values());

		System.out.println("------------------------------------------------------------------------------");	
		
		/***************************************************************************************
		 * List all the keys with a specific value in the Map:
		 * 
		 *    1. Get all the keys in the Map
		 *    2. Loop through the keys one at at a time.
		 *         a. If the value for the current key matches the one you want, display it
		 *               if it doesn't, do nothing, continue in the loop
		 * 
		 ***************************************************************************************/	
		
			System.out.println("\n---- List all the keys for a specific value ----");
			
			String valueWeWant = "Mayfield";               // Define a variable to hold the value we want to search for
			
			// For testing, add a few entries with the same value
			residence.put("Beaver Cleaver", valueWeWant);
			residence.put("Wally Cleaver", valueWeWant);
			residence.put("Eddie Haskell", valueWeWant);
			residence.put("Lumpy Rutherford", valueWeWant);
			
			Set<String> keysInMap = residence.keySet(); // Get all the keys from the Map
			 
			for(String aKey : keysInMap) {                                 // loop through the keys from the map
				if (residence.get(aKey).equals(valueWeWant)) {             //      Does the value for the current match the one we want?
				System.out.println(aKey + " has the value " + valueWeWant);//      Display the key and value
			    }
			}
	}

}
