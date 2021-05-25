package com.faceit.cv_microservices.cv_storage_service.model.elastic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "cv")
public class CvElastic {

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
