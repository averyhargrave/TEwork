package com.techelevator;

// The import statement includes Java code into your code that you need for what you are doing
// Usually this is code for Classes you use, but have defined yourself in this code
//
//      format of import statement:   package-name.ClassName
//       package.Class
import java.util.ArrayList;  // Give me access to the code that supports ArrayList class
import java.util.Collections;// Give me access to the code that supports Collections class

public class Lecture {

	public static void main(String[] args) {
		System.out.println("####################");
		System.out.println("       LISTS");
		System.out.println("####################");

// ArrayList is a type of List that acts similar to an array
//           ArrayList has elements, stored in the order in which they are added to the ArrayList

		// Two ways to define a new Arraylist:
		//
		// ArrayList<data-type-it-holds>  name = new ArrayList();
		//      List<data-type-it-holds>  name = new ArrayList();  // More about this next week
		
		// Define a new ArrayList to hold Strings representing people's names
		
		ArrayList<String> names = new ArrayList();   // new, empty ArrayList of Strings
		
		// the .add() method is used to add elements to an ArrayList
		names.add("frank");  // add a String object at top of ArrayList
		names.add("Jason");  // add a String object to the ArrayList
		names.add("Sam");
		names.add("Avery");
		names.add("Daniel");
		names.add("D");
		names.add("Joel");
//		names.add(1);   // error because we are trying to add an int to an ArrayList of Strings
		
		// .size() method returns the number of elements in an ArrayList
		
		System.out.println("Our ArrayList contains " + names.size() + " elements");

		System.out.println("####################");
		System.out.println("Lists are ordered in the order they are entered");
		System.out.println("####################");

		// .get(index) - will retrieve the element at the index specified - similar to array[index]
		
		for (int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i)); // display the element at index i
		}
		
		
		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");

		names.add("Avery");  // We can "Avery" even though it is already in the ArrayList

		for (int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i)); // display the element at index i
		}
		
		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");

		// .add(index, object) - index is the index where you want the object to be inserted
		
		// Insert an element at index 3
		names.add(3,"Anne");  // Place "Anne" at index 3 and push elements down in the ArrayList
		
		for (int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i)); // display the element at index i
		}
		
		System.out.println("####################");
		System.out.println("Lists allow elements to be removed by index");
		System.out.println("####################");
		
		// .remove(index) - remove the element at the index and move all elements up one
		
		names.remove(0);  // remove the element at index 0
		
		for (int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i)); // display the element at index i
		}
		
		
		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");
		
		// .contains(object) - return true if the object is in the ArrayList, false, if not
		
		System.out.println("Is Joel in the ArrayList? " + names.contains("Joel"));
		System.out.println("Is frank in the ArrayList? " + names.contains("frank"));
		
		if (names.contains("Anne")) {
			System.out.println("Anne is in our list");
		}
		else {
			System.out.println("Anne is not in the list");
		}

		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");

		// .indexOf(object) - return the index of the first occurrance of the object in the ArrayList
		
		System.out.println("the first time Avery appears in the list is at index " + names.indexOf("Avery"));
		
		// .lastIndexOf(object) - return the index of teh last occurance of the object in the ArrayList
		System.out.println("the last time Avery appears in the list is at index " + names.lastIndexOf("Avery"));

		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");

		// .toArray(new datatype[0]) - convert an ArrayList into an array of type specified and return the array 
		
		// Convert the ArrayList<String> into a String[] (array of Strings)
		
		String [] namesArray = names.toArray(new String[0]); // [0] hold a place the # of elements
		
		// Note: .length for and array does not have () - because length a property of array
		//       .size() for an ArrayList HAS () - because size() is method in the ArrayList class
		//                                         methods always have ()
		//       a property is a value automatically associated with a data structure
		//
		//       Whenever you see a word, that is not if, for or while, followed by (), it's method name
			
		for (int i=0; i < namesArray.length; i++) {
			System.out.println("Element #" + i + ": " + namesArray[i]); // display the element at index i
		}
		
		

		System.out.println("####################");
		System.out.println("Lists can be sorted");
		System.out.println("####################");

		// Collections.sort(ArrayListName) - The Collections class provides a sort() method
		//                                   that can be used with any type of Collection class object
		
		Collections.sort(names);  // Use the Collections class sort() method to sort the ArrayList
		                          //     Since the datatype of the ArrayList is String, it will sort alphabetically
		
		for (int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i)); // display the element at index i
		}
		
		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");

		Collections.reverse(names); // Use the Collections class reverse() to reverse the order of the list
		
		for (int i=0; i < names.size(); i++) {
			System.out.println("Element #" + i + ": " + names.get(i)); // display the element at index i
		}
		

		System.out.println("####################");
		System.out.println("       FOREACH");
		System.out.println("####################");
		System.out.println();

		// for-each loop is shorthand for-loop when you need to go through the entire array/ArrayList
		//
		// for-each always goes from the beginning to end of array/ArrayList - cannot start and stop anywhere else
		// Cannot skip elements with a for-each loop - All elements are always processed
		// You don't know which element is being processed in the loop - there is no index
		
		// format of for-each loop:  for(datatype elem-name : array/ArrayList-name)
		//
		//                           each time through the loop the elem-name is assigned the current element
		//                           use the elem-name in the loop to reference current element being processed
		
		// Use a for-each loop to display all the elements in the names ArrayList
	
		//    curr-elem  : ArrayList
		for(String aName : names) {  // Similar to for(int i=0; i < names.size(); i++)
			System.out.println("Current element is: " + aName);  // in a regular for-loop we'd uses names.get(i)
		}

	}
}
