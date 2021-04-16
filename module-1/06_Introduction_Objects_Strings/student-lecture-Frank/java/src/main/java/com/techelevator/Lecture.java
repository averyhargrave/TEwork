package com.techelevator;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("************************************");
		System.out.println("****** MAKING A STRING OBJECT ******");
		System.out.println("************************************");

		/* The String class gets special treatment in the Java language.  One
		 * example of this is that there is a literal representation of a
		 * String (i.e. characters appearing between two double quotes.  This
		 * is not the case for most classes */

		/* create an new instance of String using a literal
		 * 
		 * Two ways to create a new string:
		 *  	
		 *  	String name = new String(); // Create a new empty String
		 *  
		 *  	String name = "some value"; // Create a new String with a value
		 *  
		 *  A String literal is encolsed in " " - a series of one or more characters
		 *  
		 *  A char literal is enclosed in ' '   - a char is a SINGLE character
		 *  
		 *  a String is NOT a char and a char is NOT a String
		 *  	
		 *  */

		String softDrink = "Diet Mtn Dew";
		
		String beverage = softDrink + " by Pepsi"; // + with Strings is concatenation
		
		System.out.println(beverage); // expecting to see "Diet Mtn Dew by Pepsi"


//--------------------------------------------------------------------------------------------------------		
		System.out.println();
		System.out.println("******************************");
		System.out.println("****** MEMBER METHODS ******");
		System.out.println("******************************");
		System.out.println();

		/* 
		 * String is a class
		 * 
		 * a Class is a description of what an object of the class should look like
		 *     and what the object can do - A LOT the rest of the week
		 *     
		 * an Object is an instance of a class
		 * 
		 * in cooking, a Recipe describes the ingredients and process for creating a food dish
		 *
		 * a cake recipe describes how to make a cake - it is NOT the cake
		 * 
		 * the cake that you make from a recipe is an instance of the recipe (an Object)
		 * 
		 * a Class contains methods to interact and manipulate the Objects of the Class
		 * 
		 * a method is a function associated with a Class
		 * 
		 * a member method is a method associated with a specific Class
		 * 
		 * to use a method for a Class: anObjectOfTheClass.method(parameters);
		 */
		
		
		/* Commonly used String methods:  stringname.methodname(parameters)
		 *
		 * .length() - return the number of characters in a String
		 * .charAt(index) - returns the character at the index - 0 is first character
		 * .endsWith(search-string) - return true if the String ends with the search-string 
		 * .startsWith(search-string) - return true if the String starts with the search-string
		 * .indexOf(search-string) - return the index of the start of the 1st occurrence of search-string in the String
		 * .lastIndexOf(search-string) - return the index of the start of the last occurrence of search-string in the String
		 * .substring(start-index) - return from the start-index to rest of String
		 * .substring(start-index,end-index) - return from the start-index to the end-index (but not the char at end-index)
		 * .toLowerCase() - return the String in all lowercase
		 * .toUpperCase() - retrn the String in all uppercase
		 * .trim() - return the String with spaces removed from beginning and end of the String
		 * .split(delimiter) - returns and array of strings containing the characters between the delimiter specified
		 */
		//    indexes: 01234567891123
		String name = "Baker Mayfield";
		
		System.out.println("The variable name contains: " + name);
		System.out.println("The length of the value in name is: " + name.length());
		
		System.out.println("Does name end with the word field? " + name.endsWith("field"));
		System.out.println("Does name end with the word Baker? " + name.endsWith("Baker"));
		
		System.out.println("Does name start with the word field? " + name.startsWith("field"));
		System.out.println("Does name start with the word Baker? " + name.startsWith("Baker"));
		System.out.println("Does name end with the word baker? " + name.endsWith("baker"));     // case is important
		
		if(name.startsWith("Baker")) {
			System.out.println("Yay!");
		} else {
			System.out.println("Boooo!");
		}
		
		System.out.println("Does name contain with the word May? " + name.contains("May"));
		
		// charAt(index) - the index starts at 0 - 
		System.out.println("The character at position 4 is: " + name.charAt(3));
		System.out.println("The character at position 8 is: " + name.charAt(8));  // display the 'y' in Baker Mayfield
		
		System.out.println("Where is the 'f' in name? " + name.indexOf('f'));
		System.out.println("name in all lowercase is: " + name.toLowerCase());
		System.out.println("name in all uppercase is: " + name.toUpperCase());
		
		// you can chain methods passing the result from one method to the next
		System.out.println("Where is the 'F' when I capitalize it?: " + name.toUpperCase().indexOf("F"));
		
		// substring(index) - give me chars from the index specifed to the end
		System.out.println("Chars from the fifth  to the end in name: " + name.substring(4));
		
		// substring(start-index,end-index) - give me chars from the starting index
		//                                    to the ending index but EXCLUDING the ending index
		//                                    specify the end-index to be one more than the one you want
		System.out.println("Chars from the fifth to the eighth in name: " + name.substring(4,8));
		
		
		
