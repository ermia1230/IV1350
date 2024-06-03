package se.kth.iv1350.ermia.integration;

import se.kth.iv1350.ermia.integration.system.ExternalAccountingSystem;
import se.kth.iv1350.ermia.integration.system.ExternalInventory;

public class ExternalSystemCreator {
    private ExternalInventory inventory;
    private ExternalAccountingSystem accountingSystem;
    public ExternalSystemCreator(){
        this.inventory = new ExternalInventory();
        this.accountingSystem = new ExternalAccountingSystem();
    }
    public ExternalInventory getInventory(){
        return this.inventory;
    }
    public ExternalAccountingSystem getAccountingSystem(){
        return this.accountingSystem;

    }

}
