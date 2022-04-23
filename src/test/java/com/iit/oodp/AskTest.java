package com.iit.oodp;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Ask;
import com.iit.oops.repository.AskRepository;
import com.iit.oops.service.AskService;
import com.iit.oops.service.impl.AskServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AskTest {
    private AskService askService;
    private AskRepository askRepository;

    @Before
    public void init() {
        askRepository = new AskRepository();
        askService = new AskServiceImpl(askRepository);
    }


    @Test
    public void testCreateAsk() throws BuyNothingException {

        Ask ask = new Ask("1", "1", "ask", "car tyre", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        Assert.assertEquals(askService.createAsk(ask, "1").getAid(), "1");
    }

    @Test
    public void testDeactivateAsk() throws BuyNothingException {

        Ask ask = new Ask("1", "1", "ask", "car tyre", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());
        askService.createAsk(ask, "1");
        Assert.assertEquals(askService.deactivateAsk("1", "1").isIs_active(), false);
    }


    @Test
    public void testUpdateAsk() throws BuyNothingException {

        Ask ask = new Ask("1", "1", "ask", "car tyre", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());
        askService.createAsk(ask, "1");
        ask.setDescription("car floor mats");
        Assert.assertEquals(askService.updateAsk(ask, "1", "1").getDescription(), "car floor mats");
    }


    @Test
    public void testDeleteAsk() throws BuyNothingException {

        Ask ask = new Ask("1", "1", "ask", "car tyre", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());
        askService.createAsk(ask, "1");
        askService.deleteAsk("1", "1");
        Assert.assertEquals(askService.getAskById("1").getDescription(), null);
    }

    @Test
    public void testGetAskById() throws BuyNothingException {

        Ask ask = new Ask("1", "1", "ask", "car tyre", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());
        askService.createAsk(ask, "1");
        Assert.assertEquals(askService.getAskById("1").getDescription(), "car tyre");
    }


    @Test
    public void testGetAskByUId() throws BuyNothingException {

        Ask ask = new Ask("1", "1", "ask", "car tyre", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());
        askService.createAsk(ask, "1");
        Assert.assertEquals(askService.getAsksByUid("1", true).get(0).getDescription(), "car tyre");
    }


    @Test
    public void testGetAllAsks() throws BuyNothingException {

        Ask ask = new Ask("1", "1", "ask", "car tyre", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());
        askService.createAsk(ask, "1");
        Assert.assertEquals(askService.getAllAsks().size(), 1);
    }


    @Test
    public void testSearchAsks() throws BuyNothingException {

        Ask ask = new Ask("1", "1", "ask", "car tyre", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());
        askService.createAsk(ask, "1");
        Assert.assertEquals(askService.searchAsks("car", null, null).size(), 1);
    }

}
