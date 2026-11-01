package com.example.payments;

public record CardPayment(String id, Money amount, String cardNumber, boolean international)
        implements Payment {

    public String maskedCard() {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
