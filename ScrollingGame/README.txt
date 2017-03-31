/* COSC 102 - Scrolling Game
 * Caio Brighenti
 * Spring '17
 * README.txt File (March 2017)
 */ 

I decided to take an entirely different approach at the Scrolling Game and recreate an old classic, Tetris. I figured this wouldn't be that difficult considering it's essentially just pieces scrolling down, as opposed to left. It uses a grid just like the intended Scrolling Game, so I figured it'd be an interesting challenge to tackle.

I implemented this functionality by taking advantage of the already provided grid, cell and location classes. At a high level, pieces in Tetris merely consist of locations, and whether a location is occupied by a piece can be identified by whether or not there is something drawn in it (color/image).

The biggest challenge was designing a system where pieces could consist of multiple locations, as opposed to single square pieces. I did this by writing an abstract Piece class, from which each specific pieces, such as the L or Square pieces. 

The abstract Piece class has two instance variables; a color and an array holding the locations that Piece occupies. The class has methods for moving the piece left and right, and scrolling it down. It has a method that can be used by any of the other methods to determine whether a move is valid, and is used to simplify the proccess of determining whether or not a left/right/down move is able to happen at any moment. I'm particularly proud of the Piece class, especially the CheckMove() function.

The game itself works fairly simply. On each loop, it determines whether an activePiece currently exists. If not, it initializes a random piece with a random starting collumn and a random color. If it does, it tries to move the piece down . If the piece cannot be moved, the current active piece is set to null so that a new piece can be added on the next loop.

At the end of each loop, the game checks whether any rows are full. For any full row, points are added equal to the number of blocks cleared, the row is cleared, and all the rows above are moved down 1. A check also occurs to see if the game is over. The game is over when a piece occupies the topmost collumn. Once the game is over, the game pauses for a few seconds so you can see that you lost, and then a Game Over screen is shown. After a few seconds, the game exits.

The theme of the game is classic Tetris. All the images were made from scratch in Photoshop emulating the style of the original Nintendo Tetris.

No changes have been made to the GameGrid.java and Location.java files.