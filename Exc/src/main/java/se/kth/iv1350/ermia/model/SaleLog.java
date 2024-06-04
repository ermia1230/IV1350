package se.kth.iv1350.ermia.model;

import se.kth.iv1350.ermia.model.dto.SaleDTO;

import java.util.ArrayList;
import java.util.List;

public class SaleLog {
    private List<SaleDTO> sales;
    public SaleLog(){
        this.sales = new ArrayList<>();
    }
    public void addSale(SaleDTO saleDTO) {
        sales.add(saleDTO);
    }
    public List<SaleDTO> getSales(){
        return this.sales;
    }
}
