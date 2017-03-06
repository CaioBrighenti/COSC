import java.awt.Color;

public class Location {
	
  private int row;
  private int col;
  private Color color = null;
  
  public Location(int r, int c) {
    row = r;
    col = c;
  }
  
  public Location(int r, int c, Color acolor) {
    row = r;
    col = c;
    color = acolor;
  }
  
  public int getRow() {
    return row;
  }
  
  public int getCol() {
    return col;
  }
  
  public boolean equals(Location otherLoc) {
    return row == otherLoc.getRow() && col == otherLoc.getCol();
  }
  
  public String toString() {
    return "(" + row + ", " + col + ")";
  }
  
}