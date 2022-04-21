package com.iit.oops.service.impl;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.exception.UnAuthorizedException;
import com.iit.oops.model.Thanks;
import com.iit.oops.repository.ThanksRepository;
import com.iit.oops.service.ThanksService;

import java.util.List;
import java.util.Optional;

public class ThanksServiceImpl implements ThanksService {

    private final ThanksRepository thanksRepository;

    public ThanksServiceImpl(ThanksRepository thanksRepository) {
        this.thanksRepository = thanksRepository;
    }


    @Override
    public Thanks getThanksByTid(String tid) throws BuyNothingException {
        Optional<Thanks> thanksFromRepo = thanksRepository.getThanksById(tid);
        if (thanksFromRepo.isPresent()) {
            return thanksFromRepo.get();
        }
        throw new BuyNothingException(404, "Sorry, there is no Thanks with that id");
    }

    @Override
    public Thanks createThanks(Thanks thanks, String uid) throws BuyNothingException {
        thanks.setUid(uid);
        Optional<Thanks> thanksFromRepo = thanksRepository.createThanks(thanks);
        if (thanksFromRepo.isPresent()) {
            return thanksFromRepo.get();
        }
        throw new BuyNothingException(404, "Something has occured");
    }

    @Override
    public Thanks updateThanks(Thanks thanks, String uid, String tid) throws BuyNothingException, UnAuthorizedException {
        thanks.setUid(uid);
        Optional<Thanks> thanksFromRepo = thanksRepository.updateThanks(thanks, uid, tid);
        if (thanksFromRepo.isPresent()) {
            return thanksFromRepo.get();
        }
        throw new BuyNothingException(404, "you are unauthorized");
    }

    @Override
    public List<Thanks> getAllThanks(String keyword) throws BuyNothingException {
        Optional<List<Thanks>> thanksFromRepo = thanksRepository.getAllThanks(keyword);
        if (thanksFromRepo.isPresent()) {
            return thanksFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }

    @Override
    public List<Thanks> getAllThanksCreatedByUid(String uid) throws BuyNothingException {
        Optional<List<Thanks>> thanksFromRepo = thanksRepository.getAllThanksCreatedByUid(uid);
        if (thanksFromRepo.isPresent()) {
            return thanksFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }

    @Override
    public List<Thanks> getAllThanksReceivedByUid(String uid) throws BuyNothingException {
        Optional<List<Thanks>> thanksFromRepo = thanksRepository.getAllThanksReceivedByUid(uid);
        if (thanksFromRepo.isPresent()) {
            return thanksFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }
}
