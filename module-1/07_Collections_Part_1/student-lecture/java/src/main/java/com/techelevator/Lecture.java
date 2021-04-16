package com.techelevator;

// The import statement brings Java code into your code that you need for what you are doing
// Usually this is code for Classes you use but haven't defined yourself

// The format of import statment: package-name.ClassName
//      package /  Class
import java.util.ArrayList; // give me access to the code that supports ArrayLists
import java.util.Collections;

public class Lecture {

	public static void main(String[] args) {
		System.out.println("####################");
		System.out.println("       LISTS");
		System.out.println("####################");

// ArrayList is a type of List that acts similarly to an array
// ArrayList has elements that are stored in the order in which they were added

// Two ways to define a new ArrayList
		
		// ArrayList<data-type-it-holds> name = new ArrayList();
		//      List<data-type-it-holds> name = new ArrayList();  - more about this next week
		
		// Define a new ArrayList to hold Strings representing people's names
		
		ArrayList<String> names = new ArrayList(); // Defined a new empty ArrayList of Strings
		
		// the .add() method is used to add elements to an ArrayList
		names.add("Frank");
		names.add("Jason"); // Adding a String Object to the ArrayList
		names.add("Sam");
		names.add("Avery");
		names.add("Daniel");
		names.add("D");
		names.add("Joel");
//		names.add(1); - error because we are trying to add an int to an ArrayList of Strings
		
		// the .size() method returns the number of elements in an ArrayList
		
		System.out.println("Our ArrayList contains " + names.size() + " elements.");
		
		System.out.println("####################");
		System.out.println("Lists are ordered in the order they are entered");
		System.out.println("####################");

		// the .get(index) method will retrieve the element at the index specified - similar to array[index]
		
		for(int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i)); // display the name at index i
		}
		
		
		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");

		names.add("Avery"); // ArrayList can hold duplicates, so we can add a second "Avery"

		for(int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i));
		}
		
		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");

		// .add(index, object) - index is the index where you want the object to be inserted
		
		// insert an element at index 3
		names.add(3, "Anne"); // Place "Anne" at index 3 and push every other object down
		
		for(int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i));
		}
		
		System.out.println("####################");
		System.out.println("Lists allow elements to be removed by index");
		System.out.println("####################");

		// .remove(index) - removes the element at the index specified, move everything else up
		names.remove(0);
		
		for(int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i));
		}
		
		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");

		// .contains(object) - return true if the object is in the ArrayList, false if not
		
		System.out.println("Is Joel in the list? " + names.contains("Joel"));
		System.out.println("Is Frank in the list? " + names.contains("Frank"));
		
		if(names.contains("Anne")) {
			System.out.println("Anne is in the list.");
		} else {
			System.out.println("Anne is not in the list.");
		}
		
		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");

		// .indexOf(object) - returns the index of the first occurence of the object in the ArrayList
		System.out.println("The first time Avery appears in the list is at index: " + names.indexOf("Avery"));
		
		// .lastIndexOf(object) - returns the index of the last occurence of the object in the ArrayList
		System.out.println("The last time Avery appears in the list is at index: " + names.lastIndexOf("Avery"));
		
		
		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");

		// .toArray(new data-type[0]) - convert an ArrayList into an array and return the array
		
		// Convert the ArrayList<String> into a String[]
		
		String[] namesArray = names.toArray(new String[0]); // [0] hold a place the # of the elements 
		
		// Note: .length for an array doesn't use () because it is a property of the array
		//       .size() for an ArrayList DOES use () because size is a method in the ArrayList Class 
		
		//        a property is a value automatically associated with a data structure
		
		//        whenever you see a word that is not 'if', 'for', or 'while', followed by (), it is a method
		for(int i=0; i < namesArray.length; i++) {
			System.out.println("Element #" + i + ": " + namesArray[i]);
		}
		
		
		System.out.println("####################");
		System.out.println("Lists can be sorted");
		System.out.println("####################");

		// Collections.sort(ArrayListName) - The Collections Class provides a sort() method 
		//                                   that can be used with any type of Collection object

		Collections.sort(names);  // Use the Collections Class sort() method to sort the ArrayList
		                          // Since the datatype of the ArrayList is a String, it will sort alphabetically
		
		for(int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i));
		}
		
		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");

        Collections.reverse(names); // Use Collections class reverse() method to reverse the order of the list
		
        for(int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i));
		}
        
		System.out.println("####################");
		System.out.println("       FOREACH");
		System.out.println("####################");
		System.out.println();

		// for-each loop is a shorthand for-loop when you need to go through the entire ArrayList/array
		// for-each loop always goes from the beginning to the end of the ArrayList/array, no starting or stopping anywhere else
		// you cannot skip elements with a for-each loop - all elements are always processed
		// you don't know which element is being processed in the loop - there is no index
		
		// format of for-each loop: for(datatype element-name : ArrayList/array-name
		//                          each time through the loop, the element-name is assigned the current element in the ArrayList/array
		//                          use the element-name in the loop to reference the current element of the array being processed
		
		// Use for-each loop to display all the elements in the names ArrayList
		// current element : ArrayList-name
		for(String aName : names) { // Similar to for(i=0; i < names.size(); i++)
			System.out.println("Current element is: " + aName); // in a regular for loop we'd use names.get(i)
		}
		
	}
}
