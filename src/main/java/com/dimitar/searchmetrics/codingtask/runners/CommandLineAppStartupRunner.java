package com.dimitar.searchmetrics.codingtask.runners;

import com.dimitar.searchmetrics.codingtask.domain.Currency;
import com.dimitar.searchmetrics.codingtask.rest.client.BlockChainClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    BlockChainClient blockChainClient;


    @Override
    public void run(String...args) throws Exception {
        LOG.info("Application started... getting the rates...");
        final Currency currency = blockChainClient.updateExchangeRates();
        LOG.info(currency.toString());
    }
}