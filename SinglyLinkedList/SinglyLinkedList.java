import java.io.IOException;
import java.util.Scanner;

public class SinglyLinkedList<T> {
	private ListNode<T> head;
	private ListNode<T> tail;
	private int nodeCount;
	
	public SinglyLinkedList() {
		head = null;
		tail = null;
		nodeCount = 0;
	}
	
	public int size() {
		return nodeCount;
	}
	
	public boolean isEmpty() {
		return nodeCount == 0;
	}
	
	/*
	 * Adds a node with given data to the front of the linked list.
	 */
	public void addFirst(T data) {
		ListNode<T> newNode = new ListNode<T>(data);
		if (this.isEmpty()) {
			tail = newNode;
		}
		else {
			newNode.setNext(head);
		}
		head = newNode;
		nodeCount++;
	}
	
	/*
	 * Adds a node with given data to the end of the linked list.
	 */
	public void addLast(T data) {
		ListNode<T> newNode = new ListNode<T>(data);		
		if (this.isEmpty()) {
			head = newNode;
		}
		else {
			tail.setNext(newNode);
		}
		tail = newNode;
		nodeCount++;
	}
	
	/*
	 * Adds a node with given data to given position in the linked list.
	 */
	public void insert(T data, int position) throws IOException {
		if (position > this.nodeCount || position < 0) {
			throw new IOException("ERROR: Position out of bounds");
		}
		else if (this.isEmpty() || position == 0) {
			this.addFirst(data);
		}
		else if (position == this.nodeCount) {
			this.addLast(data);
		}
		else {
			ListNode<T> newNode = new ListNode<T>(data);
			ListNode<T> node = head;
			
			for(int i = 0; i < position - 1; i++) {
				node = node.getNext();
			}
			newNode.setNext(node.getNext());
			node.setNext(newNode);
			
			nodeCount++;
		}
	}

	/*
	 * Removes a node with the given key from the linked list.
	 */
	public void remove(T key) throws IOException {
		
		ListNode<T> node = search(key);
		
		if (node == null) {
			throw new IOException("ERROR: List is empty, cannot remove element");
		}
		else if (node == head) {
			this.removeFirst();
		}
		else if (node == tail) {
			this.removeLast();
		}
		else {
			ListNode<T> prevNode = head;
			while(prevNode.getNext().getData() != node.getData()) {
				prevNode = prevNode.getNext();
			}
			prevNode.setNext(node.getNext());
			this.nodeCount--;
		}
	}
	
	/*
	 * Removes the first node in the linked list.
	 */
	public void removeFirst() throws IOException {
		if(this.isEmpty()) {
			throw new IOException("ERROR: List is empty, cannot remove first element");
		}
		else if (this.nodeCount == 1) {
			head = null;
			tail = null;
			this.nodeCount--;
		}
		else {
			head = head.getNext();
			this.nodeCount--;
		}
	}
	
	/*
	 * Removes the last element in the linked list.
	 */
	public void removeLast() throws IOException {
		if(this.isEmpty()) {
			throw new IOException("ERROR: List is empty, cannot remove last element");
		}
		else if (this.nodeCount == 1) {
			head = null;
			tail = null;
			this.nodeCount--;
		}
		else { 			
			ListNode<T> node = head;
			for(int i = 1; i < nodeCount-1; i++) {
				node = node.getNext();
			}
			node.setNext(null);
			this.tail = node;
			this.nodeCount--;
		}
	}
	
	/*
	 * Searches the linked list and returns the first occurrence of the given key.
	 */
	private ListNode<T> search(T key) {
		if (!this.isEmpty()) {
			ListNode<T> node = head;
			while(node != null) {
				if(node.getData() == key) {
					return node;
				}
				node = node.getNext();
			}
		}
		return null;		
	}
	
	/* 
	 * Returns true if the given data is found in the linked list.
	 */
	public boolean contains(T data) {
		ListNode<T> node = search(data);
		return node != null;
	}
	
	/*
	 * Prints each node in the linked list.
	 */
	public void print() {
		ListNode<T> node = head;
		int count = 0;
		System.out.println("List is size: " + this.size());
		while (node != null) {
			System.out.println("Node " + count + ": " + node.getData());
			node = node.getNext();
			count++;
		}
	}
	
	/*
	 * Uses a simple UI that uses commands to run the various methods above.
	 */
	public static void main(String[] args) throws IOException {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		
		// Prints a simple greeting.
		System.out.println("Hello and welcome to this Linked List Assignment.");
		System.out.println("This debug console will help you test various methods. Please input your desired action.\n");
		// Declare variables
		int userInput = -1;
		int data = 0;
		String commands = "[0] Quit\n[1] addFront\n[2] addLast\n[3] insert\n[4] contains\n[5] print\n[6] remove\n[7] removeFirst\n[8] removeLast";
		
		Scanner scan = new Scanner(System.in);
		
		// Handle UI
		while(userInput != 0) {
			// Get user input
			System.out.println("Awaiting command...\n" + commands);
			userInput = scan.nextInt();
	
		    // Handle user input.
		    switch (userInput) {
		    	case 1: // addFront
			        System.out.println("Enter value for the new node: ");
			        data = scan.nextInt();
			        list.addFirst(data);
			        System.out.println("Node added to the front.");
			        break;
		    	case 2: // addLast
		    		System.out.println("Enter value for the new node: ");
			        data = scan.nextInt();
			        list.addLast(data);
			        System.out.println("Node added to the end.");
			        break;
		    	case 3: // insert
			        System.out.println("Enter value for the new node: ");
			        data = scan.nextInt();
			        System.out.println("Enter position for the new node: ");
			        userInput = scan.nextInt();
			        list.insert(data, userInput);
			        System.out.println("Node added at desired location.");
			        break;
			    case 4: // contains
			    	System.out.println("Enter value to search for: ");
			    	data = scan.nextInt();
			    	System.out.println("The list contains " + data + ": " + list.contains(data));
			    	break;
			    case 5: // print
			        list.print();
			        System.out.println("");
			        break;
			    case 6: // remove
			        System.out.println("Enter position for node deletion: ");
			        data = scan.nextInt();
			        list.remove(data);
			        System.out.println("Node removed.");
			        break;
			    case 7: // removeFirst
			        list.removeFirst();
			        System.out.println("First node removed.");
			        break;
			    case 8: // removeLast
			    	list.removeLast();
			        System.out.println("Last node removed.");
			        break;
			    } // end of switch
		  } // end of while
		scan.close();
	}
}
