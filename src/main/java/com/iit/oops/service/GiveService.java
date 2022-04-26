package com.iit.oops.service;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Give;

import java.util.List;

public interface GiveService {

    Give getGiveById(String gid) throws BuyNothingException;

    Give createGive(Give give, String uid) throws BuyNothingException;

    Give deactivateGive(String uid, String gid) throws BuyNothingException;

    Give updateGive(Give give, String uid, String gid) throws BuyNothingException;

    void deleteGive(String uid, String gid) throws BuyNothingException;

    List<Give> viewMyGives(String uid) throws BuyNothingException;

    List<Give> getAllGives(String keyword, String start_date, String end_date) throws BuyNothingException;
}
