package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSearch {

	public static void main(String[] args) throws FileNotFoundException {
	
		File theFile = new File("./alices_adventures_in_wonderland.txt");
		Scanner fileReader = new Scanner(theFile);
		Scanner keyboard = new Scanner(System.in);

		System.out.println("What is the word you are searching for?");
		String input = ""; 
		input = keyboard.nextLine();
		
		System.out.println("Should the search be case sensitive? (Y/N)");
		String caseSensitive = "";
		caseSensitive = keyboard.nextLine();
		
		int lineCounter = 0;
		
		String line = "";
		
		if(caseSensitive.equalsIgnoreCase("Y")) {
			while(fileReader.hasNextLine()) {
				line = fileReader.nextLine();
				lineCounter++;
				String[] split = line.split(" ");
				for(int i=0; i < split.length; i++) {
					if(split[i].equals(input)) {
						System.out.println(lineCounter + ") " + line);
					}
				}	
			}
		}
		if(caseSensitive.equalsIgnoreCase("N")) {
			while(fileReader.hasNextLine()) {
				line = fileReader.nextLine();
				lineCounter++;
				String[] split = line.split(" ");
				for(int i=0; i < split.length; i++) {
					if(split[i].equalsIgnoreCase(input)) {
						System.out.println(lineCounter + ") " + line);
					}
				}	
			}
		}
		fileReader.close();
	} 
}

