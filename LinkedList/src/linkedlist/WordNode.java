
/*
 * this is a node class for word with three fields: 1) storing word 2) pointer to reference list 3) pointer to next word node 
 */

package linkedlist;

public class WordNode {
	String word;
	WordNode next;
	RefList toRef;
	
	public WordNode(String w){
		word = w;
		next = null;
		toRef = null;
	}
}
