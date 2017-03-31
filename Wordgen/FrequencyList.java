import java.util.*;
import java.util.ArrayList;

public class FrequencyList {
	
	private String sequence;
	private ArrayList<Pair> freqList;	

	public FrequencyList(String seq){
		this.freqList = new ArrayList<Pair>();
		this.sequence = seq;
	}

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

	public static void main(String[] args) {
		
	}
}