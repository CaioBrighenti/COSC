// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
// Edited by: Caio Brighenti

package edu.colgate.cosc290.posets;

import java.util.*;

/**
 * Class representing a partially ordered set of integers.
 */
public class Poset {

    private int n;  // size (number of elements) in this partially ordered set
    private Map<Integer, List<Integer>> hasse; // hasse diagram
    private Map<Integer, List<Integer>> invHasse; // hasse diagram

    /**
     * Initialize partially order set (posets) based on a Hasse diagram.
     * @param H Hasse diagram in adjacency list format
     *              (a map where the key is x an element of the set,
     *              and the value value is list of *direct* successors)
     */
    public Poset(Map<Integer, List<Integer>> H) {
        n = H.size();
        checkInput(H);
        hasse = H;
        invHasse = invertHasse(H);
    }

    /**
     * @param H Hasse diagram in adjacency list format
     * @return an inverted copy of the Hasse diagram
     */
    public Map<Integer, List<Integer>> invertHasse(Map<Integer, List<Integer>> H){
      Map<Integer, List<Integer>> returnH = new HashMap<>();
      // iterate through each node in the Hasse diagram
      for (Map.Entry<Integer,List<Integer>> entry : H.entrySet()) {
        Integer ancestor = entry.getKey();
        List<Integer> successors = entry.getValue();
        // add this node to new list if not yet present
        if (returnH.get(ancestor) == null)
          returnH.put(ancestor, new LinkedList<>());
        // iterate through each succesor for that node
        for (Integer succ: successors) {
          // add this node to new list if not yet present
          if (returnH.get(succ) == null)
            returnH.put(succ, new LinkedList<>());
          returnH.get(succ).add(ancestor);
        }
      }
      return returnH;
    }

    /**
     * Returns the set of maximal elements.
     * @return the set of maximal elements
     */
    public Set<Integer> maximal() {
      Set<Integer> returnSet = new HashSet<>();
      // iterate through the Hasse diagram
      for (Map.Entry<Integer,List<Integer>> entry : hasse.entrySet()) {
        Integer ancestor = entry.getKey();
        List<Integer> successors = entry.getValue();
        if (successors.isEmpty())
          returnSet.add(ancestor);
      }
      return returnSet;
    }

    /**
     * Returns the maximum element or -1 if no such element exists.
     * @return the maximum element or -1 if no such element exists
     */
    public int maximum() {
      Set<Integer> maximalSet = maximal();
      if (maximalSet.size() == 1)
        return maximalSet.iterator().next();
      else
        return -1;
    }

    /**
     * Returns the set of minimal elements.
     * @return the set of minimal elements
     */
    public Set<Integer> minimal() {
      return minimal(hasse.keySet());
    }

    /**
     * Returns the set of minimal elements of a subset S⊆ P
     * @param S subset S⊆ P
     * @return the set of minimal elements of a subset S⊆ P
     */
    public Set<Integer> minimal(Set<Integer> S) {
      Set<Integer> returnSet = new HashSet<>();
      // iterate through each item in S and check if it has any ancestors
      for (Integer succesor : S) {
        List<Integer> ancestors = invHasse.get(succesor);
        // if no ancestors, must be minimal
        if (ancestors.isEmpty())
          returnSet.add(succesor);
        else {
          // check if ancestors are in subset S⊆ P
          boolean flag = true;
          for (Integer ancestor : ancestors) {
            if (S.contains(ancestor))
              flag = false;
          }
          if (flag)
            returnSet.add(succesor);
        }
      }
      return returnSet;
    }

    /**
     * Returns the minimum element or -1 if no such element exists.
     * @return the minimum element or -1 if no such element exists
     */
    public int minimum() {
      return minimum(hasse.keySet());
    }

    /**
     * Returns the minimum element of a subset S⊆ P or -1 if no such element exists.
     * @param S subset S⊆ P
     * @return the minimum element of a subset S⊆ P or -1 if no such element exists.
     */
    public int minimum(Set<Integer> S) {
      Set<Integer> minimalSet = minimal(S);
        if (minimalSet.size() == 1)
          return minimalSet.iterator().next();
        else
          return -1;
    }

    /**
     * Checks whether the given total order is consistent with the partial order of this set.
     * @param totalOrder
     * @return true if it is consistent, false otherwise
     */
    public boolean consistent(List<Integer> totalOrder) {
        // ensure each item in total order is minimal in the partial order with respect
        // to its successors in the total order
        for (int i = 0; i < totalOrder.size(); i++) {
          Integer node = totalOrder.get(i);
          Set<Integer> subset = new HashSet<Integer>(totalOrder.subList(i, totalOrder.size()));
          Set<Integer> minimalSet = minimal(subset);
          if (!minimalSet.contains(node))
            return false;
        }
        return true;
    }

    /**
     * Returns a topological sort of this posets.
     * @return a list containing all of the elements in the set in ascending order (if x ≤ y according to the partial
     * order, then the index of x should be less than the index of y in the returned list)
     */
    public List<Integer> topoSort() {
        List<Integer> topoList = new ArrayList<Integer>();
        Set<Integer> tempSet = hasse.keySet();
        // iteratively add a minimal element to the topo order, then re-obtain the minimal elements
        while (!tempSet.isEmpty()){
          Set<Integer> minimalSet = minimal(tempSet);
          topoList.addAll(minimalSet);
          tempSet.removeAll(minimalSet);
        }
        return topoList;
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
        H.put(0, Arrays.asList(2, 3, 1));  // edges: 0 -> 2 and 0 -> 3
        H.put(1, Arrays.asList(4));     // edges: 1 -> 4
        H.put(2, Arrays.asList(4));     // edges: 2 -> 4
        H.put(3, new LinkedList<>());   // 3 has no outgoing edges
        H.put(4, new LinkedList<>());   // 4 has no outgoing edges
        //System.out.println(H);
        Poset poset = new Poset(H);
        // Write a main method that demonstrates the correctness of your implementation.
        System.out.println("------------- TEST 1 -------------");
        Integer arr[] = { 1,2,3,4 };
        Set<Integer> subset = new HashSet<>(Arrays.asList(arr));
        System.out.println("H: " + H);
        System.out.println("invertHasse(H): " + poset.invHasse);
        System.out.println("poset.minimal(): " + poset.minimal());
        System.out.println("poset.minimum(): " + poset.minimum());
        System.out.println("poset.minimal({1,2,3,4}): " + poset.minimal(subset));
        System.out.println("poset.maximal(): " + poset.maximal());
        System.out.println("poset.maximum(): " + poset.maximum());
        Integer arr2[] = { 0,1,2,3,4 };
        List<Integer> totalOrd = new ArrayList<>(Arrays.asList(arr2));
        System.out.println("poset.consistent({ 0,1,2,3,4 }): " + poset.consistent(totalOrd));
        System.out.println("poset.topoSort(): " + poset.topoSort());
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
