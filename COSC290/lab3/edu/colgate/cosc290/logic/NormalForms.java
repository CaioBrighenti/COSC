// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
package edu.colgate.cosc290.logic;

/**
 * Static methods that rewrite a proposition into various normal forms (or otherwise simplified form).
 */
public class NormalForms {


    /**
     * Constructs a proposition psi such that (1) psi contains only the logical connectives in {&, ~} and (2)
     * psi is logically equivalent to phi.
     * @param phi a proposition to simplify
     * @return psi, a proposition with only & and ~ connectives and logically equivalent to phi
     */
    public static Proposition simplify(Proposition phi) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Constructs a proposition psi such that (1) psi is in negation normal form  and (2) psi is logically
     * equivalent to phi.  Expects a proposition that has only & and ~ connectives.
     *
     * A sentence is in negation normal form if the negation connective is applied only to atomic propositions (i.e.
     * variables) and not to more complex expressions, and furthermore, the only connectives allowed are {&, |, ~}.
     *
     * @param phi a proposition to transform to NNF
     * @return psi, a proposition in NNF and logically equivalent to phi
     * @throws IllegalPropositionException if phi contains a connective that is not in the set {&, ~}.
     */
    public static Proposition toNNF(Proposition phi) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Constructs a proposition psi such that (1) psi is in CNF and (2) psi is logically
     * equivalent to phi.  Expects a proposition phi that is in NNF.
     *
     * @param phi a proposition to transform to NNF
     * @return psi, a proposition in CNF and logically equivalent to phi
     * @throws IllegalPropositionException if phi is not in NNF
     */
    public static Proposition fromNNFtoCNF(Proposition phi) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Constructs a proposition psi such that (1) psi is conjunctive normal form and (2) psi is logically equivalent to
     * phi.
     * @param phi a proposition to transform to CNF
     * @return psi, a proposition in CNF that is logically equivalent to phi
     */
    public static Proposition toCNF(Proposition phi) {
        throw new UnsupportedOperationException("implement me!");
    }
}
