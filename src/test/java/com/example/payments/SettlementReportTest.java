package com.example.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class SettlementReportTest {
    @Test
    void declinesPaymentsOverLimit() {
        List<Payment> payments = List.of(
                new UpiPayment("a", Money.of("10", "INR"), "a@upi"),
                new UpiPayment("b", Money.of("999", "INR"), "b@upi"));
        var report = new SettlementReport();
        var lines = report.lines(payments, Money.of("100", "INR"));
        assertEquals(PaymentStatus.SETTLED, lines.get(0).status());
        assertEquals(PaymentStatus.DECLINED, lines.get(1).status());
        assertTrue(report.render(payments, Money.of("100", "INR")).contains("Declined : 1"));
    }
}
