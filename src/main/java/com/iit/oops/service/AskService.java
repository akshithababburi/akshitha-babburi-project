package com.iit.oops.service;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Ask;

import java.util.List;

public interface AskService {

    Ask createAsk(Ask ask, String uid) throws BuyNothingException;

    Ask deactivateAsk(String uid, String aid) throws BuyNothingException;

    Ask updateAsk(Ask ask, String uid, String aid) throws BuyNothingException;

    void deleteAsk(String uid, String aid) throws BuyNothingException;

    Ask getAskById(String aid) throws BuyNothingException;

    List<Ask> getAsksByUid(String uid, boolean is_active) throws BuyNothingException;

    List<Ask> getAllAsks() throws BuyNothingException;

    List<Ask> searchAsks(String keyword, String start_date, String end_date) throws BuyNothingException;
}
