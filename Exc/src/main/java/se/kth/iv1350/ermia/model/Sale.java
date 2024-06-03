/**
 * The <code>Sale</code> class represent an entire transaction. It keeps track of the total price,
 * total VAT amount, and the list of items in the sale. It provides methods to add items to the
 * sale and update the sale details accordingly.
 */
package se.kth.iv1350.ermia.model;

import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private double totalPrice;
    private double totalVATAmount;
    private List<Item> itemList;
    /**
     * It is a constructor Creates a new instance of <code>Sale</code> with an empty list of items.
     */
    public Sale(){
        this.itemList = new ArrayList<>();
    }
    /**
     * This method is used to add an item to the sale.
     * If the item is already in the sale, its quantity is increased.
     * Otherwise, the item will be added ot the list of sale.
     * The total price and total VAT amount are updated accordingly.
     *
     * @param item The item to be added to the sale.
     * @return A <code>SaleDTO</code> object containing the current state of the sale.
     */
    public SaleDTO addItem(Item item) {
        if (isItemInSale(getItemID(item))) {
            increaseQuantity(getItemID(item), item.getItemQuantity());
        } else {
            itemList.add(item);
            this.totalPrice += calcItemTotalPrice(item);
            this.totalVATAmount += calcItemTotalVAT(item);
        }
        return new SaleDTO(this.itemList, this.totalPrice, this.totalVATAmount);
    }
    public double getTotalPrice(){
        return this.totalPrice;
    }
    public List<Item> getItemList(){
        return this.itemList;
    }
    public double getTotalVATAmount(){
        return this.totalVATAmount;
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
