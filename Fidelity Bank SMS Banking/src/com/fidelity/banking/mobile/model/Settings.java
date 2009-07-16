/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fidelity.banking.mobile.model;

/**
 *
 * @author administrator
 */
public class Settings {
    private String shortcode;
    private Account[] accounts;

    public static final String DEFAULT_SHORTCODE = "1406";
    public static final String DEFAULT_ACCT_NUMBER = "8888888888";
    public static final String DEFAULT_BASE_NAME= "Account";

    public static final String RS_SETTINGS = "settings";

    public static final int BALANCE_ACTION = 0;
    public static final int MINI_STAEMENT_ACTION = 1;
    public static final int CHEQUE_STATUS_ACTION = 2;
    public static final int STOP_CHEQUE_ACTION = 3;
    public static final int NEW_CHEQUE_BOOK_ACTION = 4;
    public static final int EXCHANGE_RATE_ACTION = 0;

    public Settings() {
        shortcode = DEFAULT_SHORTCODE;
        accounts = new Account[5];
        for (int i = 0; i < accounts.length; i++){
            accounts[i] = new Account();
            accounts[i].setName(DEFAULT_BASE_NAME + " " + (i + 1));
            accounts[i].setAccountNumber(DEFAULT_ACCT_NUMBER);
        }
    }

    public Settings(String shortcode, Account[] accounts) {
        shortcode = shortcode;
        accounts = accounts;
    }

    /**
     * @return the shortcode
     */
    public String getShortcode() {
        return shortcode;
    }

    /**
     * @param shortcode the shortcode to set
     */
    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    /**
     * @return the accounts
     */
    public Account[] getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }
}
