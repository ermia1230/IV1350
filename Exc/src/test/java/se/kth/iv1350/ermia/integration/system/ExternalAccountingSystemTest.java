package se.kth.iv1350.ermia.integration.system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalAccountingSystemTest {
    private ExternalAccountingSystem accountingSystem;
    private SaleDTO saleDTO;

    @BeforeEach
    void setUp() {
        accountingSystem = new ExternalAccountingSystem();
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(new ItemDTO(100, 0.25, "Test Item", "Test desc", 10), 2));
        saleDTO = new SaleDTO(itemList, 20.0, 2.5);
    }
    @AfterEach
    void tearDown() {
        accountingSystem = null;
    }
    @Test
    void testUpdateAccountingSystem() {
        assertDoesNotThrow(() -> accountingSystem.updateAccountingSystem(saleDTO),
                "The method updateAccountingSystem should not throw any exception");
    }
}