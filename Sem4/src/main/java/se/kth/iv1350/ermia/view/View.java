/**
 * The <code>View</code> class is the hard-coded part of the user interface.
 * It interacts with the controller to perform operations and displays the results at the end.
 */
package se.kth.iv1350.ermia.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import se.kth.iv1350.ermia.controller.Controller;
import se.kth.iv1350.ermia.integration.exception.DatabaseConException;
import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.dto.SaleDTO;
import se.kth.iv1350.ermia.model.exception.ItemNotFoundException;

public class View {
    private Controller cntr;
    /**
     * The constructor which creates a new instance of <code>View</code>.
     * @param cntr The controller that is used for handling user interactions.
     */
    public View(Controller cntr){
        this.cntr = cntr;
    }
    /**
     * Runs a sample scenario of the application
     * It adds items to a sale and displaying the sale details and then handles the payment and ending the sale.
     */
    public void runSample(){
        runMultipleSales();
    }
   
    private void runMultipleSales() {
        System.out.println("\n=================== SALE 1 ===================");
        runFirstSale();
        
        System.out.println("\n\n=================== SALE 2 ===================");
        runSecondSale();
    }
    

    private void runFirstSale() {
        Random random = new Random();
        cntr.startSale();
        System.out.println("\n--------------------Start Of Sale-----------------------");
        int[] itemIds = {999, 101, 100, 234};
        for (int itemId : itemIds) {
            int randomQuantity = random.nextInt(3) + 1;
            addItemToSale(itemId, randomQuantity);
        }
        System.out.println("\n--------------------End of Scanning-----------------------");
        System.out.println("The total amount to be paid is " + getTheTotal() + "kr");
        System.out.println("Cashier receives 150 kr from the customer\n");
        double returnedChange = cntr.handlePay(150);
        System.out.println("Returned change: " + roundToTwoDecimals(returnedChange) + " SEK");
        cntr.endSale();
    }
    
    private void runSecondSale() {
        cntr.startSale();
        System.out.println("\n--------------------Start Of Sale-----------------------");
       
        int[][] itemsWithQuantities = {
            {100, 2}, 
            {101, 1}, 
            {555, 3}  
        };
        
        for (int[] item : itemsWithQuantities) {
            addItemToSale(item[0], item[1]);
        }
        
        System.out.println("\n--------------------End of Scanning-----------------------");
        System.out.println("The total amount to be paid is " + getTheTotal() + "kr");
        System.out.println("Cashier receives 200 kr from the customer\n");
        double returnedChange = cntr.handlePay(200);
        System.out.println("Returned change: " + roundToTwoDecimals(returnedChange) + " SEK");
        cntr.endSale();
    }

    private double getTheTotal(){
        return roundToTwoDecimals(cntr.getCurrentSale().getTotalPrice());
    }

    private void addItemToSale(int itemId, int quantity) {
        System.out.printf("\nAdding item (ID: %d, Quantity: %d)...\n", itemId, quantity);
        try {
            SaleDTO saleInfo = cntr.addItem(itemId, quantity);
            displaySaleInfo(saleInfo, itemId, quantity);
        } catch (ItemNotFoundException exception) {
            System.out.println("ERROR: The item with ID " + exception.getItemId() + " does not exist.");
            System.out.println("Please check the item ID and try again.");
        } catch (DatabaseConException exception) {
            System.out.println("ERROR: The system is experiencing technical difficulties.");
            System.out.println("Please try again later");
        }
    }

    private void displaySaleInfo(SaleDTO saleInfo, int itemId, int quantity) {
        System.out.println("Item added successfully.");
        System.out.printf("Running total: $%.2f (VAT: $%.2f)\n", 
                         saleInfo.totalPrice(), saleInfo.totalVATAmount());
        System.out.printf("Items in cart: %d\n", saleInfo.itemList().size());
    }

    private void printItemDetails(SaleDTO saleDTO, int itemId, int addedQuantity) {
        Item item = findItemInSale(saleDTO, itemId);
        if (item != null) {
            System.out.println("Item ID: " + item.getItemDTO().itemId());
            System.out.println("Item name: " + item.getItemDTO().name());
            System.out.println("Item cost: " + item.getItemDTO().price() + " SEK");
            System.out.println("VAT: " + (item.getItemDTO().vatRate() * 100) + "%");
            System.out.println("Item description: " + item.getItemDTO().description());
            System.out.println("Added quantity: " + addedQuantity);
        }
    }

    private void printTotalDetails(SaleDTO saleDTO) {
        System.out.println("\nTotal cost (incl VAT): " + roundToTwoDecimals(saleDTO.totalPrice()) + " SEK");
        System.out.println("Total VAT: " + roundToTwoDecimals(saleDTO.totalVATAmount()) + " SEK");
    }

    private Item findItemInSale(SaleDTO saleDTO, int itemId) {
        for (Item item : saleDTO.itemList()) {
            if (item.getItemDTO().itemId() == itemId) {
                return item;
            }
        }
        return null;
    }
    private double roundToTwoDecimals(double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
