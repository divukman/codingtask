package com.dimitar.searchmetrics.codingtask.controllers;

import com.dimitar.searchmetrics.codingtask.domain.Currency;
import com.dimitar.searchmetrics.codingtask.exceptions.NoDataException;
import com.dimitar.searchmetrics.codingtask.services.CurrencyService;
import com.dimitar.searchmetrics.codingtask.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/currency")
@CrossOrigin
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping("")
    public ResponseEntity<?> getLatestCurrency() {
        final Optional<Currency> optionalCurrency = currencyService.getLatestCurrency();
        return new ResponseEntity<Currency>(optionalCurrency.orElseThrow(() -> new NoDataException("No data.")), HttpStatus.OK);
    }

    @GetMapping("/getAllInRange")
    public ResponseEntity<?> getAllCurrenciesInRange(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from,
                                                     @RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to) {
        final Date fromStart = DateUtil.setTime(from,0,0,0,0);
        final Date toEnd = DateUtil.setTime(to,23,59,59,999);
        Iterable<Currency> result =  currencyService.findAllCurrenciesInRange(fromStart, toEnd);

        int counter = 0;
        for (Object i : result) {
            counter++;
            break;
        }

        if (counter == 0) {
            throw new NoDataException("No data.");
        }

        return new ResponseEntity<Iterable<Currency>>(result, HttpStatus.OK);
    }

}
