package com.bank;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InventoryReader {
    private static List<Account> allAccounts = new ArrayList<>();

    public static void main(String[] args) {
        createAccount();
    }

    public static List<Account> createAccount() {

        Path inventoryFilePath = Paths.get("accounts.txt");
        try {
            List<String> inventoryLines = Files.readAllLines(inventoryFilePath);

            for (String inventoryItem : inventoryLines) {
                String[] inventoryArray = inventoryItem.split(",");

                if (inventoryArray.length >= 4) {
                    String accountType = inventoryArray[0];

                    String strBalance = inventoryArray[2];
                    double balance = Double.parseDouble(strBalance);

                    String strInterest = inventoryArray[1];
                    int interest = Integer.parseInt(strInterest);

                    String strPeriods = inventoryArray[3];
                    int periods = Integer.parseInt(strPeriods);

                    Account account = Banker.createAccount(accountType);
                    account.setBalance(balance);
                    account.setInterest(interest);
                    account.setPeriods(periods);

                    allAccounts.add(account);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allAccounts;
    }

}
