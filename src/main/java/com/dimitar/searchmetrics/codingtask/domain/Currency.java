package com.dimitar.searchmetrics.codingtask.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Currency extends AbstractDomainClass {

    @JsonIgnore
    private String name;

    @JsonProperty("15m")
    private Double fifteenMinutes;

    private Double last;
    private Double buy;
    private Double sell;
    private String symbol;

}
