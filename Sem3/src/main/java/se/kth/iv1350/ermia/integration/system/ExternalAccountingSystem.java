/**
 * The <code>ExternalAccountingSystem</code> class shows the external accounting system in the
 * integration for the application.
 */
package se.kth.iv1350.ermia.integration.system;

import se.kth.iv1350.ermia.model.Item;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.List;

public class ExternalAccountingSystem {
    public void updateAccountingSystem(List<Item> itemList){
        System.out.println("The Information is sent to the Accounting System.");
    }
}
