/**
 * The <code>Register</code> represent the register in application. It has an attribute which is
 * the totalAmountInRegister. It is used to put money and take money from it while getting money and
 * giving money to the customer.
 */
package se.kth.iv1350.ermia.integration;

public class Register {
    private double totalAmountInRegister;

    /**
     * The constructor of class <code>Register</code> will let the totalAmountInRegister be equal to zero
     * when the object is created.
     */
    public Register(){
        this.totalAmountInRegister = 0;
    }

    /**
     * This method adds the paid amount of customer to the register.
     * @param paidAmount is a <code>double</code> which represents the amount paid by the customer.
     */
    public void registerPayment(double paidAmount){
        this.totalAmountInRegister += paidAmount;
        System.out.println("The amount in register has increased with " + paidAmount + "\n");
    }
    /**
     * This method adds reduces the changed amount that should be reduced form the <code>Register</code> and
     * be given to the customer.
     * @param amountToBeReduced is a <code>double</code> which represents the changed amount to pay back the customer.
     */
    public void registerWithdrawal(double amountToBeReduced){
        this.totalAmountInRegister -= amountToBeReduced;
        System.out.println("The amount in register has decreased with " + amountToBeReduced + "\n");
    }

    /**
     * The getter to get the total amount in the register
     * @return A <code>double</code> returned which shows the money in register.
     */
    public double getTotalAmountInRegister(){
        return this.totalAmountInRegister;
    }
}
