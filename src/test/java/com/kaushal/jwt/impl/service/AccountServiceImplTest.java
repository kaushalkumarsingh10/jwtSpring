package com.kaushal.jwt.impl.service;

import com.kaushal.jwt.dao.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.CollectionUtils;
import java.util.Collection;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @Spy
    @InjectMocks
    AccountServiceImpl accountService;


    @Test
    public void getAccountDetails() {
        ReflectionTestUtils.invokeMethod(accountService,"populateData");
        Collection<Account> accountDetails = accountService.getAccountDetails();
        Assert.assertFalse(CollectionUtils.isEmpty(accountDetails));
        Assert.assertEquals(2,accountDetails.size());
    }

    @Test
    public void getAccount_HappyPath() {
        ReflectionTestUtils.invokeMethod(accountService,"populateData");
        Account accountDetail = accountService.getAccount(1L);
        Assert.assertNotNull(accountDetail);
        Assert.assertEquals(1L,accountDetail.getAccountNumber());
        Assert.assertEquals("Kaushal",accountDetail.getAccountName());
        Assert.assertEquals("Savings",accountDetail.getType());
    }

    @Test
    public void getAccount_UnHappyPath() {
        ReflectionTestUtils.invokeMethod(accountService,"populateData");
        Account accountDetail = accountService.getAccount(3L);
        Assert.assertNull(accountDetail);
    }

    @Test
    public void save() {
        ReflectionTestUtils.invokeMethod(accountService,"populateData");
        Account newAcc = new Account(6L,"New","New10@gmail.com","Savings");
        accountService.save(newAcc);
        Account output = accountService.getAccount(6L);
        Assert.assertNotNull(output);
        Assert.assertEquals(6L,output.getAccountNumber());
        Assert.assertEquals("New",output.getAccountName());
        Assert.assertEquals("Savings",output.getType());
    }

    @Test
    public void udpate() {
        ReflectionTestUtils.invokeMethod(accountService,"populateData");
        Account oldAcc = new Account(5L,"New","New10@gmail.com","Savings");
        accountService.save(oldAcc);
        Account newAcc = new Account(5L,"New","New10@gmail.com","Current");
        accountService.update(newAcc);
        Account output = accountService.getAccount(3L);
        Assert.assertNotNull(output);
        Assert.assertEquals(newAcc.getAccountNumber(),output.getAccountNumber());
        Assert.assertEquals(newAcc.getAccountName(),output.getAccountName());
        Assert.assertEquals(newAcc.getType(),output.getType());
    }


    @Test
    public void accountPresent() {
        ReflectionTestUtils.invokeMethod(accountService,"populateData");
        Account oldAcc = new Account(10L,"New","New10@gmail.com","Savings");
        accountService.save(oldAcc);
        Assert.assertTrue(accountService.accountPresent(10L));
        Assert.assertFalse(accountService.accountPresent(11L));
    }

}
