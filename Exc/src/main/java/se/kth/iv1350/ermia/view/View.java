/**
 * The <code>View</code> class is the hard-coded part of the user interface.
 * It interacts with the controller to perform operations and displays the results at the end.
 */
package se.kth.iv1350.ermia.view;

import se.kth.iv1350.ermia.controller.Controller;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.Random;

public class View {
    private Controller cntr;
    /**
     * The constructor which creates a new instance of <code>View</code>.
     *
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
        SaleDTO saleDTO = cntr.addItem(101, 3);
        saleDTO = cntr.addItem(101, 4);
        saleDTO = cntr.addItem(102, 2);
        System.out.println(saleDTO);
    }
}
