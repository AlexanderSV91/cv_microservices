package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreviousWorkMongo implements Serializable {

    private String positionName;
    private String companyName;
    private String year;
}
