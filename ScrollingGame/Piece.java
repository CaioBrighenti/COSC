import java.awt.Color;

public abstract class Piece {

	protected Location[] locs;
	private Color col;

	public Piece(Location[] tlocs, Color tcolor){
		locs = tlocs;
		col = tcolor;
	}

	public Location[] getLocs(){
		return locs;
	}

	public Color getColor(){
		return col;
	}

	public boolean scroll(GameGrid grid){
		// Figure out new proposed positions
		Location[] newlocs = new Location[locs.length];
		for (int i = 0; i < locs.length; i++) {
			newlocs[i] = new Location(locs[i].getRow() + 1, locs[i].getCol(), col);
		}
		// If the move is valid, perform it
		if (checkMove(newlocs, grid)){
			for (int i = 0; i < locs.length; i++) {
				locs[i] = newlocs[i];
			}
			return true;
		}
		return false;
	}	

	public void moveLeft(GameGrid grid){
		// Figure out new proposed positions
		Location[] newlocs = new Location[locs.length];
		for (int i = 0; i < locs.length; i++) {
			newlocs[i] = new Location(locs[i].getRow(), locs[i].getCol() - 1, col);
		}
		// If the move is valid, perform it
		if (checkMove(newlocs, grid)){
			for (int i = 0; i < locs.length; i++) {
				locs[i] = newlocs[i];
			}
		}
	}

	public void moveRight(GameGrid grid){
		// Figure out new proposed positions
		Location[] newlocs = new Location[locs.length];
		for (int i = 0; i < locs.length; i++) {
			newlocs[i] = new Location(locs[i].getRow(), locs[i].getCol() + 1, col);
		}
		// If the move is valid, perform it
		if (checkMove(newlocs, grid)){
			for (int i = 0; i < locs.length; i++) {
				locs[i] = newlocs[i];
			}
		}
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
			if (checkgrid.getCellImage(positions[i]) == "piece.png")
				return false;
		}
		// If no false flags are caught, then the move is valid
		return true;
	}

	public abstract void rotate(GameGrid grid);

}