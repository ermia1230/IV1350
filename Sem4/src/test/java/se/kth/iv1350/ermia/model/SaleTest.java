package se.kth.iv1350.ermia.model;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.ermia.integration.exception.DatabaseConException;
import se.kth.iv1350.ermia.integration.system.ExternalInventory;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;
import se.kth.iv1350.ermia.model.exception.ItemNotFoundException;

class SaleTest {
    private Sale sale;
    private Item validItem;
    private Item sameItemDifferentQuantity;
    private Item nullItem;
    private ExternalInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new ExternalInventory();
        sale = new Sale(inventory);
        validItem = new Item(new ItemDTO(100, 0.25, "Apple", "AppleTest", 10), 2);
        sameItemDifferentQuantity = new Item(new ItemDTO(100, 0.25, "Apple", "AppleTest", 10), 4);
        nullItem = null;
    }

    @AfterEach
    void tearDown() {
        sale = null;
        inventory = null;
    }

    @Test
    void testAddNewItem() throws ItemNotFoundException, DatabaseConException {
        int itemId = validItem.getItemDTO().itemId();
        int quantity = validItem.getItemQuantity();
        SaleDTO saleDTO = sale.addItemById(itemId, quantity);
        assertEquals(1, saleDTO.itemList().size(),
                "Sale should contain one item after adding a new item.");
    }

    @Test
    void addExistingItem() throws ItemNotFoundException, DatabaseConException {
        int itemId = validItem.getItemDTO().itemId();
        int quantity1 = validItem.getItemQuantity();
        int quantity2 = sameItemDifferentQuantity.getItemQuantity();
        
        sale.addItemById(itemId, quantity1);
        SaleDTO saleDTO = sale.addItemById(itemId, quantity2);
        
        assertEquals(1, saleDTO.itemList().size(),
                "Sale should contain one item after adding an existing item with a different quantity.");
        Item itemInSale = saleDTO.itemList().get(0);
        int totalQuantity = quantity1 + quantity2;
        assertEquals(totalQuantity, itemInSale.getItemQuantity(),
                "Item quantity should be updated to the total of both quantities.");
    }

    @Test
    void increaseQuantityTotalPrice() throws ItemNotFoundException, DatabaseConException {
        int itemId = validItem.getItemDTO().itemId();
        int quantity1 = validItem.getItemQuantity();
        int quantity2 = sameItemDifferentQuantity.getItemQuantity();
        
        sale.addItemById(itemId, quantity1);
        sale.addItemById(itemId, quantity2);
        
        int totalQuantity = quantity1 + quantity2;
        Item itemInSale = sale.getSaleInfo().itemList().get(0);
        double itemPrice = itemInSale.getItemDTO().price();
        double expectedTotalPrice = itemPrice * totalQuantity;
        assertEquals(expectedTotalPrice, sale.getTotalPrice(), 0.001,
                "Total price should be updated correctly after increasing item quantity.");
    }

    @Test
    void testAddItemByIdValidItem() throws ItemNotFoundException, DatabaseConException {
        SaleDTO saleDTO = sale.addItemById(100, 2);
        assertEquals(1, saleDTO.itemList().size(),
                "Sale should contain one item after adding a valid item.");
    }

    @Test
    void testAddItemByIdItemNotFound() {
        int nonExistentItemId = 123;
        try {
            sale.addItemById(nonExistentItemId, 1);
            fail("Expected ItemNotFoundException was not thrown");
        } catch (ItemNotFoundException exception) {
            String expectedMessage = "Could not find item with ID: " + nonExistentItemId;
            assertEquals(expectedMessage, exception.getMessage(), 
                    "Exception message should match expected format");
            assertTrue(exception.getMessage().contains(String.valueOf(nonExistentItemId)),
                    "Exception message should contain the non-existent item ID");
            assertEquals(nonExistentItemId, exception.getItemId(),
                    "Exception should contain the correct item ID");
        } catch (Exception e) {
            fail("Wrong exception type thrown: " + e.getClass().getName());
        }
    }

    @Test
    void testDatabaseConnectionError() {
        try {
            sale.addItemById(999, 1);
            fail("Expected DatabaseConException was not thrown");
        } catch (DatabaseConException e) {
            assertEquals("Could not connect to inventory database", e.getMessage());
        } catch (Exception e) {
            fail("Wrong exception type thrown: " + e.getClass().getName());
        }
    }
}