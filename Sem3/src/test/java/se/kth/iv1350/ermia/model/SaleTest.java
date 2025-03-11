package se.kth.iv1350.ermia.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale sale;
    private Item validItem;
    private Item sameItemDifferentQuantity;
    private Item nullItem;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        validItem = new Item(new ItemDTO(100, 0.25, "Apple", "AppleTest", 10), 2);
        sameItemDifferentQuantity = new Item(new ItemDTO(100, 0.25, "Apple", "AppleTest", 10), 4);
        nullItem = null;
    }

    @AfterEach
    void tearDown() {
        sale = null;
    }

    @Test
    void testAddNewItem() {
        SaleDTO saleDTO = sale.addItem(validItem);
        assertEquals(1, saleDTO.itemList().size(),
                "Sale should contain one item after adding a new item.");
    }

    @Test
    void addExistingItem() {
        sale.addItem(validItem);
        SaleDTO saleDTO = sale.addItem(sameItemDifferentQuantity);
        assertEquals(1, saleDTO.itemList().size(),
                "Sale should contain one item after adding an existing item with a different quantity.");
        Item itemInSale = saleDTO.itemList().get(0);
        int totalQuantity = validItem.getItemQuantity() + sameItemDifferentQuantity.getItemQuantity();
        assertEquals(totalQuantity, itemInSale.getItemQuantity(),
                "Item quantity should be updated to 5.");
    }

    @Test
    void increaseQuantityTotalPrice() {
        sale.addItem(validItem);
        sale.addItem(sameItemDifferentQuantity);
        int totalQuantity = validItem.getItemQuantity() + sameItemDifferentQuantity.getItemQuantity();
        double expectedTotalPrice = validItem.getItemDTO().price() * totalQuantity;
        assertEquals(expectedTotalPrice, sale.getTotalPrice(),
                "Total price should be updated correctly after increasing item quantity.");
    }
}