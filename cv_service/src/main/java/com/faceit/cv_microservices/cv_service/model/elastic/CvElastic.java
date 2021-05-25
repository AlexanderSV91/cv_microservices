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
public class CvElastic {

    private String id;
    private String titleCv;
    private SalaryElastic salary;
    private UserElastic user;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWorkElastic> previousWorks;
    private String hrefCv;
}
