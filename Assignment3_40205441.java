

//This program Opens a file that includes some .txt file containing some information about books. First, in check whether it's date and price and ISBN format is syntatically correct, then it stores
//the valid books into genre specific .txt files. If a syntax error found , it will record it in a text file. In the second part of the program, it reads those syntatically valid books, and this time checking for semantic errors.
//It checks the overall format of the record, such as number of fields, and if any found, it will record it in .txt file , and it will through a specific exception. If a book record is semantically valid, it will will create a Book object from it,
// and it will store it in it's genre specific array. Finally it serializes the result array and writes them as a Book object in binary files. In the last part of the program, interactive menu reads those arrays and lines,and based on the commands of 
// user , it displays the desired number of books either upward or downward .


package booksInput;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import booksInput.MissingFieldException;
import booksInput.TooFewFieldsException;
import booksInput.TooManyFieldException;
import booksInput.UnknownGenreException;

public class Assignment3_40205441{
	
	//A method to help with price Validation
	public static boolean priceValidation(String price, String fileName, String curentLine) {
		PrintWriter pw = null;
		double PriceDoubleValue = Double.parseDouble(price);
		if(PriceDoubleValue< 0) {
			try {
				pw = new PrintWriter(new FileOutputStream("semantic_error_file.txt",true));
				pw.println("Semantic error in file: " + fileName + "=====================================");
				pw.println("Error: invalid price \nRecord : " + curentLine);
				pw.println("\n\n");
				pw.flush();
				pw.close();
				throw new BadPriceException();
			}catch(BadPriceException e) {
				System.out.println(e.getMessage());
			}catch(IOException e) {
				System.out.println("IOException");
			}
			return false;
		}else {
			return true;
		}
		
	}
	
	//A method to help with year Validation
	public static boolean yearValidation(String year, String fileName, String curentLine) {
		PrintWriter pw = null;
		int YearintValue = Integer.parseInt(year);
		System.out.println(YearintValue);
		if(YearintValue >= 1995 && YearintValue<= 2010) {
			return true;
		}else {
			try {
				
				pw = new PrintWriter(new FileOutputStream("semantic_error_file.txt", true));
				pw.println("Semantic error in file: " + fileName + "=====================================");
				pw.println("Error: invalid year \nRecord : " + curentLine);
				pw.println("\n\n");
				pw.flush();
				pw.close();
				throw new BadYearException();
			}catch(BadYearException e) {
				System.out.println(e.getMessage());
			}catch(IOException e) {
				System.out.println("IOException");
			}
			return false;
		}
	}
	
	//A method to help with 10 digit ISBN Validation
	public static boolean ISBNValidation10(String isbn, String fileName, String curentLine) {
		
		    PrintWriter pw = null;
			int sum =0 ;
			int j = 10;
			for(int i =0  ;i<isbn.length(); i++) {
				int digit = Character.getNumericValue(isbn.charAt(i));
				sum += digit * j;
				j--;
			}
			if(sum % 11 ==0) {
				return true;
			}else {
				
				try {
					
					pw = new PrintWriter(new FileOutputStream("semantic_error_file.txt", true));
					pw.println("Semantic error in file: " + fileName + "=====================================");
					pw.println("Error: invalid 10 digit ISBN \nRecord : " + curentLine);
					pw.println("\n\n");
					pw.flush();
					pw.close();
					throw new BadIsbn10Exception();
					
				}catch(BadIsbn10Exception e) {
					System.out.println(e.getMessage());
				}catch(IOException e) {
					System.out.println("IOException");
				}
				return false;
			}
			
		
	}
	
