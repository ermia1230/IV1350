/**
 * The class <code>Controller</code> is managing all the interaction between <code>View</code>
 * and all other parts of application with each other, parts such as, view, model and integration layers.
 */
package se.kth.iv1350.ermia.controller;

import se.kth.iv1350.ermia.integration.Printer;
import se.kth.iv1350.ermia.integration.Register;
import se.kth.iv1350.ermia.integration.system.ExternalAccountingSystem;
import se.kth.iv1350.ermia.integration.system.ExternalInventory;
import se.kth.iv1350.ermia.integration.ExternalSystemCreator;
import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.Sale;
import se.kth.iv1350.ermia.model.dto.ItemDTO;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

public class Controller {
    private ExternalInventory inventory;
    private ExternalAccountingSystem accountingSystem;
    private Printer printer;
    private Register register;
    private Sale currentSale;

    /**
     * The constructor of class <code>Controller</code>
     * This constructor sets the initial values of inventory, accountingSystem, printer and the register
     * @param externalCreator of type <code>ExternalSystemCreator </code>  is the creator used to
     * get references to the external systems in the system package.
     */
    public Controller(ExternalSystemCreator externalCreator){
        this.inventory = externalCreator.getInventory();
        this.accountingSystem = externalCreator.getAccountingSystem();
        this.printer = new Printer();
        this.register = new Register();
    }

    /**
     * This method starts a new sale.
     */
    public void startSale(){
        this.currentSale = new Sale();
    }

    /**
     * This method adds a new item to the current <code>Sale</code>
     * @param itemId is the identifier of the item to be added
     * @param quantity The quantity of the item to add
     * @return A <code>SaleDTO</code> which has all the info about the list of items, totalPrice, and total VAT amount.
     */
    public SaleDTO addItem(int itemId, int quantity) {
        ItemDTO itemDTO = inventory.fetchItem(itemId);
        Item item = new Item(itemDTO, quantity);
        SaleDTO saleDTO = currentSale.addItem(item);
        return saleDTO;
    }
}
