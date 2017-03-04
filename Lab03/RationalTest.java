/* COSC 102 - Lab 03
 * Jake French, Caio Brighenti
 * Spring '17
 * RationalTest.java File (mlyboult - Feb 2017)
 */ 

public class RationalTest{

	public static void main(String[] args){
		Rational test = new Rational(25,100);
		Rational test_1 = new Rational(25,100);
		
		System.out.println(test.add(test_1).toString());
		
	}	
	
}