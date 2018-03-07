package edu.colgate.cosc290.satsolver;

/**
 * A class for running experiments on randomly generated CNFF formulas
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class ExperimentMain {

    /**
     * See comment below for description.
     * @param args
     */
    public static void main(String[] args) {
        int n = 20;  // number of variables (set this to 40 if you are doing the challenge problem)
        int k = 3;   // variables per clause
        int numTrials =10;     // how many random CNF formulas to generate (at each ratio)
        double[] ratios = new double[] { 1, 2, 3, 4, 5, 6, 7, 8};  // ratio r = # clauses / # variables
        boolean findPureVariables = true;
        boolean findUnitClauses = true;

        // for each ratio r in ratios:
        //     repeat numTrials times:
        //         - generate a random CNF formula with given n variables, having k variables per clause, and m
        //           clauses where m = r*n
        //         - check whether the formula is satisfiable
        //         - find out how many recursive calls were made (search cost)
        //     print out: ratio r, what fraction of formulas were satisfiable, average search cost
        //     Example: "ratio r=4.0, fraction sat=0.95, avg. cost=1743.0"

        // run experiment for each ratio
        for (double r : ratios) {
          // initialize variables
          int sat = 0;
          int unsat = 0;
          int total_cost = 0;
          int m = (int) r * n;
          FormulaGenerator gen = new FormulaGenerator(n, m, k);
          // trials for each ratio
          for (int i=0; i < numTrials; i++ ) {
            CNFF formula = gen.generateRandomInstance();
            // check satisfiability and update vars accordingly
            SatSolver solver = new SatSolver(findPureVariables, findUnitClauses);
            if (solver.isSatisfiable(formula))
              sat++;
            total_cost+=solver.getSearchCost();
          }
          // calculate and print out results
          double frac_sat = (double) sat / numTrials;
          double avg_cost = total_cost / numTrials;
          System.out.println("ratio r=" + r +", fraction sat=" + frac_sat +", avg. cost=" + avg_cost);
        }
    }
}
