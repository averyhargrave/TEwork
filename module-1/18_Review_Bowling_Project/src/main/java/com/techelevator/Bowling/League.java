package com.techelevator.Bowling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class League {

	private String leagueName;
	private Map<Integer, ArrayList<Bowler>> leagueMembers; // key = team Number, value = a set of Bowlers
	
// Default constructor
	public League() { // default constructors take no parameters
		leagueName    = null;
		leagueMembers = null;
	}
	
// 2-arg constructor
	public League(String leagueName, String dataFileName) throws FileNotFoundException {
		this.leagueName = leagueName; // this. is required because the data name and the parameter name are the same
// Load the data from the File given into our leagueMembers using a member function
		loadFile(dataFileName); // run the loadFile method sending the file to be loaded
		
	}
	
// Method to load the file given into leagueMembers - private because we don't want users to be able to run it
// Only members of this class can run it
	private void loadFile(String fileNameToLoad) throws FileNotFoundException {
		File aFile = new File(fileNameToLoad);      // Define a File object for the file
		Scanner inputDataFile = new Scanner(aFile); // Define a Scanner for the File object
		
		// Define variables to hold data from file
		int teamNumber = 0;
		int bowlerNumber = 0;
		String bowlerName = "";
		ArrayList<Integer> scores = new ArrayList<>(); // Define a place to hold a set scores
		
		while(inputDataFile.hasNext()) { // Loop while the file has lines to read
			String aLine = inputDataFile.nextLine(); // read a line from the file
			String[] valuesInLine = aLine.split(",");  // separate the values at each , in the data file
		
			// Assign the values from the file into the Bowler variables
			teamNumber   = Integer.parseInt(valuesInLine[0]); // convert the value from the first index to an int and store it
			bowlerNumber = Integer.parseInt(valuesInLine[1]); // convert the value from the second index to an int and store it
			bowlerName   = valuesInLine[2];                   // Store the third value from the line
			
			scores.add(Integer.parseInt(valuesInLine[3]));    // Convert first score to an int and add it to the ArrayList
			scores.add(Integer.parseInt(valuesInLine[4]));    // Convert second score to an int and add it to the ArrayList
			scores.add(Integer.parseInt(valuesInLine[5]));    // Convert third score to an int and add it to the ArrayList
			// We have all the data from the file stored in variables
			// Now we can define a Bowler
			Bowler aBowler = new Bowler(teamNumber, bowlerNumber, bowlerName, scores);
			
			// Create an ArrayList and add it to the Map using teamNumber
		}
	}
}
