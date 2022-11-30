package com.bank;

/**
 * Represents a bank account with attributes balance, interest rate, and periods.
 */
public class Account {

    protected int accountNumber = 0;
    protected double balance = 0.0;
    protected int interest = 0;
    protected int periods = 0;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    /**
     * a) iterate over the number of periods,
     * b) in each iteration, multiply rate times balance and add to the existing balance
     */
    public void compute() {
        for (int i = 0; i < periods; i++) {
            balance += balance * (interest / 100.0);
        }
    }

    /**
     * Override toString() method to output account information in an informative and readable format
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " account #" + getAccountNumber() + ": Interest Rate = " + getInterest() +
                "%, Periods = " + getPeriods() + ", Balance = $" + String.format("%.2f", getBalance());
    }
}
