package com.faceit.cv_microservices.cv_service.model.elastic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CvElasticModel {

    private String id;
    private String titleCv;
    private SalaryElasticModel salaryElasticModel;
    private UserElasticModel userElasticModel;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWorkElasticModel> previousWorkElasticModels;
    private String hrefCv;
}
