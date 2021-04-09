package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryMongoModel {

    private String currencyType;
    private int value;
}
