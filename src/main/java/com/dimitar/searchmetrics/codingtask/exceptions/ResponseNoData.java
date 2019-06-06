package com.dimitar.searchmetrics.codingtask.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class ResponseNoData {

    @NotBlank
    private String info;
}
