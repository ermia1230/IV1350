/**
 * The <code>Sale</code> class represent an entire transaction. It keeps track of the total price,
 * total VAT amount, and the list of items in the sale. It provides methods to add items to the
 * sale and update the sale details accordingly.
 */
package se.kth.iv1350.ermia.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.ermia.integration.exception.DatabaseConException;
import se.kth.iv1350.ermia.integration.system.ExternalInventory;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;
import se.kth.iv1350.ermia.model.exception.ItemNotFoundException;

public class Sale {
    private double totalPrice;
    private double totalVATAmount;
    private List<Item> itemList;
    private ExternalInventory inventory;

    /**
    * Creates a new instance of <code>Sale</code> with an empty list of items and 
    * a reference to the external inventory system. This constructor allows the 
    * Sale object to look up item information directly.
    *
    * @param inventory The external inventory system that will be used to fetch 
    *                  item information when needed.
    */
    public Sale(ExternalInventory inventory) {
    this.itemList = new ArrayList<>();
    this.inventory = inventory;
    }
    /**
    * Adds an item to the sale using its item ID.
    *
    * @param itemId The ID of the item to add.
    * @param quantity The quantity of the item to add.
    * @return A SaleDTO containing the current state of the sale.
    * @throws ItemNotFoundException If the item with the specified ID doesn't exist.
    * @throws DatabaseConException If there's a database connection error.
    */
    public SaleDTO addItemById(int itemId, int quantity) throws ItemNotFoundException, DatabaseConException {
        ItemDTO itemDTO = inventory.fetchItem(itemId);
    
        if (itemDTO == null) {
            throw new ItemNotFoundException(itemId);
        }
        
        Item newItem = new Item(itemDTO, quantity);
        
        return addItem(newItem);
    } 
   
    private SaleDTO addItem(Item item) {
        if (isItemInSale(getItemID(item))) {
            increaseQuantity(getItemID(item), item.getItemQuantity());
        } else {
            itemList.add(item);
            this.totalPrice += calcItemTotalPrice(item);
            this.totalVATAmount += calcItemTotalVAT(item);
        }
        return new SaleDTO(this.itemList, this.totalPrice, this.totalVATAmount);
    }

    /**
     * Getter for the total price of the <code>Sale</code>
     * @return a <code>double</code> is returned which is the total price of the sale
     */
    public double getTotalPrice(){
        return this.totalPrice;
    }

    /**
     * Getter for the total VAT of the <code>Sale</code>
     * @return a <code>double</code> is returned which is the total VAT of the sale
     */
    public double getTotalVATAmount(){
        return this.totalVATAmount;
    }

    /**
     * Returns a DTO containing the current state of the sale.
     *
     * @return A <code>SaleDTO</code> containing the current sale information.
     */
    public SaleDTO getSaleInfo() {
        return new SaleDTO(this.itemList, this.totalPrice, this.totalVATAmount);
    }

    private void increaseQuantity(int itemId, int increasedQuantity) {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (getItemID(item) == itemId) {
                int newQuantity = item.getItemQuantity() + increasedQuantity;
                Item updatedItem = new Item(item.getItemDTO(), newQuantity);
                itemList.set(i, updatedItem);
                this.totalPrice += calcItemTotalPrice(updatedItem) - calcItemTotalPrice(item);
                this.totalVATAmount += calcItemTotalVAT(updatedItem) - calcItemTotalVAT(item);
                break;
            }
        }
    }
    private boolean isItemInSale(int itemId){
        for(Item item: itemList){
            if(item.getItemDTO().itemId() == itemId){
                return true;
            }
        }
        return false;
    }
    private int getItemID(Item item){
        return item.getItemDTO().itemId();
    }
    private double calcItemTotalPrice(Item item){
        return item.getItemDTO().price() * item.getItemQuantity();
    }
    private double calcItemTotalVAT(Item item) {
        double itemTotalPrice = calcItemTotalPrice(item);
        double vatRate = item.getItemDTO().vatRate();
        double netPrice = itemTotalPrice / (1 + vatRate);
        return itemTotalPrice - netPrice;
    }
}
