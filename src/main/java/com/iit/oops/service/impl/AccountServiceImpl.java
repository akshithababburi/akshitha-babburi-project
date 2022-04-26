package com.iit.oops.service.impl;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Account;
import com.iit.oops.repository.AccountRepository;
import com.iit.oops.service.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(String uid) throws BuyNothingException {
        Optional<Account> accountFromRepo = accountRepository.getAccountById(uid);
        if (accountFromRepo.isPresent()) {
            return accountFromRepo.get();
        }
        throw new BuyNothingException("404", "Sorry there is not Account with that id");
    }

    @Override
    public Account createAccount(Account account) throws BuyNothingException {
        return accountRepository.createAccount(account);
    }

    @Override
    public Account activateAccount(String uid) throws BuyNothingException {
        Optional<Account> accountFromRepo = accountRepository.activateAccount(uid);
        if (accountFromRepo.isPresent()) {
            return accountFromRepo.get();
        }
        throw new BuyNothingException("404", "Something went wrong");
    }

    @Override
    public void deleteAccount(String uid) {
        accountRepository.deleteAccount(uid);
    }

    @Override
    public Account updateAccount(String uid, Account account) throws BuyNothingException {
        Optional<Account> accountFromRepo = accountRepository.updateAccount(account, uid);
        if (accountFromRepo.isPresent()) {
            return accountFromRepo.get();
        }
        throw new BuyNothingException("404", "Something went wrong");
    }

    @Override
    public List<Account> getAllAccounts(String keyword, String start_date, String end_date) throws BuyNothingException {
        Optional<List<Account>> accountsFromRepo = accountRepository.getAllAccounts(keyword, start_date, end_date);
        if (accountsFromRepo.isPresent()) {
            return accountsFromRepo.get();
        }
        throw new BuyNothingException("404", "Something went wrong");
    }
}
