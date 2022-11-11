package com.kaushal.jwt.rest;

import com.kaushal.jwt.dao.Account;
import com.kaushal.jwt.impl.service.AccountMapServiceImpl;
import com.kaushal.jwt.impl.service.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Spy
    @InjectMocks
    AccountController accountController;
    @Mock
    AccountMapServiceImpl accountMapService;

    private HashMap<Long, Account> metaData() {
        HashMap<Long, Account> accountMap = new HashMap<>();
        accountMap.put(1L,new Account(1,"Kaushal","kaushalkumarsingh10@gmail.com","Savings"));
        accountMap.put(2L,new Account(2,"Sudha","sudhasingh@gmail.com","Current"));
        return accountMap;
    }


    @Test
    public void getAccountDetails() {
        Mockito.doReturn(metaData().values()).when(accountMapService).getAccountDetails();
        Collection<Account> output = accountController.getAccountDetails();
        Assert.assertFalse(CollectionUtils.isEmpty(output));
        Assert.assertEquals(2,output.size());
        Mockito.verify(accountMapService,Mockito.times(1)).getAccountDetails();
    }

    @Test
    public void getAccountDetail() {
        Mockito.doReturn(new Account(1,"Kaushal","kaushalkumarsingh10@gmail.com","Savings"))
                .when(accountMapService).getAccountByAccountNo(1);
        Account output = accountController.getAccountDetail(1L);
        Assert.assertNotNull(output);
        Assert.assertEquals(1L,output.getAccountNumber());
        Assert.assertEquals("Kaushal",output.getAccountName());
        Assert.assertEquals("Savings",output.getType());
    }

    @Test
    public void updateAccount() throws Exception {
        Account updated = new Account(1,"Kaushal","kaushalkumarsingh10@gmail.com","Current");
        Account old =new Account(1,"Kaushal","kaushalkumarsingh10@gmail.com","Savings");
        Mockito.doReturn(updated).when(accountMapService).updateAccountNumber(old);
        Account  newAcc  = accountMapService.updateAccountNumber(old);
        Assert.assertNotNull(newAcc);
        Assert.assertEquals(newAcc.getAccountNumber(),updated.getAccountNumber());
        Assert.assertEquals(newAcc.getAccountName(),updated.getAccountName());
        Assert.assertEquals(newAcc.getType(),updated.getType());
    }

    @Test
    public void createAccount() {
        Account old = new Account(1,"Kaushal","kaushalkumarsingh10@gmail.com","Current");
        Account newAcc = new Account(1,"Dummmy","kaushalkumarsingh10@gmail.com","Current");;
        try {
            Mockito.doReturn(null).when(accountMapService).validateAndCreateAccountNumber(old);
            newAcc = accountMapService.validateAndCreateAccountNumber(old);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            Assert.assertNull(newAcc);
        }

    }

}
