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
      Build builder = new Build();

      // base case
      if (phi.isVariable())
        return phi;

      // case if phi is a negation
      if (phi.isNotProposition()){
        phi = builder.neg(simplify(phi.getFirst()));
        return phi;
      }

      // case if phi is a binary op
      if (phi.isBinaryProposition()) {

        // if &, make recursive call
        if (phi.getConnective().toString() == "&") {
          phi = builder.conj(simplify(phi.getFirst()),simplify(phi.getSecond()));
          return phi;
        }

        // get rid of | by using (p | q) = ~(~p & ~q)
        if (phi.getConnective().toString() == "|") {
          Proposition first_neg = builder.neg(phi.getFirst());
          Proposition second_neg = builder.neg(phi.getSecond());
          phi = builder.neg(builder.conj(simplify(first_neg),simplify(second_neg)));
          return phi;
        }

        // get rid of => by using (p => q) = ~(p & ~q)
        if (phi.getConnective().toString() == "=>") {
          Proposition first = phi.getFirst();
          Proposition second_neg = builder.neg(phi.getSecond());
          phi = builder.neg(builder.conj(simplify(first),simplify(second_neg)));
          return phi;
        }
      }

      return phi;
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
      // catch IllegalPropositionException if or or implies is passed
      if (phi.isOrProposition() || phi.isIfProposition())
        throw new IllegalPropositionException();

      Build builder = new Build();
      // base case
      if (phi.isVariable())
        return phi;

      if (phi.isNotProposition()) {
        // negated variable is already in NNF
        if (phi.getFirst().isVariable()) {
            return phi;
        } else if (phi.getFirst().isBinaryProposition()) {
          // push in negations using DeMorgan's law
          Proposition first_neg = toNNF(builder.neg(phi.getFirst().getFirst()));
          Proposition second_neg = toNNF(builder.neg(phi.getFirst().getSecond()));
          return builder.disj(first_neg, second_neg);
        }
      }

      // if & make recursive call
      if (phi.isAndProposition())
        return builder.conj(toNNF(phi.getFirst()), toNNF(phi.getSecond()));

      return null;
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
        Build builder = new Build();
        // base case
        if (phi.isVariable())
          return phi;

        // negation must be literal since in NNF, else throw exception
        if (phi.isNotProposition()){
          if (phi.getFirst().isVariable())
            return phi;
          else
            throw new IllegalPropositionException();
        }

        // can't be NNF if has =>
        if (phi.isIfProposition())
          throw new IllegalPropositionException();

        // make recursive call if binary prop
        if (phi.isBinaryProposition()) {
          Proposition first = fromNNFtoCNF(phi.getFirst());
          Proposition second = fromNNFtoCNF(phi.getSecond());

          // α ∧ β is OK if α and β are in CNF
          if (phi.isAndProposition())
            return builder.conj(first, second);

          // case if or proposition
          if (phi.isOrProposition()) {
            if (first.isAndProposition()) {
              Proposition disj1 = builder.disj(first.getFirst(), second);
              Proposition disj2 = builder.disj(first.getSecond(), second);
              return builder.conj(disj1, disj2);
            }
            if (second.isAndProposition()) {
              Proposition disj1 = builder.disj(first, second.getFirst());
              Proposition disj2 = builder.disj(first, second.getSecond());
              return builder.conj(disj1, disj2);
            }
            return builder.disj(first, second);
          }
        }

        return null;
    }

    /**
     * Constructs a proposition psi such that (1) psi is conjunctive normal form and (2) psi is logically equivalent to
     * phi.
     * @param phi a proposition to transform to CNF
     * @return psi, a proposition in CNF that is logically equivalent to phi
     */
    public static Proposition toCNF(Proposition phi) {
        return fromNNFtoCNF(toNNF(simplify(phi)));
    }
}
