// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
// Edited by: Caio Brighenti
package edu.colgate.cosc290.logic;
import java.util.ArrayList;

/**
 * Checks a proposition in CNF to see if it is a tautology.
 */
public class TautologyChecker {

    /**
     * Checks a proposition in CNF for tautology.
     * @param phi a proposition, expected to be in CNF
     * @return true if phi is a tautolgy, false otherwise
     * @throws IllegalPropositionException if phi is not in CNF
     */
    public static boolean isTautology(Proposition phi) {
      // If => statement present, can't be in CNF
      if (phi.isIfProposition())
        throw new IllegalPropositionException();

      // Single variable can't be a tautology
      if (phi.isVariable())
        return false;

      // & is tautology if each clause is also tautology
      if (phi.isAndProposition())
        return (isTautology(phi.getFirst()) && isTautology(phi.getSecond()));

      // check if clause is tautology
      if (phi.isOrProposition()){
        return isOrTautology(phi);
      }

      return false;
    }

    // checks if an OR proposition is a tautology
    public static boolean isOrTautology(Proposition phi) {
      Build builder = new Build();
      ArrayList<Proposition> literals = collectLiterals(phi);
      for (Proposition lit : literals) {
        // is tautology if contains a p, ~p pair
        if (lit.isNotProposition()) {
          if (literals.contains(lit.getFirst()))
            return true;
        } else {
          if (literals.contains(builder.neg(lit)))
            return true;
        }
      }
      return false;
    }

    // collects all literals from an OR proposition
    public static ArrayList<Proposition> collectLiterals(Proposition phi){
      ArrayList<Proposition> tempList = new ArrayList<Proposition>();

      // base case, just add literal
      if (phi.isVariable() || phi.isNotProposition()){
        tempList.add(phi);
        return tempList;
      }

      // if nested & or => in |, must not be in CNF
      if (phi.getFirst().isAndProposition() || phi.getFirst().isIfProposition() || phi.getSecond().isAndProposition() || phi.getSecond().isIfProposition())
        throw new IllegalPropositionException();

      // make recursive call on OR
      if (phi.isOrProposition()){
        tempList.addAll(collectLiterals(phi.getFirst()));
        tempList.addAll(collectLiterals(phi.getSecond()));
        return tempList;
      }
      return tempList;
    }

}
