package se.kth.iv1350.ermia.integration.system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalInventoryTest {
    private ExternalInventory externalInventory;

    @BeforeEach
    void setUp() {
        externalInventory = new ExternalInventory();
    }
    @AfterEach
    void tearDown(){
        externalInventory = null;
    }

    @Test
    void fetchItemFound() {
        int itemId = 100;
        ItemDTO itemDTO = externalInventory.fetchItem(itemId);
        assertNotNull(itemDTO, "Item should be found in the inventory");
        int expectedItemId = itemDTO.itemId();
        assertEquals(itemId, expectedItemId, "Fetched item ID should match the requested ID");
    }

    @Test
    void fetchItemNotFound() {
        int nonExistentItemId = 999;
        ItemDTO itemDTO = externalInventory.fetchItem(nonExistentItemId);
        assertNull(itemDTO, "Item should not be found in the inventory");
    }

    @Test
    void updateInventory() {
        List<Item> itemsInSale = new ArrayList<>();
        int quantity = 2;
        itemsInSale.add(new Item(new ItemDTO(100, 0.25, "Milk",
                "A carton of milk weighing 1.5 kg", 15), quantity));
        externalInventory.updateInventory(itemsInSale);
        ItemDTO milkDTO = externalInventory.fetchItem(100);
        int expectedMilkQuantity = getItemQuantity(milkDTO) - quantity;
        assertEquals(expectedMilkQuantity, expectedMilkQuantity,
                "Quantity of Milk should be updated correctly");
    }
    private int getItemQuantity(ItemDTO itemDTO) {
        for (Item item : externalInventory.getInventory()) {
            if (item.getItemDTO().itemId() == itemDTO.itemId()) {
                return item.getItemQuantity();
            }
        }
        return -1;
    }
}