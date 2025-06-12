package se.kth.iv1350.ermia.controller;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.ermia.integration.ExternalSystemCreator;
import se.kth.iv1350.ermia.integration.exception.DatabaseConException;
import se.kth.iv1350.ermia.model.Sale;
import se.kth.iv1350.ermia.model.dto.SaleDTO;
import se.kth.iv1350.ermia.model.exception.ItemNotFoundException;

class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        ExternalSystemCreator externalSystemCreator = new ExternalSystemCreator();
        controller = new Controller(externalSystemCreator);
    }

    @AfterEach
    public void tearDown() {
        controller = null;
    }

    @Test
    void startSale() {
        controller.startSale();
        Sale sale = controller.getCurrentSale();
        assertNotNull(sale, "Current sale should be initialized");
    }

    @Test
    void addItemSaleDTONotNull() throws DatabaseConException {
        controller.startSale();
        int itemId = 100;
        int quantity = 2;
        SaleDTO saleDTO = controller.addItem(itemId, quantity);
        assertNotNull(saleDTO, "SaleDTO should not be null after adding an item");
    }

    @Test
    void addItemListNotEmpty() throws DatabaseConException {
        controller.startSale();
        int itemId = 100;
        int quantity = 2;
        SaleDTO saleDTO = controller.addItem(itemId, quantity);
        boolean isTheListEmpty = !saleDTO.itemList().isEmpty();
        assertTrue(isTheListEmpty, "Item list should contain items after adding");
    }

    @Test
    void addItemWithZeroQuantity() throws DatabaseConException {
        controller.startSale();
        int itemId = 101;
        int quantity = 0;
        SaleDTO saleDTO = controller.addItem(itemId, quantity);
        assertEquals(quantity, saleDTO.itemList().get(0).getItemQuantity(),
                "Quantity should be zero when added item quantity is zero");
    }

    @Test
    void testEndSale() throws DatabaseConException {
        controller.startSale();
        controller.addItem(100, 2);
        controller.handlePay(100);
        controller.endSale();
        Sale sale = controller.getCurrentSale();
        assertNull(sale, "Current sale should be null after ending sale");
    }

    @Test
    void handlePayWithExactAmount() throws DatabaseConException {
        controller.startSale();
        int itemId = 100;
        int quantity = 2;
        controller.addItem(itemId, quantity);
        double totalPrice = controller.getCurrentSale().getTotalPrice();
        double paidAmount = totalPrice;
        double changedAmount = controller.handlePay(paidAmount);
        double expectedChangedAmount = paidAmount - totalPrice;
        assertEquals(expectedChangedAmount, changedAmount, "Changed amount should be zero when paid amount is equal to total price");
    }

    @Test
    public void testHandlePayWithMoreThanTotalPrice() throws DatabaseConException {
        controller.startSale();
        int itemId = 100;
        int quantity = 2;
        controller.addItem(itemId, quantity);
        double totalPrice = controller.getCurrentSale().getTotalPrice();
        double paidAmount = totalPrice + 50;
        double expectedChangedAmount = paidAmount - totalPrice;
        double changedAmount = controller.handlePay(paidAmount);
        assertEquals(expectedChangedAmount, changedAmount, "Changed amount should be the difference between paid amount and total price");
    }

    @Test
    void addItemWithNonExistentId() {
        controller.startSale();
        int nonExistentItemId = 555;

        try {
            controller.addItem(nonExistentItemId, 1);
            fail("Expected ItemNotFoundException was not thrown");
        } catch (ItemNotFoundException exception) {
            assertEquals(nonExistentItemId, exception.getItemId(), 
                "Exception should contain the correct item ID");
            assertTrue(exception.getMessage().contains(String.valueOf(nonExistentItemId)),
                "Exception message should contain the item ID");
        } catch (Exception e) {
            fail("Wrong exception type thrown: " + e.getClass().getName());
        }
    }

    @Test
    void addItemWithDatabaseConnectionError() {
        controller.startSale();
        int errorTriggeringId = 999;

        try {
            controller.addItem(errorTriggeringId, 1);
            fail("Expected DatabaseConException was not thrown");
        } catch (DatabaseConException exception) {
            assertEquals("Could not connect to inventory database", exception.getMessage(),
                "Exception should have the correct message");
        } catch (Exception e) {
            fail("Wrong exception type thrown: " + e.getClass().getName());
        }
    }

    @Test
    void addItemValidIdNoExceptions() {
        controller.startSale();
        int validItemId = 100;

        try {
            SaleDTO result = controller.addItem(validItemId, 1);
            assertNotNull(result, "Should return a valid SaleDTO");
            assertFalse(result.itemList().isEmpty(), "The sale should contain the added item");
        } catch (Exception e) {
            fail("No exceptions should be thrown for valid operations, but got: " + e.getMessage());
        }
    }

}