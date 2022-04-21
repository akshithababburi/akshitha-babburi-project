package com.iit.oops.service.impl;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Ask;
import com.iit.oops.repository.AskRepository;
import com.iit.oops.service.AskService;

import java.util.List;
import java.util.Optional;

public class AskServiceImpl implements AskService {

    private final AskRepository askRepository;

    public AskServiceImpl(AskRepository askRepository) {
        this.askRepository = askRepository;
    }

    @Override
    public Ask getAskById(String aid) throws BuyNothingException {
        Optional<Ask> askFromRepo = askRepository.getAskById(aid);
        if (askFromRepo.isPresent()) {
            return askFromRepo.get();
        }
        throw new BuyNothingException(404, "Sorry, there is no Ask with that id");
    }

    @Override
    public List<Ask> getAsksByUid(String uid, boolean is_active) throws BuyNothingException {
        Optional<List<Ask>> asksFromRepo = askRepository.getAsksByUid(uid, is_active);
        if (asksFromRepo.isPresent()) {
            return asksFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }

    @Override
    public Ask createAsk(Ask ask, String uid) throws BuyNothingException {
        Optional<Ask> askFromRepo = askRepository.createAsk(ask, uid);
        if (askFromRepo.isPresent()) {
            return askFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }

    @Override
    public Ask deactivateAsk(String uid, String aid) throws BuyNothingException {
        Optional<Ask> askFromRepo = askRepository.deactivateAsk(uid, aid);
        if (askFromRepo.isPresent()) {
            return askFromRepo.get();
        }
        throw new BuyNothingException(404, "You are un authorized");
    }

    @Override
    public Ask updateAsk(Ask ask, String uid, String aid) throws BuyNothingException {
        Optional<Ask> askFromRepo = askRepository.updateAsk(ask, uid, aid);
        if (askFromRepo.isPresent()) {
            return askFromRepo.get();
        }
        throw new BuyNothingException(404, "You are un authorized");
    }

    @Override
    public void deleteAsk(String uid, String aid) throws BuyNothingException {
        askRepository.deleteAsk(uid, aid);
    }

    @Override
    public List<Ask> getAllAsks() throws BuyNothingException {
        Optional<List<Ask>> asksFromRepo = askRepository.getAllAsks();
        if (asksFromRepo.isPresent()) {
            return asksFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }

    @Override
    public List<Ask> searchAsks(String keyword, String start_date, String end_date) throws BuyNothingException {
        Optional<List<Ask>> asksFromRepo = askRepository.searchAsks(keyword, start_date, end_date);
        if (asksFromRepo.isPresent()) {
            return asksFromRepo.get();
        }
        throw new BuyNothingException(404, "Something went wrong");
    }
}
