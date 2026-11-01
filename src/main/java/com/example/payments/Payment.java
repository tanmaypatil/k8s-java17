package com.example.payments;

/**
 * Java 17 sealed interface: only the listed records can implement it,
 * so the compiler knows every possible payment type.
 */
public sealed interface Payment permits CardPayment, UpiPayment, WalletPayment {
    String id();
    Money amount();
}
