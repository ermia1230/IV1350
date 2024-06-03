package se.kth.iv1350.ermia.model;

import se.kth.iv1350.ermia.model.dto.ItemDTO;

public class Item {
    private ItemDTO itemDTO;
    private int quantity = 0;
    public Item(ItemDTO itemDTO, int quantity){
        this.itemDTO = itemDTO;
        this.quantity = quantity;
    }
    public ItemDTO getItemDTO(){
        return this.itemDTO;
    }
    public int getIQuantity(){
        return this.quantity;
    }
}
