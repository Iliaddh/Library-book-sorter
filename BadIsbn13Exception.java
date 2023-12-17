/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (3)
// Class: (1)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------




package booksInput;

public class BadIsbn13Exception extends Exception {
	public BadIsbn13Exception() {
		super("This 13 digit ISBN is not a multiple of 10");
	}
	
	public BadIsbn13Exception(String message) {
		super(message);
	}
}
