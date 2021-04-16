package com.techelevator;

public class Elevator {

	private int currentFloor = 1;
	private int numberOfFloors;
	private boolean doorOpen;
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	public int getNumberOfFloors() {
		return numberOfFloors;
	}
	public boolean isDoorOpen() {
		return doorOpen;
	}
	
	public Elevator(int numberOfLevels) {
		this.numberOfFloors = numberOfLevels;
	}
	
	public void openDoor() {
		if(doorOpen || !doorOpen) {
			doorOpen = true;
		}
	}
	public void closeDoor() {
		if(doorOpen || !doorOpen) {
			doorOpen = false;
		}
	}
	public void goUp(int desiredFloor) {
		if(!doorOpen) {
			if(desiredFloor <= numberOfFloors && desiredFloor > currentFloor) {
				currentFloor = desiredFloor;
		}
	}
	}
	public void goDown(int desiredFloor) {
		if(!doorOpen) {
			if(desiredFloor >= 1 && desiredFloor < currentFloor) {
				currentFloor = desiredFloor;
			}
		}
	}
}
