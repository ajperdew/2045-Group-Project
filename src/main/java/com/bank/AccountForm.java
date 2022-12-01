package com.bank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

/**
 * UI form to collect and display information about user's bank accounts
 */
public class AccountForm {
    private JPanel pnlMain;
    private JPanel pnlCenterMain;
    private JPanel pnlInnerNorth;
    private JPanel pnlInnerCenter;
    private JList lstAccounts;
    private JComboBox cmbAccountType;
    private JTextField txtBalance;
    private JTextField txtInterest;
    private JTextField txtAccountNumber;
    private JPanel jplButtonBar;
    private JButton btnSave;
    private JTextField txtPeriods;
    private JButton btnCompute;
    private JTextField txtMaturity;
    private JButton btnWithdraw;
    private JButton btnCalculate;
    private JTextField txtWithdraw;
    private JTextField txtCurrentPeriodInterest;
    private Vector<Account> allAccounts;

    public AccountForm() {
        allAccounts = new Vector<>(InventoryReader.createAccount());
        initializeAccountTypeComboBox();
        //Possible data verification here...
        //allAccounts.forEach((n) -> System.out.println(n));
        lstAccounts.setListData(allAccounts);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strAccountNumber = txtAccountNumber.getText();
                int accountNumber = Integer.parseInt(strAccountNumber);

                String strBalance = txtBalance.getText();
                double balance = Double.parseDouble(strBalance);

                String strInterest = txtInterest.getText();
                int interest = Integer.parseInt(strInterest);

                String strPeriods = txtPeriods.getText();
                int periods = Integer.parseInt(strPeriods);

                String type = cmbAccountType.getSelectedItem().toString();

                try {
                    Account account = Banker.createAccount(type);

                    account.setAccountNumber(accountNumber);
                    account.setBalance(balance);
                    account.setInterest(interest);
                    account.setPeriods(periods);

                    if (cmbAccountType.getSelectedItem().toString().equals(Banker.CD)) {
                        if (account instanceof CertificateOfDeposit) {
                            String strMaturity = txtMaturity.getText();
                            int maturity = Integer.parseInt(strMaturity);

                            CertificateOfDeposit certificateOfDeposit = (CertificateOfDeposit) account;
                            certificateOfDeposit.setMaturity(maturity);
                        }
                    }

                    allAccounts.add(account);
                } catch (Exception exception) {
                    System.out.println("Oops");
                    exception.printStackTrace();
                }

                lstAccounts.updateUI();

                txtAccountNumber.setText(null);
                txtInterest.setText(null);
                txtBalance.setText(null);
                txtMaturity.setText(null);
                txtPeriods.setText(null);
            }
        });

        cmbAccountType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbAccountType.getSelectedItem().toString().equals(Banker.CD)) {
                    txtMaturity.setEnabled(true);
                } else {
                    txtMaturity.setEnabled(false);
                }
            }
        });

        btnCompute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AtomicReference<Double> beforeAmmount = new AtomicReference<>((double) 0);
                allAccounts.stream().forEach(account -> {
                    beforeAmmount.updateAndGet(v -> new Double((double) (v + account.getBalance())));
                });
                AtomicReference<Double> afterAmmount = new AtomicReference<>((double) 0);
                allAccounts.stream().forEach(account -> {
                    account.compute();
                    afterAmmount.updateAndGet(v -> new Double((double) (v + account.getBalance())));
                });
                txtCurrentPeriodInterest.setText(String.valueOf(afterAmmount.get() - beforeAmmount.get()));
                lstAccounts.updateUI();
            }
        });
        btnWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strWithdrawal = txtWithdraw.getText();
                if (!strWithdrawal.isEmpty())
                {
                    int withdrawalAmt = Integer.parseInt(strWithdrawal);
                    var lowestAccount = Collections.min(allAccounts, Comparator.comparing(s -> s.interest));
                    var newBalance = lowestAccount.getBalance() - withdrawalAmt;
                    lowestAccount.setBalance(newBalance);

                    lstAccounts.updateUI();
                }
            }
        });

    }

    private void initializeAccountTypeComboBox() {
        DefaultComboBoxModel<String> accountTypesModel = new DefaultComboBoxModel<>();
        accountTypesModel.addElement(Banker.SAVINGS);
        accountTypesModel.addElement(Banker.CHECKING);
        accountTypesModel.addElement(Banker.CD);
        cmbAccountType.setModel(accountTypesModel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AccountForm");
        frame.setContentPane(new AccountForm().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
