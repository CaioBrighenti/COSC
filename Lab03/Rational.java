/* COSC 102 - Lab 03
 * Jake French, Caio Brighenti
 * Spring '17
 * Rational.java File (mlyboult - Feb 2017)
 */ 


public class Rational{
	
	// Instance variables
	// n is numerator
	// d is denominator 
	private int n;
	private int d;
	
	// Constructor if no parameters given
	public Rational(){
		n = 1;
		d = 1;
	}
	
	// Constructor for when parameters are given
	public Rational(int tn, int td){
		// Throw exception if denominator set to 0
		if (td == 0){
			throw new IllegalStateException("Denominator cannot be 0.");
		}
		n = tn;
		d = td;
	}
	

	public static void main(String[] args){
		
	
	}
	
	// Returns the numerator
	public int getNumerator(){
		return n;
	}
	
	// Returns the denominator
	public int getDenominator(){
		return d;
	}
	
	// Returns the rational multiplied
	public Rational negate(){
		return new Rational(-n,d);
	}
	
	public Rational reciprocal(){
		return new Rational(d,n);
	}
	
	public boolean equals(Object other){
		if (other instanceof Rational){
			Rational temp = (Rational) other;
			return ((n == temp.n) && (d == temp.d));
		}
		return false;
	}
	
	public Rational add(Rational other){
		return new Rational();
	}
	
	public Rational multiply(Rational other){
		return new Rational();
	}
	
	public Rational divide(Rational other){
		return new Rational();
	}
	
	public String toString(){
		return n + "/" + d;
	}
	
	public int gcd(int x, int y){
		if (x == 0){
			return y;
		} else if (y == 0){
			return x;
		} else {
			return gcd(y, x % y);
		}
	}
	
}


