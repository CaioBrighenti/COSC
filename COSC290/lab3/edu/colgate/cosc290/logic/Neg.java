// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
// Edited by: Caio Brighenti
package edu.colgate.cosc290.logic;

/**
 * A proposition of the form NOT(alpha) where alpha is proposition.
 */
public class Neg extends Proposition {

    private Proposition phi;

    public Neg(Proposition p) {
        this.phi = p;
    }

    @Override
    public LogicalConnective getConnective() {
        return LogicalConnective.NOT;
    }

    @Override
    public Proposition getFirst() {
        return phi;
    }

    @Override
    public String toString() {
        return "~" + phi.toString();
    }

    @Override
    public Proposition copy() {
        return new Neg(phi.copy());
    }
}
