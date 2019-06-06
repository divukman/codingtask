package com.dimitar.searchmetrics.codingtask.services;

import com.dimitar.searchmetrics.codingtask.domain.Currency;

import java.util.Date;
import java.util.Optional;

public interface CurrencyService extends CRUDService <Currency> {
    Optional<Currency> getLatestCurrency();
    Iterable<Currency> findAllCurrenciesInRange(Date start, Date end);
}
