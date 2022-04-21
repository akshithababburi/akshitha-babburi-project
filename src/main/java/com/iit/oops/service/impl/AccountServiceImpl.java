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
        throw new BuyNothingException(404, "Sorry there is not Account with that id");
    }

    @Override
    public Account createAccount(Account account) throws BuyNothingException {
        Optional<Account> accountFromRepo = accountRepository.createAccount(account);
        if (accountFromRepo.isPresent()) {
            return accountFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }

    @Override
    public Account activateAccount(String uid) throws BuyNothingException {
        Optional<Account> accountFromRepo = accountRepository.activateAccount(uid);
        if (accountFromRepo.isPresent()) {
            return accountFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
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
        throw new BuyNothingException(404, "Something went wrong");
    }

    @Override
    public List<Account> getAllAccounts(String keyword) throws BuyNothingException {
        Optional<List<Account>> accountsFromRepo = accountRepository.getAllAccounts(keyword);
        if (accountsFromRepo.isPresent()) {
            return accountsFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }
}
