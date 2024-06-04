/**
 * The <code>Item</code> class represents an item to be bought in the sale.
 * It holds the data of an item (itemDTO), including its details and the quantity being purchased.
 */
package se.kth.iv1350.ermia.model;

import se.kth.iv1350.ermia.model.dto.ItemDTO;

public class Item {
    private ItemDTO itemDTO;
    private int quantity = 0;
    /**
     * The constructor of the <code>item</code> class Creates a new instance of <code>Item</code>.
     *
     * @param itemDTO The data transfer object which has all the details of the item.
     * @param quantity The quantity of the item being purchased.
     */
    public Item(ItemDTO itemDTO, int quantity){
        this.itemDTO = itemDTO;
        this.quantity = quantity;
    }

    /**
     * Returns the DTO containing the details of the item.
     *
     * @return The <code>ItemDTO</code> of this item.
     */
    public ItemDTO getItemDTO(){
        return this.itemDTO;
    }

    /**
     * Returns the quantity of the item being bought.
     *
     * @return The quantity of the item.
     */
    public int getItemQuantity(){
        return this.quantity;
    }

    /**
     * Sets the quantity of an item. In other words, it is used to change quantity of an item.
     * @param quantity is an <code>integer</code> which is the new quantity of the item.
     */
    public void setIQuantity(int quantity) {
        this.quantity = quantity;
    }
}
