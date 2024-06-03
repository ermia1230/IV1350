/**
 * The <code>ExternalInventory</code> class represents the external inventory system
 * in the integration layer of the application. <code>ExternalInventory</code> class is responsible for
 * managing the inventory of items and providing methods to fetch item details.
 */
package se.kth.iv1350.ermia.integration.system;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import java.util.List;
import java.util.ArrayList;
public class ExternalInventory {
    private List<ItemDTO> inventory;
    /**
     * Creates a new instance of <code>ExternalInventory</code> along with initialization of  the inventory
     * with a predefined set of items.
     */
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
     * Fetches an item from the inventory based on the provided id of an item.
     * @param itemId The identifier of the item to be fetched.
     * @return An <code>ItemDTO</code> object containing the item details if found,
     *         or <code>null</code> if the item is not found. Note that in Semester 4,
     *         the <code>null</code> will be replaced with exception handling.
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
