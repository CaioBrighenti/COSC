/* COSC 102 - Assignment 02: Histogram
 * Spring '17
 * Caio Brighenti
 * 02/06/2017
 */ 

import java.util.Arrays;
import java.util.Scanner;

public class Histogram{
	// Change to true to sort histogram
	public static boolean SORTED = false;

	public static void main(String[] args) {
		int[] test = {4, 2, 9, 1, 4, 12, 100, 100, 100, 100};
		makeHistogram(test);
		System.out.println();
		makeHistogram();
	}

	// Makes a histogram based on given int array
	public static void makeHistogram(int[] nums){
		// Ask user if array is to be sorted
		getSort();
		// Count unique numbers
		int unique_count = countUnique(makeString(nums));
		// Initialize 2d array
		String[][] data = new String[unique_count][2];
		// Populate 2d array
		populateArray(data, makeString(nums));
		// Sort 2d array
		selectionSort(data);
		// Print out histogram
		printHistogram(data);	
	}

	// Makes a histogram based on user input
	public static void makeHistogram(){
		// Get input for array length
		int size = getSize();
		// Ask for each value and build array
		int[] nums = buildNumArray(size);
		// Ask user if array is to be sorted
		getSort();
		// Count unique numbers
		int unique_count = countUnique(makeString(nums));
		// Initialize 2d array
		String[][] data = new String[unique_count][2];
		// Populate 2d array
		populateArray(data, makeString(nums));
		// Sort 2d array
		selectionSort(data);
		// Print out histogram
		printHistogram(data);	
	}

	// Gets user input for size of array and returns it
	public static int getSize(){
		Scanner sc = new Scanner(System.in);
		System.out.print("How many integers do you wish to enter?: ");
		int size = sc.nextInt();
		// Validate input to make sure size is larger than 0
		while (size <= 0){
			System.out.println("Invalid input!");
			System.out.print("How many integers do you wish to enter?: ");
			size = sc.nextInt();
		}
		return size;
	}

	// Asks for user input for each number in array
	// based on array size and returns new array
	public static int[] buildNumArray(int size){
		Scanner sc = new Scanner(System.in);
		// Initialize array
		int[] user_array = new int[size];
		// Ask user for each number
		// and add it to the array
		for (int i = 0; i < size; i++) {
			System.out.print("Enter value #" + (i + 1) + ": ");
			int n = sc.nextInt();
			user_array[i] = n;
		}
		return user_array;
	}

	// Asks for user input on whether or not to sort the fianl array
	public static void getSort(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Would you like your histogram to be sorted? (Y/N): ");
		String response = sc.nextLine();
		// Validate input
		while (!response.toUpperCase().equals("Y") && !response.toUpperCase().equals("N")){
			System.out.println("Invalid input!");
			System.out.print("Would you like your histogram to be sorted? (Y/N): ");
			response = sc.nextLine();
		}
		// Set SORTED value accordingly
		if (response.toUpperCase().equals("Y")){
			SORTED = true;
		} else {
			SORTED = false;
		}
		// New line for aesthetic purposes
		System.out.println();
	}

	// Takes a integer array and returns a string array
	public static String[] makeString(int[] nums){
		String[] new_array = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			new_array[i] = Integer.toString(nums[i]);
		}
		return new_array;
	}

	// Iterate through each item, if item isn't a !, increment count and iterate setting all items equal to !
	// Slightly spaghetti code, REFACTOR BEFORE UPLOADING
	public static int countUnique(String[] nums_str){
		int count = 0;
		// Iterate through array
		for (String i : nums_str) {
			String current_n = i;
			// Check if number has not been found yet
			if (i != "!"){
				count++;
				for (int n = 0; n < nums_str.length; n++) {
					// Turn all instances of that number into !
					if (nums_str[n].equals(current_n)){
						nums_str[n] = "!";	
					}
				}
			}
		}
		return count;
	}

	// Populate the new array
	// Slightly spaghetti code, REFACTOR BEFORE UPLOADING
	public static void populateArray(String[][] new_array, String[] nums_str){
		int count = 0;
		// Iterate through original array
		for (String num : nums_str) {
			String current_n = num;
			// Check if number has not been found yet
			if (num != "!"){
				new_array[count][0] = num;
				int count2 = 0;
				for (int n = 0; n < nums_str.length; n++) {
					if (nums_str[n].equals(current_n)){
						nums_str[n] = "!";
						count2++;
						new_array[count][1] = Integer.toString(count2);
					} 
				}
				count++;
			}
		}
		// Uncomment to print out 2d array for debugging
		//System.out.println(Arrays.deepToString(new_array));
	}

	// Print out the histogram
	public static void printHistogram(String[][] data_array){
		// Figure out amount of empty spaces
		int max_length = getBiggest(data_array);
		int spaces = max_length - 1;
		// Print standard header
		printSpaces(spaces);
		System.out.println("Histogram Results");
		printSpaces(spaces);
		System.out.println("=================");
		// Iterate through data array
		// Print each number, and asterisks equal to amount of each number
		for (int i = 0; i < data_array.length; i++) {
			printSpaces(max_length - data_array[i][0].length());
			System.out.print(data_array[i][0] + ": ");
			for (int n = 0; n < Integer.parseInt(data_array[i][1]); n++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	// Find the biggest length in the given array
	public static int getBiggest(String[][] data_array){
		int max_length = 0;
		for (int i = 0; i < data_array.length; i++) {
			if (data_array[i][0].length() > max_length){
				max_length = data_array[i][0].length();
			}
		}
		return max_length;
	}

	// Print spaces given amount
	public static void printSpaces(int spaces){
		for (int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}
	}

	// Sort the 2d data array using selection sort
	public static void selectionSort(String[][] data_array){
		if (SORTED) {
			// Iterate through each data point
			for (int i = 0; i < data_array.length - 1; i++) {
				// Find minimum value
				int min_index = findMin(data_array, i);
				// Swap with minimum value
				// NOTE: Current value might be minimum, in which
				// case it swaps with itself
				swap(data_array, i, min_index);
			}
		}
	} 

	// Swaps two items in an array
	public static void swap(String[][] list, int index1, int index2){
		// Swap number 
		String temp = list[index1][0];
		list[index1][0] = list[index2][0];
		list[index2][0] = temp;
		// Swap amount 
		temp = list[index1][1];
		list[index1][1] = list[index2][1];
		list[index2][1] = temp;
	}

	// Finds the minimum value in the data array after given index
	public static int findMin(String[][] list, int index){
		int min = index;
		for (int i = index + 1; i < list.length ; i++) {
			if (Integer.parseInt(list[i][0]) < Integer.parseInt(list[min][0])){
				min = i;
			}
		}
		return min;
	}
}