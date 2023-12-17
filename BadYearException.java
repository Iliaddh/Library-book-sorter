/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (2)
// Class: (1)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------




package booksInput;

public class BadYearException  extends Exception{
	public BadYearException() {
		super("The year in invalid");
	}
	
	public BadYearException(String message) {
		super(message);
	}
}
