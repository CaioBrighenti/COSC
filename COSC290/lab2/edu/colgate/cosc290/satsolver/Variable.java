package edu.colgate.cosc290.satsolver;

/**
 * A Variable object is an atomic proposition, represented by a symbol such as "p" or "q."
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Variable {
    private String symbol;

    public Variable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    // ---- implementations of equals and hashcode so that this object can be put into hash sets/maps/etc.

    @Override
    public boolean equals(Object other) {
        return (other instanceof Variable) && symbol.equals(((Variable)other).symbol);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }
}
