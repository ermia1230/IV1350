package se.kth.iv1350.ermia.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void exactPayment() {
        double amount = 100;
        Payment payment = new Payment(amount);
        double change = payment.calTheChangedAmount(amount);
        assertEquals(0.0, change, "Change should be zero for exact payment");
    }

    @Test
    void overPayment() {
        double amountPaidByCustomer = 150;
        Payment payment = new Payment(amountPaidByCustomer);
        double totalPriceOfSale = 100;
        double change = payment.calTheChangedAmount(totalPriceOfSale);
        double expectedChanged = amountPaidByCustomer - totalPriceOfSale;
        assertEquals(expectedChanged, change, "Change should be" +
                " the difference between amount paid and total cost");
    }

    @Test
    void underPayment() {
        double amountPaidByCustomer = 100;
        Payment payment = new Payment(amountPaidByCustomer);
        double totalPriceOfSale = 150;
        double change = payment.calTheChangedAmount(totalPriceOfSale);
        double expectedChanged = amountPaidByCustomer - totalPriceOfSale;
        assertEquals(expectedChanged, change, "Change should be " +
                "negative if amount paid is less than total cost");
    }

}