package com.faceit.cv_microservices.cv_storage_service.model.elastic;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserElastic {

    @Field(type = FieldType.Text)
    private String firstName;
    @Field(type = FieldType.Text)
    private String lastName;
    @Field(type = FieldType.Keyword)
    private String srcImage;
}
