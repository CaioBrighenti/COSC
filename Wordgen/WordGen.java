/* COSC 102 - Wordgen
 * Caio Brighenti
 * Spring '17
 * Wordgen.java File (March 2017)
 */ 

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordGen {
	
	public static void main(String[] args){
		String text = "";
		String seed = "";
		int len = 1000;

		// Not greatest code but manages different arguments
		if (args.length == 0) {
			try{
				text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Caio Laptop\\Documents\\COSC 102\\Wordgen\\papertest.txt")));
			}	catch (IOException e){
  				System.out.println("file not found");
			}
		} else if (args.length == 1) {
			try{
				text = new String(Files.readAllBytes(Paths.get(args[0])));
			}	catch (IOException e){
  				System.out.println("file not found");
			}
		} else if (args.length == 2) {
			try{
				text = new String(Files.readAllBytes(Paths.get(args[0])));
			}	catch (IOException e){
  				System.out.println("file not found");
			}
			seed = args[1];
		} else if (args.length == 3) {
			try{
				text = new String(Files.readAllBytes(Paths.get(args[0])));
			}	catch (IOException e){
  				System.out.println("file not found");
			}
			seed = args[1];
			len = Integer.parseInt(args[2]);
		}



		// Creates table, sets the max len, seed to be used, populates it, and generates the text
		Table wordtable = new Table(text);
		wordtable.setLen(len);
		wordtable.populateList();
		wordtable.setSeed(seed);
		System.out.println(wordtable);
		System.out.println(wordtable.genString());
	}
}