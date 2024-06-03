package se.kth.iv1350.ermia.model;

import se.kth.iv1350.ermia.model.dto.ReceiptDTO;

import java.time.LocalDateTime;

public class Receipt {
    private ReceiptDTO receiptInfo;
    private LocalDateTime saleTime;
    public Receipt(ReceiptDTO receiptInfo){
        this.receiptInfo = receiptInfo;
        saleTime = LocalDateTime.now();
    }
    public ReceiptDTO getReceiptInfo() {
        return receiptInfo;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }
}
