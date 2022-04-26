package com.iit.oops;

import com.iit.oops.configuration.BasicConfiguration;
import com.iit.oops.repository.*;
import com.iit.oops.resource.*;
import com.iit.oops.service.*;
import com.iit.oops.service.impl.*;
import com.iit.oops.util.ResponseUtil;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BuyNothingApplication extends Application<BasicConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BuyNothingApplication().run("server", "introduction-config.yml");
    }

    @Override
    public void run(final BasicConfiguration basicConfiguration, final Environment environment) {
        final ResponseUtil responseUtil = new ResponseUtil();

        final AccountRepository accountRepository = new AccountRepository();
        final AccountService accountService = new AccountServiceImpl(accountRepository);
        final AccountResource accountResource = new AccountResource(accountService, responseUtil);

        final AskRepository askRepository = new AskRepository();
        final AskService askService = new AskServiceImpl(askRepository);

        final AskResource askResource = new AskResource(askService, responseUtil);

        final GiveRepository giveRepository = new GiveRepository();
        final GiveService giveService = new GiveServiceImpl(giveRepository);
        final GiveResource giveResource = new GiveResource(giveService, responseUtil);

        final ThanksRepository thanksRepository = new ThanksRepository();
        final ThanksService thanksService = new ThanksServiceImpl(thanksRepository);
        final ThanksResource thanksResource = new ThanksResource(thanksService, responseUtil);

        final NoteRepository noteRepository = new NoteRepository();
        final NoteService noteService = new NoteServiceImpl(noteRepository);
        final NoteResource noteResource = new NoteResource(noteService, responseUtil);

        environment.jersey().register(accountResource);
        environment.jersey().register(askResource);
        environment.jersey().register(giveResource);
        environment.jersey().register(thanksResource);
        environment.jersey().register(noteResource);
    }

    @Override
    public void initialize(final Bootstrap<BasicConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }
}
