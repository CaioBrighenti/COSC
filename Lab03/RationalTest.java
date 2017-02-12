/* COSC 102 - Lab 03
 * Jake French, Caio Brighenti
 * Spring '17
 * RatationalTest.java File (mlyboult - Feb 2017)
 */ 

public class RationalTest{

	public static void main(String[] args){
		Rational test = new Rational(1,2);
		System.out.println(test.toString());
		Rational test_1 = new Rational(10,9);
		
		System.out.println(test_1.getNumerator());
		System.out.println(test_1.getDenominator());
		System.out.println(test_1.negate().toString());
		System.out.println(test_1.equals(new Rational(1,4)));
		System.out.println(test_1.equals(new Rational(1,5)));
		System.out.println(test_1.gcd(test_1.getNumerator(), test_1.getDenominator()));

	
	}	
	
}