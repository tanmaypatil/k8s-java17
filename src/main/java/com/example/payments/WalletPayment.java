package com.example.payments;

public record WalletPayment(String id, Money amount, Wallet wallet) implements Payment {

    public enum Wallet { PAYTM, PHONEPE, AMAZON_PAY }
}
