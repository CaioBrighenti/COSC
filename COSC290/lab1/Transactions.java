package edu.colgate.cosc290.itemsets;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Transactions<E> implements Iterable<Set<E>> {
    private final List<Set<E>> listOfItemSets;
    private final int size;

    public Transactions(List<Set<E>> listOfItemSets) {
        this.listOfItemSets = listOfItemSets;
        int size = 0;
        for (Set<E> t : this) {
            size++;
        }
        this.size = size;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Set<E>> iterator() {
        return listOfItemSets.iterator();
    }
}
