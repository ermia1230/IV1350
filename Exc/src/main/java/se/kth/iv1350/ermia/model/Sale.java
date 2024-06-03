package se.kth.iv1350.ermia.model;

import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private double totalPrice;
    private double totalVATAmount;
    private List<Item> itemList;
    public Sale(){
        this.itemList = new ArrayList<>();
    }
    private boolean isItemInSale(int itemId){
        for(Item item: itemList){
            if(item.getItemDTO().itemId() == itemId){
                return true;
            }
        }
        return false;
    }
    public SaleDTO addItem(Item item) {
        if (isItemInSale(getItemID(item))) {
            increaseQuantity(getItemID(item), item.getIQuantity());
        } else {
            itemList.add(item);
            this.totalPrice += claItemTotalPrice(item);
            this.totalVATAmount += claItemTotalVAT(item, claItemTotalPrice(item));
        }
        return new SaleDTO(this.itemList, this.totalPrice, this.totalVATAmount);
    }

    public void increaseQuantity(int itemId, int increasedQuantity) {
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (getItemID(item) == itemId) {
                int newQuantity = item.getIQuantity() + increasedQuantity;
                Item updatedItem = new Item(item.getItemDTO(), newQuantity);
                itemList.set(i, updatedItem);
                this.totalPrice += claItemTotalPrice(updatedItem) - claItemTotalPrice(item);
                this.totalVATAmount += claItemTotalVAT(updatedItem, claItemTotalPrice(updatedItem)) -
                        claItemTotalVAT(item, claItemTotalPrice(item));
                break;
            }
        }
    }
    private int getItemID(Item item){
        return item.getItemDTO().itemId();
    }
    private double claItemTotalPrice(Item item){
        return item.getItemDTO().price() * item.getIQuantity();
    }
    private double claItemTotalVAT(Item item, double itemTotalPrice){
        return itemTotalPrice * item.getItemDTO().vatRate();
    }
}
