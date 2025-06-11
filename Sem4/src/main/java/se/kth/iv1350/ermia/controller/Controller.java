/**
 * The class <code>Controller</code> is managing all the interaction between <code>View</code>
 * and all other parts of application with each other, parts such as, model and integration layers.
 */
package se.kth.iv1350.ermia.controller;

import se.kth.iv1350.ermia.integration.ExternalSystemCreator;
import se.kth.iv1350.ermia.integration.Printer;
import se.kth.iv1350.ermia.integration.Register;
import se.kth.iv1350.ermia.integration.exception.DatabaseConException;
import se.kth.iv1350.ermia.integration.system.ExternalAccountingSystem;
import se.kth.iv1350.ermia.integration.system.ExternalInventory;
import se.kth.iv1350.ermia.model.Payment;
import se.kth.iv1350.ermia.model.Receipt;
import se.kth.iv1350.ermia.model.Sale;
import se.kth.iv1350.ermia.model.SaleLog;
import se.kth.iv1350.ermia.model.dto.ReceiptDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;
import se.kth.iv1350.ermia.model.exception.ItemNotFoundException;
import se.kth.iv1350.ermia.util.FileLogger;
import se.kth.iv1350.ermia.util.Logger;

public class Controller {
    private ExternalInventory inventory;
    private ExternalAccountingSystem accountingSystem;
    private Printer printer;
    private Register register;
    private Sale currentSale;
    private SaleLog saleLog;
    private Logger logger = new FileLogger();

    /**
     * The constructor of class <code>Controller</code>
     * This constructor sets the initial values of inventory, accountingSystem, printer, register and the SaleLog
     *
     * @param externalCreator of type <code>ExternalSystemCreator </code> is the creator used to
     * get references to the external systems in the system package.
     */
    public Controller(ExternalSystemCreator externalCreator){
        this.inventory = externalCreator.getInventory();
        this.accountingSystem = externalCreator.getAccountingSystem();
        this.printer = new Printer();
        this.register = new Register();
        this.saleLog = new SaleLog();
    }

    /**
     * This method starts a new sale.
     */
    public void startSale(){
        this.currentSale = new Sale(this.inventory);
    }

   /**
   * Attempts to add an item to the current sale using its ID and quantity. 
   * Retrieves item details from the inventory system and updates the sale accordingly.
   * Logs and rethrows any exceptions encountered during the process.
   *
   * @param itemId The unique ID of the item to be added
   * @param quantity The number of units to add
   * @return A SaleDTO containing the updated sale details
   * @throws ItemNotFoundException If the item ID doesn't exist in the inventory
   * @throws DatabaseConException If a database connection issue occurs
   */

    public SaleDTO addItem(int itemId, int quantity) throws ItemNotFoundException, DatabaseConException {
        try {
            return currentSale.addItemById(itemId, quantity);
        } catch (ItemNotFoundException exception) {
            logger.log("Failed to find item in inventory", exception);
            throw exception; 
        } catch (DatabaseConException exception) {
            logger.log("Database connection failed", exception);
            throw exception;
        }
    }

    /**
     * This methods takes care of the payment. It starts off with creating a <code>Payment</code> with the paid
     * amount by customer. It then increases the register by paid amount and update all the inventory system. The
     * method handles the printing of receipt and calculating the changed amount.
     * @param paidAmount is the amount that customer pays
     * @return A <code>double</code> which is the changed amount which should be given back to the customer
     */
    public double handlePay(double paidAmount){
        Payment payment = new Payment(paidAmount);
        register.registerPayment(paidAmount);
        updatingExternalSystem();
        double changedAmount = payment.calTheChangedAmount(currentSale.getTotalPrice());
        handlePrintOfReceipt(paidAmount, changedAmount);
        register.registerWithdrawal(changedAmount);
        return changedAmount;
    }

    /**
     * This method takes care of ending the entire sale. It will first save the in the <code>SaleLog</code>
     * and then it makes the currentSale equal to <code>null</code>
     */
    public void endSale(){
        saleLog.addSale(currentSale.getSaleInfo());
        this.currentSale = null;
    }

    /**
     * This methods is a getter for the currentSale.
     * @return A <code>Sale</code> which is the currentSale will be returned.
     */
    public Sale getCurrentSale(){
        return this.currentSale;
    }
    private void handlePrintOfReceipt(double paidAmount, double changedAmount) {
        SaleDTO saleDTO = generateSaleDTOForCurrentSale();
        ReceiptDTO receiptDTO = new ReceiptDTO(saleDTO, paidAmount, changedAmount);
        Receipt receipt = new Receipt(receiptDTO);
        printer.printingTheReceipt(receipt);
    }
    private void updatingExternalSystem(){
        inventory.updateInventory(currentSale.getSaleInfo().itemList());
        accountingSystem.updateAccountingSystem(currentSale.getSaleInfo());
    }
    private SaleDTO generateSaleDTOForCurrentSale(){
        return currentSale.getSaleInfo();
    }

}
