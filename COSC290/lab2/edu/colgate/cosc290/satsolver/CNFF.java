package edu.colgate.cosc290.satsolver;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A CNFF object represents a formula in conjunctive normal form (CNF).  A CNF formula is a conjunction of one or more
 * clauses, where each clause a disjunction of one or more literals.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class CNFF {

    private final Set<Clause> clauses = new HashSet<>();

    public CNFF(Collection<Clause> clauses) {
        if (clauses.size() == 0) {
            throw new RuntimeException("Must contain at least one clause!");
        }
        this.clauses.addAll(clauses);
    }

    public Set<Clause> getClauses() {
        return clauses;
    }

    /**
     * Returns a collection (such as List or Set) that contains all of the variables that appear in any
     * clause in this formula.
     * @return a collection of Variable objects
     */
    public Collection<Variable> getVariables() {
      // initialize return set
      Set<Variable> return_vars = new HashSet<>();
      for (Clause clause : clauses) {
        return_vars.addAll(clause.getVariables());
      }
      return return_vars;
    }

    public static void main(String[] args) {
        CNFF formula = CNFF.fromString("(p | ~q) & (p | q)");
        System.out.println("The CNFF is " + formula);
        System.out.println("It contains these variables " + formula.getVariables());
    }

    /**
     * Factory method for CNFF objects.  See its usage in the main method.
     * Builds a CNFF by parsing the given string.  Expects formula of the form "(p | ~q) & (p) & (~q | r)"  In other
     * words, clauses combined with & and surrounded by parenthesis.  Literals within clause are combined with | and
     * negated literals preceded by ~.
     * @param cnfStr
     * @return
     */
    public static CNFF fromString(String cnfStr) {
        String[] clauseStrs = cnfStr.split("\\s*&\\s*");
        List<Clause> clauses = new LinkedList<>();
        for (String clauseStr : clauseStrs) {
            if (!(clauseStr.startsWith("(") & clauseStr.endsWith(")"))) {
                throw new RuntimeException("invalid clause: expected open and close parens: '" + clauseStr + "'");
            }
            clauseStr = clauseStr.substring(1, clauseStr.length() - 1);
            if (clauseStr.contains("(") || clauseStr.contains(")")) {
                throw new RuntimeException("Invalid clause: '" + clauseStr + "'.  Clauses should not contain " +
                        "parentheses");
            }
            String[] literalStrs = clauseStr.split("\\s*\\|\\s*");
            List<Literal> literals = new LinkedList<>();
            for (String literalStr : literalStrs) {
                boolean isNegated;
                isNegated = literalStr.startsWith("~");
                String variableStr = literalStr;
                if (isNegated) {
                    variableStr = literalStr.substring(1);
                }
                Variable v = new Variable(variableStr);
                Literal literal = new Literal(v, !isNegated);
                literals.add(literal);
            }
            Clause clause = new Clause(literals);
            clauses.add(clause);
        }
        return new CNFF(clauses);
    }

    @Override
    public String toString() {
        return clauses.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(" & "));
    }

}
