import java.util.Random;
import java.util.Scanner;

public class ArraySorting {
	private int array[];
	
	public ArraySorting() {
		Random rand = new Random(); 
		this.array = new int[10];
		for(int i = 0; i < 10; i++) {
			this.array[i] = rand.nextInt();
		}
	}
	
	public ArraySorting(int size) {
		Random rand = new Random(); 
		this.array = new int[size];
		for(int i = 0; i < size; i++) {
			this.array[i] = rand.nextInt(10000000);
		}
	}
	
	/* 
	 * Returns true if the array is sorted.
	 */
	public boolean isSorted() {
		for(int i = 1; i < array.length; i++) {
			if (array[i-1] > array[i]) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Creates two arrays using low, mid, and high and merges them into a sorted array.
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
	
	/*
	 * Sorts an array using mergeSort algorithm.
	 */
	public void mergeSort(int low, int high) {
		if (low < high) {
			int mid = (low+high)/2;
			mergeSort(low, mid);
			mergeSort(mid+1, high);
			merge(low, mid, high);
		}		
	}
	
	/*
	 * Swaps values in an array when given their indices.
	 */
	private void swap(int x, int y) {
		int temp = this.array[x];
		this.array[x] = this.array[y];
		this.array[y] = temp;
	}
	
	/*
	 * Helper function for quickSort algorithm.
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
	
	/*
	 * Sorts an array using quickSort algorithm.
	 */
	public void quickSort(int start, int end) {
		if (start < end) {
			int pivot = partition(start, end);
			quickSort(start, pivot-1);
			quickSort(pivot+1, end);		
		}
	}
	
	/*
	 * Prints the elements of an array. If the array is very 
	 * large (n > 100), only the first 100 elements are printed.
	 */
	public void printArray() {
		if(this.array.length <= 100) {
			System.out.println("Printing array: ");
			for (int i = 0; i < this.array.length; i++) {
				System.out.print(this.array[i] + " ");
			}
		}
		else {
			System.out.println("Array is very large. Printing first 100 elements.");
			for (int i = 0; i < 100; i++) {
				System.out.print(this.array[i] + " ");
			}
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
		
		String commands = "[0] Quit\n[1] print\n[2] mergeSort\n[3] quickSort\n[4] rerandomizeArray\n[5] resizeArray\n[6] isSorted\n[7] Test sorting algorithms.";
	
		
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
		    		if(userArray.array.length <= 100) {
		    			userArray.printArray();
		    		}
		    		System.out.println("Sorting the array with mergeSort...");
		    		userArray.mergeSort(0, userArray.array.length-1);
		    		System.out.println("Sorted array: ");
		    		if(userArray.array.length <= 100) {
		    			userArray.printArray();
		    		}
		    		System.out.println("Time elapsed (ms): " + (System.currentTimeMillis() - startTime));
		    		break;
		    	case 3: // quickSort
		    		startTime = System.currentTimeMillis();
		    		System.out.println("Original array: ");
		    		if(userArray.array.length <= 100) {
		    			userArray.printArray();
		    		}
		    		System.out.println("Sorting the array with quickSort...");
		    		userArray.quickSort(0, userArray.array.length-1);
		    		System.out.println("Sorted array: ");
		    		if(userArray.array.length <= 100) {
		    			userArray.printArray();
		    		}
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
			    case 7:
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
				    	i = i * 10;
			    	}
			    	break;
			    } // end of switch
		  } // end of while
		scan.close();
		
	}
	
}
