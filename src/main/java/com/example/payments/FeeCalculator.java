package com.example.payments;

import java.math.BigDecimal;

/** Uses pattern matching for instanceof (Java 16+) and switch expressions (Java 14+). */
public class FeeCalculator {

    public Money fee(Payment payment) {
        BigDecimal rate;
        if (payment instanceof CardPayment card) {
            rate = card.international() ? new BigDecimal("0.035") : new BigDecimal("0.018");
        } else if (payment instanceof WalletPayment wallet) {
            rate = switch (wallet.wallet()) {
                case PAYTM, PHONEPE -> new BigDecimal("0.010");
                case AMAZON_PAY -> new BigDecimal("0.012");
            };
        } else {
            rate = BigDecimal.ZERO; // UPI is free
        }
        return payment.amount().times(rate);
    }

    public String channel(Payment payment) {
        var simpleName = payment.getClass().getSimpleName();
        return switch (simpleName) {
            case "CardPayment" -> "CARD";
            case "UpiPayment" -> "UPI";
            case "WalletPayment" -> {
                var w = ((WalletPayment) payment).wallet();
                yield "WALLET-" + w;
            }
            default -> throw new IllegalStateException("unknown payment " + simpleName);
        };
    }
}
