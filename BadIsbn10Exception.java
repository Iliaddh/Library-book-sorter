/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (2)
// Class: (2)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------



package booksInput;

public class BadIsbn10Exception extends Exception {
	public BadIsbn10Exception() {
		super("This 10 digit ISBN is not a multiple of 11");
	}
	
	public BadIsbn10Exception(String message) {
		super(message);
	}
}
