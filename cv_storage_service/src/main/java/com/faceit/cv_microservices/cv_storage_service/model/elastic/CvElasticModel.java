package com.faceit.cv_microservices.cv_storage_service.model.elastic;

import com.faceit.cv_microservices.cv_storage_service.model.mongo.PreviousWorkMongoModel;
import com.faceit.cv_microservices.cv_storage_service.model.mongo.SalaryMongoModel;
import com.faceit.cv_microservices.cv_storage_service.model.mongo.UserMongoModel;
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
public class CvElasticModel {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String titleCv;

    @Field(type = FieldType.Object)
    private SalaryElasticModel salaryElasticModel;

    @Field(type = FieldType.Object)
    private UserElasticModel userElasticModel;

    @Field(type = FieldType.Text)
    private List<String> typeOfEmployments;

    @Field(type = FieldType.Text)
    private String education;

    @Field(type = FieldType.Object)
    private List<PreviousWorkElasticModel> previousWorkElasticModels;

    @Field(type = FieldType.Keyword)
    private String hrefCv;
}
