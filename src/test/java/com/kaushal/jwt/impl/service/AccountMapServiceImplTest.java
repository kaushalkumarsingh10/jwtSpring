package com.kaushal.jwt.impl.service;

import com.kaushal.jwt.dao.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class AccountMapServiceImplTest {
    @Spy
    @InjectMocks
    AccountMapServiceImpl accountMapService;

    @Mock
    AccountServiceImpl accountService;

    private HashMap<Long, Account> metaData() {
        HashMap<Long, Account> accountMap = new HashMap<>();
        accountMap.put(1L,new Account(1,"Kaushal","kaushalkumarsingh10@gmail.com","Savings"));
        accountMap.put(2L,new Account(2,"Sudha","sudhasingh@gmail.com","Current"));
        return accountMap;
    }

    @Test
    public void getAccountDetails() {
        Mockito.doReturn(metaData().values()).when(accountService).getAccountDetails();
        Collection<Account> accountDetails = accountMapService.getAccountDetails();
        Assert.assertFalse(CollectionUtils.isEmpty(accountDetails));
        Assert.assertEquals(2,accountDetails.size());
        Mockito.verify(accountService,Mockito.times(1)).getAccountDetails();
    }

    @Test
    public void updateAccountNumber_HappyPath() {
        Mockito.doReturn(true).when(accountService).accountPresent(1L);
        Account oldAcc= new Account(1L,"Kaushal","kaushalkumarsingh10@gmail.com","Savings");
        Account updatedAcc = new Account(1L,"Kaushal","kaushalkumarsingh10@gmail.com","Current");
        Mockito.doReturn(updatedAcc).when(accountService).update(oldAcc);
        Account acc =null;
        try {
            acc= accountMapService.updateAccountNumber(oldAcc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertNotNull(acc);
        Assert.assertEquals(updatedAcc.getAccountNumber(),acc.getAccountNumber());
        Assert.assertEquals(updatedAcc.getAccountName(),acc.getAccountName());
        Assert.assertEquals(updatedAcc.getType(),acc.getType());

        Mockito.verify(accountService,Mockito.times(1)).update(oldAcc);
    }


    @Test(expected = RuntimeException.class)
    public void updateAccountNumber_UnHappyPath_WhenAccIsNoPresent() {
        Mockito.doReturn(false).when(accountService).accountPresent(2L);
        Account oldAcc= new Account(2L,"Kaushal","kaushalkumarsingh10@gmail.com","Savings");
        Account updatedAcc = new Account(2L,"Kaushal","kaushalkumarsingh10@gmail.com","Current");
        Mockito.doReturn(updatedAcc).when(accountService).update(oldAcc);
        Account acc =null;
        try {
            acc= accountMapService.updateAccountNumber(oldAcc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Mockito.verify(accountService,Mockito.times(1)).update(oldAcc);
    }


    @Test()
    public void validateAndCreateAccountNumber_HappyPath() {
        Mockito.doReturn(false).when(accountService).accountPresent(3L);
        Account acc= new Account(3L,"Kaushal","kaushalkumarsingh10@gmail.com","Savings");
        Mockito.doReturn(acc).when(accountService).save(acc);
        Account output =null;
        try {
            output= accountMapService.validateAndCreateAccountNumber(acc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assert.assertNotNull(output);
        Assert.assertEquals(output.getAccountNumber(),acc.getAccountNumber());
        Assert.assertEquals(output.getAccountName(),acc.getAccountName());
        Assert.assertEquals(output.getType(),acc.getType());

        Mockito.verify(accountService,Mockito.times(1)).save(output);
    }

    @Test(expected = RuntimeException.class)
    public void validateAndCreateAccountNumber_UnHappyPath_WhenAccIsPresent() {
        Mockito.doReturn(true).when(accountService).accountPresent(3L);
        Account acc= new Account(3L,"Kaushal","kaushalkumarsingh10@gmail.com","Savings");
        Mockito.doReturn(acc).when(accountService).save(acc);
        Account output =null;
        try {
            output= accountMapService.validateAndCreateAccountNumber(acc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Mockito.verify(accountService,Mockito.times(1)).save(output);
    }

}
