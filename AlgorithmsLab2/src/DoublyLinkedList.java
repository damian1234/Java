import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 12/10/15 20:44:01
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
		int size =0;
		//head.next = tail;
		//tail.prev = head;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic runtime cost: TODO
	 *
	 * Justification:
	 *  TODO
	 */
	public boolean isEmpty()
	{
		if((head == null)&&(tail == null)){
			return true;
		}
		return false;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic runtime cost: O(n)
	 *
	 * Justification:
	 * insertBefore, the worst case would be a situation where it would iterate through a list of size n
	 */
	public void insertBefore( int pos, T data ) 
	{	
		DLLNode myNode;
		if(head !=null){
			if(pos >0){
				int i=0;
				DLLNode temp = head;
				while((temp.next!=null)&&(i!=pos)){
					temp = temp.next;
					i++;
				}
				if(temp.next!=null){

					DLLNode tmp = temp.prev;
					myNode = new DLLNode(data, tmp, temp );
					tmp.next = myNode;
					temp.prev = myNode;
				}
				else{
					myNode = new DLLNode(data, temp, null );
					temp.next = myNode;
					tail = myNode;
				}
			}
			else{
				DLLNode temp = head;
				myNode = new DLLNode(data, null, temp);
				head = myNode;
				temp.prev = myNode;
			}
		}
		else{
			myNode = new DLLNode(data, null, null);
			head = myNode;
			tail = myNode;
		}
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case precise runtime cost: O(n)
	 *
	 * Justification:
	 *  the worst case would be searching through a list of size n
	 */
	public T get(int pos) 
	{
		DLLNode node = head;
		int i =0;
		while(node!=null){
			if((i >= pos)||(node.next == null)){
				return node.data;
			}
			node = node.next;
			i++;

		}
		return null;
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic runtime cost: O(n)
	 *
	 * Justification:
	 *  the worst case would be searching through a list of length N for the correct node to be deleted
	 */
	public boolean deleteAt(int pos) 
	{
		if(head != null){
			DLLNode node = head;
			int i =0;
			if(pos >= 0){
				while(i!= pos){
					if(node==null){
						return false;
					}
					node = node.next;
					i++;
				}
				if(node.next ==null){
					node = node.prev;
					tail = node;
					node.next = null;
					return true;
				}
				else if(i>0){
					DLLNode previous = node.prev;
					previous.next = node.next;
					node = node.next;
					node.prev = previous.next;
					return true;
				}
				else{
					node = node.next;
					head = node;
					node.prev = null;
					return true;
				}
			}

		}
		return false;
	}

	/*----------------------- STACK */
	/**
	 * This method should behave like the usual push method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic runtime cost: O(1)
	 *
	 * Justification:
	 *  each line in this function only takes O(1) on each line. hence the total runtime cost is O(1)
	 */
	public void push(T item) 
	{
		if(item!=null){
			DLLNode myNode;
			if(head ==null){
				myNode = new DLLNode(item, null, null);
				head = myNode;
				tail = myNode;
			}
			else{
				DLLNode firstNode = head;
				myNode = new DLLNode(item, null, firstNode);
				head = myNode;
				firstNode.prev = myNode;
			}
		}

	}

	/**
	 * This method should behave like the usual pop method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: O(1)
	 *
	 * Justification:
	 *  each line in this function only takes O(1) on each line. hence the total runtime cost is O(1)
	 */
	public T pop() 
	{
		if(head== null){
			return null;
		}
		DLLNode myNode = head;
		if(myNode.next !=null){
			head = myNode.next;
		}
		else{
			head =null;
		}
		return myNode.data;
	}

	/*----------------------- QUEUE */

	/**
	 * This method should behave like the usual enqueue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic runtime cost: O(1)
	 *
	 * Justification:
	 *  each line in this function only takes O(1) on each line. hence the total runtime cost is O(1)
	 */
	public void enqueue(T item) 
	{
		DLLNode myNode;
		if(head ==null){
			myNode = new DLLNode(item, null, null);
			head = myNode;
			tail = myNode;
		}
		else{
			DLLNode firstNode = head;
			myNode = new DLLNode(item, null, firstNode);
			head = myNode;
			firstNode.prev = myNode;
		}
	}

	/**
	 * This method should behave like the usual dequeue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the earliest item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: O(1)
	 *
	 * Justification:
	 *  each line in this function only takes O(1) on each line. hence the total runtime cost is O(1)
	 */
	public T dequeue() 
	{
		if(tail== null){
			return null;
		}
		DLLNode node = tail;
		if(node.prev ==null){
			tail = null;
			return node.data;
		}
		DLLNode prevNode = node.prev;
		tail = prevNode;
		prevNode.next = null;
		return node.data;
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}


}



