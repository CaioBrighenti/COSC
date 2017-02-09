/* COSC 102 - Lab 03
 * Jake French, Caio Brighenti
 * Spring '17
 * Rational.java File (mlyboult - Feb 2017)
 */ 


public class Rational{
	
	private int n;
	private int d;
	
	public Rational(int tn, int td){
		if (td == 0){
			throw new IllegalStateException("Cannont divide by zero!!");
		}
		n = tn;
		d = td;
	}
	
	public Rational(){
		n = 1;
		d = 1;
	}
	
	public static void main(String[] args){
		
	
	}
	
	public int getNumerator(){
		return n;
	}
	
	public int getDenominator(){
		return d;
	}
	
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


