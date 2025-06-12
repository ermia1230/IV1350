/**
 * The <code>SaleLog</code> class represents a log of sales made in the system.
 * It maintains a list of <code>SaleDTO</code> objects.
 */
package se.kth.iv1350.ermia.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.ermia.model.dto.SaleDTO;

public class SaleLog {
    private List<SaleDTO> sales;
    private List<RevenueObserver> observers;
    /**
     * Creates a new instance of <code>SaleLog</code> with an empty list of sales.
     */
    public SaleLog(){
        this.sales = new ArrayList<>();
        this.observers = new ArrayList<>();
    }
    /**
     * Adds a sale to the sale log.
     *
     * @param saleDTO The <code>SaleDTO</code> object representing the sale to be added.
     */
    public void addSale(SaleDTO saleDTO) {
        sales.add(saleDTO);
        notifyObservers(saleDTO.totalPrice());
    }
    /**
     * it is a getter for the list of sales recorded in the sale log.
     *
     * @return A list of <code>SaleDTO</code> objects representing the sales recorded in the sale log.
     */
    public List<SaleDTO> getSales(){
        return this.sales;
    }
    
    /**
     * Registers an observer that will be notified when a new sale is added.
     *
     * @param observer The observer to register.
     */
    public void addObserver(RevenueObserver observer) {
        observers.add(observer);
    }
    
    private void notifyObservers(double revenue) {
        for (RevenueObserver observer : observers) {
            observer.newSale(revenue);
        }
    }
}
