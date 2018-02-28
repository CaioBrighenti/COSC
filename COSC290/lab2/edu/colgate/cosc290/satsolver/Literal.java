package edu.colgate.cosc290.satsolver;

/**
 * A Literal object is a literal in a boolean formula.  For example, in the formula (p | ~q) & (~p | r), there are
 * four literals: p, ~p, ~q, and r.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Literal {

    private final Variable variable;
    private final boolean isPositive;

    public Literal(Variable variable, boolean isPositive) {
        this.variable = variable;
        this.isPositive = isPositive;
    }

    public Variable getVariable() {
        return variable;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public boolean isNegative() {
        return !isPositive();
    }

    /**
     * Produce the literal in string form.  Use the symbol "~" to indicate a negated literal, as in ~p, for variable p.
     * @return the literal in string form
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("implement me!");
    }

    // ---- implementations of equals and hashcode so that this object can be put into hash sets/maps/etc.

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Literal)) {
            return false;
        }
        Literal other = (Literal) obj;
        return this.isPositive() == other.isPositive() && this.variable.equals(other.variable);
    }

    @Override
    public int hashCode() {
        return 31*variable.hashCode() + (isPositive()? 1: 0);
    }

}
