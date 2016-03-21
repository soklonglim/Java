
/* 
 * Soklong Lim 
 * CSE 223/Programming Assignment 2
 * May 01, 2015
 * 
 * Description
 * 	this program is about word indexing. We need to give a filename/file path then the program will 
 * take the content of the file and convert to upper case letter and get rid of punctuation. All words
 * are stored in a linked list in alphabetical order and another reference number linked list tagged to
 * the word list. Finally, it will print the list of word in alphabetical order followed by its reference #.
 */

package linkedlist;
import java.io.File;

public class MyMain {
	public static void main(String[] args){
		
		WordIndex index = new WordIndex(new File("C:\\tmp\\text.txt"));
		
		if(index.status()!=false){					//check for error while processing data
			index.print();							//print list
		}
	}
}