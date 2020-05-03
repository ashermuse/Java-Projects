/**
 * ArraySorting.java explores 3 different sorting algorithms: quick, merge, and heap.
 * 
 * @author Asher Muse
 * @version 1.2
 * @since 2020-05-02
 */

import java.util.Random;
import java.util.Scanner;

public class ArraySorting {
	private int array[];
	
	/**
	 * ArraySorting() is the default constructor and builds a random array of size 10.
	 */
	public ArraySorting() {
		Random rand = new Random(); 
		this.array = new int[10];
		for(int i = 0; i < 10; i++) {
			this.array[i] = rand.nextInt();
		}
	}
	
	/**
	 * ArraySorting(size) is a constructor that builds a random array of a given size.
	 * @param size This is the given size for the array.
	 */
	public ArraySorting(int size) {
		Random rand = new Random(); 
		this.array = new int[size];
		for(int i = 0; i < size; i++) {
			this.array[i] = rand.nextInt(1000000000);
		}
	}
	
	/**
	 * isSorted checks to see if the array is properly sorted.
	 * @return boolean if the array is sorted
	 */
	public boolean isSorted() {
		for(int i = 1; i < array.length; i++) {
			if (array[i-1] > array[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * merge takes a low, mid, and high point and uses them to separate an array into two smaller arrays.
	 * These smaller arrays are then merged such that the final array is sorted. This is a helper for mergeSort.
	 * @param low This is the low index of the left array.
	 * @param mid This is the high index of the left array and low index for the right array.
	 * @param high This is the high index of the right array.
	 */
	private void merge(int low, int mid, int high) {
		
		// Populate left
		int left[] = new int [mid-low+1];
		for (int i = 0; i < left.length; i++) {
			left[i] = this.array[low+i];
		}
		
		// Populate right
		int right[] = new int [high - mid];
		for (int i = 0; i < right.length; i++) {
			right[i] = this.array[mid+1+i];
		}
		
		
		int i = 0;
		int j = 0;
		int k = low;
		while(i < left.length && j < right.length) {
			if (left[i] < right[j]) {
				this.array[k] = left[i];
				i++;
			}
			else {
				this.array[k] = right[j];
				j++;
			}
			k++;
		}
		while(i < left.length) {
			this.array[k] = left[i];
			i++;
			k++;
		}
	}
	
	/**
	 * mergeSort uses recursion and the merge method to sort an array.
	 * @param low This is the first index of the array partition to sort.
	 * @param high This is the right index of the array partition to sort.
	 */
	public void mergeSort(int low, int high) {
		if (low < high) {
			int mid = (low+high)/2;
			mergeSort(low, mid);
			mergeSort(mid+1, high);
			merge(low, mid, high);
		}		
	}
	
	/**
	 * swap swaps the value of two indices in an array.
	 * @param x This is the first index to swap.
	 * @param y This is the second index to swap.
	 */
	private void swap(int x, int y) {
		int temp = this.array[x];
		this.array[x] = this.array[y];
		this.array[y] = temp;
	}
	
	/**
	 * partition is a helper method for quickSort.
	 * @param start This is the first element of the array.
	 * @param end This is the last element of the array.
	 * @return int This is the pivot point in quickSort.
	 */
	private int partition(int start, int end) {
		int x = this.array[end];
		int i = start - 1;
		for (int j = start; j < end; j++) {
			if (this.array[j] < x) {
				i++;
				this.swap(i, j);
			}
		}
		this.swap(end,  i+1);
		return i + 1;
	}
	
	/**
	 * quickSort uses recursion to sort an array.
	 * @param start This is the first element of the array to be acted on.
	 * @param end This is the last element of the array to be acted on.
	 */
	public void quickSort(int start, int end) {
		if (start < end) {
			int pivot = partition(start, end);
			quickSort(start, pivot-1);
			quickSort(pivot+1, end);		
		}
	}
	
	/**
	 * maxHeapify is given an index of a node and converts that node and underlying nodes into a heap.
	 * @param parent This is the top node to "heapify".
	 */
	private void maxHeapify(int parent, int size) {
		int left = parent * 2 + 1;
		int right = left + 1;
		int max = parent;
		
		if(left < size && this.array[left] > this.array[max]) {
			max = left;
		}
		if(right < size && this.array[right] > this.array[max]) {
			max = right;
		}
		if (max != parent) {
			swap(max, parent);
			maxHeapify(max, size);
		}
	}
	
	/**
	 * buildMaxHeap takes the underlying array and sorts it such that it is a heap.
	 */
	public void buildMaxHeap() {
		for(int i = (array.length / 2 - 1); i >= 0; i--) {
			maxHeapify(i, array.length);
		}
	}
	
	/**
	 * heapSort takes an array and sorts it by turning it into a max heap.
	 */
	public void heapSort() {
		buildMaxHeap();
		for(int i = this.array.length-1; i > 0; i--) {
			swap(i, 0);
			maxHeapify(0, i);			
		}
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
	 * printArray prints the elements of the underlying array.
	 */
	public void printArray() {
		System.out.println("Printing underlying array (up to 100 elements): ");
		for (int i = 0; i < min(100,array.length); i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("");
	}
	
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		long startTime;
		
		// Prints a simple greeting.
		System.out.println("Hello and welcome to this Array Sort Assignment.");
		System.out.println("This debug console will help you test various methods.");
		System.out.print("To begin, what is your desired size for the array: ");
		int userInput = scan.nextInt();
		ArraySorting userArray = new ArraySorting(userInput);
		
		String commands = "[0] Quit\n[1] print\n[2] mergeSort\n[3] quickSort\n[4] rerandomizeArray\n[5] resizeArray\n[6] isSorted\n[7] heapSort\n[8] Test sorting algorithms";
	
		
		// Handle UI
		while(userInput != 0) {
			// Get user input
			System.out.println("Awaiting command...\n" + commands);
			userInput = scan.nextInt();
	
		    // Handle user input.
		    switch (userInput) {
		    	case 1: // printArray
		    		userArray.printArray();
			        break;
		    	case 2: // mergeSort
		    		startTime = System.currentTimeMillis();
		    		System.out.println("Original array: ");
		    		userArray.printArray();
		    		System.out.println("Sorting the array with mergeSort...");
		    		userArray.mergeSort(0, userArray.array.length-1);
		    		System.out.println("Sorted array: ");
		    		userArray.printArray();
		    		System.out.println("Time elapsed (ms): " + (System.currentTimeMillis() - startTime));
		    		break;
		    	case 3: // quickSort
		    		startTime = System.currentTimeMillis();
		    		System.out.println("Original array: ");
		    		userArray.printArray();
		    		System.out.println("Sorting the array with quickSort...");
		    		userArray.quickSort(0, userArray.array.length-1);
		    		System.out.println("Sorted array: ");
		    		userArray.printArray();
		    		System.out.println("Time elapsed (ms): " + (System.currentTimeMillis() - startTime));
		    		break;
			    case 4: // randomizeArray
			    	System.out.println("Rerandomizing the array.");
			    	userArray = new ArraySorting(userArray.array.length);
			    	break;
			    case 5: // resizeArray
			    	System.out.println("Please input desired size for new array.");
			    	userInput = scan.nextInt();
			    	userArray = new ArraySorting(userInput);
			    	break;
			    case 6: // isSorted
			    	System.out.println("Sorted: " + userArray.isSorted());
			    	break;
			    case 8: // Assignment
			    	int i = 10;
			    	while (i <= 10000000) {
				    	userArray = new ArraySorting(i);
				    	startTime = System.currentTimeMillis();
				    	userArray.mergeSort(0, userArray.array.length-1);
				    	System.out.println("Merge Sort - Size: " + i + ", Time: " + (System.currentTimeMillis() - startTime));
				    	System.out.println("Is sorted: " + userArray.isSorted());
				    	userArray = new ArraySorting(i);
				    	startTime = System.currentTimeMillis();
				    	userArray.quickSort(0, userArray.array.length-1);
				    	System.out.println("Quick Sort - Size: " + i + ", Time: " + (System.currentTimeMillis() - startTime));
				    	System.out.println("Is sorted: " + userArray.isSorted());
				    	userArray = new ArraySorting(i);
				    	startTime = System.currentTimeMillis();
				    	userArray.heapSort();
				    	System.out.println("Heap Sort - Size: " + i + ", Time: " + (System.currentTimeMillis() - startTime));
				    	System.out.println("Is sorted: " + userArray.isSorted());
				    	i = i * 10;
			    	}
			    	break;
			    case 7: // heapSort
			    	startTime = System.currentTimeMillis();
		    		System.out.println("Original array: ");
		    		userArray.printArray();
		    		System.out.println("Sorting the array with heapSort...");
		    		userArray.heapSort();
		    		System.out.println("Sorted array: ");
		    		userArray.printArray();
		    		System.out.println("Time elapsed (ms): " + (System.currentTimeMillis() - startTime));
		    		System.out.println("Is sorted: " + userArray.isSorted());
		    		break;
			    } // end of switch
		  } // end of while
		scan.close();
		
	}
	
}
