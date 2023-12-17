/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (2)
// Class: (6)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------






package booksInput;

public class MissingFieldException extends Exception {

	public MissingFieldException() {
		super("Missing fields detected");
	}
	
	public MissingFieldException(String message) {
		super(message);
	}
}
