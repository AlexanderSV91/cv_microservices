package com.faceit.cv_microservices.cv_storage_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRequest {

    private String currencyType;
    private int value;
}
