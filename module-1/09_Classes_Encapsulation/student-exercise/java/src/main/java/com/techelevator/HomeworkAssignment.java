package com.techelevator;

public class HomeworkAssignment {

	// private means only members of the class have access to this data
	//         outsiders need methods
	private int    earnedMarks;
	private int    possibleMarks;
	private String submitterName;
	
	public int getEarnedMarks() {
		return earnedMarks;
	}
	public void setEarnedMarks(int earnedMarks) {
		this.earnedMarks = earnedMarks;
	}
	public int getPossibleMarks() {
		return possibleMarks;
	}
	public String getSubmitterName() {
		return submitterName;
	}
	// Constructor - 2 arguments
	public HomeworkAssignment(int possibleMarks, String submitterName) {
		this.possibleMarks = possibleMarks; // set the data member to the parameter
		this.submitterName = submitterName; // set the data member to the parameter
	}
	
	// a derived attribute/value is one that a method determines and returns
	// method to determine the derived value letterGrade
	// we make it public so anyone with an object of this class can access the method
	public String getLetterGrade() {
		String letterGrade = ""; // hold the letterGrade to be returned
		
//                         
		double grade = (double)earnedMarks / possibleMarks; // we are allowed to use the data members
		                                            // because this method is members of the class
		if(grade >= 0.90) {
			letterGrade = "A";
		} else if(grade >= 0.80) {
			letterGrade = "B";
		} else if(grade >= 0.70) {
			letterGrade = "C";
		} else if(grade >= 0.60) {
			letterGrade = "D";
		} else {
			letterGrade = "F";
		}
		
		return letterGrade;      // return the letterGrade
	}
	
}

