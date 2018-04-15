// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
// Edited by: Caio Brighenti
package edu.colgate.cosc290.closure;

import java.util.NoSuchElementException;

/**
 * A class for computing transitive closure using Warshall's algorithm
 */
public class Warshall {

    /**
     * Returns transitive closure of R.  This method uses Warshall's algorithm.
     * @param R relation, represented as a matrix (double array)
     * @return the transitive closure of R
     */
    public static boolean[][] transitiveClosure(boolean[][] R) {
      int n = R.length;
      if (R[0].length != n) {
          throw new UnsupportedOperationException("expecting an n by n boolean double array!");
      }
      boolean[][] R_Warshall = Relations.copyRelation(R);
      // loop for each Warshall relation k
      for (int k = 0; k < n; k++) {
        // loop through each item in column k
        for (int r = 0; r < n; r++) {
          // if edge at (r,k), check for corresponding edges in row k
          if (R_Warshall[r][k]) {
            for (int c = 0; c < n; c++) {
              // corresponding edge present at (k,c), so add edge at (r,c)
              if (R_Warshall[k][c])
                R_Warshall[r][c] = true;
            }
          }
        }
      }

      return R_Warshall;
    }

    // Less efficient implementation of Warshall relations
    // checks [k][c] and [r][k] for each r,c pair separately
    public static boolean[][] transitiveClosureSlow(boolean[][] R) {
      int n = R.length;
      if (R[0].length != n) {
          throw new UnsupportedOperationException("expecting an n by n boolean double array!");
      }
      boolean[][] R_Warshall = Relations.copyRelation(R);
      // loop for each Warshall relation k
      for (int k = 0; k < n; k++) {
        // loop through each item r,c pair in column and row k
        for (int r = 0; r < n; r++) {
          for (int c = 0; c < n; c++) {
            // edges present at (r,k) and (k,c), so add edge at (r,c)
            if (R_Warshall[k][c] && R_Warshall[r][k])
              R_Warshall[r][c] = true;
          }
        }
      }

      return R_Warshall;
    }
}
