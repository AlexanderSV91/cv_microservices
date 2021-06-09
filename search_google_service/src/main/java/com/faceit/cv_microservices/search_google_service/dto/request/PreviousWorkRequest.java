package com.faceit.cv_microservices.search_google_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreviousWorkRequest {

    private String positionName;
    private String companyName;
    private String year;
}
