package se.kth.iv1350.ermia.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.Receipt;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import se.kth.iv1350.ermia.model.dto.ReceiptDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    private Printer printer;
    private ByteArrayOutputStream inMemPrintOut;
    private PrintStream originalSysOut;
    private Receipt testReceipt;

    @BeforeEach
    void setUp() {
        printer = new Printer();
        inMemPrintOut = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(inMemPrintOut);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);

        // Initialize the test receipt
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(new ItemDTO(100, 0.25, "Milk", "A carton of milk weighing 1.5 kg", 15), 2));

        SaleDTO saleDTO = new SaleDTO(itemList, 30.00, 7.50);
        ReceiptDTO receiptDTO = new ReceiptDTO(saleDTO, 50.00, 20.00);
        testReceipt = new Receipt(receiptDTO);
    }

    @AfterEach
    void tearDown() {
        printer = null;
        inMemPrintOut = null;
        System.setOut(originalSysOut);
    }

    @Test
    void printReceipt() {
        printer.printingTheReceipt(testReceipt);
        String printOut = inMemPrintOut.toString();
        String expectedOutput = "--------------------Receipt-----------------------";
        assertTrue(printOut.contains(expectedOutput), "Printer is not working");
    }

}