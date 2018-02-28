package edu.colgate.cosc290.satsolver;

import java.util.*;

/**
 * A Model represents a (partial) assignment of truth values to Variables.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Model {

    private final Set<Variable> variables;
    private Map<Variable, Boolean> assignment;
    private Set<Variable> unassigned;

    /**
     * Construct a model for the given collection of variables.
     * @param variables to include in the model
     */
    public Model(Collection<Variable> variables) {
        this.variables = new HashSet<>();
        this.variables.addAll(variables);
        assignment = new HashMap<>();
        unassigned = new HashSet<>();
        unassigned.addAll(variables);
    }

    /**
     * Assign a truth value to a given variable.
     * @param v the variable
     * @param value the truth value to assign to the variable
     */
    public void assign(Variable v, Boolean value) {
        if (!variables.contains(v)) {
            throw new RuntimeException("Variable " + v + " is not part of this model!");
        }
        if (isAssigned(v)) {
            throw new RuntimeException("Variable " + v + " already assigned!");
        }
        assignment.put(v, value);
        unassigned.remove(v);
    }

    /**
     * Unassign a truth value to a given variable.  Expects this variable to already have been assigned.
     * @param v the variable
     */
    public void unassign(Variable v) {
        if (isUnassigned(v)) {
            throw new RuntimeException("Variable " + v + " is not assigned!");
        }
        assignment.remove(v);
        unassigned.add(v);
    }

    public boolean isAssigned(Variable v) {
        return !unassigned.contains(v);
    }

    public boolean isUnassigned(Variable v) {
        return !isAssigned(v);
    }

    private boolean getTruthValue(Variable v) {
        if (isUnassigned(v)) {
            throw new RuntimeException("Variable " + v + " is unassigned!");
        }
        return assignment.get(v);
    }

    /**
     * Return true if this clause is necessarily True under this model assignment.  A clause must be true if there
     * exists one positive literal assigned True or there exists one negative literal assigned False.  Otherwise, it
     * is not necessarily True.
     * @param c the clause to check
     * @return true of the clause is *necessarily* true
     */
    public boolean isTrue(Clause c) {
        // grab true and false vars in the clause
        Collection<Variable> true_vars = c.getPositiveVariables();
        Collection<Variable> false_vars = c.getNegativeVariables();
        // iterate through map
        // return true for true assignments in true vars or false assignments in false_vars
        for (Map.Entry<Variable,Boolean> entry : assignment.entrySet()) {
          Variable key = entry.getKey();
          Boolean value = entry.getValue();
          if (value && true_vars.contains(key))
            return true;
          else if (!value && false_vars.contains(key))
            return true;
        }
        // if condition is never met in foreach loop, clause must be false
        return false;
    }

    /**
     * Return true if this clause is necessarily False under this model assignment.  A clause must be False if all
     * positive literals are assigned False and all negative literals are assigned True.  Otherwise, it is not
     * necessarily False.  For example, if the clause contains an unassigned variable, then it is not necessarily False.
     * @param c the clause to check
     * @return true of the clause is *necessarily* false
     */
    public boolean isFalse(Clause c) {
      // grab true and false vars in the clause
      Collection<Variable> true_vars = c.getPositiveVariables();
      Collection<Variable> false_vars = c.getNegativeVariables();
      // ensure all true variables are both in the mapping and mapped to false
      for (Variable true_var : true_vars) {
        if (!assignment.containsKey(true_var))
          return false;
        if (assignment.get(true_var))
          return false;
      }
      // ensure all false variables are both in the mapping and mapped to true
      for (Variable false_var : false_vars) {
        if (!assignment.containsKey(false_var))
          return false;
        if (!assignment.get(false_var))
          return false;
      }
      // if condition is never met in foreach loop, clause must be false
      return true;
    }

    @Override
    public String toString() {
        List<String> pairs = new ArrayList<>(assignment.size());
        for (Map.Entry<Variable, Boolean> e : assignment.entrySet()) {
            Variable v = e.getKey();
            boolean value = e.getValue();
            pairs.add(v + " -> " + (value ? "T" : "F"));
        }
        Collections.sort(pairs);
        return "Model{" + String.join(", ", pairs) + "}";
    }

}
