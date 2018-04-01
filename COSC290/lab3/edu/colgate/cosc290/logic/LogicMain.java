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

        System.out.println("---- TEST 1 -----");
        Variable p = new Variable("p");
        Variable q = new Variable("q");
        Variable r = new Variable("r");
        Proposition phi = builder.implies(p, q);
        phi = builder.conj(p, phi);
        phi = builder.implies(phi, q);
        System.out.println("phi := " + phi);
        phi = NormalForms.simplify(phi);
        System.out.println("phiSimplified := " + phi);
        phi = NormalForms.toNNF(phi);
        System.out.println("phiNNF := " + phi);
        phi = NormalForms.fromNNFtoCNF(phi);
        System.out.println("distributed := " + phi);

        System.out.println("---- TEST 2 -----");
        phi = builder.implies(builder.neg(q), builder.neg(p));
        phi = builder.conj(p, phi);
        phi = builder.implies(phi, q);
        System.out.println("phi := " + phi);
        phi = NormalForms.simplify(phi);
        System.out.println("phiSimplified := " + NormalForms.simplify(phi));
        phi = NormalForms.toNNF(phi);
        System.out.println("phiNNF := " + NormalForms.toNNF(phi));
        phi = NormalForms.fromNNFtoCNF(phi);
        System.out.println("distributed := " + phi);

        System.out.println("---- TEST 3 -----");
        phi = builder.conj(q, r);
        phi = builder.implies(p, phi);
        System.out.println("phi := " + phi);
        phi = NormalForms.simplify(phi);
        System.out.println("phiSimplified := " + NormalForms.simplify(phi));
        phi = NormalForms.toNNF(phi);
        System.out.println("phiNNF := " + NormalForms.toNNF(phi));
        phi = NormalForms.fromNNFtoCNF(phi);
        System.out.println("distributed := " + phi);
    }
}
