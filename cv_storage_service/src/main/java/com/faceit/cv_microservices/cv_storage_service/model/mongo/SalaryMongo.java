package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryMongo implements Serializable {

    private String currencyType;
    private int value;
}
