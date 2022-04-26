package com.iit.oodp;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.model.Thanks;
import com.iit.oops.repository.ThanksRepository;
import com.iit.oops.service.ThanksService;
import com.iit.oops.service.impl.ThanksServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ThanksTest {

    private ThanksService thanksService;
    private ThanksRepository thanksRepository;

    @Before
    public void init() {
        thanksRepository = new ThanksRepository();
        thanksService = new ThanksServiceImpl(thanksRepository);
    }


    @Test
    public void testCreateThanks() throws BuyNothingException {

        Thanks thanks = new Thanks("1", "1", "2", "Thanks for the tyre",
                LocalDate.now());

        Assert.assertEquals(thanksService.createThanks(thanks, "1").getUid(), "1");
    }


    @Test
    public void testGetThanksByTid() throws BuyNothingException {
        Thanks thanks = new Thanks("1", "1", "2", "Thanks for the tyre",
                LocalDate.now());
        thanksService.createThanks(thanks, "1");
        Assert.assertEquals(thanksService.getThanksByTid("1").getDescription(), "Thanks for the tyre");
    }


    @Test
    public void testUpdateThanks() throws BuyNothingException {
        Thanks thanks = new Thanks("1", "1", "2", "Thanks for the tyre",
                LocalDate.now());
        thanksService.createThanks(thanks, "1");
        thanks.setDescription("Thanks for the tool");

        Assert.assertEquals(thanksService.updateThanks(thanks, "1", "1").getDescription(), "Thanks for the tool");
    }


    @Test
    public void testGetAllThanks() throws BuyNothingException {
        Thanks thanks = new Thanks("1", "1", "2", "Thanks for the tyre",
                LocalDate.now());
        thanksService.createThanks(thanks, "1");

        Assert.assertEquals(thanksService.getAllThanks(null).size(), 1);
    }

    @Test
    public void testGetAllThanksWithKeyWord() throws BuyNothingException {
        Thanks thanks = new Thanks("1", "1", "2", "Thanks for the tyre",
                LocalDate.now());
        thanksService.createThanks(thanks, "1");

        Assert.assertTrue(thanksService.getAllThanks("tyre").size() > 0);
    }


    @Test
    public void testGetAllThanksCreatedByUid() throws BuyNothingException {
        Thanks thanks = new Thanks("1", "1", "2", "Thanks for the tyre",
                LocalDate.now());
        thanksService.createThanks(thanks, "1");

        Assert.assertTrue(thanksService.getAllThanksCreatedByUid("1").size() > 0);
    }

    @Test
    public void testGetAllThanksReceivedByUid() throws BuyNothingException {
        Thanks thanks = new Thanks("1", "1", "2", "Thanks for the tyre",
                LocalDate.now());
        thanksService.createThanks(thanks, "1");

        Assert.assertTrue(thanksService.getAllThanksReceivedByUid("2").size() > 0);
    }
}
