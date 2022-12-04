package com.bank.test;

import com.bank.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTests {

    @Test
    public void computeChecking_validate10Periods5Interest() {
        Checking checking = new Checking();
        checking.setInterest(5);
        checking.setPeriods(10);
        checking.setBalance(10000);
        checking.compute();
        assertEquals(16226.0, checking.getBalance(), 1.0);
    }

    @Test
    public void computeSavings_validate10Periods5Interest() {
        Savings savings = new Savings();
        savings.setInterest(5);
        savings.setPeriods(10);
        savings.setBalance(10000);
        savings.compute();
        assertEquals(16288.0, savings.getBalance(), 1.0);
    }

    @Test
    public void computeCD_validate10Periods5Interest() {
        CertificateOfDeposit certificateOfDeposit = new CertificateOfDeposit();
        certificateOfDeposit.setInterest(5);
        certificateOfDeposit.setPeriods(10);
        certificateOfDeposit.setBalance(10000);
        certificateOfDeposit.compute();
        assertEquals(16288.0, certificateOfDeposit.getBalance(), 1.0);
    }

    @Test
    public void certificateOfDeposit_setAndValidateTerm() {
        CertificateOfDeposit certificateOfDeposit = new CertificateOfDeposit();
        final int MATURITY = 5;
        certificateOfDeposit.setMaturity(5);
        assertEquals(MATURITY, certificateOfDeposit.getMaturity());
    }

    @Test
    public void banker_createNewCheckingAccount() {
        Checking checking = new Checking();
        checking.setAccountNumber(123);
        checking.setBalance(20000);
        checking.setInterest(10);
        checking.setPeriods(5);
        assertEquals(123, checking.getAccountNumber());
    }

    @Test
    public void banker_createNewSavingsAccount() {
        Savings savings = new Savings();
        savings.setAccountNumber(456);
        savings.setBalance(20000);
        savings.setInterest(10);
        savings.setPeriods(5);
        assertEquals(456, savings.getAccountNumber());
    }

    @Test
    public void balance_IsNotNegative() {
        Account account = new Account();
        account.getBalance();
        assertTrue(account.getBalance() >= 0);
    }
}
