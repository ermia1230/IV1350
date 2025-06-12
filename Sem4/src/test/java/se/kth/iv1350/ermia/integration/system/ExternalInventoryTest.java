package se.kth.iv1350.ermia.integration.system;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.ermia.integration.exception.DatabaseConException;
import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.dto.ItemDTO;

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
    void fetchItemFound() throws DatabaseConException {
        int itemId = 100;
        ItemDTO itemDTO = externalInventory.fetchItem(itemId);
        assertNotNull(itemDTO, "Item should be found in the inventory");
        int expectedItemId = itemDTO.itemId();
        assertEquals(itemId, expectedItemId, "Fetched item ID should match the requested ID");
    }

    @Test
    void fetchItemNotFound() throws DatabaseConException {
        int nonExistentItemId = 123; 
        ItemDTO itemDTO = externalInventory.fetchItem(nonExistentItemId);
        assertNull(itemDTO, "Item should not be found in the inventory");
    }

    @Test
    void updateInventory() throws DatabaseConException {
        List<Item> itemsInSale = new ArrayList<>();
        int quantity = 2;
        itemsInSale.add(new Item(new ItemDTO(100, 0.25, "Milk",
                "A carton of milk weighing 1.5 kg", 15), quantity));
                
        int initialQuantity = getItemQuantity(externalInventory.fetchItem(100));
        
        externalInventory.updateInventory(itemsInSale);
        
        int actualQuantity = getItemQuantity(externalInventory.fetchItem(100));
        int expectedQuantity = initialQuantity - quantity;
        
        assertEquals(expectedQuantity, actualQuantity,
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

    @Test
    void fetchItemDatabaseConnectionError() {
        int errorTriggeringId = 999;
        
        try {
            externalInventory.fetchItem(errorTriggeringId);
            fail("Expected DatabaseConException was not thrown");
        } catch (DatabaseConException exception) {
            String expectedMessage = "Could not connect to inventory database";
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage, actualMessage,
                    "Exception message should match expected format");
        } catch (Exception e) {
            fail("Wrong exception type thrown: " + e.getClass().getName());
        }
    }

    @Test
    void fetchItemSuccessfulNoException() {
        int validItemId = 100;

        try {
            ItemDTO result = externalInventory.fetchItem(validItemId);
            assertNotNull(result, "Should return a valid ItemDTO for existing item");
        } catch (Exception e) {
            fail("No exception should be thrown for valid item ID, but got: " + e.getMessage());
        }
    }
}