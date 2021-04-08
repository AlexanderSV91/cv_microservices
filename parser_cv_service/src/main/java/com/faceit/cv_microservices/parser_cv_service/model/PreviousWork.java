package com.faceit.cv_microservices.parser_cv_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreviousWork {

    private String positionName;
    private String companyName;
    private String year;
}
