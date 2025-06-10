/**
 * The <code>ItemDTO</code> is a of type record introduced in Java 14
 * record represents a Data Transfer Object (DTO) for items which is immutable .
 * It is used to transfer item data between different layers of the application.
 * The DTO has information about an item, including its identifier,
 * VAT rate, name, description, and price.
 *
 * @param itemId The identifier of the item.
 * @param vatRate The VAT rate of item.
 * @param name The name of the item.
 * @param description A brief description of the item.
 * @param price The price of the item.
 */
package se.kth.iv1350.ermia.model.dto;

public record ItemDTO(int itemId, double vatRate, String name, String description, double price) {
}
