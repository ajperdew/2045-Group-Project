package com.bank;

/**
 * A specific type of Account with a $5 fee every period
 */
public class Checking extends Account {

    public static final int FEE = 5;

    public void compute() {
        for (int i = 0; i < periods; i++) {
            balance += (balance * (interest/100.0)) - FEE;
        }
    }
}