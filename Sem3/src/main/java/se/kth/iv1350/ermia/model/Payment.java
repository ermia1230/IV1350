/**
 * The <code>Payment</code> class represents a payment made by a customer.
 * It provides methods to calculate the change amount.
 */
package se.kth.iv1350.ermia.model;

public class Payment{
    private double amountPaidByCustomer;
    /**
     * Creates a new instance of <code>Payment</code> with the specified amount paid by the customer.
     *
     * @param amountPaidByCustomer The amount paid by the customer.
     */
    public Payment(double amountPaidByCustomer){
        this.amountPaidByCustomer = amountPaidByCustomer;
    }
    /**
     * Calculates the change amount by subtracting the total cost of the sale from the amount paid by the customer.
     *
     * @param totalCostOfSale The total cost of the sale.
     * @return The change amount.
     */
    public double calTheChangedAmount(double totalCostOfSale){
        return this.amountPaidByCustomer - totalCostOfSale;
    }
}
