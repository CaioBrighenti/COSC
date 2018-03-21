// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
package edu.colgate.cosc290.logic;

/**
 * Class representing logical connectives such as NOT, AND, OR, and IF.
 */
public enum LogicalConnective {
    NOT("~"),
    AND("&"),
    OR("|"),
    IF("=>");

    private String connectiveSymbol;

    LogicalConnective(String connectiveSymbol) {
        this.connectiveSymbol = connectiveSymbol;
    }

    @Override
    public String toString() {
        return connectiveSymbol;
    }
}
