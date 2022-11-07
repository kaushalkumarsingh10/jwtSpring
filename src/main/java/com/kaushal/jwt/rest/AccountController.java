package com.kaushal.jwt.rest;

import com.kaushal.jwt.api.service.AccountMapService;
import com.kaushal.jwt.api.service.AccountService;
import com.kaushal.jwt.dao.Account;
import com.kaushal.jwt.impl.service.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@RequestMapping("/Account")
@org.springframework.web.bind.annotation.RestController
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountMapService accountMapService;

    @GetMapping("/getAccountDetails")
    public Collection<Account> getAccountDetails() {
        logger.debug("-- /getAccountDetails ");
        return accountMapService.getAccountDetails();
    }

    @GetMapping("/getAccountDetail")
    public Account getAccountDetail(@RequestParam Long accountNumber ) {
        logger.debug("-- /getAccountDetail  accountNumber : {}",accountNumber);
        return accountMapService.getAccountByAccountNo(accountNumber);
    }

    @PostMapping("/updateAccount")
    public Account updateAccount(@RequestBody Account account) {
        logger.debug("-- /updateAccount  account : {}",account);
        return accountMapService.updateAccountNumber(account);
    }

    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody Account account) throws Exception{
        logger.debug("-- /createAccount  account : {}",account);
        return accountMapService.validateAndCreateAccountNumber(account);
    }

}
