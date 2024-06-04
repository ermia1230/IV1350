package se.kth.iv1350.ermia.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    private Register register;
    @BeforeEach
    void setUp() {
        register = new Register();
    }
    @AfterEach
    void tearDown() {
        register = null;
    }

    @Test
    void initialTotalAmountInRegister() {
        double zeroAmount = 0;
        assertEquals(zeroAmount, register.getTotalAmountInRegister(),
                "Register should have 0 SEK when created");
    }

    @Test
    void registerPaymentPositiveAmount() {
        double amountToBeRegistered = 100;
        register.registerPayment(amountToBeRegistered);
        double amountInRegister = register.getTotalAmountInRegister();
        assertEquals(amountToBeRegistered, amountInRegister,
                "Register should contain 100 after payment of 100.");
    }

    @Test
    void registerPaymentZeroAmount() {
        double amountToBeRegistered = 0;
        register.registerPayment(amountToBeRegistered);
        double amountInRegister = register.getTotalAmountInRegister();
        assertEquals(amountToBeRegistered, amountInRegister,
                "Register should remain 0 after payment of 0.");
    }

    @Test
    void registerPaymentNegativeAmount() {
        register.registerPayment(-50);
        assertEquals(-50, register.getTotalAmountInRegister(),
                "Register should reflect -50 after negative payment of -50.");
    }

    @Test
    void registerPaymentExtremeAmount() {
        double largeAmount = Double.MAX_VALUE;
        register.registerPayment(largeAmount);
        double amountInRegister = register.getTotalAmountInRegister();
        assertEquals(largeAmount, amountInRegister,
                "Register should contain the maximum double value after payment.");
    }

    @Test
    void registerWithdrawalPositiveAmount() {
        double amountToBeRegistered = 200;
        double amountToBeReduced = 50;
        double expectedAmount = amountToBeRegistered - amountToBeReduced;
        register.registerPayment(amountToBeRegistered);
        register.registerWithdrawal(amountToBeReduced);
        double amountInRegister = register.getTotalAmountInRegister();
        assertEquals(expectedAmount, amountInRegister,
                "Register should contain" + expectedAmount +"after withdrawal of"
                        + amountToBeReduced +"from" + amountToBeRegistered);
    }

    @Test
    void registerWithdrawalZeroAmount() {
        double amountToBeRegistered = 200;
        double amountToBeReduced = 0;
        double expectedAmount = amountToBeRegistered - amountToBeReduced;
        register.registerPayment(amountToBeRegistered);
        register.registerWithdrawal(amountToBeReduced);
        double amountInRegister = register.getTotalAmountInRegister();
        assertEquals(expectedAmount, amountInRegister,
                "Register should contain" + expectedAmount +"after withdrawal of"
                        + amountToBeReduced +"from" + amountToBeRegistered);
    }
    @Test
    void registerWithdrawalExtremeAmount() {
        double largeAmount = Double.MAX_VALUE;
        register.registerPayment(largeAmount);
        register.registerWithdrawal(largeAmount);
        double amountInRegister = register.getTotalAmountInRegister();
        assertEquals(0, amountInRegister,
                "Register should be zero after withdrawal of the maximum double value.");
    }
}