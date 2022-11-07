package com.kaushal.jwt.impl.service;

import com.kaushal.jwt.api.service.AccountService;
import com.kaushal.jwt.dao.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


@Component
public class AccountServiceImpl implements AccountService {
    Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private HashMap<Long, Account> accountMap = new HashMap<>();

    @PostConstruct
    void populateData() {
        accountMap.put(1L,new Account(1,"Kaushal","kaushalkumarsingh10@gmail.com","Savings"));
        accountMap.put(2L,new Account(2,"Sudha","sudhasingh@gmail.com","Current"));
    }

    /**
     * returns all the accountDetails present in the system
     * @return
     */
    @Override
    public Collection<Account> getAccountDetails() {
        logger.debug("-- getAccountDetails()");
        return accountMap.values();
    }

    /**
     * Get Account detail based on account Number
     * @param accNumber
     * @return
     */
    @Override
    public Account getAccount(long accNumber) {
        logger.debug("-- getAccount() for Account Number :{}",accNumber);
        return accountMap.getOrDefault(accNumber, null);
    }

    /**
     * Save the Account Details
     * @param account
     * @return
     */
    @Override
    public Account save(Account account) {
        logger.debug("-- save() for Account :{}",account);
        return accountMap.put(account.getAccountNumber(),account);
    }

    /**
     * Update the Account Details
     * @param account
     * @return
     */
    @Override
    public Account update(Account account) {
        logger.debug("-- update() for Account :{}",account);
        accountMap.put(account.getAccountNumber(),account);
        return accountMap.get(account.getAccountNumber());
    }

    /**
     * check is account is present
     * @param accNumber
     * @return
     */
    @Override
    public boolean accountPresent(long accNumber) {
        return accountMap.containsKey(accNumber);
    }

}
