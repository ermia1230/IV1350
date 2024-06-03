/**
 * The <code>View</code> class is the hard-coded part of the user interface.
 * It interacts with the controller to perform operations and displays the results at the end.
 */
package se.kth.iv1350.ermia.view;

import se.kth.iv1350.ermia.controller.Controller;
import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

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
     * It adds items to a sale and displaying the sale details
     */
    public void runSample(){
        Random random = new Random();
        cntr.startSale();
        int [] itemIds = {102, 101, 100};
        for(int itemId : itemIds){
            int randomQuantity = random.nextInt(3) + 1;
            addItemToSale(itemId, randomQuantity);
        }
        System.out.println("\n--------------------End of Scanning-----------------------");
        System.out.println("The total amount to be paid is " + getTheTotal() + "kr");
        System.out.println("Cashier receives  150 kr form the customer");
        double returnedChanged = cntr.pay(150);
        System.out.println("Returned change: " + roundToTwoDecimals(returnedChanged) + " SEK");
    }
    private double getTheTotal(){
        return roundToTwoDecimals(cntr.getCurrentSale().getTotalPrice());
    }
    private void addItemToSale(int itemId, int quantity) {
        SaleDTO saleDTO = cntr.addItem(itemId, quantity);
        System.out.println("\nAdded " + quantity + " item(s) with item id " + itemId + ":");
        printItemDetails(saleDTO, itemId, quantity);
        printTotalDetails(saleDTO);
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
