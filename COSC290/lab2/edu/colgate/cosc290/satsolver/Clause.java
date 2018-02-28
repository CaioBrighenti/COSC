package edu.colgate.cosc290.satsolver;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Clause object represents a clause in a CNFF formula.  Thus, it represents a disjunction of literals.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Clause {

    private final Set<Literal> literals = new HashSet<>();

    public Clause(Collection<Literal> literals) {
        if (literals.size() == 0) {
            throw new RuntimeException("Must contain at least one literal!");
        }
        this.literals.addAll(literals);
    }

    public Collection<Variable> getVariables() {
        // follow the DRY principle and avoid repeating yourself with these three methods that get variables
        // hint: create a helper method that takes in a parameter that tells it whether to get all variables, or
        // only positive ones, or only negative ones.
        throw new UnsupportedOperationException("implement me!");
    }

    public Collection<Variable> getPositiveVariables() {
        // follow the DRY principle and avoid repeating yourself with these three methods that get variables
        // hint: create a helper method that takes in a parameter that tells it whether to get all variables, or
        // only positive ones, or only negative ones.
        throw new UnsupportedOperationException("implement me!");
    }

    public Collection<Variable> getNegativeVariables() {
        // follow the DRY principle and avoid repeating yourself with these three methods that get variables
        // hint: create a helper method that takes in a parameter that tells it whether to get all variables, or
        // only positive ones, or only negative ones.
        throw new UnsupportedOperationException("implement me!");
    }

    // ---- implementation of toString to make debugging easier

    /**
     * Produce the clause in string form.  Uses the symbol "|" to indicate the OR of literals, as in p | q, for p OR q.
     * @return the clause in string form
     */
    @Override
    public String toString() {
        // if you get a compiler error here, you are not running Java 8!
        return "(" +
                literals.stream()
                        .map(i -> i.toString())
                        .collect(Collectors.joining(" | "))
                + ")";
    }

    // ---- implementations of equals and hashcode so that this object can be put into hash sets/maps/etc.

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Clause)) {
            return false;
        }
        Clause other = (Clause) obj;
        return this.literals.equals(other.literals);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Literal literal : literals) {
            hash += 31*hash + literal.hashCode();
        }
        return hash;
    }
}
