// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
// Edited by: Caio Brighenti
package edu.colgate.cosc290.closure;


/**
 * A simple class that contains a main method for comparing algorithm runtimes.
 */
public class CompareAlgorithms {

    /**
     * Compare the runtimes of the two methods for transitive closure.  {@see System#currentTimeMillis()}
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Comparing runtime of three algorithms for transitive closure on a 'chain' relation on the set {1..n}.");
        System.out.println("(Runtimes are reported in milliseconds.)");
        int n = 0;
        long slow_time = 0;
        long fast_time = 0;
        long warshall_slow = 0;
        for (int k = 1; k <= 15; k++) {
          n = 20 * k;
          boolean[][] chain = Relations.makeChain(n);
          // store runtime for slow transitive closure
          slow_time = System.currentTimeMillis();
          Relations.transitiveClosure(chain);
          slow_time = System.currentTimeMillis() - slow_time;
          // store runtime for Warshall transitive closure
          fast_time = System.currentTimeMillis();
          Warshall.transitiveClosure(chain);
          fast_time = System.currentTimeMillis() - fast_time;
          // store runtime for slower Warshall transitive closure
          warshall_slow = System.currentTimeMillis();
          Warshall.transitiveClosureSlow(chain);
          warshall_slow = System.currentTimeMillis() - warshall_slow;
          System.out.println("n = " + n + " regular = " + slow_time + " Warshall slow = " + warshall_slow + " Warshall = " + fast_time );
        }
    }
}
