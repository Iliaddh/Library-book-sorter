/**
 * Full Name: Ilyad Hemmatjoo
 * ID: 40205441
 * Course: COMP249
 * Assignment: Assignment 1
 * Due Date: November 11, 2023
 */

// -----------------------------------------------------
// Assignment (5)
// Class: (1)
// Written by: (Ilyad Hemmatjoo ---- ID: 40205441)
// -----------------------------------------------------





package booksInput;

import java.io.Serializable;

public class Book implements Serializable{

	String title;
	String authors;
	double price;
	String isbn;
	int year;
	String genre;
	
	public Book() {
		this.title = "";
		this.genre=  "";
		this.authors = "";
		this.price = 0.0;
		this.year = 0;
		this.isbn=  "";
	}
	
	public Book (String title, String author, double price, String isbn,String genre, int year  ) {
		this.title=  title;
		this.authors = author;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}
	
	public void setTitle(String t) {
		this.title = t;
	}
	
	public void setAuthor(String a) {
		this.authors = a;
	}
	public void setPrice(double p) {
		this.price = p;
	}
	public void setIsbn(String i) {
		this.isbn = i;
	}
	public void setGenre(String g) {
		this.genre = g;
	}
	public void setYear(int y) {
		this.year = y;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAtuthor() {
		return this.authors;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	
	public String getGenre() {
		return this.genre;
	}
	
	
	public int getYear() {
		return this.year;
	}
	
	public String toString() {
		return "The title of this book is "+ this.title+ " and the author is " + this.authors+ " and the price: "+ this.price+ " the ISBN is "+ this.isbn+ ", the genre is "+ this.genre +" and the year is "+ this.year;
		
	}
	
	public boolean equals(Object other) {
        if (this == other) {
            return true; // Both references point to the same object
        }

        if (other == null || getClass() != other.getClass()) {
            return false; // The other object is null or of a different class
        }

        Book otherBook = (Book) other; // Cast the other object to a Book

        // Compare the attributes of both Book objects for equality
        return Double.compare(otherBook.price, price) == 0 &&
               year == otherBook.year &&
               title.equals(otherBook.title) &&
               authors.equals(otherBook.authors) &&
               isbn.equals(otherBook.isbn) &&
               genre.equals(otherBook.genre);
    }
	
	
	
	
}
