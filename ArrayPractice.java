/* COSC 102 - Lab 02
 * Spring '17
 * Caio Brighenti
 * 02/03/2017
 */ 

import java.util.Scanner;
import java.util.Arrays;

public class ArrayPractice{
  
   public static void main(String[] args) {
    // Test cases
      int[] test = {4, 2, 9, 1, 4, 12};
      System.out.println(Arrays.toString(findBetween(test, 10, 2)));

      int[] test1 = {4, 12, 7, 8, 2, 9, 5};
      System.out.println(Arrays.toString(findBetween(test1, 4, 10)));

      int[] test2 = {8, 8, 10, 10, 8};
      System.out.println(Arrays.toString(findBetween(test2, 9, 7)));

      int[] test3 = {1, 2, 3, 4, 5};
      System.out.println(Arrays.toString(findBetween(test3, 9728, 9921)));

      int[] test4 = {4, 4, 4, 4, 4, 4};
      System.out.println(Arrays.toString(findBetween(test4, 1, 5)));

      int[] test5 = {4, 3, 65, 7, 8, 123};
      System.out.println(Arrays.toString(findBetween(test5, 4, 4)));
  }
  
  /* Accepts an array of ints, as well as two ints representing bounds.
   * Returns a new array of ints that fall between the two bound arguments, inclusive.
   * The returned array should be exactly the same size as the number of applicable numbers,
   * with no extra indices.
   * 
   * Returns a brand new array and does not modify the original argument array.
   */
  public static int[] findBetween(int[] nums, int bound1, int bound2){

    // Find ceiling and floor
  	int ceil = findCeil(bound1, bound2);
    int floor = findFloor(bound1, bound2);
  	
    // Count numbers in range
  	int n_in_range = numsInRange(nums, ceil, floor);
  	
    // Initialize array of proper size
  	int[] new_nums = new int[n_in_range];
  	
    // Add numbers in range to array
  	populateArray(nums, ceil, floor, new_nums);
  	
    return new_nums;
  }

  /*
  * Helper methods
  */

  // Finds and returns the ceil
  // given two bounds           
  public static int findCeil(int bound1, int bound2){
    if (bound1 > bound2)
      return bound1;
    return bound2;
  }
  
  // Finds and returns the floor
  // given two bounds    
  public static int findFloor(int bound1, int bound2){
    if (bound1 < bound2)
      return bound1;
    return bound2;
  }

  // Returns amount of numbers in
  // given range
  public static int numsInRange(int[] nums, int ceil, int floor){
    int n_in_range = 0;
    // Iterate through array, increment count for
    // each number in range
    for (int i = 0; i < nums.length; i++){
      if (nums[i] >= floor && nums[i] <= ceil)
        n_in_range++;
    }
    return n_in_range;
  }

  // Populates empty array with numbers
  // found in the given range
  public static void populateArray(int[] nums, int ceil, int floor, int[] newArray){
    int count = 0;
    // Iterate through array, add each number 
    // in range to new array
    for (int i = 0; i < nums.length; i++){
      if (nums[i] >= floor && nums[i] <= ceil){
        newArray[count] = nums[i];
        count ++;
      }
    }
  }
}