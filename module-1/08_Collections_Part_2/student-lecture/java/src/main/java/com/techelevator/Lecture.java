package com.techelevator;

import java.util.HashMap;       // include the code to support a HashMap
import java.util.LinkedHashMap; // include the code support a LinkedHashMap
import java.util.TreeMap;       // include the code to support a TreeMap
import java.util.Map;           // include the code to support a generic Map
import java.util.Set;           // include the code to support a Set

public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();

// Map is a Collection Class to hold and access key-value pairs
// The key is a unique value that identifies the pair
// The value is what you want associated with the key
// Map is also known as an "associative array"
		
// a key must be unique within the Map
		
// Types of Maps:
// 		HashMap - entries are stored in an unknown order     / use if you don't care about order
//  	TreeMap - entries are stored in key sequence         / use if you care about order and are doing a lot of searching
// 		LinkedHashMap - entries are stored in entry sequence / use if you want stored the same order you entered
		
// To define a map: 
// 		Map<key-datatype, value-datatype> nameOfMap = new TypeOfMap();
//  	TypeOfMap<key-datatype, value-datatype> nameOfMap = new TypeOfMap();
		
// Define a Map where the key is a String and the value is a String
		
//      Map<String, String> myMap = new HashMap();
//      HashMap<String, String> myMap = new HashMap();
//      Map<String, String> myMap = new HashMap<String, String>();
//      HashMap<String, String> myMap = new HashMap<String, String>();
		
// Define a Map to associate a person's name to where they live: ex. "Frank" - "Mayfield"
// Key - person's name (a String), Value - where they live (a String)
	
		//   key  ,  value     name     type-of-Map
		Map<String, String> residence = new LinkedHashMap(); // Decide how you want the data stored
		
// Let's add a few people to the map
//     .put(key, value) will add an entry to the map
		residence.put("Frank", "Mayfield");
		residence.put("Jeff", "Istanbul");
		residence.put("Brianna", "North Tonawanda");
		residence.put("D", "Wakanda");
		residence.put("Sam", "Youngstown");
		residence.put("Avery", "Cleveland Heights");
		residence.put("Daniel", "Lakewood");
		residence.put("Wally", "Mayfield");
		
// Use .get(key-value) to access a map using the key
//     .get returns a null if the key is not in the Map
												 //  Case matters
	    System.out.println("D lives in " + residence.get("D"));
	    System.out.println("Brianna lives in " + residence.get("Brianna"));
	    System.out.println("Jeff lives in " + residence.get("Jeff"));
		
	    String name = "Sam";
	    
	    System.out.println(name + " lives in " + residence.get(name));
	    
	    System.out.println("----------------------------------");
	    
// To process all the entries in a Map you need to get a list of keys in the Map
//    and use to process the entries
	    
// We need to get the list of keys out of the Map and then iterate through that list using some form of a for-loop

// 	.keySet() returns the list of keys in a Map as a Set object

// A Set object is an instance of the Collection class Set 
	    
// Set class is an unordered set of elements that must be unique
//   different from ArrayList because elements in ArrayList do not have to be unique
	    
// to Define a Set: Set<datatype>
	    
// Get the list of keys in our Map
	    Set<String> theKeys = residence.keySet(); // String is used because the keys are Strings
	    
// Go through the set of keys using a for-loop since we want to process all of the keys
//      for(datatype nameOfAnElement : objectWithElements)	    
	    for(String i : theKeys) { // Loop through theKeys one at a time assigning the current key to anElement
	    	String theValue = residence.get(i); // Get the value for the current key and store it in theValue
	    	System.out.println(i + " lives in " + theValue); // Display the person's name and where they live
	    }
	
	    System.out.println("----------------------------------");

// .remove(key) will remove an entry from the Map
	    residence.remove("Frank"); // Remove the entry with the key Frank
	    
	    System.out.println("Frank lives in " + residence.get("Frank"));
	    
	    System.out.println("----------------------------------");

// Since keys must be unique in a Map, if you try to add a key that already exists, the value for the key will be updated
	    System.out.println("Try to add Daniel living in Timbuktu");
	    System.out.println();
	    
	    residence.put("Daniel", "Timbuktu"); // Updated Daniel's value from Lakewood to Timbuktu because a key cannot be reused
	   
	    System.out.println("Daniel lives in " + residence.get("Daniel"));
	    
	    for(String i : theKeys) { // Loop through theKeys one at a time assigning the current key to anElement
	    	String theValue = residence.get(i); // Get the value for the current key and store it in theValue
	    	System.out.println(i + " lives in " + theValue); // Display the person's name and where they live
	    }
	
	}

}
