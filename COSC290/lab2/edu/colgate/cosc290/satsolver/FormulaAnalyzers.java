package edu.colgate.cosc290.satsolver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Helper methods for identify variables that are pure and variables that appear in unit clauses.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class FormulaAnalyzers {

    /**
     * Finds all variables that appear in unit clauses under this model.  The model can affect whether or not a
     * clause is considered a unit clause.  For example, the clause (p | q) when q is False becomes the unit clause
     * (p) because the only way for that clause to be true is if p is true.
     * @param clauses the clauses to check
     * @param model the current variable assignment
     * @return the set of all variables that appear in unit clauses; each variable is paired with the truth value it
     * must take.
     */
    public static Set<Pair<Variable, Boolean>> findUnitClauseVariables(Set<Clause> clauses, Model model) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Finds all variables that are pure in the given clauses under this model.  The model can affect whether or not
     * a variable is considered pure.  For example, in the formula (p | q) & (p | r) & (~p | s) when s is True, then
     * we can ignore the last clause (s being True, makes the last clause True) and so p becomes pure and it must be
     * assigned to True.
     * @param clauses the clauses to check
     * @param model the current variable assignment
     * @return the set of all variables that are pure; each variable is paired with the truth value it must take.
     */
    public static Set<Pair<Variable,Boolean>> findPureVariables(Set<Clause> clauses, Model model) {
        throw new UnsupportedOperationException("implement me!");
    }

    // --- below are helper methods that call the above methods.  you may wish to use these helper methods elsewhere in
    // your code (e.g., in SatSolver) ----

    /**
     * Looks for a unit clause variable and returns one if one exists.  If more than one exists, this method chooses
     * one arbitrarily.
     * @param clauses clauses to check
     * @param model the model containing a partial assignment of variables
     * @return a unit clause variable if one exists under this model
     */
    public static Pair<Variable, Boolean> findUnitClauseVariable(Set<Clause> clauses, Model model) {
        return getPair(clauses, model, false);
    }

    /**
     * Looks for a pure variable and returns one if one exists.  If more than one exists, this method chooses
     * one arbitrarily.
     * @param clauses clauses to check
     * @param model the model containing a partial assignment of variables
     * @return a pure variable if one exists under this model
     */
    public static Pair<Variable, Boolean> findPureVariable(Set<Clause> clauses, Model model) {
        return getPair(clauses, model, true);
    }

    private static Pair<Variable, Boolean> getPair(Set<Clause> clauses, Model model, boolean pure) {
        Set<Pair<Variable, Boolean>> varAndValues;
        if (pure) {
            varAndValues = findPureVariables(clauses, model);
        } else {
            varAndValues = findUnitClauseVariables(clauses, model);
        }
        if (varAndValues.size() > 0) {
            return varAndValues.iterator().next();  // any one is fine, just grab the first one
        }
        return null;
    }

}
