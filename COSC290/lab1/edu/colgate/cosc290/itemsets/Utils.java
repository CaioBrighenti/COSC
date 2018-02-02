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
        List<E> setlist = new ArrayList();
        setlist.addAll(s);
        List<Set<E>> returnlist = subsetRecursiveHelper(setlist);
        Set<Set<E>> returnset = new HashSet(returnlist);
        return returnset;
    }

    public static <E> List<Set<E>> subsetRecursiveHelper(List<E> l) {
      List<Set<E>> templist = new ArrayList<>();
      // base case, return emptyset and set of singleton
      if (l.size() == 0) {
        Set<E> emptyset = new HashSet<>();
        templist.add(emptyset);
      } else {
        E element = l.get(l.size() - 1);
        l.remove(element);
        List<Set<E>> recurlist = subsetRecursiveHelper(l);
        List<Set<E>> recurlistcopy = new ArrayList();
        for (Set<E> set_el : recurlist) {
          recurlistcopy.add(new HashSet(set_el));
          set_el.add(element);
        }
        templist.addAll(recurlist);
        templist.addAll(recurlistcopy);
      }
      return templist;
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
        Set<Set<E>> fullset = allSubsets(s);
        Set<Set<E>> newset = new HashSet<>();
        for (Set<E> subset: fullset) {
          if (subset.size() == k)
            newset.add(subset);
        }
        return newset;
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
