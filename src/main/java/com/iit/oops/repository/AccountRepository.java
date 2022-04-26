package com.iit.oops.repository;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Account;
import com.iit.oops.model.Address;
import org.apache.commons.lang3.StringUtils;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AccountRepository {
    static Map<String, Account> accountMap = new HashMap<>();

    public AccountRepository() {
        initialData();
    }

    public Optional<Account> getAccountById(String uid) throws BuyNothingException {
        if (null != accountMap.get(uid))
            return Optional.of(accountMap.get(uid));
        else
            throw new BuyNothingException("400", "No Account Present present with the id ");
    }

    public Account createAccount(Account account) {

        if (StringUtils.isBlank(account.getUid()))
            account.setUid("" + accountMap.size() + 1);

        accountMap.put(account.getUid(), account);
        return account;
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
            throw new BuyNothingException("500", "The uid " + uid + "is not allowed to update uid" + account.getUid());
        }
    }

    public Optional<List<Account>> getAllAccounts(String keyword, String start_date, String end_date) {
        if (StringUtils.isNotEmpty(keyword) || StringUtils.isNotEmpty(start_date) || StringUtils.isNotEmpty(end_date)) {
            List<Account> accountListFromDB = new ArrayList<>(accountMap.values());
            List<Account> accountListFiltered = new ArrayList<>();

            if (StringUtils.isNotEmpty(keyword)) {
                for (Account account : accountListFromDB) {
                    if (account.toString().toLowerCase().contains(keyword.toLowerCase()))
                        accountListFiltered.add(account);
                }
            }

            if (StringUtils.isNotEmpty(start_date)) {
                LocalDate startDate = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                if (!accountListFiltered.isEmpty()) {
                    accountListFromDB = accountListFiltered;
                    accountListFiltered = new ArrayList<>();
                }
                for (Account account : accountListFromDB) {
                    if (account.getDate_created().isAfter(startDate))
                        accountListFiltered.add(account);
                }
            }

            if (StringUtils.isNotEmpty(end_date)) {
                LocalDate endDate = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                if (!accountListFiltered.isEmpty()) {
                    accountListFromDB = accountListFiltered;
                    accountListFiltered = new ArrayList<>();
                }
                for (Account account : accountListFromDB) {
                    if (account.getDate_created().isBefore(endDate))
                        accountListFiltered.add(account);
                }
            }
            return Optional.of(accountListFiltered);
        }
        return Optional.of(new ArrayList<>(accountMap.values()));
    }

    public void initialData() {
        Address uid1Address = new Address("10 West 31st ST", "60616");
        Account uid1Account = new Account("1", "Virgil Bistriceanu", uid1Address,
                "312-567-5146", "http://cs.iit.edu/~virgil/pictures/virgil-head-small-200811.jpg"
                , true, LocalDate.now());

        Address uid2Address = new Address("123 2nd ST", "60607");
        Account uid2Account = new Account("2", "Jane Smith", uid2Address,
                "217-456-7890", "http://example.com/images/jane-smith.jpeg"
                , false, LocalDate.now());

        Address uid3Address = new Address("101 W Main St.", "60010");
        Account uid3Account = new Account("3", "CSR #1", uid3Address,
                "(847) 842-8048", "http://example.com/images/jane-smith.jpeg"
                , true, LocalDate.now());

        accountMap.put(uid1Account.getUid(), uid1Account);
        accountMap.put(uid2Account.getUid(), uid2Account);
        accountMap.put(uid3Account.getUid(), uid3Account);
    }
}
