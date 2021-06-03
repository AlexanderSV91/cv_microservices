package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreviousWorkMongo {

    private String positionName;
    private String companyName;
    private String year;
}
