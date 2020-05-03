/**
 * Heap.java provides an API for the heap data structure.
 * The heap is built on an arraylist and utililzes Java generics.
 * 
 * @author Asher Muse
 * @version 1.4
 * @since 2020-05-03
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Heap<T extends Comparable<T>> {

	private ArrayList<T> heap = new ArrayList<T>();
	
	/**
	 * Heap() is the default constructor and creates an empty arraylist to represent the heap.
	 */
	public Heap() {
		heap = new ArrayList<T>();
	}
		
	/**
	 * Heap(arr) is a constructor that given an array converts the array into a heap.
	 * @param arr This is an array that will be converted into a heap.
	 */
	public Heap(T [] arr) {
		heap = new ArrayList<T>(arr.length);
		for(int i = 0; i < arr.length; i++) {
			heap.add(arr[i]);
		}
		buildMaxHeap();
	}
	
	/**
	 * Heap(ArrayList) is a constructor that given an arraylist adds all elements to the heap.
	 * @param arr This is an arraylist that will be converted into a heap.
	 */
	public Heap(ArrayList<T> arr) {
		heap.clear();
		heap.addAll(arr);
	}
	
	/**
	 * isOutOfBounds checks that the index is valid (>= 0 and < size).
	 * Size is the size of the heap and not the size of the array.
	 * @param index This is the index in an array.
	 * @return boolean This returns if the index is valid.
	 */
	private boolean isOutOfBounds(int index) {
		return index > heap.size() || index < 0;
	}
	
	/**
	 * size returns the size of the heap.
	 * @return int The size of the heap.
	 */
	public int size() {
		return heap.size();
	}
	
	/**
	 * min compares to integers and returns the smaller of the two.
	 * @param a This is an integer to be compared.
	 * @param b This is an integer to be compared.
	 * @return int The smaller of a and b.
	 */
	private int min(int a, int b) {
		if (a < b) {
			return a;
		}
		return b;
	}
	
	/**
	 * maxHeapify is given an index of a node and converts that node and underlying nodes into a heap.
	 * @param parent This is the top node to "heapify".
	 */
	private void maxHeapify(int parent) {
		int left = findLeft(parent);
		int right = findRight(parent);
		int max = parent;
		
		if(left < heap.size() && heap.get(left).compareTo(heap.get(max)) > 0) {
			max = left;
		}
		if(right < heap.size() && heap.get(right).compareTo(heap.get(max)) > 0) {
			max = right;
		}
		if (max != parent) {
			Collections.swap(heap, max, parent);
			maxHeapify(max);
		}
	}
	
	/**
	 * findLeft finds the index of the left child node of a given parent node.
	 * @param parent This is the index of a node in the heap.
	 * @return int This is the index of the left child node.
	 */
	private int findLeft(int parent) {
		return parent * 2 + 1;
	}
	
	/**
	 * findRight finds the index of the right child node of a given parent node.
	 * @param parent This is the index of a node in the heap.
	 * @return int This is the index of the right child node.
	 */
	private int findRight(int parent) {
		return parent * 2 + 2;
	}
	
	/**
	 * findParent finds the index of the parent node for a given child node.
	 * @param child This is the index of a node in the heap.
	 * @return int This is the index of the parent node.
	 */
	private int findParent(int child) {
		return (child - 1) / 2;
	}
	
	/**
	 * buildMaxHeap takes the underlying array and sorts it such that it is a heap.
	 */
	private void buildMaxHeap() {
		for(int i = (findParent(heap.size()-1)); i >= 0; i--) {
			maxHeapify(i);
		}
	}
			
	/**
	 * addNode takes a value and adds it to the heap.
	 * @param value This is the value that will be added to the heap.
	 */
	public void addNode(T value) {
		heap.add(value);
		buildMaxHeap();
	}
	
	/**
	 * removeNodeByIndex removes the node at a given index.
	 * @param index This is the index that will be removed from the heap.
	 * @throws IOException Error when the index is out of bounds.
	 */
	public void removeNodeByIndex(int index) throws IOException {
		if(isOutOfBounds(index)) {
			throw new IOException("Error in removal: data not found or index out of bounds.");
		}
		heap.remove(index);
		buildMaxHeap();
	}
	
	/**
	 * removeNodeByIndex removes the node with the first occurrence of the given value.
	 * @param value This is the value that will be removed from the heap.
	 * @throws IOException Error when index is out of bounds.
	 */
	public void removeNodeByValue(T value) throws IOException {
		removeNodeByIndex(heap.indexOf(value));
	}

	/**
	 * printHeap prints the elements of the underlying arraylist.
	 */
	public void printHeap() {
		System.out.println("Printing underlying array (up to 100 elements): ");
		for (int i = 0; i < min(100,heap.size()); i++) {
			System.out.print(heap.get(i) + " ");
		}
		System.out.println("");
	}
	
	/**
	 * isHeap checks if the underlying arraylist meets the confines to be a heap.
	 * @param parent This is the parent node of the heap.
	 * @return boolean If the underlying array is a heap.
	 */
	public boolean isHeap(int parent) {		
		int left = findLeft(parent);
		int right = findRight(parent);
		int max = parent;
		
		for(int i = findParent(heap.size()-1); i >= 0; i--) {
			if(left < heap.size() && heap.get(left).compareTo(heap.get(max)) > 0) {
				max = left;
			}
			if(right < heap.size() && heap.get(right).compareTo(heap.get(max)) > 0) {
				max = right;
			}
			if (max != parent) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * isEmpty checks if the heap is empty.
	 * @return boolean if the heap is empty.
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	/**
	 * findMax peeks at the maximum value in the heap.
	 * @return T This is the maximum value of the heap.
	 * @throws IOException Error when heap is empty.
	 */
	public T findMax() throws IOException {
		if(isEmpty()) {
			throw new IOException("Error in peek: the heap is empty");
		}
		return heap.get(0);
	}
	
	/**
	 * deleteMax() remove the maximum value from the heap.
	 * @throws IOException Error when heap is empty.
	 */
	public void deleteMax() throws IOException {
		if(isEmpty()) {
			throw new IOException("Error in deletion: the heap is empty");
		}
		heap.remove(0);
		maxHeapify(0);
	}
	
	/**
	 * extractMax deletes the maximum value of the heap and returns it.
	 * @return T This is the maximum value in the heap.
	 * @throws IOException Error when heap is empty.
	 */
	public T extractMax() throws IOException {
		T max = findMax();
		deleteMax();
		return max;
	}
	
	/**
	 * extractMin deletes the minimum value of the heap and returns it.
	 * @return T This is the minimum value in the heap.
	 * @throws IOException Error when heap is empty.
	 */
	public T extractMin() throws IOException {
		if(isEmpty()) {
			throw new IOException("Error in removal: the heap is empty.");
		}
		int minIndex = heap.size()/2;
		T min = heap.get(minIndex);
		for (int i = ((heap.size() / 2) + 1); i < heap.size(); i++) {
			if (min.compareTo(heap.get(i)) > 0) {
				min = heap.get(i);
				minIndex = i;
			}
		}
		heap.remove(minIndex);
		buildMaxHeap();
		return min;
	}
		
	/**
	 * increaseNodeValue increases the value of a node at a given index.
	 * @param index This is the index of the node to be changed.
	 * @param value This is the value that the node will be updated to.
	 * @throws IOException Error when index is out of bounds or value is less than old value.
	 */
	private void increaseNodeValue(int index, T value) throws IOException {
		if (isOutOfBounds(index) || value.compareTo(heap.get(index)) <= 0) {
			throw new IOException("Error: new value is not larger or index is out of bounds");
		}
		int parent = findParent(index);
		heap.set(index, value);
		while(parent > 0 && heap.get(index).compareTo(heap.get(parent)) > 0) {
			Collections.swap(heap, index, parent);
			index = parent;
			parent = findParent(index);
		}
	}
	
	/**
	 * decreaseNodeValue decreases the value of a node at a given index.
	 * @param index This is the index of the node to be changed.
	 * @param value This is the value that the node will be updated to.
	 * @throws IOException Error when index is out of bounds or value is greater than old value.
	 */
	private void decreaseNodeValue(int index, T value) throws IOException {
		if (isOutOfBounds(index) || value.compareTo(heap.get(index)) >= 0) {
			throw new IOException("Error: new value is not smaller or index is out of bounds");
		}
		heap.set(index, value);
		maxHeapify(index);
	}
	
	/**
	 * updateNodeValue changes the value of a node at a given index.
	 * @param index This is the index of the node to be changed.
	 * @param value This is the value that the node will be updated to.
	 * @throws IOException Error when index is out of bounds.
	 */
	public void updateNodeValue(int index, T value) throws IOException {
		if(isOutOfBounds(index)) {
			throw new IOException("Error: index out of bounds");
		}
		if (heap.get(index).compareTo(value) > 0) {
			decreaseNodeValue(index, value);
		}
		else {
			increaseNodeValue(index, value);
		}
	}
	
	/**
	 * Main provides the user an environment to test the heap methods.
	 * @param args This is arguments from the command line.
	 * @throws Error when method returns an error.
	 */
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		int userInputA, userInputB, userCommand = 1;
		
		// Prints a simple greeting.
		System.out.println("Welcome to the Heap API testing environment.");
		Heap<Integer> userHeap = new Heap<Integer>();
		
		String commands = "[0] Quit, [1] Print Heap, [2] removeNodeByValue, [3] removeNodeByIndex\n[4] addNode, [5] extractMax, [6] extractMin, [7] updateNodeValue, [8] randHeap";

		// Handle UI
		while(userCommand != 0) {
			// Get user input
			System.out.println("Awaiting command...\n" + commands);
			userCommand = scan.nextInt();
	
		    // Handle user input.
		    switch (userCommand) {
		    	case 1: // printHeap
		    		userHeap.printHeap();
		    		System.out.println("Heap Size: " + userHeap.size() + ", Is heap: " + userHeap.isHeap(0));
			        break;
		    	case 2: // removeNodeByValue
		    		System.out.println("Please insert the value of a node to be removed: ");
		    		userInputA = scan.nextInt();
		    		userHeap.removeNodeByValue(userInputA);
		    		break;
			    case 3: // removeNodeByIndex
			    	System.out.println("Please insert the index of a node to be removed: ");
		    		userInputA = scan.nextInt();
		    		userHeap.removeNodeByIndex(userInputA);
		    		break;
			    case 4: // addNode
		    		System.out.println("Please insert value for new node: ");
		    		userInputA = scan.nextInt();
		    		userHeap.addNode(userInputA);
		    		break;
			    case 5: //extractMax
			    	System.out.println("Maximum value: " + userHeap.extractMax());
			    	break;
			    case 6: //extractMin
			    	System.out.println("Minimum value: " + userHeap.extractMin());
			    	break;
			    case 7: // updateNodeValue
			    	System.out.println("Please input the index of the node to modify: ");
			    	userInputA = scan.nextInt();
			    	System.out.println("Please input the new value for the node: ");
			    	userInputB = scan.nextInt();
			    	userHeap.updateNodeValue(userInputA, userInputB);
			    	break;
			    case 8:
			    	System.out.println("Please insert desired size of the random heap: ");
			    	userInputA = scan.nextInt();
			    	Random rand = new Random();
					userHeap.heap.clear();
			    	for(int i = 0; i < userInputA; i++) {
						userHeap.addNode(rand.nextInt(10000));
					}
					break;
		    }
		} // end of while
		scan.close();
	}
}
