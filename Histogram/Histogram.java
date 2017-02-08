/* COSC 102 - Assignment 02: Histogram
 * Spring '17
 * Caio Brighenti
 * 02/06/2017
 */ 

import java.util.Arrays;

public class Histogram{

	public static void main(String[] args) {
		int[] test = {4, 2, 9, 1, 4, 12, 100, 100, 100, 100};
		makeHistogram(test);
	}

	public static void makeHistogram(int[] nums){
		// Count unique numbers
		int unique_count = countUnique(makeString(nums));
		// Initialize array
		String[][] data = new String[unique_count][2];
		// Populate array
		populateArray(data, makeString(nums));
		// Print out histogram
		printHistogram(data);
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
		System.out.println(Arrays.deepToString(new_array));
	}

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

	public static int getBiggest(String[][] data_array){
		int max_length = 0;
		for (int i = 0; i < data_array.length; i++) {
			if (data_array[i][0].length() > max_length){
				max_length = data_array[i][0].length();
			}
		}
		return max_length;
	}

	public static void printSpaces(int spaces){
		for (int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}
	}
}