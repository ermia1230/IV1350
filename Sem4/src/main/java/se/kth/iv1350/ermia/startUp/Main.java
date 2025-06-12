/**
 * The <code>Main</code> class is the point of entry to the application.
 * It initializes the external systems, the controller, and the view,
 * and then starts the sample run of the application.
 */
package se.kth.iv1350.ermia.startUp;

import se.kth.iv1350.ermia.controller.Controller;
import se.kth.iv1350.ermia.integration.ExternalSystemCreator;
import se.kth.iv1350.ermia.view.View;

public class Main {
    /**
     * The main method, which starts the entire application.
     * @param args The arguments.
     */
    public static void main(String [] args){
        ExternalSystemCreator externalCreator = new ExternalSystemCreator();
        Controller contr = new Controller(externalCreator);
        
        View view = new View(contr);
        view.runSample();
    }
}
