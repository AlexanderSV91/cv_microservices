package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryMongo {

    private String currencyType;
    private int value;
}
