package se.kth.iv1350.ermia.util;

/**
 * Interface for logging.
 */
public interface Logger {   
    /**
     * Logs the specified error message and exception.
     *
     * @param message The error message to log.
     * @param exception The exception to log.
     */
    void log(String message, Exception exception);
}