package se.kth.iv1350.ermia.integration;

import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.Receipt;

import java.time.format.DateTimeFormatter;

public class Printer {
    public void printingTheReceipt(Receipt receipt) {
        System.out.println("\n--------------------Receipt-----------------------\n");
        String receiptToPrint = generateReceipt(receipt);
        System.out.println(receiptToPrint);
        System.out.println("\n--------------------End Of Receipt-----------------------\n");
    }

    private String generateReceipt(Receipt receipt) {
        StringBuilder receiptBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        appendTime(receiptBuilder, receipt.getSaleTime().format(formatter));
        appendItems(receiptBuilder, receipt);
        appendTotal(receiptBuilder, receipt);
        appendPaymentDetails(receiptBuilder, receipt);
        return receiptBuilder.toString();
    }

    private void appendTime(StringBuilder receiptBuilder, String saleTime) {
        receiptBuilder.append("Sale Time: ").append(saleTime).append("\n")
                .append("---------------------------------------------------\n");
    }

    private void appendItems(StringBuilder receiptBuilder, Receipt receipt) {
        receiptBuilder.append("Items:\n");
        for (Item item : receipt.getReceiptInfo().saleInfo().itemList()) {
            receiptBuilder.append(String.format("%-20s : %s\n", "Item", item.getItemDTO().name()))
                    .append(String.format("%-20s : %s\n", "Description", item.getItemDTO().description()))
                    .append(String.format("%-20s : %.2f SEK\n", "Price", item.getItemDTO().price()))
                    .append(String.format("%-20s : %d\n", "Quantity", item.getItemQuantity()))
                    .append(String.format("%-20s : %.2f SEK\n", "Subtotal", item.getItemDTO().price() * item.getItemQuantity()))
                    .append(String.format("%-20s : %.2f%%\n", "VAT", item.getItemDTO().vatRate() * 100))
                    .append("---------------------------------------------------\n");
        }
    }

    private void appendTotal(StringBuilder receiptBuilder, Receipt receipt) {
        receiptBuilder.append("\nTotal:\n")
                .append(String.format("%-20s : %.2f SEK\n", "Total Price (incl. VAT)", receipt.getReceiptInfo().saleInfo().totalPrice()))
                .append(String.format("%-20s : %.2f SEK\n", "Total VAT", receipt.getReceiptInfo().saleInfo().totalVATAmount()))
                .append("---------------------------------------------------\n");
    }

    private void appendPaymentDetails(StringBuilder receiptBuilder, Receipt receipt) {
        receiptBuilder.append("\nPayment:\n")
                .append(String.format("%-20s : %.2f SEK\n", "Amount Paid", receipt.getReceiptInfo().amountPaid()))
                .append(String.format("%-20s : %.2f SEK\n", "Changed Amount", receipt.getReceiptInfo().changedAmount()))
                .append("---------------------------------------------------\n");
    }
}
