package se.kth.iv1350.ermia.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleLogTest {
    private SaleLog saleLog;

    @BeforeEach
    void setUp() {
        saleLog = new SaleLog();
    }
    @AfterEach
    void tearDown() {
        saleLog = null;
    }

    @Test
    void testAddSale() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(new ItemDTO(100, 0.25, "Milk",
                "A carton of milk weighing 1.5 kg", 15), 2));
        SaleDTO saleDTO = new SaleDTO(itemList, 30.00, 7.50);
        saleLog.addSale(saleDTO);
        List<SaleDTO> sales = saleLog.getSales();
        assertEquals(saleDTO, sales.get(0), "The sale added should be the same as the sale in the log");
    }
}