	//A method to help with 13 digit ISBN Validation
	public static boolean ISBNValidation13(String isbn, String fileName, String curentLine) {
			PrintWriter pw = null;
			int sum =0 ;
			for(int i =0  ;i<isbn.length(); i++) {
				
				int digit = Character.getNumericValue(isbn.charAt(i));
				int j = (i % 2 == 0) ? 1 : 3;
				sum += digit * j;
				j+=2;
			}
			if(sum % 10 == 0) {
				return true;
			}else {
				
				
				try {
					
					pw = new PrintWriter(new FileOutputStream("semantic_error_file.txt", true));
					pw.println("Semantic error in file: " + fileName + "=====================================");
					pw.println("Error: invalid 13 digit ISBN \nRecord : " + curentLine);
					pw.println("\n\n");
					pw.flush();
					pw.close();
					throw new BadIsbn13Exception();
					
				}catch(BadIsbn13Exception e) {
					System.out.println(e.getMessage());
				}catch(IOException e) {
					System.out.println("IOException");
				}
				return false;
			}
			
	}
	
	//                  -----------------------------------------------------------PART 1-----------------------------------------
	public static void do_part1() {
		int countOfBooksArray = 0;
    	int countOfLines = 0;
    	
    	int CartoonsLineNumber = 0;
        int HobbiesLineNumber = 0;
        int MoviesLineNumber = 0;
        int MusicLineNumber = 0;
        int NostalgiaLineNumber = 0;
        int OldTimeLineNumber = 0;
        int SportsLineNumber = 0;
        int TrainsLineNumber = 0;
    	
        //Empty arrays to be filled with valid syntax books
    	String[] CartoonsComics = new String[2000];
    	String[] HobbiesCollectibles = new String[2000];
    	String[] MoviesTVBooks = new String[2000];
    	String[] MusicRadioBooks = new String[2000];
    	String[] NostalgiaEclecticBooks = new String[2000];
    	String[] OldTimeRadioBooks= new String[2000];
    	String[] SportsSportsMemorabilia = new String[2000];
    	String[] TrainsPlanesAutomobiles = new String[2000];
    	
    
    	//Empty arrays to be filled with vsyntax errors specific to each book
    	String[] SyntaxErrorsbooks1995 = new String[2000];
    	String[] SyntaxErrorsbooks1996 = new String[2000];
    	String[] SyntaxErrorsbooks1997 = new String[2000];
    	String[] SyntaxErrorsbooks1998 = new String[2000];
    	String[] SyntaxErrorsbooks1999 = new String[2000];
    	String[] SyntaxErrorsbooks2000 = new String[2000];
    	String[] SyntaxErrorsbooks2001 = new String[2000];
    	String[] SyntaxErrorsbooks2002 = new String[2000];
    	String[] SyntaxErrorsbooks2003 = new String[2000];
    	String[] SyntaxErrorsbooks2004 = new String[2000];
    	String[] SyntaxErrorsbooks2005 = new String[2000];
    	String[] SyntaxErrorsbooks2006 = new String[2000];
    	String[] SyntaxErrorsbooks2007 = new String[2000];
    	String[] SyntaxErrorsbooks2008 = new String[2000];
    	String[] SyntaxErrorsbooks2009 = new String[2000];
    	String[] SyntaxErrorsbooks2010 = new String[2000];
    	
    	//A two dimensional array that keeps the arrays of valid syntax books arrays
    	String[][] ValidbooksArray = {CartoonsComics,HobbiesCollectibles,MoviesTVBooks,MusicRadioBooks,NostalgiaEclecticBooks,OldTimeRadioBooks,SportsSportsMemorabilia,TrainsPlanesAutomobiles};
    	
    	//A two dimensional array that keeps the array of syntax errors
    	String[][] ErrorbooksArray = {SyntaxErrorsbooks1995,SyntaxErrorsbooks1996,SyntaxErrorsbooks1997,SyntaxErrorsbooks1998,SyntaxErrorsbooks1999,SyntaxErrorsbooks2000,SyntaxErrorsbooks2001,SyntaxErrorsbooks2002,SyntaxErrorsbooks2003,SyntaxErrorsbooks2004,SyntaxErrorsbooks2005,SyntaxErrorsbooks2006,SyntaxErrorsbooks2007,SyntaxErrorsbooks2008,SyntaxErrorsbooks2009,SyntaxErrorsbooks2010};
    	
    	//An array that keeps the names of file names
    	String[] pathNames = {"Cartoons_Comics.csv.txt","Hobbies_Collectibles.csv.txt","Movies_TV_Books.csv.txt","Music_Radio_Books.csv.txt","Nostalgia_Eclectic_Books.csv.txt","Old_Time_Radio.csv.txt","Sports_Sports_Memorabilia.csv.txt","Trains_Planes_Automobiles.csv.txt"};
 
    	

    	 try	{
    	 		 
    		 	//Scanner object to read input files
                Scanner scanner1 = new Scanner(new FileInputStream("part1_input_file_names.txt"));

                //Consume the file count line
                int fileCount = scanner1.nextInt();
                scanner1.nextLine();

                while (scanner1.hasNextLine()) {
                    String inputFileName = scanner1.nextLine();

                    try (Scanner scanner2 = new Scanner(new FileInputStream(inputFileName))) {

                        while (scanner2.hasNextLine()) {
                        	countOfLines++;
                            String line = scanner2.nextLine();
                            
                            //Splitting lines
                            String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                            
                            //Catching too fiew fileds exceptions
                            if (fields.length < 6) {
                            	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Too few fields\nRecord:  " + line + "\n";
                                
                                try {
                                	throw new TooFewFieldsException();
                                }catch(TooFewFieldsException  e) {
                                	System.out.println(e.getMessage());
                                }
                            }
                            
                          //Catching too many fileds exceptions
                            if (fields.length > 6) {
                            	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Too many fields\nRecord:  " + line + "\n";
                            	System.out.println("Error: Too many fields\nRecord:  " + line + "\n");
                                try {
                                	throw new TooManyFieldException();
                                }catch(TooManyFieldException  e) {
                                	System.out.println(e.getMessage());
                                }
                            }

                            if (fields.length == 6) {
                                String title = fields[0];
                                String authors = fields[1];
                                String price = fields[2];
                                String isbn = fields[3];
                                String genre = fields[4];
                                String year = fields[5];

                                if (title.isEmpty()) {
                                	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Missing title\nRecord:  " + line + "\n";
                                    try {
                                    	throw new MissingFieldException("Missing title");
                                    }catch(MissingFieldException e) {
                                    	System.out.println(e.getMessage());
                                    }
                                } else if (authors.isEmpty()) {
                                	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Missing author\nRecord:  " + line + "\n";
                                    
                                    try {
                                    	throw new MissingFieldException("Missing author");
                                    }catch(MissingFieldException e) {
                                    	System.out.println(e.getMessage());
                                    }
                                } else if (price.isEmpty()) {
                                	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Missing price\nRecord:  " + line + "\n";
                                   
                                    try {
                                    	throw new MissingFieldException("Missing price");
                                    }catch(MissingFieldException e) {
                                    	System.out.println(e.getMessage());
                                    }
                                } else if (isbn.isEmpty()) {
                                	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Missing ISBN\nRecord:  " + line + "\n";
                                	
                                    try {
                                    	throw new MissingFieldException("Missing ISBN");
                                    }catch(MissingFieldException e) {
                                    	System.out.println(e.getMessage());
                                    }
                                } else if (genre.isEmpty()) {
                                	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Missing genre\nRecord:  " + line + "\n";
                                
                                    try {
                                    	throw new MissingFieldException("Missing genre");
                                    }catch(MissingFieldException e) {
                                    	System.out.println(e.getMessage());
                                    }
                                } else if (year.isEmpty()) {
                                	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Missing year\nRecord:  " + line + "\n";
                                	
                                    try {
                                    	throw new MissingFieldException("Missing year");
                                    }catch(MissingFieldException e) {
                                    	System.out.println(e.getMessage());
                                    }
                                } else if (!genre.isEmpty()) {
                                    switch (genre) {
                                        case "CCB":
                                            CartoonsLineNumber++;
                                            ValidbooksArray[0][countOfLines] = line;
                                            break;
                                        case "HCB":
                                            HobbiesLineNumber++;
                                            ValidbooksArray[1][countOfLines] = line;
                                            break;
                                        case "MTV":
                                            MoviesLineNumber++;
                                            ValidbooksArray[2][countOfLines] = line;
                                            break;
                                        case "MRB":
                                            MusicLineNumber++;
                                            ValidbooksArray[3][countOfLines] = line;
                                            break;
                                        case "NEB":
                                            NostalgiaLineNumber++;
                                            ValidbooksArray[4][countOfLines] = line;
                                            break;
                                        case "OTR":
                                            OldTimeLineNumber++;
                                            ValidbooksArray[5][countOfLines] = line;
                                            break;
                                        case "SSM":
                                            SportsLineNumber++;
                                            ValidbooksArray[6][countOfLines] = line;
                                            break;
                                        case "TPA":
                                            TrainsLineNumber++;
                                            ValidbooksArray[7][countOfLines] = line;
                                            break;
                                        default:
                                        	ErrorbooksArray[countOfBooksArray][countOfLines] = "Error: Unknown genre\nRecord:  " + line + "\n";
                                            
                                            try {
                                            	throw new UnknownGenreException("Unknown genre");
                                            }catch(UnknownGenreException e) {
                                            	System.out.println(e.getMessage());
                                            }
                                    }
                                }
                            }
                          
                           
                        }
                        
                        
                    } catch (IOException e) {
                    	System.out.println(e.getMessage());
                    }
                    countOfBooksArray++;

                }

            } catch (IOException e) {
            	System.out.println(e.getMessage());
            } 
    	 
    	 //Array containing each book's line counts
     	int[] lineNumbers = {CartoonsLineNumber,HobbiesLineNumber,MoviesLineNumber,MusicLineNumber,NostalgiaLineNumber,OldTimeLineNumber,SportsLineNumber,TrainsLineNumber};

     	
     	//PRINTING VALID BOOKS TO THE FILES
    	 for(int i =0  ; i<ValidbooksArray.length; i++) {
    		 PrintWriter pw = null;
    		 try {
    		  pw = new PrintWriter(new FileOutputStream(pathNames[i]));
    		 
    		 }catch(FileNotFoundException e) {
					System.out.println(e.getMessage());
    		 }catch(IOException e) {
					System.out.println(e.getMessage());
    		 }
    		 
    		 pw.println(lineNumbers[i]);
  			 for(int j =0 ; j<ValidbooksArray[i].length; j++) {
  				
  				 if(ValidbooksArray[i][j] != null) {
  					
  					 
  						pw.println(ValidbooksArray[i][j]);
  						
  				 }
  				
  			}
     		 
  			pw.flush();
			pw.close();
     	 }

    	 
    	 
    	 ////PRINTING ERRORS TO THE FILE
    	 PrintWriter pw = null;
    	 Scanner scanner = null;
    	 try {
    		 scanner = new Scanner(new FileInputStream("part1_input_file_names.txt"));
    		 pw = new PrintWriter(new FileOutputStream("syntax_error_file.txt"));
    	 }catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
		 }catch(IOException e) {
				System.out.println(e.getMessage());
		 }
    	 int fileCount = scanner.nextInt();
         scanner.nextLine();
    	 while(scanner.hasNextLine()) {
    		 
    		 for(int i =0  ; i<ErrorbooksArray.length; i++) {
    			 String fileName = scanner.nextLine();
    			 pw.println("Syntax errors in file: " + fileName +"=====================================");
    	 			for(int j =0 ; j<ErrorbooksArray[i].length; j++) {
    	 				
    	 				if(ErrorbooksArray[i][j] != null) {
    	 					
    	 					pw.println(ErrorbooksArray[i][j]);
    	 					 pw.flush();
    	 		 			
    	 				}
    	 			}
    	 			pw.println("\n\n");
    	 			
    	    	 }
    		
    	 }
    	 pw.close();
	}
	
