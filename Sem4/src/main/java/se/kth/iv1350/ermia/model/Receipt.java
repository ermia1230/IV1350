/**
 * The <code>Receipt</code> class represents a receipt generated for a sale.
 * It contains information about the sale and the time of the sale.
 */
package se.kth.iv1350.ermia.model;

import se.kth.iv1350.ermia.model.dto.ReceiptDTO;

import java.time.LocalDateTime;

public class Receipt {
    private ReceiptDTO receiptInfo;
    private LocalDateTime saleTime;
    /**
     * Creates a new instance of <code>Receipt</code> with the specified receipt information.
     *
     * @param receiptInfo The receipt information.
     */
    public Receipt(ReceiptDTO receiptInfo){
        this.receiptInfo = receiptInfo;
        saleTime = LocalDateTime.now();
    }
    /**
     * Getter of the receipt information.
     *
     * @return The receipt information.
     */
    public ReceiptDTO getReceiptInfo() {
        return receiptInfo;
    }

    /**
     * Getter of the time of the sale.
     *
     * @return The time of the sale.
     */
    public LocalDateTime getSaleTime() {
        return saleTime;
    }
}
