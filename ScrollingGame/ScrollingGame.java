import java.awt.event.KeyEvent;
import java.util.Random;

public class ScrollingGame {
	
	// set DEMO to false to use your code (true uses DemoGame.class)
	private static final boolean DEMO = false;           
	
	// Game window should be wider than tall:  H_DIM < W_DIM   
	// (more effectively using space)
	// # of cells vertically by default: height of game
	private static final int H_DIM = 5; 
	// # of cells horizontally by default: width of game
	private static final int W_DIM = 10;  
	// default location of the user at the start of the game
	private static final int U_ROW = 0;
	
	private static final int FACTOR = 3;      // you might change that this
	                           // setting or declaration when working on timing
	                           // (speed up and down the game)
	                           
	                           
	private final String USER_IMG = "user.gif";    //ADD others
	                        
	private static Random rand = new Random();  //USE ME 
	                                          // don't instantiate me every frame
	
	private GameGrid grid; // graphical window to display the game (the VIEW)
	                       // a 2D game made of two dimensional array of Cells
	private int userRow;
	
	private long msElapsed;		// game clock: number of ms since start of
	                          // game main loop---see play() method
	private int timerClicks;	// used to space animation and key presses
	private int pauseTime;		// to control speed of game

	
	public ScrollingGame() {
		this(H_DIM, W_DIM, U_ROW);
	}
	
	public ScrollingGame(int hdim, int wdim, int urow) {
		// to be filled
		
		init(hdim, wdim, urow);
	}
	
	private void init(int hdim, int wdim, int urow) {  
		
		/* initialize the game window

				NOTE: look at the various constructors to see what you can do!
				For example:
					grid = new GameGrid(hdim, wdim, Color.MAGENTA);
					
				You need to use the one that takes an image to implement the 
				splashscreen functionality (don't start there, but do it as your
				first extension)
		*/
		grid = new GameGrid(hdim, wdim);   
		
		/* initialize other aspects of game state */

		// animation settings
		timerClicks = 0;
		msElapsed = 0;
		pauseTime = 100;

		// store and initialize user position
		userRow = urow;
		grid.setCellImage(new Location(userRow, 0), USER_IMG);
		
		updateTitle();
		
	}
	
	public void play() {
		
		/* main game loop */
		while (!isGameOver()) {
			
			grid.pause(pauseTime); 	 // pause for some time (smooth animation)
			msElapsed += pauseTime;	 // count the total amount of time elapsed
			timerClicks++;				 // increment the timer count
		
			handleKeyPress();        // update state based on user key press
			
			handleMouseClick();      // when the game is running: 
			                         // click & read the console output 
			
			
			if (timerClicks % FACTOR == 0) {  // if it's the FACTOR timer tick
				                            // constant 3 initially
				scrollLeft();
				populateRightEdge();
			}
			
			updateTitle();
			msElapsed += pauseTime;
		}
	}
	
	public void handleMouseClick() {
		
		Location loc = grid.checkLastLocationClicked();
		
		if (loc != null)
			System.out.println("You clicked on a square " + loc);
	
	}

	
	public void handleKeyPress() {
			
		int key = grid.checkLastKeyPressed();
		
		//use Java constant names for key presses
		//http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_DOWN
		
		// Q for quit
		if (key == KeyEvent.VK_Q)
			System.exit(0);
		
		else if (key == KeyEvent.VK_S)
			System.out.println("could save the screen: add the call");
		
		/* To help you with step 9: 
		   use the 'T' key to help you with implementing speed up/slow down/pause
    	   this prints out a debugging message */
		else if (key == KeyEvent.VK_T)  {
			boolean interval =  (timerClicks % FACTOR == 0);
			System.out.println("pauseTime " + pauseTime + " msElapsed reset " + 
				msElapsed + " interval " + interval);
		}
	}
	
	// update game state to reflect adding in new cells in the right-most column 
	public void populateRightEdge() {
		
	}
	
	// updates the game state to reflect scrolling left by one column 
	public void scrollLeft() {
		
	}
	
	public void handleCollision(Location loc) {
		
	}
	
	// return the "score" of the game 
	public int getScore() {
		return 0;    //dummy for now
	}
	
	
	// update the title bar of the game window 
	public void updateTitle() {
		grid.setTitle("Scrolling Game:  " + getScore());
	}
	
	
   // return true if the game is finished, false otherwise
	//      used by play() to terminate the main game loop 
	public boolean isGameOver() {
		return false;
	}
	
	public static void run() {
		if (DEMO) {       // reference game: 
			//   - play and observe first the mechanism of the demo 
			//     to understand the basic game 
			//   - go back to the demo anytime you don't know what the
			//     next step to implement and to test are. you should always be 
			//     clear and concrete about the ~5 lines you are trying to code and
			//     how to validate them
			//         figure out according to the game play 
			//         (the sequence of display and action) how the functionality
			//         you are implementing next is supposed to operate
			
			// It's critical to have a plan for each piece of code: follow, understand
			// and study the assignment description details; and explore the basic game. 
			// You should always know what you are doing (your current small goal) 
			// before implementing that piece or talk to us. 
			
			System.out.println("Running the demo: DEMO=" + DEMO);
			//constructor for client to adjust the game window size 
			//TRY different values
			DemoGame game = new DemoGame(5, 10, 0);
			//default constructor   (4 by 10)
			// DemoGame game = new DemoGame();
			game.play();
			
		} else {
			System.out.println("Running student game: DEMO=" + DEMO);
			// !DEMO   -> your code should execute those lines when you are
			// implementing your game
			
			//test 1: with parameterless constructor
			ScrollingGame game = new ScrollingGame();
			
			//test 2: with constructor specifying grid size  
			//IT SHOULD ALSO WORK as long as height < width
			//ScrollingGame game = new ScrollingGame(10, 20, 4);
			
			game.play();
		}
	}
	
	public static void main(String[] args) {
		run();
	}
}