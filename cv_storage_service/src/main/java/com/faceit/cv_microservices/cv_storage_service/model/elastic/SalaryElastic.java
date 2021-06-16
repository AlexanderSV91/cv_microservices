package com.faceit.cv_microservices.cv_storage_service.model.elastic;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryElastic implements Serializable {

    @Field(type = FieldType.Text)
    private String currencyType;
    @Field(type = FieldType.Integer)
    private int value;
}
