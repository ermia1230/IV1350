package se.kth.iv1350.ermia.model.exception;
/**
 * Thrown when no item is found for the given identifier in the inventory.
 */
public class ItemNotFoundException extends RuntimeException {
    private final int itemId;

    /**
    * Creates a new exception with a message showing the missing item ID.
    *
    * @param itemId The ID of the item that was not found.
    */
    public ItemNotFoundException(int itemId) {
        super("Could not find item with ID: " + itemId);
        this.itemId = itemId;
    }
    
   /**
   * Returns the ID of the item that was not found.
   *
   * @return The item ID.
   */
    public int getItemId() {
        return this.itemId;
    }
}