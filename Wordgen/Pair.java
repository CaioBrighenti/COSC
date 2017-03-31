// A class for binding key/value pairs.
// Change the file name!
import java.util.Map;


/**
* A class implementing a key-value pair.  This class pairs an 
* immutable key with a mutable value.  Can be used in many collection structures.
*
* @author 
**/

public class Pair<K,V> implements Map.Entry<K,V> {
	/**
	* The immutable key.  An arbitrary object.
	*/
	                   // the key of the key-value pair
	/**
	* The mutable value.  An arbitrary object.
	*/
	                   // the value of the key-value pair
	
	/*
	for example:
	Pair<String,Integer> personAttribute =
	new Pair<String,Integer>("Age",34);
	*/
	
	/**
	* Constructs a pair from a key and value.
	*
	* @pre key is non-null
	* @post constructs a key-value pair
	* @param key A non-null object.
	* @param value A (possibly null) object.
	*/

	private K key;
	private V value;


	public Pair(K keyt, V valuet) {
		this.key = keyt;
		this.value = valuet;
	}
	
	/**
	* Constructs a pair from a key; value is null.
	*
	* @pre key is non-null
	* @post constructs a key-value pair; value is null
	* @param key A non-null key value.
	*/
	//public Pair(...)
	
	/**
	* Standard comparison function.  Comparison based on keys only.
	*
	* @pre other is non-null Pair
	* @post returns true iff the keys are equal
	* @param other Another pair.
	* @return true iff the keys are equal.
	*/
	public boolean equals(Object other) {
		if (other instanceof Pair){
			Pair temp = (Pair) other;
			return (this.key == temp.getKey());
		}
		return false;
	}
	
	/**
	* Standard hashcode function.
	*
	* @post return hash code pair with this pair
	* @return A hash code for pair.
	* @see Hashtable
	*/
	public int hashCode() {
		return getKey().hashCode();
	}
	
	/**
	* Fetch value from pair.  May return null.
	*
	* @post returns value from pair
	* @return The value field of the pair.
	*/
	public V getValue(){
		return this.value;
	}
	
	/**
	* Fetch key from pair.  Should not return null.
	*
	* @post returns key from pair
	* @return Key of the key-value pair.
	*/
	public K getKey(){
		return this.key;
	}
	// two accessor methods
	
	/**
	* Sets the value of the key-value pair.
	*
	* @post sets pair's value to value
	* @param value The new value.
	*/
	public V setValue(V temp){
		V old = this.value;
		this.value = temp;
		return old;
	}
	
	/**
	* Standard string representation of an pair.
	*
	* @post returns string representation
	* @return String representing key-value pair.
	*/
	public String toString() {
		return (this.key + " " + this.value);
	}
	
	public static void main(String[] args) {
		
		//buggy tester... the generic types are to complete
		
		Pair[] classesTaken = new Pair[5];
      classesTaken[0] = new Pair("Vijay", new Integer(5));
      classesTaken[1] = new Pair("Matt", new Integer(1));
      classesTaken[2] = new Pair("Elodie", new Integer(3));
      classesTaken[3] = new Pair("Joel", new Integer(2));
      classesTaken[4] = new Pair("Madeline", new Integer(1));
      
      //print out each item in the array
      for (int i = 0; i< classesTaken.length; i++){
      	System.out.println("This Student has taken " + classesTaken[i].getValue() 
      		+ " classes from " + classesTaken[i].getKey() + ".");
      }
      
   }
   
}
