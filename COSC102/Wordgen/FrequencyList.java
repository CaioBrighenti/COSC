/* COSC 102 - Wordgen
 * Caio Brighenti
 * Spring '17
 * FrequencyList.java File (March 2017)
 */ 

import java.util.*;
import java.util.ArrayList;

public class FrequencyList {
	
	private String sequence;
	private ArrayList<Pair> freqList;	

	// Constructor
	public FrequencyList(String seq){
		this.freqList = new ArrayList<Pair>();
		this.sequence = seq;
	}

	// Update freqlist to reflect presence of char parameter
	public void addChar(String character){
		int idx = findChar(character);
		if (idx != -1){
			int val = (int)freqList.get(idx).getValue();
			freqList.get(idx).setValue( val + 1);
		}
		else{
			freqList.add(new Pair(character, 1));
		}
	}

	// Find if char is already present in a pair in the freqlist
	public int findChar(String c){
		for (int i = 0; i < freqList.size(); i++) {
			if (freqList.get(i).getKey().equals(c))
				return i;
		}
		return -1;
	}

	public String toString(){
		String t = "";
		for (int i = 0; i < freqList.size(); i++) {
			t += " "  + freqList.get(i) + " ";
		}
		return t;
	}

	// Calculate a random char based on freqlist weightings
	public String calcChar(){
		String probstring = buildProbString();
		Random rand = new Random(); 
		int r = rand.nextInt(probstring.length());
		return "" + probstring.charAt(r);
	}

	// Builds a string of all chars in the freqlist, repeating based on probabilities
	// This string is used to determine which random char will be added
	public String buildProbString(){
		String str = "";
		for (int i = 0; i < freqList.size() ; i++) {
			for (int n = 0; n < (int)freqList.get(i).getValue(); n++) {
				str+= freqList.get(i).getKey();
			}
		}
		return str;
	}

	public static void main(String[] args) {
		
	}
}