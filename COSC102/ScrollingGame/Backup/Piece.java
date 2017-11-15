import java.awt.Color;

public class Piece {

	private Location loc;
	private Color col;

	public Piece(Location tloc, Color tcolor){
		loc = tloc;
		col = tcolor;
	}

	public Location getLoc(){
		return loc;
	}

	public Color getColor(){
		return col;
	}

	public boolean scroll(GameGrid grid){
		Location tloc = new Location(loc.getRow() + 1, loc.getCol(), col);
		Location[] newlocs = new Location[]{tloc};
		if (checkMove(newlocs, grid)){
			loc = tloc;
			return true;
		}
		return false;
	}	

	public void moveLeft(GameGrid grid){
		Location tloc = new Location(loc.getRow(), loc.getCol() - 1, col);
		Location[] newlocs = new Location[]{tloc};
		if (checkMove(newlocs, grid))
			loc = tloc;
	}

	public void moveRight(GameGrid grid){
		Location tloc = new Location(loc.getRow(), loc.getCol() + 1, col);
		Location[] newlocs = new Location[]{tloc};
		if (checkMove(newlocs, grid))
			loc = tloc;
	}

	// Receives an array of locations and a GameGrid as parameters
	// Returns true or false based on whether or not the positions provided
	// are valid positions to make a move to.
	// A position is invalid when it is out of grid bounds or is already 
	// occupied by a piece.
	public boolean checkMove(Location[] positions, GameGrid checkgrid){
		for (int i = 0; i < positions.length; i++) {
			int trow = positions[i].getRow();
			int tcol = positions[i].getCol();

			// Check if row is in grid bounds
			if (trow >= checkgrid.getNumRows() || trow < 0 )
				return false;

			// Check if col is in grid bounds
			if (tcol >= checkgrid.getNumCols() || tcol < 0 )
				return false;

			// Check if position is empty
			if (checkgrid.getCellImage(positions[i]) == "test.jpg")
				return false;
		}
		// If no false flags are caught, then the move is valid
		return true;
	}
}