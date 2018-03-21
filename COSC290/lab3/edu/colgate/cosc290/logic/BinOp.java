// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
package edu.colgate.cosc290.logic;

/**
 * A proposition of the form alpha OP beta where alpha and beta are propositions and OP is a logical connective (AND,
 * OR, IMPLIES, etc).
 */
public class BinOp extends Proposition {

    private LogicalConnective op;
    private Proposition phi_1;
    private Proposition phi_2;


    public BinOp(LogicalConnective op, Proposition p, Proposition q) {
        this.op = op;
        this.phi_1 = p;
        this.phi_2 = q;
    }

    @Override
    public LogicalConnective getConnective() {
        return op;
    }

    @Override
    public Proposition getFirst() {
        return phi_1;
    }

    @Override
    public Proposition getSecond() {
        return phi_2;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("implement me!");
    }

    @Override
    public Proposition copy() {
        return new BinOp(op, phi_1.copy(), phi_2.copy());
    }
}
