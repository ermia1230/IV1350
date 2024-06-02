package se.kth.iv1350.ermia;

public class Main {
    public static void main(String[] args) {
        Amount transfered = new Amount(10);
        Account fromAccount = new Account();
        Account toAccount = new Account(10);
        try {
            toAccount.deposite(transfered.getAmount());
            fromAccount.withdraw(transfered.getAmount());
        } catch (RejectedException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(toAccount.getBalance());
        System.out.println("Donkey");
    }
}
