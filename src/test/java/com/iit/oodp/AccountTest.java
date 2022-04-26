package com.iit.oodp;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Account;
import com.iit.oops.model.Address;
import com.iit.oops.repository.AccountRepository;
import com.iit.oops.service.AccountService;
import com.iit.oops.service.impl.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class AccountTest {

    private AccountService accountService;
    private AccountRepository accountRepository;

    @Before
    public void init() {
        accountRepository = new AccountRepository();
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    public void testCreateAccount() throws BuyNothingException {

        Address address = new Address("S King Dr", "60181");
        Account account = new Account("1", "Akshitha Babburi",
                address, "12345", "123.jpg", true, LocalDate.now());

        Assert.assertEquals(accountService.createAccount(account).getUid(), "1");
    }

    @Test
    public void testActivateAccount() throws BuyNothingException {

        Address address = new Address("S King Dr", "60181");
        Account account = new Account("1", "Akshitha Babburi",
                address, "12345", "123.jpg", false, LocalDate.now());

        accountService.createAccount(account);
        Assert.assertEquals(accountService.activateAccount("1").isIs_active(), true);
    }


    @Test
    public void testGetAccountById() throws BuyNothingException {

        Address address = new Address("S King Dr", "60181");
        Account account = new Account("1", "Akshitha",
                address, "12345", "123.jpg", false, LocalDate.now());

        accountService.createAccount(account);
        Assert.assertEquals(accountService.getAccountById("1").getName(), "Akshitha");
    }


    @Test
    public void testDeleteAccount() throws BuyNothingException {

        Address address = new Address("S King Dr", "60181");
        Account account = new Account("4", "Akshitha",
                address, "12345", "123.jpg", false, LocalDate.now());

        accountService.createAccount(account);
        accountService.deleteAccount("4");
        Assert.assertEquals(accountService.getAllAccounts(null, null, null).size(), 3);
    }


    @Test
    public void testUpdateAccount() throws BuyNothingException {

        Address address = new Address("S King Dr", "60181");
        Account account = new Account("1", "Akshitha",
                address, "12345", "123.jpg", false, LocalDate.now());

        accountService.createAccount(account);
        account.setIs_active(true);
        Assert.assertEquals(accountService.updateAccount("1", account).isIs_active(), true);
    }


    @Test
    public void testGetAllAccounts() throws BuyNothingException {

        Address address = new Address("S King Dr", "60181");
        Account account = new Account("4", "Akshitha",
                address, "12345", "123.jpg", false, LocalDate.now());

        accountService.createAccount(account);

        Assert.assertEquals(accountService.getAllAccounts(null, null, null).size(), 4);
    }


}
