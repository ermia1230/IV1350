package se.kth.iv1350.ermia.model.dto;

public record ReceiptDTO(SaleDTO saleInfo, double amountPaid, double changedAmount) {
}
