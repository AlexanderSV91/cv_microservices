package com.faceit.cv_microservices.cv_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CvResponse {

    private String id;
    private String titleCv;
    private SalaryResponse salary;
    private UserResponse user;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWorkResponse> previousWorks;
    private String hrefCv;
}
