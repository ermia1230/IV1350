/**
 * The <code>ReceiptDTO</code> is a of type record introduced in Java 14.
 * Record represents a Data Transfer Object (DTO) for objects which are immutable.
 * It is used to transfer the receipt data between different layers of the application.
 * <code>Receipt</code> holds information about a sale, the amount paid by the customer, and
 * the changed amount to give back to the customer.
 * @param saleInfo it is a <code>SaleDTO</code> which has the entire information about the sale.
 * @param amountPaid it is a <code>double</code> which is the amount paid by the customer
 * @param changedAmount it is a <code>double</code> which is the amount to pay back the customer
 *
 */
package se.kth.iv1350.ermia.model.dto;
public record ReceiptDTO(SaleDTO saleInfo, double amountPaid, double changedAmount) {
}
