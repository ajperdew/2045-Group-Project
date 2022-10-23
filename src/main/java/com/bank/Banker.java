package com.bank;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Banker {
    public static final String SAVINGS = "Savings";
    public static final String CD = "CertificateOfDeposit";
    public static final String CHECKING = "Checking";
    private static ArrayList<Account> allAccounts = new ArrayList<>();

    /**
     * Asks user to input account information, computes and displays the account's balance
     *
     * @param args required in Java; ignored in all cases for this program
     */
    public static void main(String[] args) {
        promptUser();
        displayOutput();
    }

    private static void promptUser() {

        int goAgain = JOptionPane.NO_OPTION;
        do {
            String[] availableAccounts = {SAVINGS, CD, CHECKING};
            Object accountType = JOptionPane.showInputDialog(null, "Choose an account to create",
                    "Choose an Account", JOptionPane.QUESTION_MESSAGE, null, availableAccounts, SAVINGS);

            try {
                Account account = createAccount(accountType);

                String strAccountNumber = JOptionPane.showInputDialog("Enter account number");
                int accountNumber = Integer.parseInt(strAccountNumber);
                account.setAccountNumber(accountNumber);

                String strBalance = JOptionPane.showInputDialog("Enter account balance");
                double balance = Double.parseDouble(strBalance);
                account.setBalance(balance);

                String strInterest = JOptionPane.showInputDialog("Enter interest rate");
                int interest = Integer.parseInt(strInterest);
                account.setInterest(interest);

                String strPeriods = JOptionPane.showInputDialog("Enter number of periods");
                int periods = Integer.parseInt(strPeriods);
                account.setPeriods(periods);

                if (accountType.toString().equals(CD)) {
                    CertificateOfDeposit cdAccount = (CertificateOfDeposit) account;
                    String strMaturity = JOptionPane.showInputDialog("Enter length to maturity");
                    int maturity = Integer.parseInt(strMaturity);
                    cdAccount.setMaturity(maturity);
                }

                allAccounts.add(account);
            } catch (Exception exception){
                System.out.println("Oops");
            }

            goAgain = JOptionPane.showConfirmDialog(null, "Do you want to enter another account?",
                    "Go Again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } while (goAgain == JOptionPane.YES_OPTION);
    }

    private static void displayOutput() {
        for (Account account : allAccounts) {
            account.compute();
            JOptionPane.showMessageDialog(null, "Ending balance after " + account.getPeriods() +
                    " period(s): \nAccount type: " + account.getClass().getSimpleName() + "\nAccount number: " +
                    account.getAccountNumber() + "\nEnding balance: $" + String.format("%.2f", account.getBalance()));
        }
    }


    /**
     * Simple factory method to create and return a subclass of type Account.
     *
     * @param selectedAccount A string representing the account we want to create.
     * @return the created account.
     */
    public static Account createAccount(final Object selectedAccount) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return Class.forName("com.bank." + selectedAccount.toString()).asSubclass(Account.class).getDeclaredConstructor().newInstance();
    }
}
