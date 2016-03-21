
/*
 * this is a node class for reference number with two fields: 1) integer field and 2) pointer to next node field
 */

package linkedlist;

public class RefNode {
	int data;			
	RefNode next;
	
	public RefNode(int d){
		next = null;
		data = d;
	}
}

