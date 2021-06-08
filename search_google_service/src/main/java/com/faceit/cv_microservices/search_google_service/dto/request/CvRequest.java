package com.faceit.cv_microservices.search_google_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CvRequest {

    private String id;
    private String titleCv;
    private SalaryRequest salary;
    private UserRequest user;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWorkRequest> previousWorks;
    private String hrefCv;
}
