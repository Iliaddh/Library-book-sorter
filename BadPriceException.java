/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (2)
// Class: (4)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------






package booksInput;

public class BadPriceException extends Exception {

	public BadPriceException() {
		super("The price in invalid");
	}
	
	public BadPriceException(String message) {
		super(message);
	}
}
