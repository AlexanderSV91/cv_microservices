package com.faceit.cv_microservices.cv_storage_service.model.elastic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryElasticModel {

    @Field(type = FieldType.Text)
    private String currencyType;
    @Field(type = FieldType.Integer)
    private int value;
}
