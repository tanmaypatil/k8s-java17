package com.example.payments;

public enum PaymentStatus {
    SETTLED, DECLINED;

    public static PaymentStatus decide(Payment payment, Money limit) {
        return payment.amount().amount().compareTo(limit.amount()) > 0 ? DECLINED : SETTLED;
    }
}
