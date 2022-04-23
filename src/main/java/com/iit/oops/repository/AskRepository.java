package com.iit.oops.repository;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Ask;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;

public class AskRepository {
    static Map<String, Ask> askMap = new HashMap<>();

    static Map<String, List<String>> uidAidMap = new HashMap<>();

    public Optional<Ask> getAskById(String aid) {
        if (null != askMap.get(aid))
            return Optional.of(askMap.get(aid));
        else
            return Optional.of(new Ask());
    }

    public Optional<List<Ask>> getAsksByUid(String uid, boolean is_active) {
        List<Ask> askList = null;
        List<String> aidList = uidAidMap.get(uid);
        if (null != aidList) {
            askList = new ArrayList<>();
            for (String aid : aidList) {
                Ask ask = askMap.get(aid);
                if (ask.isIs_active() == is_active)
                    askList.add(ask);
            }
        }
        return Optional.of(askList);
    }


    public Optional<Ask> createAsk(Ask ask, String uid) {
        List<String> aidList = null;

        if (uidAidMap.containsKey(uid))
            aidList = uidAidMap.get(uid);
        else
            aidList = new ArrayList<>();
        aidList.add(ask.getAid());
        uidAidMap.put(uid, aidList);
        askMap.put((ask.getAid()), ask);
        return Optional.of(ask);
    }

    public Optional<Ask> updateAsk(Ask ask, String uid, String aid) throws BuyNothingException {
        List<String> askListAssociatedWithUid = uidAidMap.get(uid);
        if (null != askListAssociatedWithUid && askListAssociatedWithUid.contains(aid)) {
            askMap.put(aid, ask);
            return Optional.of(ask);
        } else
            throw new BuyNothingException(500);
    }


    public void deleteAsk(String uid, String aid) throws BuyNothingException {
        List<String> askListAssociatedWithUid = uidAidMap.get(uid);
        if (null != askListAssociatedWithUid && askListAssociatedWithUid.contains(aid)) {
            askListAssociatedWithUid.remove(aid);
            uidAidMap.put(uid, askListAssociatedWithUid);
            askMap.remove(aid);
        } else
            throw new BuyNothingException(500);
    }

    public Optional<Ask> deactivateAsk(String uid, String aid) throws BuyNothingException {
        List<String> askListAssociatedWithUid = uidAidMap.get(uid);
        if (null != askListAssociatedWithUid && askListAssociatedWithUid.contains(aid)) {
            Ask ask = askMap.get(aid);
            ask.setIs_active(false);
            return Optional.of(askMap.put(aid, ask));
        } else
            throw new BuyNothingException(500);
    }


    public Optional<List<Ask>> getAllAsks() {
        return Optional.of(new ArrayList<>(askMap.values()));
    }


    public Optional<List<Ask>> searchAsks(String keyword, String start_date, String end_date) {
        if (StringUtils.isNotEmpty(keyword) || StringUtils.isNotEmpty(start_date) || StringUtils.isNotEmpty(end_date)) {
            List<Ask> askListFromDb = new ArrayList<>(askMap.values());
            List<Ask> askListFiltered = new ArrayList<>();
            if (StringUtils.isNotEmpty(keyword)) {
                for (Ask ask : askListFromDb) {
                    if (ask.toString().contains(keyword))
                        askListFiltered.add(ask);
                }
            }
            if (StringUtils.isNotEmpty(start_date)) {
                if (askListFiltered.isEmpty())
                    askListFiltered = askListFromDb;
                for (Ask ask : askListFiltered) {
                    LocalDate startDate = LocalDate.parse(start_date);
                    if (ask.getStart_date().isAfter(startDate))
                        askListFiltered.add(ask);
                }
            }

            if (StringUtils.isNotEmpty(end_date)) {
                if (askListFiltered.isEmpty())
                    askListFiltered = askListFromDb;
                for (Ask ask : askListFiltered) {
                    LocalDate endDate = LocalDate.parse(end_date);
                    if (ask.getEnd_date().isBefore(endDate))
                        askListFiltered.add(ask);
                }
            }
            return Optional.of(askListFiltered);
        }
        return Optional.of(new ArrayList<>(askMap.values()));
    }

}
