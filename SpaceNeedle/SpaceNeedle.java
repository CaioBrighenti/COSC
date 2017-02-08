/* COSC 102 - Space Needle
 * Spring '17
 * Caio Brighenti
 * 02/03/2017
 */ 


public class SpaceNeedle{
	// Scale variable, can be any number, but near 50 it starts to get
	// too big to see well in the terminal
	public static final int SCALE = 7;

	public static void main(String[] args) {
		// Calculate maximum line length, then draw each pattern
		int maxLength = 8 + (6 * (SCALE - 1));
		drawP1(SCALE, maxLength);
		drawP2(SCALE, maxLength);
		drawP3(SCALE, maxLength);
		drawP4(SCALE, maxLength);
		drawP1(SCALE, maxLength);
		drawP5(SCALE, maxLength);
		drawP2(SCALE, maxLength);
		drawP3(SCALE, maxLength);
	}

	public static void drawP1(int size,int length){
		for (int n = 0; n < size; n++){
			// Print empty space
			for (int i = 0; i < ((length - 2) / 2); i++){
				System.out.print(" ");
			}
			// Print standard middle
			System.out.print("||");
			System.out.println();
		}

	}

	public static void drawP2(int size,int length){
		for (int n = 0; n < size; n++){
			// Print empty space
			int empty_spaces = (length - 8 - (6 * n)) / 2;
			for (int i = 0; i < empty_spaces; i++){
				System.out.print(" ");
			}
			// Print standard opener
			System.out.print("__/");
			// Print colons
			for (int i = 0; i < (n * 3); i++){
				System.out.print(":");
			}
			// Print standard middle
			System.out.print("||");
			// Print colons
			for (int i = 0; i < (n * 3); i++){
				System.out.print(":");
			}
			// Print standard closer
			System.out.print("\\__");
			// Start new line
			System.out.println();
		}
	}
	public static void drawP3(int size, int length){
		// Print standard opener
		System.out.print("|");
		// Print quotation mark pattern
		for (int i = 0; i < (length - 2); i++){
			System.out.print("\"");
		}
		// Print standard closer
		System.out.print("|");
		// Start new line
		System.out.println();
	}
	public static void drawP4(int size, int length){
		for (int n = 0; n < size; n++){
			// Print empty spaces
			for (int i = 0; i < (n * 2); i++){
				System.out.print(" ");
			}
			// Print standard opener
			System.out.print("\\_");
			// Print body
			for (int i = 0; i < ((length - 4 - (n * 4)) / 2); i++) {
				System.out.print("/\\");
			}
			// Print standard closer
			System.out.print("_/");
			// Start new line
			System.out.println();
		}
	}
	public static void drawP5(int size, int length){
		// Print pattern
		for (int n = 0; n < (size * 4); n++) {
			// Print empty spaces
			for (int i = 0; i < (length - 8) / 2; i++) {
				System.out.print(" ");
			}
			// Print standard pattern	
			System.out.println("|%%||%%|");
		}
	}
}