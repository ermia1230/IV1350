package se.kth.iv1350.ermia.view;

import se.kth.iv1350.ermia.controller.Controller;
import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.Random;

public class View {
    private Controller cntr;
    public View(Controller cntr){
        this.cntr = cntr;
    }
    public void runSample(){
        Random random = new Random();
        cntr.startSale();
        SaleDTO saleDTO = cntr.addItem(101, 3);
        saleDTO = cntr.addItem(101, 4);
        saleDTO = cntr.addItem(102, 2);
        System.out.println(saleDTO);






    }
}
