package com.example.payments;

import java.util.List;

public class App {
    public static void main(String[] args) {
        var limit = Money.of("50000", "INR");
        List<Payment> payments = List.of(
                new CardPayment("p1", Money.of("1200.00", "INR"), "4111111111111111", false),
                new CardPayment("p2", Money.of("75000.00", "INR"), "5500000000000004", true),
                new UpiPayment("p3", Money.of("499.00", "INR"), "tanmay@upi"),
                new WalletPayment("p4", Money.of("250.00", "INR"), WalletPayment.Wallet.PHONEPE));

        System.out.print(new SettlementReport().render(payments, limit));
        System.out.println("Running on Java " + Runtime.version());
    }
}
