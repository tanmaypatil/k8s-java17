package com.example.payments;

public record UpiPayment(String id, Money amount, String vpa) implements Payment {
}
