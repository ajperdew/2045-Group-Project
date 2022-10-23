package com.bank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
    private Vector<Account> allAccounts = new Vector<>();

    public AccountForm() {

        initializeAccountTypeComboBox();
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

                String type = cmbAccountType.getSelectedItem().toString();

                try {
                    Account account = Banker.createAccount(type);

                    account.setAccountNumber(accountNumber);
                    account.setBalance(balance);
                    account.setInterest(interest);

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

                txtPeriods.setEnabled(true);
                btnCompute.setEnabled(true);
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
                String strPeriods = txtPeriods.getText();
                int periods = Integer.parseInt(strPeriods);

                allAccounts.stream().forEach(account -> {
                    account.setPeriods(periods);
                    account.compute();
                });
                lstAccounts.updateUI();
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
