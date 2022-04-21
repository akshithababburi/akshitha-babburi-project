package com.iit.oops.service;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Account;

import java.util.List;

public interface AccountService {

    Account getAccountById(String uid) throws BuyNothingException;

    Account createAccount(Account account) throws BuyNothingException;

    Account activateAccount(String uid) throws BuyNothingException;

    void deleteAccount(String uid);

    Account updateAccount(String uid, Account account) throws BuyNothingException;

    List<Account> getAllAccounts(String keyword) throws BuyNothingException;
}
