// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
// Edited by: Caio Brighenti
package edu.colgate.cosc290.logic;

/**
 * An atomic proposition.
 */
public class Variable extends Proposition {
    private String symbol;

    public Variable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }

    @Override
    public boolean isVariable() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Variable) && symbol.equals(((Variable)other).symbol);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }

    @Override
    public Proposition copy() {
        return new Variable(symbol);
    }
}
