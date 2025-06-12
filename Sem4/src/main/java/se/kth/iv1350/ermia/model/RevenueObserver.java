package se.kth.iv1350.ermia.model;

/**
 * The observer interface for classes that want to be notified about 
 * sales revenue. Part of the Observer pattern implementation.
 */
public interface RevenueObserver {
    /**
     * Called when a new sale has been registered, providing the revenue from that sale.
     * 
     * @param revenue The revenue from the new sale.
     */
    void newSale(double revenue);
}
