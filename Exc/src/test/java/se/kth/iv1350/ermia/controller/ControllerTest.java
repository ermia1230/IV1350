package se.kth.iv1350.ermia.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.ermia.integration.ExternalSystemCreator;
import se.kth.iv1350.ermia.model.Sale;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import static org.junit.jupiter.api.Assertions.*;
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
    void addItemSaleDTONotNull() {
        controller.startSale();
        int itemId = 100;
        int quantity = 2;
        SaleDTO saleDTO = controller.addItem(itemId, quantity);
        assertNotNull(saleDTO, "SaleDTO should not be null after adding an item");


    }
    @Test
    void addItemListNotEmpty() {
        controller.startSale();
        int itemId = 100;
        int quantity = 2;
        SaleDTO saleDTO = controller.addItem(itemId, quantity);
        boolean isTheListEmpty = !saleDTO.itemList().isEmpty();
        assertTrue(isTheListEmpty, "Item list should contain items after adding");
    }

    @Test
    void addItemWithZeroQuantity() {
        controller.startSale();
        int itemId = 101;
        int quantity = 0;
        SaleDTO saleDTO = controller.addItem(itemId, quantity);
        assertEquals(quantity, saleDTO.itemList().get(0).getItemQuantity(),
                "Quantity should be zero when added item quantity is zero");
    }
    @Test
    void testEndSale() {
        controller.startSale();
        controller.addItem(100, 2);
        controller.handlePay(100);
        controller.endSale();
        Sale sale = controller.getCurrentSale();
        assertNull(sale, "Current sale should be null after ending sale");
    }
    @Test
    void handlePayWithExactAmount() {
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
    public void testHandlePayWithMoreThanTotalPrice() {
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

}