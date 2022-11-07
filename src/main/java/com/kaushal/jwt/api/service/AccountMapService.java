package com.kaushal.jwt.api.service;

import com.kaushal.jwt.dao.Account;

import java.util.Collection;
import java.util.List;

public interface AccountMapService {

    /**
     * returns all the accountDetails present in the system
     * @return
     */
    public Collection<Account> getAccountDetails();

    /**
     * Get Account details based on account Number
     * @param accNumber
     * @return
     */
    public Account getAccountByAccountNo(long accNumber);

    /**
     * Update the account Details for the Account object provided
     * @param account
     * @return
     */
    public Account updateAccountNumber(Account account);
    /**
     * Validate and create account Details for the Account object provided
     * @param account
     * @return
     */
    public Account validateAndCreateAccountNumber(Account account) throws Exception;


}
