package se.kth.iv1350.ermia;

public class Account {
    private int balance;
    public Account(){
        this(0);
    }
    public Account(int balance){
        this.balance = balance;
    }
    public int getBalance(){
        return this.balance;
    }
    public void deposite(int value) throws RejectedException {
        if (value < 0) {
            throw new RejectedException("Deposite Rejected: illegal value: " + value);
        }
        balance += value;
    }

    public void withdraw(int value) throws RejectedException{
        if(value < 0){
            throw new RejectedException("Withdrwal Rejection: Illegal vlaue:" + value);
        }
        if((this.balance - value) < 0){
            throw new RejectedException("Withdrawl Rejection: negative balance: " + (this.balance - value));
        }
        balance -= value;
    }
    public String toString(){
        return "Account has balance" + balance + "kr";
    }
}
