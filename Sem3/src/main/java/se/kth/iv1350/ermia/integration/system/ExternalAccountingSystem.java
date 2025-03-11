/**
 * The <code>ExternalAccountingSystem</code> class shows the external accounting system in the
 * integration for the application.
 */
package se.kth.iv1350.ermia.integration.system;

import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.List;

public class ExternalAccountingSystem {
    /**
     * This method is for updating the accounting system. It will just print that it received the sale info.
     * @param saleInfo A <code>SaleDTO</code> data transfer object that has all the information about the sale.
     */
    public void updateAccountingSystem(SaleDTO saleInfo){
        System.out.println("The Information is sent to the Accounting System.");
    }
}
