/* COSC 102 - Scrolling Game
 * Caio Brighenti
 * Spring '17
 * Worm.java File (March 2017)
 */ 

import java.awt.Color;

public class Worm extends Piece{
 
	private int rotationState;

 	public Worm(Location tlloc, Color tcolor){
 		super(Worm.getLocationArray(tlloc), tcolor);
 		rotationState = 1;
 	}

 	public static Location[] getLocationArray(Location topleft){
		Location loc1 = topleft;
 		Location loc2 = new Location(topleft.getRow() + 1, topleft.getCol());
 		Location loc3 = new Location(topleft.getRow() + 2, topleft.getCol());
 		Location loc4 = new Location(topleft.getRow() + 3, topleft.getCol());
 		Location[] locs = new Location[]{loc1, loc2, loc3, loc4};
 		return locs;
 	}

 	// Piece has four different rotation states
 	// each rotation is different based on the state
 	// for each rotation, change each location and update the rotation state
 	public void rotate(GameGrid grid){
 		if (rotationState == 1) {
 			Location loc1 = new Location(locs[0].getRow() + 1, locs[0].getCol() - 1);
 			Location loc2 = new Location(locs[1].getRow(), locs[1].getCol());
 			Location loc3 = new Location(locs[2].getRow() - 1, locs[2].getCol() + 1);
 			Location loc4 = new Location(locs[3].getRow() - 2, locs[3].getCol() + 2);
 			Location[] newlocs = new Location[]{loc1, loc2, loc3, loc4};
 			if (checkMove(newlocs, grid)) {
 				for (int i = 0; i < locs.length; i++) {
					locs[i] = newlocs[i];
				}
				rotationState = 2;
 			}
 		}

 		else if (rotationState == 2) {
 			Location loc1 = new Location(locs[0].getRow() + 1, locs[0].getCol() + 1);
 			Location loc2 = new Location(locs[1].getRow(), locs[1].getCol());
 			Location loc3 = new Location(locs[2].getRow() - 1, locs[2].getCol() - 1);
 			Location loc4 = new Location(locs[3].getRow() - 2, locs[3].getCol() - 2);
 			Location[] newlocs = new Location[]{loc1, loc2, loc3, loc4};
 			if (checkMove(newlocs, grid)) {
 				for (int i = 0; i < locs.length; i++) {
					locs[i] = newlocs[i];
				}
				rotationState = 3;
 			}
 		}

 		else if (rotationState == 3) {
 			Location loc1 = new Location(locs[0].getRow() - 1, locs[0].getCol() + 1);
 			Location loc2 = new Location(locs[1].getRow(), locs[1].getCol());
 			Location loc3 = new Location(locs[2].getRow() + 1, locs[2].getCol() - 1);
 			Location loc4 = new Location(locs[3].getRow() + 2, locs[3].getCol() - 2);
 			Location[] newlocs = new Location[]{loc1, loc2, loc3, loc4};
 			if (checkMove(newlocs, grid)) {
 				for (int i = 0; i < locs.length; i++) {
					locs[i] = newlocs[i];
				}
				rotationState = 4;
 			}
 		}

 		else if (rotationState == 4) {
 			Location loc1 = new Location(locs[0].getRow() - 1, locs[0].getCol() - 1);
 			Location loc2 = new Location(locs[1].getRow(), locs[1].getCol());
 			Location loc3 = new Location(locs[2].getRow() + 1, locs[2].getCol() + 1);
 			Location loc4 = new Location(locs[3].getRow() + 2, locs[3].getCol() + 2);
 			Location[] newlocs = new Location[]{loc1, loc2, loc3, loc4};
 			if (checkMove(newlocs, grid)) {
 				for (int i = 0; i < locs.length; i++) {
					locs[i] = newlocs[i];
				}
				rotationState = 1;
 			}
 		}
 	}

}