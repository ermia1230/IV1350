package se.kth.iv1350.ermia.controller;

import se.kth.iv1350.ermia.integration.Printer;
import se.kth.iv1350.ermia.integration.Register;
import se.kth.iv1350.ermia.integration.system.ExternalAccountingSystem;
import se.kth.iv1350.ermia.integration.system.ExternalInventory;
import se.kth.iv1350.ermia.integration.ExternalSystemCreator;

public class Controller {
    private ExternalInventory inventory;
    private ExternalAccountingSystem accountingSystem;
    private Printer printer;
    private Register register;

    public Controller(ExternalSystemCreator externalCreator){
        this.inventory = externalCreator.getInventory();
        this.accountingSystem = externalCreator.getAccountingSystem();
        this.printer = new Printer();
        this.register = new Register();
    }
}
