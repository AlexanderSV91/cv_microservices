package com.faceit.cv_microservices.cv_storage_service.model.elastic;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreviousWorkElastic {

    @Field(type = FieldType.Text)
    private String positionName;
    @Field(type = FieldType.Text)
    private String companyName;
    @Field(type = FieldType.Text)
    private String year;
}
