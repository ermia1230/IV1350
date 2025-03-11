/**
 * The <code>SaleDTO</code> is a of type record introduced in Java 14
 * record represents a Data Transfer Object (DTO) for items which is immutable .
 * It is used to transfer sale data between different layers of the application.
 * <code>SaleDTO</code> holds information about a sale, including the list of items
 * in the sale, the total price, and the total VAT amount.
 *
 * @param itemList The list of items included in the sale.
 * @param totalPrice The total price of all items in the sale.
 * @param totalVATAmount The total VAT amount for all items in the sale.
 */
package se.kth.iv1350.ermia.model.dto;

import se.kth.iv1350.ermia.model.Item;

import java.util.List;

public record SaleDTO(List<Item> itemList, double totalPrice, double totalVATAmount) {
}
