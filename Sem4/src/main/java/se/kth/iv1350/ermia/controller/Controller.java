/**
 * The class <code>Controller</code> is managing all the interaction between <code>View</code>
 * and all other parts of application with each other, parts such as, model and integration layers.
 */
package se.kth.iv1350.ermia.controller;

import se.kth.iv1350.ermia.integration.Printer;
import se.kth.iv1350.ermia.integration.Register;
import se.kth.iv1350.ermia.integration.system.ExternalAccountingSystem;
import se.kth.iv1350.ermia.integration.system.ExternalInventory;
import se.kth.iv1350.ermia.integration.ExternalSystemCreator;
import se.kth.iv1350.ermia.model.*;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import se.kth.iv1350.ermia.model.dto.ReceiptDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

public class Controller {
    private ExternalInventory inventory;
    private ExternalAccountingSystem accountingSystem;
    private Printer printer;
    private Register register;
    private Sale currentSale;
    private SaleLog saleLog;

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
        this.currentSale = new Sale();
    }

    /**
     * This method adds a new item to the current <code>Sale</code>
     *
     * @param itemId is the identifier of the item to be added
     * @param quantity The quantity of the item to add
     *
     * @return A <code>SaleDTO</code> which has all the info about the list of items, totalPrice, and total VAT amount.
     */
    public SaleDTO addItem(int itemId, int quantity) {
        ItemDTO itemDTO = inventory.fetchItem(itemId);
        Item item = new Item(itemDTO, quantity);
        SaleDTO saleDTO = currentSale.addItem(item);
        return saleDTO;
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
        saleLog.addSale(new SaleDTO(currentSale.getItemList(), currentSale.getTotalPrice(),
                currentSale.getTotalVATAmount()));
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
        inventory.updateInventory(currentSale.getItemList());
        SaleDTO saleDTO = generateSaleDTOForCurrentSale();
        accountingSystem.updateAccountingSystem(saleDTO);
    }
    private SaleDTO generateSaleDTOForCurrentSale(){
        return new SaleDTO(currentSale.getItemList(), currentSale.getTotalPrice(),
                currentSale.getTotalVATAmount());
    }

}
