package se.kth.iv1350.ermia.view;

import se.kth.iv1350.ermia.model.RevenueObserver;

/**
 * Shows the total revenue since the program started on the user interface.
 * This class is an observer of the SaleLog, getting notified when a new sale is made.
 */
public class TotalRevenueView implements RevenueObserver {
    private double totalRevenue = 0;
    
    /**
     * Updates the total revenue with the specified amount and displays it.
     * 
     * @param revenue The revenue from the most recent sale.
     */
    @Override
    public void newSale(double revenue) {
        totalRevenue += revenue;
        printCurrentRevenue();
    }
    
    private void printCurrentRevenue() {
        System.out.println("*** Total revenue since program start: " + totalRevenue + " ***");
    }
}