//	-------------------------------------------------------- PART 2----------------------------------------------------------------------
	public static void do_part2() {
		
		//syntatically valid books created in part 1
		String[] fileNames = {"Cartoons_Comics.csv.txt","Hobbies_Collectibles.csv.txt","Movies_TV_Books.csv.txt","Music_Radio_Books.csv.txt","Nostalgia_Eclectic_Books.csv.txt","Old_Time_Radio.csv.txt","Sports_Sports_Memorabilia.csv.txt","Trains_Planes_Automobiles.csv.txt"};
//		Name of new files to be createds
    	String[] fileNames2 = {"Cartoons_Comics.csv.ser","Hobbies_Collectibles.csv.ser","Movies_TV_Books.csv.ser","Music_Radio_Books.csv.ser","Nostalgia_Eclectic_Books.csv.ser","Old_Time_Radio.csv.ser","Sports_Sports_Memorabilia.csv.ser","Trains_Planes_Automobiles.csv.ser"};

    	
    	//Looping through syntatically valid books created in part 1
    	Scanner sc = null;
    	ObjectOutputStream os = null;
    	for(int i =0; i<fileNames.length;i++) {
    		
    		//An array containing semantically valid books for each file input
    		Book[] BooksArray = new Book[1000];
    		int lineCount = 0;
    		
    		
    		try {
    			
    			//A scanner object to read each file
    			sc= new Scanner(new FileInputStream(fileNames[i]));
    			
    			//consume first line
    			int fileCount = sc.nextInt();
                sc.nextLine();
                
                //Read lines
    			while(sc.hasNextLine()) {
    				String curentLine = sc.nextLine();
        			String[] fields = curentLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        			
        			String title = fields[0];
                    String authors = fields[1];
                    String price = fields[2];
                    double PriceDoubleValue = Double.parseDouble(price);
                    String isbn = fields[3];
                    System.out.println(isbn);
                    String genre = fields[4];
                    String year = fields[5];
                    int YearintValue = Integer.parseInt(year);
                    
                    //Checking ISBN based on number of digits
                    boolean isbnValue = false;
                    if(isbn.length() == 10) {
                    	isbnValue = ISBNValidation10(isbn,fileNames[i],curentLine);
                    }else if(isbn.length() == 13){
                    	isbnValue = ISBNValidation13(isbn,fileNames[i],curentLine);
                    }
                    
                    //Price validation
                    if(priceValidation(price,fileNames[i],curentLine) == true && yearValidation(year,fileNames[i],curentLine) == true && isbnValue == true ) {
                    	
                    	//Creat book object for each valid line
                    	Book book = new Book(title, authors, PriceDoubleValue, isbn, genre, YearintValue);
                    	
                    	//Add Object to array
                    	BooksArray[lineCount] = book;
                    }
                    
                    
                   lineCount++; 
    			}
    			
    			//Create binary file
    	        String fileName = fileNames2[i];
    	        os = new ObjectOutputStream(new FileOutputStream(fileName));
    	        
    	        //Write each array object to binary file
    	        os.writeObject(BooksArray);
    	    	os.flush();
    			os.close();
    			
    			
    		}catch(FileNotFoundException e) {
    			System.out.println(e.getMessage());
    		}catch(EOFException e) {
    			System.out.println(e.getMessage());
    		}catch(IOException e){
    			System.out.println(e.getMessage());
    		}
    	}
	}
	
	//-------------------------------PART 3 -------------------------------------------------------------------
	public static void do_part3() {
		String[] fileNames = {"Cartoons_Comics.csv.ser","Hobbies_Collectibles.csv.ser","Movies_TV_Books.csv.ser","Music_Radio_Books.csv.ser","Nostalgia_Eclectic_Books.csv.ser","Old_Time_Radio.csv.ser","Sports_Sports_Memorabilia.csv.ser","Trains_Planes_Automobiles.csv.ser"};
    	
		//Arrays to be filled with book lines for each genre
    	Book[] CartoonsComics = new Book[2000];
    	Book[] HobbiesCollectibles = new Book[2000];
    	Book[] MoviesTVBooks = new Book[2000];
    	Book[] MusicRadioBooks = new Book[2000];
    	Book[] NostalgiaEclecticBooks = new Book[2000];
    	Book[] OldTimeRadioBooks= new Book[2000];
    	Book[] SportsSportsMemorabilia = new Book[2000];
    	Book[] TrainsPlanesAutomobiles = new Book[2000];
    	
    	//Keeping the track of number of each line to be used in loops
    	int cartoonsComicsIndex = 0;
        int hobbiesCollectiblesIndex = 0;
        int moviesTVIndex = 0;
        int musicIndex = 0;
        int nostalgiaIndex= 0;
        int oldtimeIndex=  0;
        int sportsIndex= 0;
        int trainsIndex= 0;
        
    	
        
    	// Deserialize and store objects in the appropriate array
        
        //The Book array to store the book arrays that will be read from binary files
    	Book[] bookArray = null;
    	ObjectInputStream ois =  null;
    	
    	//Lopp through file names
        for(String filePath :fileNames ) {
        	try {
        		
        		//open files for reading
        		ois = new ObjectInputStream(new FileInputStream(filePath));
        		
        		//Cast each array, and assign it to bookArray
        		bookArray = (Book[]) ois.readObject();
    		
        		
        		//Loop through each line of books, and store them in genre specific arrays to be used in interactive menu
    			for(Book book : bookArray) {
        				if(book != null) {
        					if (book.getGenre().equals("CCB")) {
        			        	CartoonsComics[cartoonsComicsIndex] = book;
        			            cartoonsComicsIndex++;
        			        }
        			        
        			        if (book.getGenre().equals("HCB")) {
        			        	HobbiesCollectibles[hobbiesCollectiblesIndex] = book;
        			        	hobbiesCollectiblesIndex++;
        			        }
        			        
        			        if (book.getGenre().equals("MTV")) {
        			        	MoviesTVBooks[moviesTVIndex] = book;
        			        	moviesTVIndex++;
        			        }
        			        if (book.getGenre().equals("MRB")) {
        			        	MusicRadioBooks[musicIndex] = book;
        			        	musicIndex++;
        			        }
        			        if (book.getGenre().equals("NEB")) {
        			        	NostalgiaEclecticBooks[nostalgiaIndex] = book;
        			        	nostalgiaIndex++;
        			        }
        			        if (book.getGenre().equals("OTR")) {
        			        	OldTimeRadioBooks[oldtimeIndex] = book;
        			        	oldtimeIndex++;
        			        }
        			        if (book.getGenre().equals("SSM")) {
        			        	SportsSportsMemorabilia[sportsIndex] = book;
        			        	sportsIndex++;
        			        }
        			        if (book.getGenre().equals("TPA")) {
        			        	TrainsPlanesAutomobiles[trainsIndex] = book;
        			        	trainsIndex++;
        			        }
        			    }
        			}
    			
    	}catch(IOException e) {
    		System.out.println(e.getMessage());
    	} catch (ClassNotFoundException e) {
    		System.out.println(e.getMessage());
		}

    	
    	
   }
   
//   for(Book b: CartoonsComics) {if(b != null)System.out.println(b);};
   
   
   
        //A two dimensional array store the book objects
		Book[][] Books = {CartoonsComics, HobbiesCollectibles,MoviesTVBooks,MusicRadioBooks,NostalgiaEclecticBooks,OldTimeRadioBooks,SportsSportsMemorabilia,TrainsPlanesAutomobiles};
		
		//Count of book object records stored on an array
		int[] numOfRecords=  {cartoonsComicsIndex,hobbiesCollectiblesIndex,moviesTVIndex,musicIndex, nostalgiaIndex,oldtimeIndex,sportsIndex,trainsIndex};
		
		Scanner sc = new Scanner(System.in);
		int index= 0;
   		   
		//Interactive menu
   		boolean menu = true;
   		while(menu) {
   			System.out.println("\n-----------------------------\nMain Menu\n-----------------------------\nv View the selected file: " + fileNames[index] +" ("+ numOfRecords[index]+" records found)\ns Select a file to view\nx Exit\n------------------------------\n\nEnter Your Choice: ");
   	   		String choice = sc.next();
   	   		
   	   		//Viewing the selected book file
   			if(choice.equalsIgnoreCase("v")) {
   				System.out.println("Viewing : " + fileNames[index]+ " ("+ numOfRecords[index] + " records found)\n");
   	   			for(int i =0; i< Books[index].length; i++) {
   	   				if(Books[index][i] != null) {
   	   					System.out.println(Books[index][i].toString());
   	   				}
   	   				
   	   			}
   	   			boolean e  = true;
   	   			int bookInput = 0;
   	   			int index2 =0; 
   	   			while(e) {
   	   			
   	   				System.out.println("\nPlease enter the line number command to view: ");
   	   				try {
   	   					bookInput =sc.nextInt();
   	   						if(bookInput == 1 |bookInput == -1 ) {
   	   							
   	   								if(Books[index][index2] != null) {
   	   									System.out.println(Books[index][index2].toString());
   	   									continue;
   	   								}
   	   						}else if(bookInput > 1) {
   	   							
   	   							if(index2+bookInput > (numOfRecords[index])) {
   	   								System.out.println("EOF reached");
   	   								break;
   	   							}
   	   								for(int i = index2; i<(bookInput+ index2); i++) {
   	   										if(Books[index][i] != null) {
   	   										System.out.println(Books[index][i].toString());
   	   										}
   	   										
    	   				
   							
   	   								}
   	   								index2 += bookInput-1;
   	   						
   	   						}else if(bookInput < -1) {
   	   							
   	   							if(index2+bookInput<-1) {
   	   								System.out.println("BOF reached");
   	   								break;
   	   							}
   	   								for(int i = index2; i>(bookInput+ index2); i--) {
   	   										if(Books[index][i] != null) {
   	   										System.out.println(Books[index][i].toString());
   	   										}
   	   										
    	   				
   							
   	   								}
   	   								index2 -= Math.abs(bookInput)-1;
   	   						
   	   						}
   	   					
   	   				}catch(InputMismatchException m) {
   	   					System.out.println("Please enter a valid input: ");
   	   					System.out.println("Please enter the line number command to view: ");
   	   					try {
   	   						sc.nextLine();
   	   						bookInput =sc.nextInt();
   	   					}catch(InputMismatchException s) {
   	   						sc.next();
   	   						System.out.println("Input invalid, system ended.\n\n");
   	   						e= false;
   	   					}
   	   				}
   	   			}
   	   			
   	   			
   	   		}
   			
   			//Select a book to read
   			if(choice.equalsIgnoreCase("s")) {
   				for(int i =0; i< fileNames.length; i++)System.out.println((i+1) +" "+ fileNames[i] + " ("+ numOfRecords[i] + " records found)");
   				boolean selectBook = true;
   				while(selectBook) {
   					System.out.println("\nplease select a Book to view: ");
   					int bookInput =sc.nextInt();
   					index = bookInput-1;
   					selectBook=  false;
   				}
   			}
   			
   			if(choice.equalsIgnoreCase("x")) {
   				System.out.println("Thank you for using our system!");
   				menu = false;
   			}
   		}
	}
	

    public static void main(String[] args) {
    	
    	do_part1();
    	do_part2();
    	do_part3();

    	 
    

		
	
    	
    	
    }
}
