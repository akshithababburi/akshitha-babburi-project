package com.iit.oops.repository;

import com.iit.oops.exception.UnAuthorizedException;
import com.iit.oops.model.Give;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.*;

public class GiveRepository {

    static Map<String, Give> giveMap = new HashMap<>();

    static Map<String, List<String>> uidGidMap = new HashMap<>();

    public Optional<Give> getGiveById(String gid) {
        if (null != giveMap.get(gid))
            return Optional.of(giveMap.get(gid));
        else
            return Optional.of(new Give());
    }


    public Optional<Give> createGive(Give give, String uid) {
        List<String> gidList = null;

        if (uidGidMap.containsKey(uid))
            gidList = uidGidMap.get(uid);
        else
            gidList = new ArrayList<>();
        gidList.add(give.getGid());
        uidGidMap.put(uid, gidList);
        giveMap.put((give.getGid()), give);
        return Optional.of(give);
    }

    public Optional<Give> updateGive(Give give, String uid, String gid) throws UnAuthorizedException {
        List<String> giveListAssociatedWithUid = uidGidMap.get(uid);
        if (null != giveListAssociatedWithUid && giveListAssociatedWithUid.contains(gid)) {
            giveMap.put(gid, give);
            return Optional.of(give);
        } else
            throw new UnAuthorizedException(500);
    }

    public Optional<Give> deactivateGive(String uid, String gid) throws UnAuthorizedException {
        List<String> giveListAssociatedWithUid = uidGidMap.get(uid);
        if (null != giveListAssociatedWithUid && giveListAssociatedWithUid.contains(gid)) {
            Give give = giveMap.get(gid);
            give.setIs_active(false);
            return Optional.of(giveMap.put(gid, give));
        } else
            throw new UnAuthorizedException(500);
    }

    public void deleteGive(String uid, String gid) throws UnAuthorizedException {
        List<String> giveListAssociatedWithUid = uidGidMap.get(uid);
        if (null != giveListAssociatedWithUid && giveListAssociatedWithUid.contains(gid)) {
            giveListAssociatedWithUid.remove(gid);
            uidGidMap.put(uid, giveListAssociatedWithUid);
            giveMap.remove(gid);
        } else
            throw new UnAuthorizedException(500);
    }

    public Optional<List<Give>> viewMyGives(String uid) {
        List<Give> giveList = new ArrayList<>();
        List<String> giveListAssociatedWithUid = uidGidMap.get(uid);
        for (String gid : giveListAssociatedWithUid) {
            Give give = giveMap.get(gid);
            giveList.add(give);
        }
        return Optional.of(giveList);
    }

    public Optional<List<Give>> getAllGives(String keyword, String start_date, String end_date) {
        if (StringUtils.isNotEmpty(keyword) || StringUtils.isNotEmpty(start_date) || StringUtils.isNotEmpty(end_date)) {
            List<Give> giveListFromDb = new ArrayList<>(giveMap.values());
            List<Give> giveListFiltered = new ArrayList<>();
            if (StringUtils.isNotEmpty(keyword)) {
                for (Give give : giveListFromDb) {
                    if (give.toString().contains(keyword))
                        giveListFiltered.add(give);
                }
            }
            if (StringUtils.isNotEmpty(start_date)) {
                if (giveListFiltered.isEmpty())
                    giveListFiltered = giveListFromDb;
                for (Give give : giveListFiltered) {
                    LocalDate startDate = LocalDate.parse(start_date);
                    if (give.getStart_date().isAfter(startDate))
                        giveListFiltered.add(give);
                }
            }

            if (StringUtils.isNotEmpty(end_date)) {
                if (giveListFiltered.isEmpty())
                    giveListFiltered = giveListFromDb;
                for (Give give : giveListFiltered) {
                    LocalDate endDate = LocalDate.parse(end_date);
                    if (give.getEnd_date().isBefore(endDate))
                        giveListFiltered.add(give);
                }
            }
            return Optional.of(giveListFiltered);
        }
        return Optional.of(new ArrayList<>(giveMap.values()));
    }

}
