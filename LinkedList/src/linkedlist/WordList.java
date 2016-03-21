
/*
 * this is the word list node which is the collection of all the words and this list is alphabetically sorted
 */

package linkedlist;

public class WordList {
	WordNode wordNodeSentinel;
	
	public WordList(){										//constructor
		wordNodeSentinel = new WordNode("DummyNode");		//sentinel node of Word List
	}
	
	public void add(String w, int ref){						//add new data method
		int reValue;										//return of comparing words
		WordNode tempNext = wordNodeSentinel.next;
		WordNode tempPre = wordNodeSentinel;
		
		if(tempNext==null){									//empty list
			WordNode newWord = new WordNode(w);
			wordNodeSentinel.next = newWord;				//point to new created node
			
			RefList newRef = new RefList();
			newWord.toRef = newRef;							//point head of reference list
			newRef.add(ref);								//add reference number to list
			return;											//return when done
			
		} else {
			tempNext = wordNodeSentinel.next;					//beginning of the list
			tempPre = wordNodeSentinel;							//
			
			while(tempNext!=null){
				reValue = w.compareTo(tempNext.word);			//compare new word with exiting word:
				if(reValue<0){									//(<0 new word comes first, >0 new word comes after, =0 same word)
					WordNode newWord = new WordNode(w);			//add new word
					newWord.next = tempPre.next;				//similar to add node in between 2 nodes
					tempPre.next = newWord;						//new created node is placed before the compared node
				
					RefList newRef = new RefList();				//new reference list
					newRef.add(ref);							//add new reference number
					newWord.toRef = newRef;						//point to new RefList
					return;										//return when done
					
				} else if(reValue==0){							//existing word found
					tempNext.toRef.add(ref);					//directly add new reference number to the existing list
					return;										//return when done
					
				} else if(tempNext.next==null && reValue>0){	//add at the end of the word list
					WordNode newWord = new WordNode(w);			//add new word
					tempNext.next = newWord;					//last node point to new created node
					
					RefList newRef = new RefList();				//new reference list
					newRef.add(ref);							//add new reference number
					newWord.toRef = newRef;						//add in the end of the list
					return;										//return when done	
				}
				
				tempPre = tempNext;								//remember previous visited node
				tempNext = tempNext.next;						//move to next node
			}
		}
	}	

	public void print(){									//print method
		WordNode wordNodeTemp = wordNodeSentinel.next; 

		System.out.println("Word\t\tReference #");			//header of the list
		
		do{
			System.out.format("%-15s ", wordNodeTemp.word);	//print word			
			wordNodeTemp.toRef.print();						//print reference number
			System.out.println();
			wordNodeTemp = wordNodeTemp.next;				//next node			
		}while(wordNodeTemp!=null);
	}
}