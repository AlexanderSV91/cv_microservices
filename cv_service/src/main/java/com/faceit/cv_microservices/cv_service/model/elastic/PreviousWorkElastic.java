package com.faceit.cv_microservices.cv_service.model.elastic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreviousWorkElastic {

    private String positionName;
    private String companyName;
    private String year;
}
