  /*  ****
   *  Caio Brighenti '20
   * April 2017
   * Lab 12
   *  ****
   */
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class MindReader{
  
  //The score at which the game ends
  private static final int MAX_SCORE = 25;  
  //The number of turns the computer looks back to determine its probabilities
  //In other words, the length of the keys in your HashMap
  private static final int MEMORY_SIZE = 3;

  
  //Valid inputs for prompting the user for yes/no or a color
  private static final String[] RED_BLUE = {"r", "b"};
  private static final String[] YES_NO = {"y", "n"};

  //If DEBUG is enabled, prints out extra data relevant to the probabilities
  //of the computer's next color choice
  private static final boolean DEBUG = true;

  //Stores the player and cpu's scores
  private int playerScore, cpuScore;
  
  // Stores the last seq of characters
  private String lastSeq;
  
  // Hash map storing guesses
  private HashMap guesses;
  
  // Random object
  private Random rand;

  
  //Initializes the game, resets/initializes everything as needed
  private void init(){
    //Resets the scores back to 0
    playerScore = 0;
    cpuScore = 0;
    // Initialize the HashMap
    guesses = new HashMap<String, int[]>();
    // Empty the sequence holder
    lastSeq = "";
    // Initialize rand
    rand = new Random();
  }
  
  
  
  //Checks to see if the input string matches any of the items in the 
  //validValues array
  private static boolean validate(String input, String[] validValues){
    for (int i = 0; i < validValues.length; i++){
      if (validValues[i].equals(input))
        return true;
    }
    return false;
    
  }
  
  //Prompts the user to enter an input, displaying the message contained in the
  //prompt String argument.  Continues to prompt the user until they enter one
  //of the values contained in the validValues argument array.
  private static String promptUser(String prompt, String[] validValues)
  {
    Scanner s = new Scanner(System.in);
    System.out.println(prompt);
    while (true){
      String input = s.nextLine().toLowerCase();
      if (input != null && validate(input, validValues))
        return input;
      System.err.print("Invalid Input!  Enter: ");
      for (int i = 0; i < validValues.length - 1; i++)
        System.err.print(validValues[i] + ", ");
      System.err.println("or " + validValues[validValues.length-1] +"!");
    }
  }

  
    
  //Outputs the current player and cpu scores
  //If DEBUG is set to true, outputs the historical data the CPU is using
  //to determine its probabilities
  private void printScores(){
    System.out.println("\nPlayer:  " + playerScore + "\t   CPU:  " + cpuScore);
    
    System.out.println("========================================");
    //Implement the debug output!
    if (DEBUG){
    	int r = 0;
    	int b = 0;
    	if (guesses.containsKey(lastSeq)){
    		int[] tempArr = (int[])guesses.get(lastSeq);
    		r = tempArr[0];
    		b = tempArr[1];
    	}
      System.out.println("Current history: " + lastSeq + ", data = [" + r + ", " + b + "]" );
     }
  }
  
  
  //Plays one game of the color guessing game
  public void playGame(){
    init();
    
    String pGuess;
    String cGuess;
    // Game loop
    while (playerScore < MAX_SCORE & cpuScore < MAX_SCORE){
    	// Print scores
    	printScores();
    	
    	// Get player guess
    	String[] tempArr = {"r","b"};
    	pGuess = promptUser("Pick a color! CHoose either (r)ed or (b)lue: ", tempArr);
    	
    	// Get computer guess
    	cGuess = cpuGuess(!guesses.containsKey(lastSeq), lastSeq);
    	
    	// Manage scores
    	if (pGuess.equals(cGuess)){
    		System.out.println("Computer correctly guessed: " + cGuess);
    		cpuScore++;
    	} else {
    		System.out.println("Computer incorrectly guessed: " + cGuess);
    		playerScore++;
    	}
    	
    	// Add player guess to hash map
    	if (lastSeq.length() >= MEMORY_SIZE){
    		addGuess(lastSeq, pGuess);
    		lastSeq = lastSeq.substring(1) + pGuess;
    	} else
    		lastSeq += pGuess;
    }
    
    if (cpuScore > playerScore)
    	System.out.println("Computer wins " + cpuScore + " to " + playerScore + "!");
    else
    	System.out.println("Player wins " + playerScore + " to " + cpuScore + "!");
  }
  
  public void addGuess(String seq, String g){
  	  // If sequence isnt in hash map, add it
  	  if (!guesses.containsKey(seq)){
  	  	  int[] tempArr = {0,0};
  	  	  guesses.put(seq, tempArr);
  	  }
  	
  	  // Add guess to appropriate value
  	  int idx = 0;
  	  if (g.equals("b"))
  	  	  	  idx = 1;
  	  int[] guessArr = (int[])guesses.get(seq);
  	  guessArr[idx]+=1;
  }
  
  public String cpuGuess(boolean noAi, String seq){
  	  // If no AI just do 50/50
  	  if (noAi){
  	  	  int r = rand.nextInt(2);
  	  	  return RED_BLUE[r];
  	  } else {  // If AI, calculate probabilities
  	  	  String[] tempArr = probArray(seq);
  	  	  int r = rand.nextInt(tempArr.length);
  	  	  return tempArr[r];
  	  }
  }
  
  // Creates a probability array to calculate cpu guess
  public String[] probArray(String seq){
  	  int[] guessArr = (int[])guesses.get(seq);
  	  int reds = guessArr[0];
  	  int blues = guessArr[1];
  	  String[] newArray = new String[reds+blues];
  	  // Add reds
  	  for (int i = 0; i < reds; i++){
  	  	  newArray[i] = RED_BLUE[0];
  	  }
  	  // Add blues
  	  for (int n = 0; n < blues; n++){
  	  	  newArray[reds + n] = RED_BLUE[1];
  	  }
  	  return newArray;
  }
  
  

  
  //Plays one game of the color guessing game, then prompts the user to play again
  public static void main(String[] args){
    String again = "y";
    MindReader mr = new MindReader();
    while (again.equals("y")){
      mr.playGame();
      again = promptUser("Would you like to play again? (y/n): ", YES_NO);
    }
    System.out.println("Thanks for playing!");
    
    
  }
  

}

