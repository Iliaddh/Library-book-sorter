/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (2)
// Class: (8)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------






package booksInput;

public class TooManyFieldException extends Exception {

	public TooManyFieldException() {
		super("Too many fields detected");
	}
	
	public TooManyFieldException(String message) {
		super(message);
	}
}
