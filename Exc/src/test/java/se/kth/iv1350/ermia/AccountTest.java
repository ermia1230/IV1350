package se.kth.iv1350.ermia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private static final int INITIAL_AMOUNT = 10;
    private Account account;


    @BeforeEach
    void initTest() {
        account = new Account(INITIAL_AMOUNT);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testWithdraw() {
        int withdrawAmt = 30;
        try{
            account.withdraw(withdrawAmt);
            fail("EXplaining Message");
        }catch (RejectedException re){
            assertTrue(re.getMessage().contains(Integer.toString(INITIAL_AMOUNT )),
                    "No balance in exception message");
            System.err.println(re.getMessage());
        }
    }

    @Test
    void getBalance() {
    }

    @Test
    void deposite() {
        System.out.println("Hej");
    }


}