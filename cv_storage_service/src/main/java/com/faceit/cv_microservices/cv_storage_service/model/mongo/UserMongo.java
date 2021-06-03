package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMongo {

    private String firstName;
    private String lastName;
    private String srcImage;
}