//--------------------------------------------------------------------------------------------------------	
		
		/*   A String may also be created easily from an array of characters (char) */

		char[] helloArray = new char[] { 'H', 'e', 'l', 'l', 'o' }; // Array of characters (String)
		
		String hello1 = new String(helloArray);  // Create a String using the array of characters
		String hello2 = new String(helloArray);  // Create a String using the array of characters
		
//--------------------------------------------------------------------------------------------------------			
		System.out.println();
		System.out.println("**********************");
		System.out.println("****** EQUALITY ******");
		System.out.println("**********************");
		System.out.println();

		/* Double equals will compare to see if the two variables, hello1 and
		 * hello2 point to the same object in memory. Are they the same object? */

		if (hello1 == hello2) {
			System.out.println("They are equal!");
		} else {
			System.out.println(hello1 + " is not equal to " + hello2);
		}

		String hello3 = hello1;
		if (hello1 == hello3) {
			System.out.println("hello1 is the same reference as hello3");
		}

		/* So, to compare the values of two objects, we need to use the equals method.
		 * Every object type has an equals method */
		if (hello1.equals(hello2)) {
			System.out.println("They are equal!");
		} else {
			System.out.println(hello1 + " is not equal to " + hello2);
		}

//--------------------------------------------------------------------------------------------------------			
		System.out.println();
		System.out.println("**************************");
		System.out.println("****** USING .split ******");
		System.out.println("**************************");
		System.out.println();

		String movieTitle = "Eternal Sunshine of the Spotless Mind";
		
		// We want to display each word in the movie title on a separate line
		// That means we need to split the movie title based on the space
		// the String.split method will separate a String into parts based on a character you give it
		// .split method returns an array of Strings with the parts of the original String
		
		String[] wordsInTitle = movieTitle.split(" "); // separate parts of movieTitle based on the space " "
		
		System.out.print("Movie Title: " + movieTitle); // .print method does NOT go to a new line when it's done
		System.out.println(" and it contains " + wordsInTitle.length + " words\n"); // '\n' is a new line character to give us a blank line
		
		for (int i=0; i<wordsInTitle.length; i++) {
			System.out.println("Word #" + (i+1) + " is " + wordsInTitle[i]);
		}
		
		System.out.println();
		movieTitle = "The good, the bad, and the ugly";
		
		// We want to display each word in the movie title on a separate line
		// That means we need to split the movie title based on the space
		// the String.split method will separate a String into parts based on a character you give it
		// .split method returns an array of Strings with the parts of the original String
		
		wordsInTitle = movieTitle.split(","); // separate parts of movieTitle based on the comma ","
		
		System.out.print("Movie Title: " + movieTitle); // .print method does NOT go to a new line when it's done
		System.out.println(" and it contains " + wordsInTitle.length + " phrases\n"); // '\n' is a new line character to give us a blank line
		
		for (int i=0; i<wordsInTitle.length; i++) {
			System.out.println("Phrase #" + (i+1) + " is " + wordsInTitle[i].trim()); // .trim() will remove leading and trailing spaces
		}
	}
}
