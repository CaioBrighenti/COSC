// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
// Edited by: Caio Brighenti
package edu.colgate.cosc290.closure;

/**
 * A class for various static methods on relations (represented as boolean double arrays)
 */
public class Relations {

    public static void main(String[] args) {
      System.out.println("---- TEST 1 -----");
      boolean[][] testRelation = {{false,false,false,false},{false,false,false,true},{false,true,false,false},{false,false,false,false}};
      boolean[][] testRelation2 = {{false,false,false,false},{false,false,false,true},{false,true,false,false},{false,false,false,false}};
      System.out.println("S = " + relationToString(testRelation));
      System.out.println("R = " + relationToString(testRelation2));
      System.out.println("S U R = " + relationToString(union(testRelation,testRelation2)));
      System.out.println("S o R = " + relationToString(compose(testRelation,testRelation2)));
      System.out.println("transitiveClosure = " + relationToString(transitiveClosure(testRelation2)));
      System.out.println("Warshall Closure = " + relationToString(Warshall.transitiveClosure(testRelation2)));
      System.out.println("---- TEST 2 -----");
      boolean[][] testRelation3 = {{false,false,false,true},{false,false,false,false},{false,false,false,false},{false,false,false,false}};
      boolean[][] testRelation4 = {{false,false,false,false},{false,false,false,false},{false,false,false,false},{true,false,false,false}};
      System.out.println("S = " + relationToString(testRelation3));
      System.out.println("R = " + relationToString(testRelation4));
      System.out.println("S U R = " + relationToString(union(testRelation3,testRelation4)));
      System.out.println("S o R = " + relationToString(compose(testRelation3,testRelation4)));
      System.out.println("transitiveClosure = " + relationToString(transitiveClosure(testRelation4)));
      System.out.println("Warshall Closure = " + relationToString(Warshall.transitiveClosure(testRelation4)));
      System.out.println("---- TEST 3 -----");
      boolean[][] testRelation5 = {{false,true,true,true},{false,false,false,false},{true,true,false,true},{true,true,true,false}};
      boolean[][] testRelation6 = {{false,true,false,false},{false,false,false,false},{false,true,false,false},{false,true,false,false}};
      System.out.println("S = " + relationToString(testRelation5));
      System.out.println("R = " + relationToString(testRelation6));
      System.out.println("S U R = " + relationToString(union(testRelation5,testRelation6)));
      System.out.println("S o R = " + relationToString(compose(testRelation5,testRelation6)));
      System.out.println("transitiveClosure = " + relationToString(transitiveClosure(testRelation6)));
      System.out.println("Warshall Closure = " + relationToString(Warshall.transitiveClosure(testRelation6)));
      System.out.println("---- TEST 4 -----");
      boolean[][] testRelation7 = {{false,true,false,false},{false,false,true,false},{false,false,false,true},{false,false,false,false}};
      boolean[][] testRelation8 = {{false,true,false,false},{false,false,false,true},{false,false,false,false},{true,false,true,false}};
      System.out.println("S = " + relationToString(testRelation7));
      System.out.println("R = " + relationToString(testRelation8));
      System.out.println("S U R = " + relationToString(union(testRelation7,testRelation8)));
      System.out.println("S o R = " + relationToString(compose(testRelation7,testRelation8)));
      System.out.println("transitiveClosure = " + relationToString(transitiveClosure(testRelation8)));
      System.out.println("Warshall Closure = " + relationToString(Warshall.transitiveClosure(testRelation8)));
    }

