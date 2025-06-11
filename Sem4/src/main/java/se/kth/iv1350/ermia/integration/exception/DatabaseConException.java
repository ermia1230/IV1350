package se.kth.iv1350.ermia.integration.exception;

/**
 * Thrown when the app can't reach the database or it's not running.
 */

public class DatabaseConException extends Exception {
    
    /**
    * Creates a new exception with a message about the database error.
    *
    */
    public DatabaseConException() {
        super("Could not connect to inventory database");
    }
    
}