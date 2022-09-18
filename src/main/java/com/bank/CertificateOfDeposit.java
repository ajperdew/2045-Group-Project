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
}