    /**
     * Returns composition of R and S, denoted S ° R.  Returns relation T such that if (i, j) in R and (j, k) in S then
     * (i, k) in T.
     * @param S relation, represented as a matrix (double array)
     * @param R relation, represented as a matrix (double array)
     * @return the composition of R and S
     */
    public static boolean[][] compose(boolean[][] S, boolean[][] R) {
        // compare matrix sizes and make sure they agree: if R is n1 x n2 and S is n3 x n4, then n2 = n3.
        int n1 = R.length;
        int n3 = S.length;
        if (n1 == 0 || n3 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        int n2 = R[0].length;
        int n4 = S[0].length;
        if (n2 == 0 || n4 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        if (n2 != n3) {
            throw new UnsupportedOperationException("Number of columns of R must match number of rows of S");
        }
        boolean[][] tempMatrix = new boolean[n1][n4];
        // iterate through each row, column pair
        for (int i = 0; i < n1; i++) {
          for (int j = 0; j < n2 ; j++ ) {
              // (i,j) pair present in R
              if (R[i][j]){
                // search for (j,k) pairs
                for (int k = 0; k < n2 ; k++ ) {
                  if (S[j][k]){
                    tempMatrix[i][k] = true;
                  }
                }
              }
          }
        }
        return tempMatrix;
    }

    /**
     * Returns union of R and S.  Return relation T such that if (i, j) in R or (i, j) in S, then (i, j) in T.
     * @param R relation, represented as a matrix (double array)
     * @param S relation, represented as a matrix (double array)
     * @return the union of R and S
     */
    public static boolean[][] union(boolean[][] R, boolean[][] S) {
        // compare matrix sizes and make sure they agree: if R is n1 x n2 then S should be n1 x n2.
        int n1 = R.length;
        int n3 = S.length;
        if (n1 == 0 || n3 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        int n2 = R[0].length;
        int n4 = S[0].length;
        if (n1 != n3 || n2 != n4) {
            throw new UnsupportedOperationException("array dimensions must match!");
        }
        boolean[][] tempMatrix = new boolean[n1][n2];
        // iterate through each row, column pair
        for (int r = 0; r < n1; r++) {
          for (int c = 0; c < n2 ; c++ ) {
              if (R[r][c] || S[r][c])
                tempMatrix[r][c] = true;
          }
        }
        return tempMatrix;
    }

    /**
     * Returns transitive closure of R.  This method uses a less efficient algorithm than Warshall's algorithm.
     * @param R relation, represented as a matrix (double array)
     * @return the transitive closure of R
     */
    public static boolean[][] transitiveClosure(boolean[][] R) {
        // compare matrix and make sure it's square: if R is n1 x n2 then n1 = n2.
        int n = R.length;
        if (R[0].length != n) {
            throw new UnsupportedOperationException("expecting an n by n boolean double array!");
        }
        boolean[][] R_prime = copyRelation(R);
        while(true){
          boolean[][] R_new = compose(R, R_prime);
          // new - R'
          for (int r = 0; r < n; r++) {
            for (int c = 0; c < n ; c++ ) {
                if (R_prime[r][c])
                  R_new[r][c] = false;
              }
          }
          // R' U new
          R_prime = union(R_prime, R_new);
          // if no new elements, done looping
          if (getCardinality(R_new) == 0)
            break;
        }
        return R_prime;
    }

    // returns the cardinality of a relation
    public static int getCardinality(boolean[][] R){
      int n = R.length;
      int total = 0;
      for (int r = 0; r < n; r++) {
        for (int c = 0; c < n ; c++ ) {
            if (R[r][c])
              total++;
        }
      }
      return total;
    }

    // returns a copy of the input relation
    public static boolean[][] copyRelation(boolean[][] R){
      int n1 = R.length;
      int n2 = R[0].length;
      boolean[][] newMatrix = new boolean[n1][n2];
      // iterate through each row, column pair
      for (int r = 0; r < n1; r++) {
        for (int c = 0; c < n2 ; c++ ) {
            if (R[r][c])
              newMatrix[r][c] = true;
        }
      }
      return newMatrix;
    }

    // --- useful tools for debugging are provided below  ---

    /**
     * Returns string representation of R as a double array of T/F values.
     * @param R a relation, double array of booleans
     */
    public static String doubleArrayToString(boolean[][] R) {
        // please do not modify
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R.length; i++) {
            for (int j = 0; j < R[i].length; j++) {
                sb.append((R[i][j]? "T":"F") + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns string representation of R as a set of pairs.
     * @param R a relation, double array of booleans
     */
    public static String relationToString(boolean[][] R) {
        // please do not modify
        String descriptor = "{1.." + R.length + "} x {1.." + R[0].length + "}";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R.length; i++) {
            for (int j = 0; j < R[i].length; j++) {
                String s = "(" + (i+1) + "," + (j+1) + ")";
                if (R[i][j]) {
                    sb.append("(" + (i+1) + "," + (j+1) + ")");
                    sb.append(", ");
                }
            }
        }
        return "{" + sb.substring(0, Math.max(sb.length()-2,0)) + "}, a subset of " + descriptor;
    }

    /**
     * Returns a relation R where adjacent pairs are related.  In other words, for all i between 0 and n-1, we have (
     * i, i+1) in R.
     * @param n size of relation
     * @return relation R
     */
    public static boolean[][] makeChain(int n) {
        // please do not modify
        boolean[][] R = new boolean[n][n];
        for (int i = 0; i < R.length-1; i++) {
            R[i][i+1] = true;
        }
        return R;
    }
}
