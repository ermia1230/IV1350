package se.kth.iv1350.ermia.integration;

import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.Receipt;

import java.time.format.DateTimeFormatter;

public class Printer {
    public void printingTheReceipt(Receipt receipt) {
        String formattedReceipt = formatReceipt(receipt);
        System.out.println(formattedReceipt);
    }

    private String formatReceipt(Receipt receipt) {
        StringBuilder receiptBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        appendHeader(receiptBuilder, receipt.getSaleTime().format(formatter));
        appendItems(receiptBuilder, receipt);
        appendTotal(receiptBuilder, receipt);
        appendPaymentDetails(receiptBuilder, receipt);
        appendFooter(receiptBuilder);

        return receiptBuilder.toString();
    }

    private void appendHeader(StringBuilder receiptBuilder, String saleTime) {
        receiptBuilder.append("\n--------------------Receipt-----------------------\n")
                .append("Sale Time: ").append(saleTime).append("\n")
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

    private void appendFooter(StringBuilder receiptBuilder) {
        receiptBuilder.append("\n--------------------End Of Receipt-----------------------\n");
    }
}
