package com.dimitar.searchmetrics.codingtask.rest.client;

import com.dimitar.searchmetrics.codingtask.domain.Currency;
import com.dimitar.searchmetrics.codingtask.services.CurrencyService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Simple client for a blockchain.com rest API.
 * @ToDo: move all autowiring to constructor, for eventual mocking in unit test
 */
@Component
@Slf4j
public class BlockChainClient implements ICryptoClient {
    @Value("${appconfig.blockchain.api}")
    private String URL_BLOCKCHAIN;

    @Value("${appconfig.blockchain.api.USD}")
    private String USD;

    @Autowired
    CurrencyService currencyService;



    /**
     * Gets the new exchange rates and saves to local database.
     * @return currency object if operation has succeeded, null otherwise
     */
    @Scheduled(fixedRateString = "${appconfig.check.period.millis}")
    public Currency updateExchangeRates() {
        final RestTemplate restTemplate = new RestTemplate();
        final String strRates = restTemplate.getForObject(URL_BLOCKCHAIN, String.class);

        Currency result = null;

        log.info("Got data from" + URL_BLOCKCHAIN + " : " + strRates);

        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final JsonNode jsonNode = objectMapper.readTree(strRates);
            final JsonNode USDNode = jsonNode.get(USD);
            result = objectMapper.treeToValue(USDNode, Currency.class);
            result.setName(USD);

            currencyService.saveOrUpdate(result);
        } catch (IOException e) {
            log.error("Error parsing API response from: " + URL_BLOCKCHAIN +  ", response: " + strRates);
        }

        return result;
    }
}
