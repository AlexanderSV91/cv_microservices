package com.faceit.cv_microservices.cv_storage_service.model.elastic;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "cv")
public class CvElastic implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String titleCv;

    @Field(type = FieldType.Object)
    private SalaryElastic salary;

    @Field(type = FieldType.Object)
    private UserElastic user;

    @Field(type = FieldType.Text)
    private List<String> typeOfEmployments;

    @Field(type = FieldType.Text)
    private String education;

    @Field(type = FieldType.Object)
    private List<PreviousWorkElastic> previousWorks;

    @Field(type = FieldType.Keyword)
    private String hrefCv;
}
