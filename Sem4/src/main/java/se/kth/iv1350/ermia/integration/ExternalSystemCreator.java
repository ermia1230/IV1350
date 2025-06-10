/**
 * The <code>ExternalSystemCreator</code> class is responsible for creating instances
 * of the external systems used in the application, such as the external inventory and
 * external accounting system. It provides methods two different methods to access these external systems.
 */
package se.kth.iv1350.ermia.integration;

import se.kth.iv1350.ermia.integration.system.ExternalAccountingSystem;
import se.kth.iv1350.ermia.integration.system.ExternalInventory;

public class ExternalSystemCreator {
    private ExternalInventory inventory;
    private ExternalAccountingSystem accountingSystem;
    /**
     * The constructor of  <code>ExternalSystemCreator</code> Creates a new instance of
     * <code>ExternalSystemCreator</code> and initializes
     * the external inventory and accounting system.
     */
    public ExternalSystemCreator(){
        this.inventory = new ExternalInventory();
        this.accountingSystem = new ExternalAccountingSystem();
    }
    /**
     * Returns the instance of <code>ExternalInventory</code>.
     *
     * @return The external inventory system.
     */
    public ExternalInventory getInventory(){
        return this.inventory;

    }
    /**
     * Returns the instance of <code>ExternalAccountingSystem</code>.
     *
     * @return The external accounting system.
     */
    public ExternalAccountingSystem getAccountingSystem(){
        return this.accountingSystem;

    }

}
