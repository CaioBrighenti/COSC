/* COSC 102 - Assignment 02: Histogram
 * Spring '17
 * Caio Brighenti
 * 02/06/2017
 */ 

public class Histogram{

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

	public static void makeHistogram(int[] nums){
		// Count unique numbers
		// Initialize array
		// Populate array
	}

	public static int countUnique(int[] nums_str){
		// Iterate through each item, if item isn't a !, increment count and iterate setting all items equal to !
		int count = 0;
		for (int i : nums_str) {
			if (nums_str[i] != "!"){
				count++;
				for (int n : nums_str) {
					// Problem here, store original value in variable to compare
					if (nums_str[n] == nums[i]){
						nums_str[n] = "!";
					}
				}
			}
		}
		return count;
	}

	public static void populateArray(int[] new_array, int[] nums){

	}
}