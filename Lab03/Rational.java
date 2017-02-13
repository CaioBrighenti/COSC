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
	
	// Returns the reciprocal of the rational
	public Rational reciprocal(){
		return new Rational(d,n);
	}
	
	// Returns true if the objects equal one another
	// and false otherwise
	public boolean equals(Object other){
		if (other instanceof Rational){
			Rational temp = (Rational) other;
			return ((n == temp.n) && (d == temp.d));
		}
		return false;
	}
	
	// Adds two rationals and returns the sum
	public Rational add(Rational other){
		// (ny+xd)/dy returns
		int ny = this.n * other.getDenominator();
		int xd = other.getNumerator() * this.d;
		int dy = this.d * other.getDenominator();
		return new Rational((ny + xd), dy);
	}
	
	public Rational multiply(Rational other){
		return new Rational(this.n * other.getNumerator(), this.d * other.getDenominator());
	}
	
	public Rational divide(Rational other){
		return new Rational();
	}
	
	public String toString(){
		return n + "/" + d;
	}
	
	public static int gcd(int x, int y){
		System.out.println("X: " + x + "Y: " + y);
		if (x == 0){
			return y;
		} else if (y == 0){
			return x;
		} else {
			return gcd(y, x % y);
		}
	}
	
}


