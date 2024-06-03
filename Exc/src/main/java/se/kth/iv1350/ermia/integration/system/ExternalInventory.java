package se.kth.iv1350.ermia.integration.system;

import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.dto.ItemDTO;

import java.util.List;
import java.util.ArrayList;


public class ExternalInventory {
    private List<ItemDTO> inventory;
    public ExternalInventory(){
        inventory = new ArrayList<>();
        initializeInventory();
    }
    private void initializeInventory() {
        inventory.add(new ItemDTO(100, 0.25, "Milk", "A carton of milk weighing 1.5 kg", 15));
        inventory.add(new ItemDTO(101, 0.12, "Bread", "A loaf of bread 5hg", 10));
        inventory.add(new ItemDTO(102, 0.25, "Butter", "A pack of butter 1kg", 20));
        inventory.add(new ItemDTO(104, 0.06, "Yogurt", "A cup of Turkish yogurt", 40));
        inventory.add(new ItemDTO(105, 0.06, "Apple Juice", "A bottle of apple juice", 45));
    }
    /**
     * Explain that later in Sem 4, the null will be replace with exception handling!
     *

     */
    public ItemDTO fetchItem(int itemId){
        for(ItemDTO itemDTO : this.inventory){
            if(itemDTO.itemId() == itemId){
                return itemDTO;
            }
        }
        return null;
    }

}
