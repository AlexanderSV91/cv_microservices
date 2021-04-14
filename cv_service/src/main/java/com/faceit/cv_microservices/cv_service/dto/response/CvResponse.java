package com.faceit.cv_microservices.cv_service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private SalaryResponse salaryResponse;
    private UserResponse userResponse;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWorkResponse> previousWorkResponses;
    private String hrefCv;
}
