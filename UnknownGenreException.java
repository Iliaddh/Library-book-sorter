/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (2)
// Class: (9)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------




package booksInput;

public class UnknownGenreException extends Exception {
	
	public UnknownGenreException() {
		super("Unknown genre detected");
	}
	
	public UnknownGenreException(String message) {
		super(message);
	}
}
