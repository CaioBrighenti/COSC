/* COSC 102 - Scrolling Game
 * Caio Brighenti
 * Spring '17
 * CaioBrighentiGame.java File (March 2017)
 */ 

import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaioBrighentiGame {
	
	// set DEMO to false to use your code (true uses DemoGame.class)
	private static final boolean DEMO = false;           
	
	// Game window should be wider than tall:  H_DIM < W_DIM   
	// (more effectively using space)
	// # of cells vertically by default: height of game
	private static final int H_DIM = 25; 
	// # of cells horizontally by default: width of game
	private static final int W_DIM = 10;  
	// default location of asdadthe user at the start of the game
	private static final int U_ROW = 0;
	
	private static final int FACTOR = 1;      // you might change that this
	                           // setting or declaration when working on timing
	                           // (speed up and down the game)
	                           
	// Images used in game                          
	private final String PIECE_IMG = "piece.png";
	private final String BG_IMG = "background.jpg";
	private final String SPLASH_IMG = "splash.jpg";
	private final String GAMEOVER_IMG = "gameover.jpg";

	// Array of possible piece colors
	private final Color[] colorArray = new Color[]{Color.YELLOW, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED};
	                        
	private static Random rand = new Random();  //USE ME 
	                                          // don't instantiate me every frame
	
	private GameGrid grid; // graphical window to display the game (the VIEW)
	                       // a 2D game made of two dimensional array of Cells
	private long msElapsed;		// game clock: number of ms since start of
	                          // game main loop---see play() method
	private int timerClicks;	// used to space animation and key presses
	private int pauseTime;		// to control speed of game

	private Piece activePiece = null; // holds the current active piece

	private boolean gameOver = false; // whether or not the game is over
	private boolean isPaused = true; // used to control whether the game is paused
	private boolean splashShown = false;
	private boolean linesToggled = false; // whether or not the lines are toggled

	private int gameScore; // holds the user's score

	
	public CaioBrighentiGame() {
		this(H_DIM, W_DIM, U_ROW);
	}
	
	public CaioBrighentiGame(int hdim, int wdim, int urow) {
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
		grid.setSplash(SPLASH_IMG);
		
		/* initialize other aspects of game state */

		// animation settings
		timerClicks = 0;
		msElapsed = 0;
		pauseTime = 100;

		updateTitle();

		grid.setGameBackground(BG_IMG);
		
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
			
			
			if (timerClicks % FACTOR == 0 && !isPaused) {  // if it's the FACTOR timer tick
				                            // constant 3 initially
				if (activePiece ==  null)
					addPiece();
				else
				scrollDown();
			}
			
			updateTitle();
			msElapsed += pauseTime;

		}
		grid.pause(3500); 
		grid.setSplash(GAMEOVER_IMG);
		grid.pause(7500);
		System.exit(0);
	}
	
	public void handleMouseClick() {
		
		Location loc = grid.checkLastLocationClicked();
		
		if (loc != null)
			System.out.println("You clicked on a square " + loc);
			//System.out.println(grid.getCellImage(loc));
	
	}

	
	public void handleKeyPress() {
			
		int key = grid.checkLastKeyPressed();
		
		//use Java constant names for key presses
		//http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_DOWN
		if (key != -1 & !splashShown){
			grid.setSplash(null);
			isPaused = false;
			splashShown = true;
		}


		// Q for quit
		if (key == KeyEvent.VK_Q)
			System.exit(0);
		
		// S for saving screenshot
		else if (key == KeyEvent.VK_S){
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			grid.save("Screenshot " + timeStamp +".jpg");
		}	
		
		/* To help you with step 9: 
		   use the 'T' key to help you with implementing speed up/slow down/pause
    	   this prints out a debugging message */
		else if (key == KeyEvent.VK_T)  {
			boolean interval =  (timerClicks % FACTOR == 0);
			System.out.println("pauseTime " + pauseTime + " msElapsed reset " + 
				msElapsed + " interval " + interval);
		} 

		// Moves piece left
		else if (key == KeyEvent.VK_LEFT && !isPaused) {
			// Avoid run time error by checking if piece exists
			if (activePiece == null)
				return;		
			hidePiece();
			activePiece.moveLeft(grid);
			drawPiece();
		}

		// Moves piece right
		else if (key == KeyEvent.VK_RIGHT && !isPaused){
			// Avoid run time error by checking if piece exists
			if (activePiece == null)
				return;		
			hidePiece();
			activePiece.moveRight(grid);
			drawPiece();
		} 

		// Rotates piece
		else if (key == KeyEvent.VK_UP && !isPaused) {
			if (activePiece == null)
				return;		
			hidePiece();
			activePiece.rotate(grid);
			drawPiece();
		}

		// Moves piece down;
		else if (key == KeyEvent.VK_DOWN && !isPaused) {
			if (activePiece == null)
				return;		
			hidePiece();
			activePiece.scroll(grid);
			drawPiece();
		}

		// Pauses game
		else if (key == KeyEvent.VK_P){
			isPaused = !isPaused;
		}

		// Draws grid lines
		else if (key == KeyEvent.VK_D){
			if (!linesToggled)
				grid.setLineColor(Color.WHITE);
			else
				grid.setLineColor(null);
			linesToggled = !linesToggled;
		}

		else if (key == KeyEvent.VK_COMMA)
			pauseTime+=35;

		else if (key == KeyEvent.VK_PERIOD){
			if (pauseTime > 35)
				pauseTime-= 35;
		}
	}
	
	// Adds a new random piece to the game
	public void addPiece() {
		// Randomize color
		int r = rand.nextInt(7);
		Color piececolor = colorArray[r];

		//Randomize location
		r = rand.nextInt(grid.getNumCols() - 3);
		Location pieceloc = new Location(0, r);

		// Randomize piece
		r = rand.nextInt(4);
		if (r == 0)
			activePiece = new L(pieceloc, piececolor);
		else if (r == 1)
			activePiece = new Square(pieceloc, piececolor);
		else if (r == 2)
			activePiece = new Cobra(pieceloc, piececolor);
		else if (r == 3)
			activePiece = new Worm(pieceloc, piececolor);

		// Draw the piece on the game board
		drawPiece();	
	}

	// Hides the currently active piece from the board
	public void hidePiece(){
		if (activePiece != null){
			for (int i = 0; i < activePiece.getLocs().length; i++ ) {
				grid.setCellImage(activePiece.getLocs()[i], null);
				grid.setColor(activePiece.getLocs()[i], null);
			}
		}
	}

	// Draws the currently active piece on the game board
	public void drawPiece(){
		boolean collision = false;
		if (activePiece != null) {
			for (int i = 0; i < activePiece.getLocs().length; i++ ) {
				// Make sure there's no collision when spawning, if so end the game
				if (!(grid.getCellImage(activePiece.getLocs()[i]) == PIECE_IMG)){
					grid.setCellImage(activePiece.getLocs()[i], PIECE_IMG);
					grid.setColor(activePiece.getLocs()[i], activePiece.getColor());
				} else
					collision = true;
			}
		}
		if (collision)
			gameOver = true;
	}
	
	// updates the game state to reflect scrolling down one row
	public void scrollDown() {	
		hidePiece();
		// If piece can't be scrolled, end the game
		if (!activePiece.scroll(grid)) {
			drawPiece();
			activePiece = null;
			checkRows();
			gameOver = checkEndGame();
		} else
			drawPiece();
	}

	// Checks for full rows
	public void checkRows(){
		// Iterate through each col
		for (int row = 0; row < grid.getNumRows(); row++) {
			// For each row, check if it is full and if so clear it and add points
			// After clearing, lower all blocks above and call CheckRows() again
			if (checkFullRow(row)){
				clearRow(row);
				addScore(grid.getNumCols());
				lowerBlocks(row);
				checkRows();
				break;
			}
		}
	}

	// Checks if a specific row is full
	// returns true if it is full, otherwise false
	public boolean checkFullRow(int rownumber){
		for (int col = 0; col < grid.getNumCols(); col++) {
			if (grid.getCellImage(new Location(rownumber, col)) != PIECE_IMG)
				return false;
		}
		return true;
	}

	// Clears a given row, setting all the cells to blank
	public void clearRow(int rownumber){
		for (int col = 0; col < grid.getNumCols(); col++) {
			grid.setCellImage(new Location(rownumber, col), null);
			grid.setColor(new Location(rownumber, col), null);
		}
	}

	// Method to have blocks above a cleared row "fall"
	public void lowerBlocks(int startrow){
		for (int r = startrow - 1; r > 0; r--) {
			for (int c = 0; c < grid.getNumCols(); c++) {
				grid.setColor(new Location(r + 1, c), grid.getColor(new Location(r,c)));
				grid.setCellImage(new Location(r + 1, c), grid.getCellImage(new Location(r,c)));
				grid.setColor(new Location(r,c), null);
				grid.setCellImage(new Location(r,c), null);
			}
		}
	}

	// Checks if any cell in row 0 are occupied
	// if so, return true, otherwise false
	public boolean checkEndGame(){
		for (int i = 0; i < grid.getNumCols(); i++) {
			if (grid.getCellImage(new Location(0, i)) == PIECE_IMG)
				return true;
		}
		return false;
	}

	// return the score of the game 
	public int getScore() {
		return gameScore;   
	}

	// adds points to the game score
	public void addScore(int points){
		gameScore+=points;
	}
	
	
	// update the title bar of the game window 
	public void updateTitle() {
		grid.setTitle("Tetris:  " + getScore());
	}
	
   // return true if the game is finished, false otherwise
	//      used by play() to terminate the main game loop 
	public boolean isGameOver() {
		return gameOver;
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
			System.out.println("Running Caio student game: DEMO=" + DEMO);
			// !DEMO   -> your code should execute those lines when you are
			// implementing your game
			
			//test 1: with parameterless constructor
			CaioBrighentiGame game = new CaioBrighentiGame();
			
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