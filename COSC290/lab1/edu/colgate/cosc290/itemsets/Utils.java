package edu.colgate.cosc290.itemsets;

import java.util.*;

/**
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Utils {

    /**
     * Given a set s, containing elements of type E, return the powerset of s
     * @param s a set of elements
     * @param <E> type of elements
     * @return the set of all subsets of input set s
     */
    public static <E> Set<Set<E>> allSubsets(Set<E> s) {
        Set<E> s_copy = new HashSet<>(s);
        Set<Set<E>> returnset = subsetRecursiveHelper(s_copy);
        return returnset;
    }

    public static <E> Set<Set<E>> subsetRecursiveHelper(Set<E> s) {
      Set<Set<E>> tempset = new HashSet<>();
      // base case, return emptyset and set of singleton
      if (s.size() == 0) {
        Set<E> emptyset = new HashSet<>();
        tempset.add(emptyset);
      } else {
        Iterator<E> it = s.iterator();
        E element = it.next();
        s.remove(element);
        Set<Set<E>> recurset = subsetRecursiveHelper(s);
        Set<Set<E>> recursetcopy = new HashSet<>();
        for (Set<E> set_el : recurset) {
          recursetcopy.add(new HashSet<>(set_el));
          set_el.add(element);
        }
        tempset.addAll(recurset);
        tempset.addAll(recursetcopy);
      }
      return tempset;
    }

    /**
     * Given a set s, containing elements of type E, return set of all subsets of s such that each
     * subset contains k elements
     * @param s a set of elements
     * @param k the desired number of elements in each subset
     * @param <E> type of elements
     * @return all subsets of size k from set s
     */
    public static <E> Set<Set<E>> allSubsetsOfSize(Set<E> s, int k) {

      Set<E> empty_set = new HashSet<>();
      Set<E> s_copy = new HashSet<>(s);
      return allSubsetsRecurHelper(s_copy, empty_set, k);
    }

    public static <E> Set<Set<E>> allSubsetsRecurHelper(Set<E> s, Set<E> curr_set, Integer k){
      // Prepare variables
      Set<Set<E>> return_set = new HashSet<>();
      Set<E> curr_set_copy = new HashSet<>(curr_set);

      // Check first base case
      if (curr_set_copy.size() == k){
        return_set.add(curr_set_copy);
        return return_set;
      }

      // Check second base case
      if (s.size() <= 0)
        return null;

      // Make recursive calls
      Set<E> s_copy = new HashSet<>(s);
      Iterator<E> it = s_copy.iterator();
      E element = it.next();
      s_copy.remove(element);
      Set<Set<E>> recur_set1 = allSubsetsRecurHelper(s_copy, curr_set_copy, k);
      curr_set_copy.add(element);
      Set<Set<E>> recur_set2 = allSubsetsRecurHelper(s_copy, curr_set_copy, k);

      // Return sets from both recursive calls
      if (recur_set1 != null)
        return_set.addAll(recur_set1);
      if (recur_set2 != null)
        return_set.addAll(recur_set2);
      return return_set;
    }

    /**
     * Utility for making a new set: see usage in main method below.
     * @param elements
     * @param <E>
     * @return
     */
    @SafeVarargs
    public static <E> Set<E> makeSet(E... elements) {
        Set<E> set = new HashSet<>();
        Collections.addAll(set, elements);
        return set;
    }

    /**
     * Utility for making a set of sets: see usage in main method below.
     * @param sets double array where sets[i] is the ith set and sets[i][j] is element of ith set.
     * @param <E>
     * @return
     */
    public static <E> Set<Set<E>> makeSetOfSets(E[][] sets) {
        Set<Set<E>> setOfSets = new HashSet<>();
        for (int i = 0; i < sets.length; i++) {
            E[] s = sets[i];
            Set<E> sAsSet = new HashSet<E>();
            Collections.addAll(sAsSet, s);
            setOfSets.add(sAsSet);
        }
        return setOfSets;
    }

    public static void main(String[] args) {
        // a little demonstration of the methods
        Set<String> S = Utils.makeSet("a", "b");
        System.out.println("S = " + S);

        Set<Set<String>> powersetS = Utils.makeSetOfSets(new String[][]{
                {},
                {"a"},
                {"b"},
                {"a", "b"}
        });
        System.out.println("power set of S should be " + powersetS);
        System.out.println("power set method returns " + allSubsets(S));

        Set<Set<String>> subsetsSizeOne = Utils.makeSetOfSets(new String[][]{
                {"a"},
                {"b"},
        });
        System.out.println("subsets of size 1 of S should be " + subsetsSizeOne);
        System.out.println("power set method returns " + allSubsetsOfSize(S, 1));

        Set<Set<String>> subsetsSizeTwo = Utils.makeSetOfSets(new String[][]{
                {"a", "b"}
        });
        System.out.println("subsets of size 2 of S should be " + subsetsSizeTwo);
        System.out.println("power set method returns " + allSubsetsOfSize(S, 2));
    }

}
