package com.faceit.cv_microservices.cv_storage_service.dto.request;

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
public class CvRequest {

    private String id;
    private String titleCv;
    @JsonProperty("salary")
    private SalaryRequest salaryRequest;
    @JsonProperty("user")
    private UserRequest userRequest;
    private List<String> typeOfEmployments;
    private String education;
    @JsonProperty("previousWorks")
    private List<PreviousWorkRequest> previousWorkRequests;
    private String hrefCv;
}
