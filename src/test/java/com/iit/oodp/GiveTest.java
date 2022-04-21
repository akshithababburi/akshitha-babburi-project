package com.iit.oodp;

import com.iit.oops.exception.BuyNothingException;
import com.iit.oops.exception.UnAuthorizedException;
import com.iit.oops.model.Give;
import com.iit.oops.repository.GiveRepository;
import com.iit.oops.service.GiveService;
import com.iit.oops.service.impl.GiveServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GiveTest {

    private GiveService giveService;
    private GiveRepository giveRepository;

    @Before
    public void init() {
        giveRepository = new GiveRepository();
        giveService = new GiveServiceImpl(giveRepository);
    }


    @Test
    public void testCreateGive() throws BuyNothingException {

        Give give = new Give("1", "give", "Drill Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        Assert.assertEquals(giveService.createGive(give, "1").getGid(), "1");
    }

    @Test
    public void testGetGiveById() throws BuyNothingException {

        Give give = new Give("1", "give", "Drill Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        giveService.createGive(give, "1");

        Assert.assertEquals(giveService.getGiveById("1").getDescription(), "Drill Tool");
    }

    @Test
    public void testDeleteGive() throws BuyNothingException, UnAuthorizedException {

        Give give = new Give("1", "give", "Drill Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        giveService.createGive(give, "1");
        giveService.deleteGive("1", "1");

        Assert.assertEquals(giveService.getGiveById("1").getDescription(), null);
    }


    @Test
    public void testDeactivateGive() throws BuyNothingException, UnAuthorizedException {

        Give give = new Give("1", "give", "Drill Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        giveService.createGive(give, "1");

        Assert.assertEquals(giveService.deactivateGive("1", "1").isIs_active(), false);
    }

    @Test
    public void testUpdateGive() throws BuyNothingException, UnAuthorizedException {

        Give give = new Give("1", "give", "Drill Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        giveService.createGive(give, "1");

        give.setDescription("Saw Tool");

        Assert.assertEquals(giveService.updateGive(give, "1", "1").getDescription(), "Saw Tool");
    }

    @Test
    public void testViewMyGives() throws BuyNothingException, UnAuthorizedException {

        Give give = new Give("1", "give", "Drill Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        giveService.createGive(give, "1");

        Assert.assertEquals(giveService.viewMyGives("1").get(0).getDescription(), "Drill Tool");
    }

    @Test
    public void testViewAllGives() throws BuyNothingException, UnAuthorizedException {

        Give give = new Give("1", "give", "Drill Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        Give give2 = new Give("2", "give", "Saw Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        giveService.createGive(give, "1");
        giveService.createGive(give2, "1");

        Assert.assertEquals(giveService.getAllGives(null, null, null).size(), 2);
    }

    @Test
    public void testViewAllGivesWithKeywords() throws BuyNothingException, UnAuthorizedException {

        Give give = new Give("1", "give", "Drill Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        Give give2 = new Give("2", "give", "Saw Tool", LocalDate.now(), LocalDate.now(),
                new String[]{"20301", "34232"}, true, LocalDateTime.now());

        giveService.createGive(give, "1");
        giveService.createGive(give2, "1");

        Assert.assertTrue(giveService.getAllGives("Saw", ""+LocalDate.now(), ""+LocalDate.now()).size() > 0);
    }
}
