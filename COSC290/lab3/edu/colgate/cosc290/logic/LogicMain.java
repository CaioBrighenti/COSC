// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
package edu.colgate.cosc290.logic;

/**
 * A main class where students can demonstrate the correctness of their implementation.
 */
public class LogicMain {

    /**
     * A short demonstration that illustrates (with three example propositions) how a proposition
     * can be converted into CNF.
     * @param args
     */
    public static void main(String[] args) {
        Build builder = new Build();
        Variable p = new Variable("p");
        Variable q = new Variable("q");
        Variable r = new Variable("r");
        Proposition phi_1 = builder.disj(p, q);
        Proposition neg = builder.neg(r);
        Proposition phi_2 = builder.conj(phi_1, neg);
        System.out.println(phi_2);
        System.out.println(NormalForms.simplify(phi_2));
    }
}
