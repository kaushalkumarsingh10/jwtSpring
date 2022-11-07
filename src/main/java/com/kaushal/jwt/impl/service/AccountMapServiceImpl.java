package com.kaushal.jwt.impl.service;

import com.kaushal.jwt.api.service.AccountMapService;
import com.kaushal.jwt.api.service.AccountService;
import com.kaushal.jwt.dao.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class AccountMapServiceImpl implements AccountMapService {

    Logger logger = LoggerFactory.getLogger(AccountMapServiceImpl.class);
    @Autowired
    private AccountService accountService;
    /**
     * returns all the accountDetails present in the system
     *
     * @return
     */
    @Override
    public Collection<Account> getAccountDetails() {
        return accountService.getAccountDetails();
    }

    /**
     * Get Account details based on account Number
     *
     * @param accNumber
     * @return
     */
    @Override
    public Account getAccountByAccountNo(long accNumber) {
        return accountService.getAccount(accNumber);
    }

    /**
     * Update the account Details for the Account object provided
     *
     * @param account
     * @return
     */
    @Override
    public Account updateAccountNumber(Account account) {
        return accountService.accountPresent(account.getAccountNumber())
                ? accountService.update(account):accountService.save(account);
    }

    /**
     * Validate and create account Details for the Account object provided
     *
     * @param account
     * @return
     */
    @Override
    public Account validateAndCreateAccountNumber(Account account) throws Exception {
         if(!accountService.accountPresent(account.getAccountNumber()))
             return accountService.save(account);
        else {
            logger.warn("Cannot create account with already existing account number");
             throw new Exception("Invalid Account Number");
         }
    }
}
