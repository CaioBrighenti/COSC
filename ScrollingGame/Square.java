/* COSC 102 - Scrolling Game
 * Caio Brighenti
 * Spring '17
 * Square.java File (March 2017)
 */ 

import java.awt.Color;

public class Square extends Piece{
 
 	public Square(Location tlloc, Color tcolor){
 		super(Square.getLocationArray(tlloc), tcolor);
 	}

 	public static Location[] getLocationArray(Location topleft){
		Location loc1 = topleft;
 		Location loc2 = new Location(topleft.getRow(), topleft.getCol() + 1);
 		Location loc3 = new Location(topleft.getRow() + 1, topleft.getCol());
 		Location loc4 = new Location(topleft.getRow() + 1, topleft.getCol() + 1);
 		Location[] locs = new Location[]{loc1, loc2, loc3, loc4};
 		return locs;
 	}

 	//Unnecessary
 	public void rotate(GameGrid grid){}

}