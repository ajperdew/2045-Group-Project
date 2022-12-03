package com.bank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class InventoryReader {
    private static Map<Integer, Account> allAccounts = new HashMap<>();

    public static void main(String[] args) {
        createAccount();
    }

    public static void createAccount() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("accounts.json"));
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Account.class, new AccountDeserializer());
            Gson gson = gsonBuilder.create();
            Vector<Account> inAccounts = gson.fromJson(reader, new TypeToken<Vector<Account>>() {
            }.getType());
            for(int accountNumber = 0; accountNumber < inAccounts.size(); accountNumber++){
                Account account = inAccounts.get(accountNumber);
                account.setAccountNumber(accountNumber);
                allAccounts.put(accountNumber, account);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void createAccount() {
//
//        Path inventoryFilePath = Paths.get("accounts.txt");
//
//
//        try {
//            List<String> inventoryLines = Files.readAllLines(inventoryFilePath);
//
//            for (String inventoryItem : inventoryLines) {
//                String[] inventoryArray = inventoryItem.split(",");
//
//                if (inventoryArray.length >= 4) {
//                    int accountNumber = inventoryLines.indexOf(inventoryItem);
//
//                    String accountType = inventoryArray[0];
//
//                    String strBalance = inventoryArray[2];
//                    double balance = Double.parseDouble(strBalance);
//
//                    String strInterest = inventoryArray[1];
//                    int interest = Integer.parseInt(strInterest);
//
//                    String strPeriods = inventoryArray[3];
//                    int periods = Integer.parseInt(strPeriods);
//
//                    Account account = Banker.createAccount(accountType);
//                    account.setAccountNumber(accountNumber);
//                    account.setBalance(balance);
//                    account.setInterest(interest);
//                    account.setPeriods(periods);
//
//                    allAccounts.put(accountNumber, account);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static Account fetchAccount(int accountNumber) {
        return allAccounts.get(accountNumber);
    }

    public static Map<Integer, Account> getAllAccounts(){
        return allAccounts;
    }
}
