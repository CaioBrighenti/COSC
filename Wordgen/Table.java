import java.util.*;
import java.util.ArrayList;

public class Table {
	
	private String text;
	private ArrayList<Pair> tableList;
	private int rows;

	public Table(String textt){
		this.tableList = new ArrayList<Pair>();
		this.rows = 0;
		this.text = "Hello world my name is Caio this is a test string just to see what happens hello hello hello";
	}

	public void populateList(){
		for (int i = 0; i < text.length() - 1; i++) {
			String charPair = text.charAt(i) +"" + text.charAt(i+1);
			if (!charPairPresent(charPair)) {
				tableList.add(new Pair(charPair, new FrequencyList(charPair)));
				rows++;
				calcFrequency();
			}
		}
	}

	public void calcFrequency(){
		for (int i = 0; i < text.length() - 2; i++) {
			String charPair = text.charAt(i) +"" + text.charAt(i+1);
			if (charPair.equals(tableList.get(rows - 1).getKey())) {
				FrequencyList flist = (FrequencyList)tableList.get(rows - 1).getValue();
				flist.addChar("" + text.charAt(i+2));
			}
		}
	}

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

	public static void main(String[] args) {
		Table test = new Table("test");
		test.populateList();
		System.out.println(test.toString());
	}
}