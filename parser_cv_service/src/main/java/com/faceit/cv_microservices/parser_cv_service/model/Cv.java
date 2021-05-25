package com.faceit.cv_microservices.parser_cv_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cv {

    private String id;
    private String titleCv;
    private Salary salary;
    private User user;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWork> previousWorks;
    private String hrefCv;
}
