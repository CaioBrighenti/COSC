// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
package edu.colgate.cosc290.logic;

/**
 * Static methods to help write complex propositions.
 */
public class Build {

    /**
     * Returns proposition that is negation of p
     * @param p
     * @return proposition that is ~p
     */
    public static Proposition neg(Proposition p) {
      if (p.isNotProposition())
        return p.getFirst();
      else
        return new Neg(p);
    }

    /**
     * Returns proposition that is conjunction of p and q
     * @param p
     * @param q
     * @return proposition that is p & q
     */
    public static Proposition conj(Proposition p, Proposition q) {
        return new BinOp(LogicalConnective.AND, p, q);
    }

    /**
     * Returns proposition that is disjunction of p and q
     * @param p
     * @param q
     * @return proposition that is p | q
     */
    public static Proposition disj(Proposition p, Proposition q) {
        return new BinOp(LogicalConnective.OR, p, q);
    }

    /**
     * Returns proposition that is p implies q
     * @param p
     * @param q
     * @return proposition that is p => q
     */
    public static Proposition implies(Proposition p, Proposition q) {
        // look at the other examples above for tips on how to write this
        return new BinOp(LogicalConnective.IF, p, q);
    }
}
