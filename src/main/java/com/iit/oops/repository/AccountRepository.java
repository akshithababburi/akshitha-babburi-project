package com.iit.oops.repository;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Account;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class AccountRepository {
    static Map<String, Account> accountMap = new HashMap<>();

    public Optional<Account> getAccountById(String uid) {
        if (null != accountMap.get(uid))
            return Optional.of(accountMap.get(uid));
        else
            return Optional.of(new Account());
    }

    public Optional<Account> createAccount(Account account) {
        accountMap.put(account.getUid(), account);
        return Optional.of(account);
    }

    public Optional<Account> activateAccount(String uid) {
        Account account = accountMap.get(uid);
        account.setIs_active(true);
        accountMap.put(uid, account);
        return Optional.of(account);
    }

    public void deleteAccount(String uid) {
        accountMap.remove(uid);
    }

    public Optional<Account> updateAccount(Account account, String uid) throws BuyNothingException {
        if (uid.equals(account.getUid())) {
            accountMap.put(uid, account);
            return Optional.of(account);
        } else {
            throw new BuyNothingException(500, "The uid " + uid + "is not allowed to update uid" + account.getUid());
        }
    }

    public Optional<List<Account>> getAllAccounts(String keyword) {
        if (StringUtils.isNotEmpty(keyword)) {
            List<Account> accountList = new ArrayList<>();
            for (Map.Entry<String, Account> entry : accountMap.entrySet()) {
                Account account = entry.getValue();
                if (account.toString().contains(keyword))
                    accountList.add(account);
            }
            return Optional.of(accountList);
        }
        return Optional.of(new ArrayList<>(accountMap.values()));
    }
}
