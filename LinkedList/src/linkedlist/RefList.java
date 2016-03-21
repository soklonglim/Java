
/*
 * this is the reference number list which is the collection of the reference number of each word on the word list
 */

package linkedlist;

public class RefList {
	RefNode sentinel;
	
	public RefList(){						//constructor
		sentinel = new RefNode(11);			//sentinel Node && unused data
	}
	
	public void add(int d){					//add new data method
		RefNode tempNext = sentinel.next;
		RefNode tempPre = sentinel;
		
		while(tempNext!=null){				//finding the last node
			tempPre = tempNext;
			tempNext = tempNext.next;
		}									

		//tempPre points to the last node on the list
		RefNode newNode = new RefNode(d);	//create new node with data
		tempPre.next = newNode;				//last node point to new node
		newNode.next = null;				//new node point to null (end of list)
	}	
	
	public void print(){					//print method
		RefNode temp = sentinel.next;		//set to first data 
		
		while(temp!=null){
			System.out.print(temp.data + ", ");	//print data
			temp = temp.next;				//next node
		}
	}
}