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
    public boolean isItemInSale(int itemId){
        for(Item item: itemList){
            if(item.getItemDTO().itemId() == itemId){
                return true;
            }
        }
        return false;
    }
    public SaleDTO addItem(Item item){
        itemList.add(item);
        double itemTotalPrice = item.getItemDTO().price() * item.getIQuantity();
        double itemTotalVAT = itemTotalPrice * item.getItemDTO().vatRate() * item.getIQuantity();

        this.totalPrice += itemTotalPrice;
        this.totalVATAmount += itemTotalVAT;

        return new SaleDTO(this.itemList, this.totalPrice, this.totalVATAmount);
    }
}
