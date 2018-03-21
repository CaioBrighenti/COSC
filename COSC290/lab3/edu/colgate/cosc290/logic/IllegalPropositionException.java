// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay
package edu.colgate.cosc290.logic;

/**
 * This exception is thrown whenever a proposition processor encounters
 * an unexpected propositional form.
 */
public class IllegalPropositionException extends RuntimeException {
    public IllegalPropositionException() {
        super();
    }
    public IllegalPropositionException(String message) {
        super(message);
    }
}
