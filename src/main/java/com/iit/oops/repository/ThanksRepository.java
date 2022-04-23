package com.iit.oops.repository;

import com.iit.oops.exception.UnAuthorizedException;
import com.iit.oops.model.Thanks;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ThanksRepository {

    static Map<String, Thanks> thanksMap = new HashMap<>();

    static Map<String, List<String>> thanksCreatedUidMap = new HashMap<>();

    static Map<String, List<String>> thanksReceivedUidMap = new HashMap<>();


    public Optional<Thanks> getThanksById(String tid) {
        if (null != thanksMap.get(tid))
            return Optional.of(thanksMap.get(tid));
        else
            return Optional.of(new Thanks());
    }


    public Optional<Thanks> createThanks(Thanks thanks) {
        List<String> thanksCreatedList;
        List<String> thankToList;

        if (thanksCreatedUidMap.containsKey(thanks.getUid()))
            thanksCreatedList = thanksCreatedUidMap.get(thanks.getUid());
        else
            thanksCreatedList = new ArrayList<>();
        thanksCreatedList.add(thanks.getTid());
        thanksCreatedUidMap.put(thanks.getUid(), thanksCreatedList);

        if (thanksReceivedUidMap.containsKey(thanks.getThank_to()))
            thankToList = thanksReceivedUidMap.get(thanks.getThank_to());
        else
            thankToList = new ArrayList<>();
        thankToList.add(thanks.getTid());
        thanksReceivedUidMap.put(thanks.getThank_to(), thankToList);

        thanksMap.put((thanks.getTid()), thanks);

        return Optional.of(thanks);
    }

    public Optional<Thanks> updateThanks(Thanks thanks, String uid, String tid) throws UnAuthorizedException {
        List<String> thanksToUidList = thanksCreatedUidMap.get(uid);
        if (null != thanksToUidList && thanksToUidList.contains(tid)) {
            thanks.setTid(tid);
            thanksMap.put(tid, thanks);
            return Optional.of(thanks);
        } else
            throw new UnAuthorizedException(500);
    }

    public Optional<List<Thanks>> getAllThanks(String keyword) {
        if (StringUtils.isNotEmpty(keyword)) {
            List<Thanks> thanksListFromDb = new ArrayList<>(thanksMap.values());
            List<Thanks> thanksListFiltered = new ArrayList<>();
            if (StringUtils.isNotEmpty(keyword)) {
                for (Thanks thanks : thanksListFromDb) {
                    if (thanks.toString().contains(keyword))
                        thanksListFiltered.add(thanks);
                }
            }
            return Optional.of(thanksListFiltered);
        }
        return Optional.of(new ArrayList<>(thanksMap.values()));
    }


    public Optional<List<Thanks>> getAllThanksCreatedByUid(String uid) {
        return getThanks(uid, thanksCreatedUidMap);
    }


    public Optional<List<Thanks>> getAllThanksReceivedByUid(String uid) {
        return getThanks(uid, thanksReceivedUidMap);
    }

    private Optional<List<Thanks>> getThanks(String uid, Map<String, List<String>> thanksReceivedUidMap) {
        List<String> thanksReceivedList = thanksReceivedUidMap.get(uid);
        List<Thanks> thanksList = new ArrayList<>();
        if (null != thanksReceivedList) {
            for (String tid : thanksReceivedList) {
                thanksList.add(thanksMap.get(tid));
            }
        }
        return Optional.of(thanksList);
    }
}
