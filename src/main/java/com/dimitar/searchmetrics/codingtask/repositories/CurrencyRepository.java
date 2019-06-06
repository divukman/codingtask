package com.dimitar.searchmetrics.codingtask.repositories;

import com.dimitar.searchmetrics.codingtask.domain.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Currency findTopByOrderByIdDesc();
    //Iterable<Currency> findAllByDateCreatedBetweenAndOrderByDateCreated(Date start, Date end);


    @Query(value = "from Currency c where dateCreated BETWEEN :startDate AND :endDate")
    public Iterable<Currency> getAllBetweenDates(@Param("startDate")Date startDate, @Param("endDate")Date endDate);
}
