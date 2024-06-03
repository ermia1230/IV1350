package se.kth.iv1350.ermia.integration;

public class Register {
    private double totalAmountInRegister;
    public Register(){
        this.totalAmountInRegister = 0;
    }
    public void registerPayment(double paidAmount){
        this.totalAmountInRegister += paidAmount;
    }
}
