package com.bank;
/**
 * A specific type of Account with an additional maturity term
 */
public class CertificateOfDeposit extends Account {

    private int maturity;

    public void setMaturity(int maturity) {
        this.maturity = maturity;
    }

    public int getMaturity() {
        return maturity;
    }

    /**
     * Override toString() method to output account information in an informative and readable format including the
     * extra maturity attribute associated with the CertificateOfDeposit subclass
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " account #" + getAccountNumber() + ": Interest Rate = " + getInterest() +
                "%, Periods = " + getPeriods() + ", Maturity = " + getMaturity() + ", Balance = $" + String.format("%.2f", getBalance());
    }
}
