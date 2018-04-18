// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay

package edu.colgate.cosc290.posets;

import java.util.*;

/**
 * Class representing a partially ordered set of integers.
 */
public class Poset {

    private int n;  // size (number of elements) in this partially ordered set

    /**
     * Initialize partially order set (posets) based on a Hasse diagram.
     * @param H Hasse diagram in adjacency list format
     *              (a map where the key is x an element of the set,
     *              and the value value is list of *direct* successors)
     */
    public Poset(Map<Integer, List<Integer>> H) {
        n = H.size();
        checkInput(H);
        throw new UnsupportedOperationException("implement me!");
    }



    /**
     * Returns the set of maximal elements.
     * @return the set of maximal elements
     */
    public Set<Integer> maximal() {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Returns the maximum element or -1 if no such element exists.
     * @return the maximum element or -1 if no such element exists
     */
    public int maximum() {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Returns the set of minimal elements.
     * @return the set of minimal elements
     */
    public Set<Integer> minimal() {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Returns the minimum element or -1 if no such element exists.
     * @return the minimum element or -1 if no such element exists
     */
    public int minimum() {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Checks whether the given total order is consistent with the partial order of this set.
     * @param totalOrder
     * @return true if it is consistent, false otherwise
     */
    public boolean consistent(List<Integer> totalOrder) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Returns a topological sort of this posets.
     * @return a list containing all of the elements in the set in ascending order (if x ≤ y according to the partial
     * order, then the index of x should be less than the index of y in the returned list)
     */
    public List<Integer> topoSort() {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Returns a lower bound for the set S.
     * @param S a subset of this poset
     * @return a set of elements that correspond to a lower bound for S
     */
    public Set<Integer> lowerBound(Set<Integer> S) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Returns a upper bound for the set S.
     * @param S a subset of this poset
     * @return a set of elements that correspond to a upper bound for S
     */
    public Set<Integer> upperBound(Set<Integer> S) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Returns the greatest lower bound (GLB) for the set S, or -1 if a GLB does not exist.
     * @param S a subset of this poset
     * @return the GLB or -1 if it does not exists
     */
    public int GLB(Set<Integer> S) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Returns the least upper bound (LUB) for the set S, or -1 if a LUB does not exist.
     * @param S a subset of this poset
     * @return the LUB or -1 if it does not exists
     */
    public int LUB(Set<Integer> S) {
        throw new UnsupportedOperationException("implement me!");
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> H = new HashMap<>();
        // example Hasse diagram over {0, 1, 2, 3, 4} with the following order relationships
        // (only showing *direct* successors and omitting pairs that follow from reflexivity
        // and transitivity)
        // 0 ≤ 2
        // 0 ≤ 3
        // 1 ≤ 4
        // 2 ≤ 4
        H.put(0, Arrays.asList(2, 3));  // edges: 0 -> 2 and 0 -> 3
        H.put(1, Arrays.asList(4));     // edges: 1 -> 4
        H.put(2, Arrays.asList(4));     // edges: 2 -> 4
        H.put(3, new LinkedList<>());   // 3 has no outgoing edges
        H.put(4, new LinkedList<>());   // 4 has no outgoing edges
        Poset poset = new Poset(H);
        // Write a main method that demonstrates the correctness of your implementation.
    }

    /**
     * Makes sure that every item in map is a number between 0 and n-1 where n is the size of the map.
     * @param hasse in adjancency list format
     * @throws RuntimeException if an invalid input is found
     */
    private void checkInput(Map<Integer, List<Integer>> hasse) {
        for (int i : hasse.keySet()) {
            if (i < 0 || i > n-1) {
                throw new RuntimeException("Invalid set element: " + i + ". Expecting ints from 0 to n-1");
            }
            List<Integer> nbrs = hasse.get(i);
            for (Integer j : nbrs) {
                if (j < 0 || j > n-1) {
                    throw new RuntimeException("Invalid set element: " + j + ". Expecting ints from 0 to n-1");
                }
            }
        }
    }
}
