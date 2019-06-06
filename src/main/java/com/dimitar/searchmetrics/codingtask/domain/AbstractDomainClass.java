package com.dimitar.searchmetrics.codingtask.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class AbstractDomainClass {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @JsonIgnore
    @Version
    private Integer version;

    @JsonProperty("date")
    private Date dateCreated;

//    @JsonIgnore
//    private Date lastUpdated;

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
      //  lastUpdated = new Date();
        if (dateCreated==null) {
            dateCreated = new Date();
        }
    }
}
