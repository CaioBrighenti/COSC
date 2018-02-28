package edu.colgate.cosc290.satsolver;

/**
 * A utility class for representing a pair of objects of certain generic types X and Y.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Pair constructor.
     * @param x first object in pair
     * @param y second object in pair
     */
    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getFirst() {
        return x;
    }

    public Y getSecond() {
        return y;
    }

    @Override
    public String toString() {
        return "Pair(" + x + ", " + y + ")";
    }

    // ---- implementations of equals and hashcode so that this object can be put into hash sets/maps/etc.

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair other = (Pair) obj;
        return this.x.equals(other.x) && this.y.equals(other.y);
    }

    @Override
    public int hashCode() {
        return x.hashCode() ^ y.hashCode(); // exclusive OR
    }
}
