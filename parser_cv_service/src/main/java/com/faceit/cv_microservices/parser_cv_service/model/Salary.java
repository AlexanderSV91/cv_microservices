package com.faceit.cv_microservices.parser_cv_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Salary {

    private String currencyType;
    private int value;
}
