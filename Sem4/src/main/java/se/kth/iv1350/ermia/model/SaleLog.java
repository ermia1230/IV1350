/**
 * The <code>SaleLog</code> class represents a log of sales made in the system.
 * It maintains a list of <code>SaleDTO</code> objects.
 */
package se.kth.iv1350.ermia.model;

import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class SaleLog {
    private List<SaleDTO> sales;
    /**
     * Creates a new instance of <code>SaleLog</code> with an empty list of sales.
     */
    public SaleLog(){
        this.sales = new ArrayList<>();
    }
    /**
     * Adds a sale to the sale log.
     *
     * @param saleDTO The <code>SaleDTO</code> object representing the sale to be added.
     */
    public void addSale(SaleDTO saleDTO) {
        sales.add(saleDTO);
    }
    /**
     * it is a getter for the list of sales recorded in the sale log.
     *
     * @return A list of <code>SaleDTO</code> objects representing the sales recorded in the sale log.
     */
    public List<SaleDTO> getSales(){
        return this.sales;
    }
}
