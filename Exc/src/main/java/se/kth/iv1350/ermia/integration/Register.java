package se.kth.iv1350.ermia.integration;

public class Register {
    private double totalAmountInRegister;
    public Register(){
        this.totalAmountInRegister = 0;
    }
    public void registerPayment(double paidAmount){
        this.totalAmountInRegister += paidAmount;
        System.out.println("The amount in register has increased with " + paidAmount + "\n");
    }
    public void registerWithdrawal(double amountToBeReduced){
        this.totalAmountInRegister -= amountToBeReduced;
        System.out.println("The amount in register has decreased with " + amountToBeReduced + "\n");
    }
}
