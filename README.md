
//This program Opens a file that includes some .txt file containing some information about books. First, in check whether it's date and price and ISBN format is syntatically correct, then it stores
//the valid books into genre specific .txt files. If a syntax error found , it will record it in a text file. In the second part of the program, it reads those syntatically valid books, and this time checking for semantic errors.
//It checks the overall format of the record, such as number of fields, and if any found, it will record it in .txt file , and it will through a specific exception. If a book record is semantically valid, it will will create a Book object from it,
// and it will store it in it's genre specific array. Finally it serializes the result array and writes them as a Book object in binary files. In the last part of the program, interactive menu reads those arrays and lines,and based on the commands of 
// user , it displays the desired number of books either upward or downward .
