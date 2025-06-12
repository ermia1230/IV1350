package se.kth.iv1350.ermia.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.kth.iv1350.ermia.model.RevenueObserver;

/**
 * Writes the total revenue to a file when a new sale is made.
 * This class is an observer of the SaleLog, getting notified when a new sale is made.
 */
public class TotalRevenueFile implements RevenueObserver {
    private double totalRevenue = 0;
    private PrintWriter revenueFile;
    
    /**
     * Creates a new instance and opens the revenue log file.
     */
    public TotalRevenueFile() {
        try {
            revenueFile = new PrintWriter(new FileWriter("total_revenue.txt", false), true);
        } catch (IOException ex) {
            System.out.println("Could not create revenue file writer.");
            ex.printStackTrace();
        }
    }
    
    /**
     * Updates the total revenue with the specified amount and logs it to the file.
     * 
     * @param revenue The revenue from the most recent sale.
     */
    @Override
    public void newSale(double revenue) {
        totalRevenue += revenue;
        logCurrentRevenue();
    }
    
    private void logCurrentRevenue() {
        StringBuilder logMsg = new StringBuilder();
        logMsg.append(createTimestamp());
        logMsg.append(" Total revenue: ").append(totalRevenue);
        revenueFile.println(logMsg);
    }
    
    private String createTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + now.format(formatter) + "]";
    }
}
