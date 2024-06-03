package se.kth.iv1350.ermia.model;

public class Payment{
    private double amountPaidByCustomer;
    public Payment(double amountPaidByCustomer){
        this.amountPaidByCustomer = amountPaidByCustomer;
    }
    public double calTheChangedAmount(double totalCostOfSale){
        return this.amountPaidByCustomer - totalCostOfSale;
    }
}
