package com.iit.oops.service.impl;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Give;
import com.iit.oops.repository.GiveRepository;
import com.iit.oops.service.GiveService;

import java.util.List;
import java.util.Optional;

public class GiveServiceImpl implements GiveService {

    private final GiveRepository giveRepository;

    public GiveServiceImpl(GiveRepository giveRepository) {
        this.giveRepository = giveRepository;
    }


    @Override
    public Give getGiveById(String gid) throws BuyNothingException {
        Optional<Give> giveFromRepo = giveRepository.getGiveById(gid);
        if (giveFromRepo.isPresent()) {
            return giveFromRepo.get();
        }
        throw new BuyNothingException("404", "Sorry, there is no Ask with that id");
    }

    @Override
    public Give createGive(Give give, String uid) throws BuyNothingException {
        Optional<Give> giveFromRepo = giveRepository.createGive(give, uid);
        if (giveFromRepo.isPresent()) {
            return giveFromRepo.get();
        }
        throw new BuyNothingException("404", "Something went wrong");
    }

    @Override
    public Give deactivateGive(String uid, String gid) throws BuyNothingException {
        Optional<Give> giveFromRepo = giveRepository.deactivateGive(gid, uid);
        if (giveFromRepo.isPresent()) {
            return giveFromRepo.get();
        }
        throw new BuyNothingException("404", "You are un authorized");
    }

    @Override
    public Give updateGive(Give give, String uid, String gid) throws BuyNothingException {
        Optional<Give> giveFromRepo = giveRepository.updateGive(give, uid, gid);
        if (giveFromRepo.isPresent()) {
            return giveFromRepo.get();
        }
        throw new BuyNothingException("404", "You are un authorized");
    }

    @Override
    public void deleteGive(String uid, String gid) throws BuyNothingException {
        giveRepository.deleteGive(uid, gid);
    }

    @Override
    public List<Give> viewMyGives(String uid) throws BuyNothingException {
        Optional<List<Give>> givesFromRepo = giveRepository.viewMyGives(uid);
        if (givesFromRepo.isPresent()) {
            return givesFromRepo.get();
        }
        throw new BuyNothingException("404", "Something went wrong");
    }

    @Override
    public List<Give> getAllGives(String keyword, String start_date, String end_date) throws BuyNothingException {
        Optional<List<Give>> givesFromRepo = giveRepository.getAllGives(keyword, start_date, end_date);
        if (givesFromRepo.isPresent()) {
            return givesFromRepo.get();
        }
        throw new BuyNothingException("404", "Something went wrong");
    }
}
