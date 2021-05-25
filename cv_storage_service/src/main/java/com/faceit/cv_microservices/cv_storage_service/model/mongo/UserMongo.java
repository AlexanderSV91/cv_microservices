package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMongo {

    private String firstName;
    private String lastName;
    private String srcImage;
}
