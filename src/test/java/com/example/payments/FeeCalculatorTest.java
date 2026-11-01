package com.example.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FeeCalculatorTest {
    private final FeeCalculator calc = new FeeCalculator();

    @Test
    void domesticCardFee() {
        var p = new CardPayment("c", Money.of("1000", "INR"), "4111111111111111", false);
        assertEquals(Money.of("18.00", "INR"), calc.fee(p));
        assertEquals("CARD", calc.channel(p));
    }

    @Test
    void upiIsFree() {
        var p = new UpiPayment("u", Money.of("1000", "INR"), "a@upi");
        assertEquals(Money.of("0", "INR"), calc.fee(p));
    }

    @Test
    void walletChannelIncludesWalletName() {
        var p = new WalletPayment("w", Money.of("10", "INR"), WalletPayment.Wallet.PAYTM);
        assertEquals("WALLET-PAYTM", calc.channel(p));
    }
}
