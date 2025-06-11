package se.kth.iv1350.ermia.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A logger that writes log messages to a file.
 */
public class FileLogger implements Logger {
    private static final String LOG_FILE_NAME = "sale_log.txt";
    private PrintWriter logFile;
    
    /**
     * Creates a new instance and opens the log file.
     */
    public FileLogger() {
        try {
            logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
        } catch (IOException ex) {
            System.out.println("Could not create logger.");
            ex.printStackTrace();
        }
    }  
    
    /**
     * Logs the specified error message and exception to the log file.
     *
     * @param message The error message to log.
     * @param exception The exception to log.
     */
    @Override
    public void log(String message, Exception exception) {
        StringBuilder logMsg = new StringBuilder();
        logFile.println(" ");
        logMsg.append(createLogEntry(message));
        logMsg.append("\n");
        logMsg.append("Exception: ").append(exception.getMessage());
        logMsg.append("\nStacktrace:");
        for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
            logMsg.append("\n").append(stackTraceElement);
        }
        logFile.println(logMsg);
        logFile.println(" ");
        logFile.println("--------------------------------------------------");
    }
    
    private String createLogEntry(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + now.format(formatter) + "] " + message;
    }
}