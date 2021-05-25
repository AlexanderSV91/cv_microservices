package com.faceit.cv_microservices.cv_service.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CvMongo {

    private String id;
    private String titleCv;
    private SalaryMongo salary;
    private UserMongo user;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWorkMongo> previousWorks;
    private String hrefCv;
}
