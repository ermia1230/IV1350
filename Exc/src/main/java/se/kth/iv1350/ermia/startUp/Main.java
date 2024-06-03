package se.kth.iv1350.ermia.startUp;

import se.kth.iv1350.ermia.controller.Controller;
import se.kth.iv1350.ermia.integration.ExternalSystemCreator;
import se.kth.iv1350.ermia.view.View;

public class Main {
    public static void main(String [] args){
        ExternalSystemCreator externalCreator = new ExternalSystemCreator();
        Controller contr = new Controller(externalCreator);
        View view = new View(contr);
        view.runSample();
        
    }
}
