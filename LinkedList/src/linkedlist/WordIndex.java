
/*
 * this is the word index class that takes care of opening and reading data from the file then call add method to
 * store all the words on the word list. It contains boolean method that checks for error each time word coming in
 * and print method to show all the data back to user.
 */

package linkedlist;
import java.io.*;
import java.util.Scanner;

public class WordIndex {
	File fName;				
	WordList word = new WordList();
	int reference;								//reference number counter
	int flag;									//0 = succeed; 1=fail
	
	public WordIndex(File f){					//constructor embedded Read Class call
		fName = f;
		try{
			read();								//call read method
		} catch(Exception e){
			System.out.println("Error: WordIndex/Read Class!!!" + e);
			return;
		}
	}
	
	public void read(){							//read input method
		Scanner stream;
		
		try{									
			stream = new Scanner(fName);
			flag = 0;	//succeed flag
		} catch(Exception e){
			System.out.println("Error: File Not FOUND!!!\n" + e);			//error mseg
			flag = 1;	//fail flag
			return; 	//return after setting flag
		}
			while(stream.hasNext()){									//until EOF
				String input = stream.next();							//take one word at a time 
				input = input.toUpperCase();							//to upper case 
				input = input.replaceAll("[^A-Z]", "");					//get rid of all punctuation
				if(input.equals("")==false){							//check for stand alone punctuation
					reference+=1;										//increment reference number
					word.add(input, reference);							//add word to Word List
				}
			}
			stream.close();		//close stream
		
	}	
	
	public boolean status(){					//check status method
		if(flag!=1)
			return(true);
		return(false);
	}
	
	public void print(){						//print method
		try{
			word.print();						//call print method from WordList class
		} catch(Exception e){
			if(reference==0){					//no word found error
				System.out.println("------No Word Exist------");	
			} else {							//regular error mseg
			System.out.println("Error: WordIndex/Print Class!!!" + e);
			}
		}
	}
}
	

