// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
package edu.colgate.cosc290.logic;

/**
 * An abstract class representing a logical proposition.
 */
public abstract class Proposition {

    /**
     * Returns a string representation of a proposition.  The string representation should include parentheses to
     * indicate the operator precedence.  Example:
     * (p & q) | r
     * As a challenge problem, implement this method such that parens are dropped if the operators are of the same
     * type. Example:
     * (p & q & r) | (q | ~r)
     * @return a string representation of proposition
     */
    @Override
    public abstract String toString();

    /**
     * @return a deep copy of this proposition
     */
    public abstract Proposition copy();

    // --- HELPER METHODS FOR CHECKING THE STRUCTURE OF A COMPLEX PROPOSITION ---

    /**
     * @return true is the proposition is of the form p for some variable p.
     */
    public boolean isVariable() {
        return false;
    }

    /**
     * @return true is the proposition is of the form ~phi for some proposition phi.
     */
    public boolean isNotProposition() {
        return hasConnective(LogicalConnective.NOT);
    }

    /**
     * @return true is the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is
     * some logical connective.
     */
    public boolean isBinaryProposition() {
        return isOrProposition() || isAndProposition() || isIfProposition();
    }

    /**
     * @return true is the proposition is of the form phi1 | phi2.
     */
    public boolean isOrProposition() {
        return hasConnective(LogicalConnective.OR);
    }

    /**
     * @return true is the proposition is of the form phi1 & phi2.
     */
    public boolean isAndProposition() {
        return hasConnective(LogicalConnective.AND);
    }

    /**
     * @return true is the proposition is of the form phi1 => phi2.
     */
    public boolean isIfProposition() {
        return hasConnective(LogicalConnective.IF);
    }

    /**
     * If the proposition is binary -- i.e., it is of the form  phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective, then OPERATOR is returned.
     * Example: if proposition is p & q, it returns &.
     *
     * @return the logical connective associated with this sentence if it has one, otherwise null
     */
    public LogicalConnective getConnective() {
        return null;
    }

    /**
     * If the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective -- then phi1 is returned.
     *
     * Example: if proposition is (p | r) & q, it returns (p | r).
     *
     * If the proposition is unary -- i.e., it is of the form OPERATOR phi1 where OPERATOR is some logical
     * connective -- then phi1 is returned.
     *
     * Example: if proposition is ~p, it returns p.
     *
     * @return the left argument if it is binary, the argument if it is unary, otherwise null
     */
    public Proposition getFirst() {
        return null;
    }

    /**
     * If the proposition is binary -- i.e., it is of the form phi1 OPERATOR phi2 where OPERATOR is some logical
     * connective -- then phi2 is returned.
     *
     * Example: if proposition is (p | r) & q, it returns q.
     *
     * @return the right argument if this sentence is a binary proposition, otherwise null
     */
    public Proposition getSecond() {
        return null;
    }

    /**
     * Checks whether the sentence has connective c at its root.
     *
     * Example: if proposition is (p | r) & q, then hasConnective(&) is true, but hasConnective(|) is false.
     *
     * @param c the connective type
     * @return true if the root of the sentence is equal to c
     */
    public boolean hasConnective(LogicalConnective c) {
        return getConnective() != null && getConnective().equals(c);
    }

}
