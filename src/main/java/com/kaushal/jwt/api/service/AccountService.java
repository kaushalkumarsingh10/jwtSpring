package com.kaushal.jwt.api.service;

import com.kaushal.jwt.dao.Account;

import java.util.Collection;
import java.util.List;

public interface AccountService {

    /**
     * returns all the accountDetails present in the system
     * @return
     */
    public Collection<Account> getAccountDetails();

    /**
     * Get Account detail based on account Number
     * @param accNumber
     * @return
     */
    public Account getAccount(long accNumber);

    /**
     * Save the Account Details
     * @param account
     * @return
     */
    public Account save(Account account);

    /**
     * Update the Account Details
     * @param account
     * @return
     */
    public Account update(Account account);

    public boolean accountPresent(long accNumber) ;
}
