/* COSC 102 - Wordgen
 * Caio Brighenti
 * Spring '17
 * Table.java File (March 2017)
 */ 

import java.util.*;
import java.util.ArrayList;

public class Table {
	
	private String text;
	private ArrayList<Pair> tableList;
	private int rows;
	private String seed;
	private int len;

	// Constructor
	public Table(String textt){
		this.tableList = new ArrayList<Pair>();
		this.rows = 0;
		this.text = textt;
	}

	public void setLen(int tlen){
		this.len = tlen;
	}

	public void setSeed(String tseed){
		if (tseed.equals("")) {
			this.seed = "" + tableList.get(0).getKey();
		} else {
			this.seed = tseed;
		}
	}

	// Fills the table with the sequences and freqlists
	public void populateList(){
		for (int i = 0; i < text.length() - 1; i++) {
			String charPair = text.charAt(i) +"" + text.charAt(i+1);
			// Determine whether seq is already present in list
			if (!charPairPresent(charPair)) {
				tableList.add(new Pair(charPair, new FrequencyList(charPair)));
				rows++;
				// Method to create a freqlist for current char
				calcFrequency();
			}
		}
	}

	// Creates the freqlist for a given seq
	public void calcFrequency(){
		for (int i = 0; i < text.length() - 2; i++) {
			String charPair = text.charAt(i) +"" + text.charAt(i+1);
			if (charPair.equals(tableList.get(rows - 1).getKey())) {
				FrequencyList flist = (FrequencyList)tableList.get(rows - 1).getValue();
				// Calls the Freqlist method to add the presence of curr char
				flist.addChar("" + text.charAt(i+2));
			}
		}
	}

	// Determines whether the seq is already existant in list
	public boolean charPairPresent(String chars){
		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).getKey().equals(chars))
				return true;
		}
		return false;
	}

	public String toString(){
		for (int i = 0; i < tableList.size(); i++) {
			System.out.print(tableList.get(i).getKey() + "   ");
			System.out.print(tableList.get(i).getValue().toString());
			System.out.println();
		}
		return "";
	}

	// Generates the AI based string
	public String genString(){
		String newstr = this.seed;
		String seq;
		// Keep adding chars while the length is under the maximum
		while (newstr.length() < this.len) {
			// Grab latest sequence
			seq = newstr.substring(newstr.length() - 2);
			boolean charFound = false;
			// Iterate through tablelist to find freqlist
			for (int i = 0; i < tableList.size(); i++) {
				if (tableList.get(i).getKey().equals(seq)){
					// Use freqlist to determine which char to add
					FrequencyList freq = (FrequencyList)tableList.get(i).getValue();
					newstr += freq.calcChar();
					charFound = true;
				}
			}
			// If an entry is not found for char, exit the while loop
			if (!charFound)
				return newstr;
		}
		return newstr; 
	}

}