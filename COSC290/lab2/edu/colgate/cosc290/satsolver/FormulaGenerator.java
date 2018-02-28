package edu.colgate.cosc290.satsolver;

import java.util.*;

/**
 * A FormulaGenerator object is capable of generating a random CNFF formula.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class FormulaGenerator {
    private final Random r;
    private int numVariables;
    private int numClauses;
    private int varsPerClause;
    private List<Variable> variables;

    /**
     * Construct a formula generator
     * @param numVariables number of variables across all clauses
     * @param numClauses number of clauses in generated formula
     * @param varsPerClause number of variables per clause in generated formula
     */
    public FormulaGenerator(int numVariables, int numClauses, int varsPerClause) {
        this(numVariables, numClauses, varsPerClause, 0);
    }

    /**
     /**
     * Construct a formula generator
     * @param numVariables number of variables across all clauses
     * @param numClauses number of clauses in generated formula
     * @param varsPerClause number of variables per clause in generated formula
     * @param seed a random seed
     */
    public FormulaGenerator(int numVariables, int numClauses, int varsPerClause, long seed) {
        this.numVariables = numVariables;
        this.numClauses = numClauses;
        this.varsPerClause = varsPerClause;
        generateVariables();
        r = new Random(seed);
    }

    /**
     * Returns a randomly generated CNFF formula.
     * @return a CNFF object
     */
    public CNFF generateRandomInstance() {
        Set<Clause> clauses = new HashSet<>();
        int duplicateClauses = 0;
        for (int i = 0; i < numClauses; ) {
            Clause c = generateClause();
            if (clauses.add(c)) {   // check that c is not duplicate of previously generated clause
                i++;
                duplicateClauses = 0;
            } else {
                duplicateClauses++;
                if (duplicateClauses > 100) {
                    throw new RuntimeException("Giving up random clause generation after " +
                            duplicateClauses + " duplicate clauses were generated.");
                }
            }
        }
        return new CNFF(clauses);
    }

    private void generateVariables() {
        variables = new ArrayList<>();
        for (int i = 0; i < numVariables; i++) {
            variables.add(new Variable("p" + (i+1)));
        }
    }

    private Clause generateClause() {
        List<Literal> literals = new LinkedList<>();
        Collections.shuffle(variables, r);
        for (int i = 0; i < varsPerClause; i++) {
            Variable v = variables.get(i);
            boolean isPositive = false;
            if (r.nextBoolean()) {
                isPositive = true;
            }
            literals.add(new Literal(v, isPositive));
        }
        return new Clause(literals);
    }

    public static void main(String[] args) {
        FormulaGenerator generator = new FormulaGenerator(4, 3, 3);
        CNFF CNFF = generator.generateRandomInstance();
        System.out.println("Randomly generated CNFF formula: " + CNFF);
    }
}
