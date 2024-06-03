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
        for(int i = 100; i < 101; i++){
            int randomQuantity = random.nextInt(5);
            SaleDTO saleDTO = cntr.addItem(i, randomQuantity);
        }
        SaleDTO saleDTO = cntr.addItem(101, 20);
        




    }
}
