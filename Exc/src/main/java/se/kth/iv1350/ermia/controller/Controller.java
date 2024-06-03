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


    public Controller(ExternalSystemCreator externalCreator){
        this.inventory = externalCreator.getInventory();
        this.accountingSystem = externalCreator.getAccountingSystem();
        this.printer = new Printer();
        this.register = new Register();
    }
    public void startSale(){
        this.currentSale = new Sale();
    }
    public SaleDTO addItem(int itemId, int quantity) {

        ItemDTO itemDTO = inventory.fetchItem(itemId);
        Item item = new Item(itemDTO, quantity);
        SaleDTO saleDTO = currentSale.addItem(item);
        return saleDTO;
    }
}
