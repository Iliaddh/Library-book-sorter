/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (2)
// Class: (7)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------



package booksInput;

public class TooFewFieldsException extends Exception {

	public TooFewFieldsException() {
		super("Too few fields detected");
	}
	
	public TooFewFieldsException(String message) {
		super(message);
	}
}
