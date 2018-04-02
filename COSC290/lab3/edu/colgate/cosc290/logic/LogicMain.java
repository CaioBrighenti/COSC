// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
// Edited by: Caio Brighenti
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
        Proposition phi = builder.conj(p, q);
        phi = builder.conj(p, phi);
        phi = builder.disj(phi, builder.neg(q));
        System.out.println("phi := " + phi);
        Proposition phiSimplified = NormalForms.simplify(phi);
        System.out.println("phiSimplified := NormalForms.simplify(phi)");
        System.out.println("phiSimplified = " + phiSimplified);
        Proposition phiNNF = NormalForms.toNNF(phiSimplified);
        System.out.println("phiNNF := NormalForms.toNNF(phiSimplified)");
        System.out.println("phiNNF = " + phiNNF);
        Proposition phiDistributed = NormalForms.fromNNFtoCNF(phiNNF);
        System.out.println("phiDistributed := NormalForms.fromNNFtoCNF(phiNNF)");
        System.out.println("phiDistributed = " + phiDistributed);
        Proposition phiCNF = NormalForms.toCNF(phi);
        System.out.println("phiCNF := NormalForms.toCNF(phi)");
        System.out.println("phiCNF = " + phiCNF);
        boolean isTautology = TautologyChecker.isTautology(phiCNF);
        System.out.println("isTautology := TautologyChecker.isTautology(phiCNF)");
        System.out.println("isTautology = " + isTautology);
        System.out.println();

        System.out.println("---- TEST 2 -----");
        phi = builder.disj(builder.neg(q), builder.neg(p));
        phi = builder.disj(p, phi);
        phi = builder.implies(phi, q);
        System.out.println("phi := " + phi);
        phiSimplified = NormalForms.simplify(phi);
        System.out.println("phiSimplified := NormalForms.simplify(phi)");
        System.out.println("phiSimplified = " + phiSimplified);
        phiNNF = NormalForms.toNNF(phiSimplified);
        System.out.println("phiNNF := NormalForms.toNNF(phiSimplified)");
        System.out.println("phiNNF = " + phiNNF);
        phiDistributed = NormalForms.fromNNFtoCNF(phiNNF);
        System.out.println("phiDistributed := NormalForms.fromNNFtoCNF(phiNNF)");
        System.out.println("phiDistributed = " + phiDistributed);
        phiCNF = NormalForms.toCNF(phi);
        System.out.println("phiCNF := NormalForms.toCNF(phi)");
        System.out.println("phiCNF = " + phiCNF);
        isTautology = TautologyChecker.isTautology(phiCNF);
        System.out.println("isTautology := TautologyChecker.isTautology(phiCNF)");
        System.out.println("isTautology = " + isTautology);
        System.out.println();

        System.out.println("---- TEST 3 -----");
        phi = builder.conj(p, q);
        phi = builder.neg(phi);
        phi = builder.disj(p, phi);
        Proposition phi2 = builder.neg(builder.conj(r, builder.neg(r)));
        phi = builder.conj(phi, phi2);
        System.out.println("phi := " + phi);
        phiSimplified = NormalForms.simplify(phi);
        System.out.println("phiSimplified := NormalForms.simplify(phi)");
        System.out.println("phiSimplified = " + phiSimplified);
        phiNNF = NormalForms.toNNF(phiSimplified);
        System.out.println("phiNNF := NormalForms.toNNF(phiSimplified)");
        System.out.println("phiNNF = " + phiNNF);
        phiDistributed = NormalForms.fromNNFtoCNF(phiNNF);
        System.out.println("phiDistributed := NormalForms.fromNNFtoCNF(phiNNF)");
        System.out.println("phiDistributed = " + phiDistributed);
        phiCNF = NormalForms.toCNF(phi);
        System.out.println("phiCNF := NormalForms.toCNF(phi)");
        System.out.println("phiCNF = " + phiCNF);
        isTautology = TautologyChecker.isTautology(phiCNF);
        System.out.println("isTautology := TautologyChecker.isTautology(phiCNF)");
        System.out.println("isTautology = " + isTautology);
    }
}
