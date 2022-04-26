package com.iit.oops.service;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Thanks;

import java.util.List;

public interface ThanksService {

    Thanks getThanksByTid(String tid) throws BuyNothingException;

    Thanks createThanks(Thanks thanks, String uid) throws BuyNothingException;

    Thanks updateThanks(Thanks thanks, String uid, String tid) throws BuyNothingException;

    List<Thanks> getAllThanks(String keyword) throws BuyNothingException;

    List<Thanks> getAllThanksCreatedByUid(String uid) throws BuyNothingException;

    List<Thanks> getAllThanksReceivedByUid(String uid) throws BuyNothingException;
}
