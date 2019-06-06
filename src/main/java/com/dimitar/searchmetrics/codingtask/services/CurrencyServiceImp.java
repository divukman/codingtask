package com.dimitar.searchmetrics.codingtask.services;

import com.dimitar.searchmetrics.codingtask.domain.Currency;
import com.dimitar.searchmetrics.codingtask.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("currencyService")
public class CurrencyServiceImp implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImp(final CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> listAll(){
        final List<Currency> lstCurrencies = new ArrayList<>();
        currencyRepository.findAll().forEach(lstCurrencies::add);
        return lstCurrencies;
    }

    public Iterable<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Optional getById(final Long id) {
        return currencyRepository.findById(id);
    }

    @Override
    public Optional<Currency> saveOrUpdate(Currency domainObject) {
        return Optional.of(currencyRepository.save(domainObject));
    }

    @Override
    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public Optional<Currency> getLatestCurrency() {
        final Currency currency = currencyRepository.findTopByOrderByIdDesc();
        return Optional.ofNullable(currency);
    }

    @Override
    public Iterable<Currency> findAllCurrenciesInRange(Date start, Date end) {
        return currencyRepository.getAllBetweenDates(start, end);
    }
}
