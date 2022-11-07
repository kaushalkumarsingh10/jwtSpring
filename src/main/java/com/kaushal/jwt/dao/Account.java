package com.kaushal.jwt.dao;

import java.io.Serializable;

public class Account implements Serializable {
    private long accountNumber;
    private String accountName;
    private String emailId;
    private String type;

    public Account() { }

    public Account(long accountNumber, String accountName, String emailId, String type) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.emailId = emailId;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountName='" + accountName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
