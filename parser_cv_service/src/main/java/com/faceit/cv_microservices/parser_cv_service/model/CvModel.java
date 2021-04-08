package com.faceit.cv_microservices.parser_cv_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CvModel {

    private long id;
    private String titleCv;
    private Salary salary;
    private User user;
    private String[] typeOfEmployment;
    private String education;
    private List<PreviousWork> previousWorks;
    private String hrefCv;
    private String dateCreateCv;
    private LocalDateTime dateTimeParsingCv;
}
